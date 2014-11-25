package br.com.curriculo.mbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.curriculo.entity.Pearson;
import br.com.curriculo.mbean.lazymodel.PearsonLazyDataModel;
import br.com.curriculo.service.PearsonService;

@ManagedBean
@ViewScoped
public class PearsonBean extends AbstractBean<Pearson, PearsonService> {

	@EJB
	private PearsonService pearsonService;
	
	@Override
	protected void posConstruct() {
		super.lazyDataModel = new PearsonLazyDataModel(this.pearsonService);
	}
	
	
	@Override
	protected Pearson createNovaEntidade() {
		return new Pearson();
	}

	@Override
	protected void preCreateUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected PearsonService getService() {
		return this.pearsonService;
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
	protected String getBindingFormID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}



}
