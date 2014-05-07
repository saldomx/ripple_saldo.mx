package com.mxinteligente.model.negocio.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mxinteligente.infra.model.nego.impl.GenServicio;
import com.mxinteligente.model.negocio.RippleServI;

@Service("easyWalletServ")
public class RippleServ extends GenServicio implements RippleServI{

	private final String USER_AGENT = "Mozilla/5.0";
	
	@Override
	public String crearWallet(String secretKey, String paymentWalletID){
		
		try{
		String url = "https://ripple.org/api/v1/new_wallet";
		 
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
 
		// add header
		post.setHeader("User-Agent", USER_AGENT);
 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("payment_wallet_id", paymentWalletID));
// 
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
 
		HttpResponse response = client.execute(post);
//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Post parameters : " + post.getEntity());
//		System.out.println("Response Code : " +  response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		JSONArray jsonRoot = new JSONArray("["+result.toString()+"]");
		JSONObject quote = (JSONObject) jsonRoot.get(0);
		
		//System.out.println ("wallet_id = " + );
		return quote.getString("wallet_id");
		//return "["+result.toString()+"]";
		
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}

	@Override
	public String obtenerAddress(String walletID) {
		try{
			
			String url = "https://ripple.org/api/v1/w/<id>/address";
			 
			HttpClient client = HttpClientBuilder.create().build();
			url=url.replaceAll("<id>", walletID);
			
			HttpGet request = new HttpGet(url);
	 
			// add request header
			request.addHeader("User-Agent", USER_AGENT);
	 
			HttpResponse response = client.execute(request);
	 
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + 
//	                       response.getStatusLine().getStatusCode());
	 
			BufferedReader rd = new BufferedReader(
	                       new InputStreamReader(response.getEntity().getContent()));
	 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			JSONArray jsonRoot = new JSONArray("["+result.toString()+"]");
			JSONObject quote = (JSONObject) jsonRoot.get(0);
			
			System.out.println ("address = " + quote.getString("address"));
			
			return quote.getString("address");
			//return "["+result.toString()+"]";
	 
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String obtenerBalance(String walletID) {
		try{
			String url = "https://ripple.org/api/v1/w/<id>/balance";
			 
			HttpClient client = HttpClientBuilder.create().build();
			url=url.replaceAll("<id>", walletID);
			
			HttpGet request = new HttpGet(url);
	 
			// add request header
			request.addHeader("User-Agent", USER_AGENT);
	 
			HttpResponse response = client.execute(request);
	 
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + 
//	                       response.getStatusLine().getStatusCode());
	 
			BufferedReader rd = new BufferedReader(
	                       new InputStreamReader(response.getEntity().getContent()));
	 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
	 
			JSONArray jsonRoot = new JSONArray("["+result.toString()+"]");
			JSONObject quote = (JSONObject) jsonRoot.get(0);
			
			System.out.println ("balance = " + quote.getString("balance"));
			
			//return quote.getString("balance");
			
			return "["+result.toString()+"]";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String obtenerBalanceSinConfirmar(String walletID) {
		try{
			String url = "https://ripple.org/api/v1/w/<id>/balance_unconfirmed";
			 
			HttpClient client = HttpClientBuilder.create().build();
			url=url.replaceAll("<id>", walletID);
			
			HttpGet request = new HttpGet(url);
	 
			// add request header
			request.addHeader("User-Agent", USER_AGENT);
	 
			HttpResponse response = client.execute(request);
	 
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + 
//	                       response.getStatusLine().getStatusCode());
	 
			BufferedReader rd = new BufferedReader(
	                       new InputStreamReader(response.getEntity().getContent()));
	 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
	 
			JSONArray jsonRoot = new JSONArray("["+result.toString()+"]");
			JSONObject quote = (JSONObject) jsonRoot.get(0);
			
			System.out.println ("balance_unconfirmed = " + quote.getString("balance_unconfirmed"));
			
			return "["+result.toString()+"]";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String realizarTransferencia(String walletID, String addressTarget,
			String amount, String currency, String emailFrom,
			String emailMessage, String Language) {
		try{
			
			String url = "https://ripple.org/api/v1/w/<id>/payment";
			
			url=url.replaceAll("<id>", walletID);
			
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
	 
			// add header
			post.setHeader("User-Agent", USER_AGENT);
	 
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("address", addressTarget));
			urlParameters.add(new BasicNameValuePair("amount", amount));
			urlParameters.add(new BasicNameValuePair("currency", currency));
			urlParameters.add(new BasicNameValuePair("email_from", emailFrom));
			urlParameters.add(new BasicNameValuePair("email_msg", emailMessage));

//			urlParameters.add(new BasicNameValuePair("num", "12345"));
	// 
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
	 
			HttpResponse response = client.execute(post);
//			System.out.println("\nSending 'POST' request to URL : " + url);
//			System.out.println("Post parameters : " + post.getEntity());
//			System.out.println("Response Code : " +  response.getStatusLine().getStatusCode());
	 
			BufferedReader rd = new BufferedReader(
	                        new InputStreamReader(response.getEntity().getContent()));
	 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
	 
			System.out.println(result.toString());
			return result.toString();
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String obtenerTipoCambio() {
		try{
			String url = "http://api.bitcoincharts.com/v1/markets.json";
			 
			HttpClient client = HttpClientBuilder.create().build();
			
			HttpGet request = new HttpGet(url);
	 
			// add request header
			request.addHeader("User-Agent", USER_AGENT);
	 
			HttpResponse response = client.execute(request);
	 
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + 
	                       response.getStatusLine().getStatusCode());
	 
			BufferedReader rd = new BufferedReader(
	                       new InputStreamReader(response.getEntity().getContent()));
	 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			JSONArray jsonRoot = new JSONArray(result.toString());
			JSONObject quote = (JSONObject) jsonRoot.get(0);
			System.out.println (result.toString());
			String valorCambio = "";
			for(int i =0; i<jsonRoot.length(); i++){
				JSONObject row = jsonRoot.getJSONObject(i);
				String currency = row.getString("currency");
				if(currency.equals("MXN")){
					valorCambio = row.getString("high");
				}
			}
			System.out.println ("MXN = " + valorCambio);
			
			return valorCambio;
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

}
