package br.com.curriculo.service;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.curriculo.entity.Pearson;

@Stateless
public class PearsonService extends AbstractService<Pearson> {

	@Override
	protected Class<Pearson> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TypedQuery<Pearson> getQuerySearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TypedQuery<Long> getQueryCount() {
		// TODO Auto-generated method stub
		return null;
	}




}
