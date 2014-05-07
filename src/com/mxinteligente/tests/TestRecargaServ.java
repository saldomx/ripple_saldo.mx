package com.mxinteligente.tests;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.Test;

import com.mxinteligente.infra.model.nego.MailService;
import com.mxinteligente.model.entidades.Catproductos;
import com.mxinteligente.model.entidades.ControlRecargas;
import com.mxinteligente.model.entidades.Producto;
import com.mxinteligente.model.entidades.Usuarios;
import com.mxinteligente.model.negocio.RecargaServI;
import com.mxinteligente.model.negocio.impl.RecargaServ;

public class TestRecargaServ {
	
	
	
	
	
	public static void main(String args[]){
		
		RecargaServI recargaServ = new RecargaServ();
		
		ControlRecargas recarga = new ControlRecargas();
		recarga.setAccountId("1111111111");
		recarga.setAmount("30.0000");
		recarga.setInvoiceId(2);
		recarga.setProductId("5100030");
		
		String response="<PinDistSaleResponse><Version>01</Version><InvoiceNo>1</InvoiceNo><ResponseCode>00</ResponseCode><PIN>1111111111</PIN><ControlNo>71885044</ControlNo><CarrierControlNo></CarrierControlNo><CustomerServiceNo></CustomerServiceNo><TransactionDateTime>2013-08-06 11:22:26.623</TransactionDateTime><ResultCode>00</ResultCode><ResponseMessage>Vigencia 30 dias.";
		
		String res = new TestRecargaServ().obtenerTag(response,"<ResponseMessage>","</ResponseMessage>");	
		
		System.out.println("res " + res);
		
//		boolean resultado = recargaServ.recargaOtros(recarga);
//		
//		if(resultado)
//			System.out.println("exitoso");
//		else
//			System.out.println("faliido");
	}
	
	
	
	
	
	
	

	
	RecargaServI recargaServ;
	
	@Test(enabled=true)
	public void setConfiguration() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"/WebContent/WEB-INF/applicationContext.xml");
		
		

		//ingresosDao = (IngresosDaoI) ctx.getBean("ingresosDao");
		//catingresosDao = (CatingresosDaoI) ctx.getBean("catingresosDao");

		//egresosDao = (EgresosDaoI) ctx.getBean("egresosDao");
		
	//	passwordEncoder = (PasswordEncoder) ctx.getBean("passwordEncode");
		
		recargaServ = (RecargaServI)  ctx.getBean("recargaServ");

	}
	

	
	@Test(enabled = false, dependsOnMethods = { "setConfiguration" })
	public void obtenerCategorias() {
		ControlRecargas recarga = new ControlRecargas();
		recarga.setAccountId("1111111111");
		recarga.setAmount("30.0000");
		recarga.setInvoiceId(3);
		recarga.setProductId("5100030");
		
		boolean resultado = recargaServ.recargaOtros(recarga);
		
		if(resultado)
			System.out.println("exitoso");
		else
			System.out.println("faliido");
		
	}
	
	//TEST ----- TST
	@Test(enabled = true, dependsOnMethods = { "setConfiguration" })
	public void PruebasEmida() {
		ControlRecargas recarga = new ControlRecargas();
		recarga.setInvoiceId(15);
		recarga = (ControlRecargas) recargaServ.buscarObjeto(recarga).get(0);
		
		boolean resultado = recargaServ.recargaOtros(recarga);
		//boolean resultado = recargaServ.recargaTelcel(recarga);
		
		if(resultado)
			System.out.println("exitoso");
		else
			System.out.println("faliido");
		
	}
	
	
	public String obtenerTag(String response, String tagIni, String tagFin){	
		try{
		if(!response.equals("EE")){
			
			if(response.contains(tagFin)){
				int inicialIndex = response.lastIndexOf(tagIni);
				int finalIndex = response.indexOf(tagFin);
				String codestr = response.substring(inicialIndex,finalIndex);
			
				int temp = codestr.lastIndexOf(">");
			
			
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
			return "null";
		}
	}
	
}
