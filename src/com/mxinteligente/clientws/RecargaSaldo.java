package com.mxinteligente.clientws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.SOAPException;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;


public class RecargaSaldo {

	
	public static void main2(String args[]) throws MalformedURLException, SOAPException{
		URL url = new URL("https://ws.microrecargas.com:8449/soap/servlet/rpcrouter");
		
		String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
		Call call = new Call ();
		call.setTargetObjectURI ("urn:debisys-soap-services");
		call.setMethodName ("CommTest");
		call.setEncodingStyleURI(encodingStyleURI);
		
		Response resp = call.invoke(url, "");
		
Parameter result = resp.getReturnValue();
System.out.println (result.getValue ());

	}
	
	public static void main(String args[]) {
		 String ID="123138588250";
		 String MERCHANT_ID="424967497859";
		 String SITE_ID = "5785544";
		 String CLERK_CODE="112233";
		 String TELCEL = "https://ws.microrecargas.com:8449/soap/servlet/rpcrouter";
		 String OTROS="https://ws.terecargamos.com:8448/soap/servlet/rpcrouter";
		 String VERSION ="01";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams()
		.setParameter("http.useragent", "Web Service Test Client");
		 
		BufferedReader br = null;
		String PinDistSale = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
					 " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
					 " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> <SOAP-ENV:Body> " +
					 " <m:PinDistSale xmlns:m=\"urn:debisys-soap-services\" " +
					 " SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> " +
					 " <version xsi:type=\"xsd:string\">01</version>" +
					 " <terminalId xsi:type=\"xsd:string\">5785544</terminalId> " +
					 " <clerkId xsi:type=\"xsd:string\">112233</clerkId> " +
					 " <productId xsi:type=\"xsd:string\">5118030</productId> " +
					 " <accountId xsi:type=\"xsd:string\">1111111111</accountId>" +
					 " <amount xsi:type=\"xsd:string\">30</amount> " +
					 " <invoiceNo xsi:type=\"xsd:string\">95</invoiceNo> " +
					 " <languageOption xsi:type=\"xsd:string\">2</languageOption> " +
					 " </m:PinDistSale> " +
					 " </SOAP-ENV:Body> " +
					 " </SOAP-ENV:Envelope>";
		
		
		String GetProductList = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
				" <soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
				"<soap:Body>" +
				"<GetProductList xmlns=\"urn:debisys-soap-services\">" +
				"<version xsi:type=\"xsd:string\">01</version>" +
				"<terminalId xsi:type=\"xsd:string\">" +SITE_ID + "</terminalId>" +
						"<transTypeId xsi:type=\"xsd:string\">" +
						"</transTypeId><carrierId xsi:type=\"xsd:string\"></carrierId>" +
								"<categoryId xsi:type=\"xsd:string\"></categoryId>" +
								"<productId xsi:type=\"xsd:string\"></productId>" +
								"</GetProductList></soap:Body></soap:Envelope>";
		
		
		String LookUpTransactionByInvocieNo = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> " +
				"<SOAP-ENV:Body>" +
				"<m:LookUpTransactionByInvocieNo xmlns:m=\"urn:debisys-soap-services\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> " +
				"<version xsi:type=\"xsd:string\">01</version> " +
				"<terminalId xsi:type=\"xsd:string\">"+SITE_ID+"</terminalId>" +
				"<clerkId xsi:type=\"xsd:string\">"+CLERK_CODE+"</clerkId> " +
				"<invoiceNo xsi:type=\"xsd:string\">1</invoiceNo> " +
				"</m:LookUpTransactionByInvocieNo>" +
				" </SOAP-ENV:Body></SOAP-ENV:Envelope>";
		
		System.out.println("request " + GetProductList);
		 try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PostMethod methodPost = new PostMethod(
		"https://ws.microrecargas.com:8449/soap/servlet/rpcrouter");
		 
		methodPost.setRequestBody(LookUpTransactionByInvocieNo);
		methodPost.setRequestHeader("Content-Type", "text/xml");
		 
		try {
		int returnCode = httpClient.executeMethod(methodPost);
		 
		if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
		System.out
		.println("The Post method is not implemented by this URI");
		methodPost.getResponseBodyAsString();
		} else {
		br = new BufferedReader(new InputStreamReader(methodPost
		.getResponseBodyAsStream()));
		String readLine;
		while (((readLine = br.readLine()) != null)) {
		System.out.println(readLine);
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		methodPost.releaseConnection();
		if (br != null)
		try {
		br.close();
		} catch (Exception fe) {
		fe.printStackTrace();
		}
		}
		 
		}
	
}
