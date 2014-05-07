package com.mxinteligente.model.negocio;

import com.mxinteligente.infra.model.nego.GenServicioI;

public interface RippleServI extends GenServicioI{

	
	
	public String crearWallet(String secretKey, String paymentWalletID);
	
	public String obtenerAddress(String walletID);
	
	public String obtenerBalance(String walletID);
	
	public String obtenerBalanceSinConfirmar(String walletID);
	
	public String realizarTransferencia(String walletID, String addressTarget, String amount, String currency, String emailFrom, String emailMessage, String Language);
	
	public String obtenerTipoCambio();
}
