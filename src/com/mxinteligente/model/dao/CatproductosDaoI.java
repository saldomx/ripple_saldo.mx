package com.mxinteligente.model.dao;

import java.io.Serializable;
import java.util.List;

import com.mxinteligente.infra.model.dao.GenericoDaoI;
import com.mxinteligente.model.entidades.Catproductos;


public interface CatproductosDaoI extends GenericoDaoI<Catproductos,Serializable>{

	
	public List<Catproductos> obtenerCategorias();
	
}
