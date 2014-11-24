package br.com.curriculo.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.curriculo.entity.Pessoa;
import br.com.curriculo.service.PessoaService;

@ManagedBean
@ViewScoped
public class PessoaBean extends AbstractBean<Pessoa, PessoaService> {

	@Override
	protected Pessoa createNovaEntidade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void preCreateUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected PessoaService getService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void posCreateUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validaForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void posConstruct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getBindingFormID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}



}
