package com.mxinteligente.model.negocio.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.params.CoreConnectionPNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxinteligente.infra.ReminderBeep;
import com.mxinteligente.infra.Timer;
import com.mxinteligente.infra.Timer20;
import com.mxinteligente.infra.model.nego.impl.GenServicio;
import com.mxinteligente.model.dao.ControlRecargasDaoI;
import com.mxinteligente.model.entidades.ControlRecargas;
import com.mxinteligente.model.entidades.Producto;
import com.mxinteligente.model.negocio.RecargaServI;

@Service("recargaServ")
public class RecargaServ extends GenServicio implements RecargaServI{

	private String ID_TELCEL="123138588250";
	private String ID_OTROS="453009785757";
	private String MERCHANT_ID_TELCEL="424967497859";
	private String MERCHANT_ID_OTROS="379529190584";
	private String SITE_ID_TELCEL = "5785544";
	private String SITE_ID_OTROS = "4073600";
	private String CLERK_CODE_TELCEL="112233";
	private String CLERK_CODE_OTROS="112233";
	private String TELCEL = "https://ws.microrecargas.com:8449/soap/servlet/rpcrouter";
	private String OTROS="https://ws.terecargamos.com:8448/soap/servlet/rpcrouter";
	private String VERSION ="01";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
	private Date RequestTime;


	@Autowired(required=true)
	ControlRecargasDaoI controlRecargasDao;
	
	
	public void guardarPeticionRecarga(Producto recarga, String celular) {
		
		ControlRecargas control = new ControlRecargas();
		control.setProductId(recarga.getProductID());
		control.setAccountId(celular);
		control.setAmount(recarga.getPrice().toPlainString());
		control.setResultCode("P");
		if(recarga.getCategoria().getCompany().equals("RECARGA_TELCEL")){
			control.setSiteID(SITE_ID_TELCEL);
		}else{
			control.setSiteID(SITE_ID_OTROS);
		}
		
		controlRecargasDao.insertar(control);
	}
	
	
	@Override
	public boolean recargaTelcel(ControlRecargas recarga) {
		String respuesta = "";
		System.out.println("Lanzado : " + sdf.format(new Date()));
		RequestTime = new Date();
		respuesta = llamarPinDistSale(recarga.getAccountId(), recarga.getProductId(), recarga.getAmount(),recarga.getInvoiceId(), TELCEL, SITE_ID_TELCEL, CLERK_CODE_TELCEL);
		
		if(!obtenerResponseCode(respuesta).equals("00")){
			try {
				//Thread.sleep(20000);
				RequestTime = new Date();
				System.out.println("Lanzado : " + sdf.format(new Date()));
				respuesta = intentarRecarga(recarga.getInvoiceId()+"", TELCEL, SITE_ID_TELCEL, CLERK_CODE_TELCEL);
				
				
				if(obtenerResponseCode(respuesta).equals("32")){
					Thread.sleep(10000);
					
					RequestTime = new Date();
					System.out.println("Lanzado : " + sdf.format(new Date()));
					respuesta = intentarRecarga(recarga.getInvoiceId()+"", TELCEL, SITE_ID_TELCEL, CLERK_CODE_TELCEL);					

					if(obtenerResponseCode(respuesta).equals("32")){
						Thread.sleep(10000);
						
						RequestTime = new Date();
						System.out.println("Lanzado : " + sdf.format(new Date()));			
						respuesta = intentarRecarga(recarga.getInvoiceId()+"", TELCEL, SITE_ID_TELCEL, CLERK_CODE_TELCEL);						


						if(!obtenerResponseCode(respuesta).equals("00") ){
							this.actualizarControl(respuesta, recarga, RequestTime);
							return false;

						}else{
							this.actualizarControl(respuesta, recarga, RequestTime);
							return true;
						}

						
					}else if(obtenerResponseCode(respuesta).equals("00")){
						this.actualizarControl(respuesta, recarga, RequestTime);
						return true;
					}else{
						this.actualizarControl(respuesta, recarga, RequestTime);
						return false;
					}
					
				}else if(obtenerResponseCode(respuesta).equals("00")){
					this.actualizarControl(respuesta, recarga, RequestTime);
					return true;
				}else{
					this.actualizarControl(respuesta, recarga, RequestTime);
					return false;
				}
				
				
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		}else{
			this.actualizarControl(respuesta, recarga, RequestTime);
			return true;
		}
	}

	@Override
	public boolean recargaOtros(ControlRecargas recarga) {
		String respuesta="";
		System.out.println("Lanzado PIND: " + sdf.format(new Date()));
		RequestTime = new Date();
		respuesta = llamarPinDistSale(recarga.getAccountId(), recarga.getProductId(), recarga.getAmount(),recarga.getInvoiceId(), OTROS, SITE_ID_OTROS, CLERK_CODE_OTROS);
		
		if(!obtenerResponseCode(respuesta).equals("00")){
			try {
				//Thread.sleep(20000);
				
				System.out.println("Lanzado 1 LOOK: " + sdf.format(new Date()));
				RequestTime = new Date();
				respuesta = intentarRecarga(recarga.getInvoiceId()+"", OTROS,SITE_ID_OTROS, CLERK_CODE_OTROS);
				
				
				if(obtenerResponseCode(respuesta).equals("32")){
					Thread.sleep(10000);
					
					System.out.println("Lanzado 2 LOOK : " + sdf.format(new Date()));
					RequestTime = new Date();
					respuesta = intentarRecarga(recarga.getInvoiceId()+"", OTROS,SITE_ID_OTROS, CLERK_CODE_OTROS);
					


					if(obtenerResponseCode(respuesta).equals("32")){
						Thread.sleep(10000);
						
						System.out.println("Lanzado 3 LOOK : " + sdf.format(new Date()));
						RequestTime = new Date();
						respuesta = intentarRecarga(recarga.getInvoiceId()+"", OTROS,SITE_ID_OTROS, CLERK_CODE_OTROS);
						

						if(!obtenerResponseCode(respuesta).equals("00") ){
							this.actualizarControl(respuesta, recarga, RequestTime);
							return false;

						}else{
							this.actualizarControl(respuesta, recarga, RequestTime);
							return true;
						}

						
					}else if(obtenerResponseCode(respuesta).equals("00")){
						this.actualizarControl(respuesta, recarga, RequestTime);
						return true;
					}else{
						this.actualizarControl(respuesta, recarga, RequestTime);
						return false;
					}
					
				}else if(obtenerResponseCode(respuesta).equals("00")){
					this.actualizarControl(respuesta, recarga, RequestTime);
					return true;
				}else{
					this.actualizarControl(respuesta, recarga, RequestTime);
					return false;

				}
				
				
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		}else{
			this.actualizarControl(respuesta, recarga, RequestTime);
			return true;
		}
		
		
		

	}
	
	
	
	
	public String llamarPinDistSale(String celular, String producto, String monto, Integer invoiceID, String company, String SITE_ID, String CLERK_CODE){
		
		
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter("http.useragent", "Web Service Test Client");
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20 * 1000);
		 
		BufferedReader br = null;
		String PinDistSale = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
					 " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
					 " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> <SOAP-ENV:Body> " +
					 " <m:PinDistSale xmlns:m=\"urn:debisys-soap-services\" " +
					 " SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> " +
					 " <version xsi:type=\"xsd:string\">"+VERSION+"</version>" +
					 " <terminalId xsi:type=\"xsd:string\">"+SITE_ID+"</terminalId> " +
					 " <clerkId xsi:type=\"xsd:string\">"+CLERK_CODE+"</clerkId> " +
					 " <productId xsi:type=\"xsd:string\">"+producto+"</productId> " +
					 " <accountId xsi:type=\"xsd:string\">"+celular+"</accountId>" +
					 " <amount xsi:type=\"xsd:string\">"+monto+"</amount> " +
					 " <invoiceNo xsi:type=\"xsd:string\">"+invoiceID+"</invoiceNo> " +
					 " <languageOption xsi:type=\"xsd:string\">2</languageOption> " +
					 " </m:PinDistSale> " +
					 " </SOAP-ENV:Body> " +
					 " </SOAP-ENV:Envelope>";
		
		
		PostMethod methodPost = new PostMethod(company);
		 
		methodPost.setRequestBody(PinDistSale);
		methodPost.setRequestHeader("Content-Type", "text/xml");
		 
		try {
			int returnCode = httpClient.executeMethod(methodPost);
		 
		if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
		//	System.out.println("The Post method is not implemented by this URI");
			methodPost.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(methodPost.getResponseBodyAsStream()));
				String readLine;
				while (((readLine = br.readLine()) != null)) {

					if(readLine.contains("ResponseCode")){
						String nuevo = readLine.replace("&lt;", "<");
						String nuevo2 = nuevo.replace("&gt;", ">");
						//int code = nuevo2.indexOf("</ResponseCode>");
						//String codestr = nuevo2.substring(code-2,code);						
						//System.out.println(nuevo2);
			//			System.out.println(codestr);						
						return nuevo2;
						
					}
					

				}
			}
		}catch(RuntimeException e){
			//e.printStackTrace();
			//System.out.println("cachado");
			return "EE";		
		}catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("cachado");

			return "EE";
		} finally {
			methodPost.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
					
					//fe.printStackTrace();
					return "EE";
				}
		}
		
		return "EE";
	}
	
	public boolean existeSaldo(){
		
		return false;
	}


	
	public String intentarRecarga(String invoiceNumber, String company, String SITE_ID, String CLERK_CODE) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter("http.useragent", "Web Service Test Client");
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
		 
		BufferedReader br = null;
		String LookUpTransactionByInvocieNo = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> " +
				"<SOAP-ENV:Body>" +
				"<m:LookUpTransactionByInvocieNo xmlns:m=\"urn:debisys-soap-services\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> " +
				"<version xsi:type=\"xsd:string\">01</version> " +
				"<terminalId xsi:type=\"xsd:string\">"+SITE_ID+"</terminalId>" +
				"<clerkId xsi:type=\"xsd:string\">"+CLERK_CODE+"</clerkId> " +
				"<invoiceNo xsi:type=\"xsd:string\">"+invoiceNumber+"</invoiceNo> " +
				"</m:LookUpTransactionByInvocieNo>" +
				" </SOAP-ENV:Body></SOAP-ENV:Envelope>";
		
		PostMethod methodPost = new PostMethod(company);
		 
		methodPost.setRequestBody(LookUpTransactionByInvocieNo);
		methodPost.setRequestHeader("Content-Type", "text/xml");
		 
		try {
			int returnCode = httpClient.executeMethod(methodPost);
		 
		if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
			methodPost.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(methodPost.getResponseBodyAsStream()));
				String readLine;
				while (((readLine = br.readLine()) != null)) {
					if(readLine.contains("ResponseCode")){
						String nuevo = readLine.replace("&lt;", "<");
						String nuevo2 = nuevo.replace("&gt;", ">");
//						int code = nuevo2.indexOf("</ResponseCode>");
//						String codestr = nuevo2.substring(code-2,code);						
//						
						//System.out.println(nuevo2);
					//	System.out.println(codestr);						
						
						return nuevo2;
						
					}
					
					//System.out.println("rd "+readLine);

				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			methodPost.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
					//fe.printStackTrace();
				}
		}
		
		return "EE";
	}

	
	public String obtenerResponseCode(String response){	
		if(!response.equals("EE")){
			int code = response.indexOf("</ResponseCode>");
			String codestr = response.substring(code-2,code);
			
			return codestr;		
		}
		else 
			return response;
	}
	
	public String obtenerTag(String response, String tagIni, String tagFin){	
		try{
		if(!response.equals("EE")){
			
			if(response.contains(tagFin)){
				int inicialIndex = response.lastIndexOf(tagIni);
				int finalIndex = response.indexOf(tagFin);
				String codestr = response.substring(inicialIndex,finalIndex);			
				return codestr.substring(tagIni.length(), codestr.length());	
			}else{
				int inicialIndex = response.lastIndexOf(tagIni);
				int finalIndex = response.length();
				String codestr = response.substring(inicialIndex,finalIndex);
			
				return codestr.substring(tagIni.length(), codestr.length());
				
			}
		}
		else 
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	public boolean actualizarControl(String response, ControlRecargas control, Date requestTimeLaunch){
		try{
//		control.setProductId(recarga.getCategoria().getProductId());
//		control.setAccountId(celular);
//		control.setAmount(recarga.getPrice().toPlainString());
			control.setResultCode(obtenerResponseCode(response));
			control.setCarrierControl(obtenerTag(response,"<CarrierControlNo>","</CarrierControlNo>"));
			control.setResponseMessage(obtenerTag(response,"<ResponseMessage>","</ResponseMessage>"));
			//System.out.println("ResponseMessage: " + obtenerTag(response,"<ResponseMessage>","</ResponseMessage>"));
			String time = obtenerTag(response,"<TransactionDateTime>","</TransactionDateTime>");
			
			if(!time.isEmpty() && time.contains(".") ){
				control.setTransactionDateTime(sdf.parse(time));
			}else if(!time.isEmpty()) {
				control.setTransactionDateTime(sdf2.parse(time));
			}
			
			control.setTransactionId(obtenerTag(response,"<TransactionId>","</TransactionId>"));
			control.setTransactionDateTimeLocal(requestTimeLaunch);
			if(controlRecargasDao.actualizar(control))
				return true;
			else
				return false;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		//recarga.set
		
	}
	

}
