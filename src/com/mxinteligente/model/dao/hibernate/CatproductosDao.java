package com.mxinteligente.model.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mxinteligente.infra.model.dao.hibernate.GenericoDao;
import com.mxinteligente.model.dao.CatproductosDaoI;
import com.mxinteligente.model.entidades.Catproductos;

@Repository("catproductosDao")
public class CatproductosDao extends GenericoDao<Catproductos, Serializable> implements CatproductosDaoI{

	@Transactional(readOnly=true)
	public List<Catproductos> obtenerCategorias() {
		return super.buscarAll();
		
	}

	
}
