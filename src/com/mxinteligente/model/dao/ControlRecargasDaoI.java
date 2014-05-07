package com.mxinteligente.model.dao;

import java.io.Serializable;
import java.util.List;

import com.mxinteligente.infra.model.dao.GenericoDaoI;
import com.mxinteligente.model.entidades.ControlRecargas;

public interface ControlRecargasDaoI extends GenericoDaoI<ControlRecargas,Serializable>{

	
	public List obtenerRecargasPendientes();
	
	
}
