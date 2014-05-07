package com.mxinteligente.model.negocio;

import com.mxinteligente.infra.model.nego.GenServicioI;
import com.mxinteligente.model.entidades.ControlRecargas;
import com.mxinteligente.model.entidades.Producto;

public interface RecargaServI extends GenServicioI{

	public void guardarPeticionRecarga(Producto recarga, String celular);
	
	public boolean recargaTelcel(ControlRecargas recarga);
	
	public boolean recargaOtros(ControlRecargas recarga);
	
}
