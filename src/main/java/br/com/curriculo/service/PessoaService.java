package br.com.curriculo.service;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.curriculo.entity.Pessoa;

@Stateless
public class PessoaService extends AbstractService<Pessoa> {

	@Override
	protected Class<Pessoa> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TypedQuery<Pessoa> getQuerySearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TypedQuery<Long> getQueryCount() {
		// TODO Auto-generated method stub
		return null;
	}




}
