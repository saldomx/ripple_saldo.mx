package com.mxinteligente.model.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mxinteligente.infra.model.dao.hibernate.GenericoDao;
import com.mxinteligente.model.dao.ControlRecargasDaoI;
import com.mxinteligente.model.entidades.ControlRecargas;
import com.mxinteligente.model.entidades.Usuarios;

@Repository("controlRecargasDao")
public class ControlRecargasDao extends GenericoDao<ControlRecargas, Serializable> implements ControlRecargasDaoI{

	@Transactional(readOnly = true)
	public List obtenerRecargasPendientes() {
		Criteria crit = getSession().createCriteria(ControlRecargas.class);
		crit.add(Restrictions.eq("resultCode", "P"));
		crit.addOrder(Order.asc("invoiceId"));		
		return crit.list();
	}

	
	
	
}
