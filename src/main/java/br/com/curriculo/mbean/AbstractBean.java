package br.com.curriculo.mbean;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import br.com.curriculo.entity.AbstractEntity;
import br.com.curriculo.mbean.lazymodel.AbstractLazyModel;
import br.com.curriculo.service.AbstractService;
import br.com.curriculo.utils.BeanHelper;



public abstract class AbstractBean<M extends AbstractEntity, T extends AbstractService<M>> {

	protected AbstractLazyModel<M, T> lazyDataModel;

	protected AbstractService<M> service;

	protected M entidade;

	protected Boolean isCreate;

	protected BeanHelper beanHelper;

	protected abstract M createNovaEntidade();

	protected abstract void preCreateUpdate();

	protected abstract T getService();

	protected abstract void posCreateUpdate();

	protected abstract void validaForm();

	protected abstract void posConstruct();

	protected abstract String getBindingFormID();

	@PostConstruct
	protected void posConstructAbstract() {
		this.beanHelper = BeanHelper.newInstance();
		this.entidade = this.createNovaEntidade();
		posConstruct();

	}

	public void createUpdate() {
		try {
			validaForm();
			preCreateUpdate();
			if (isCreate) {
				this.getService().create(this.entidade);
				beanHelper.exibirMensagemCreatePadrao(getBindingFormID());
			} else {
				this.getService().update(this.entidade);
				beanHelper.exibirMensagemUpdatePadrao(getBindingFormID());
			}
			posCreateUpdate();
		} catch (Exception e) {
			beanHelper.exibirMensagemErroInesperado();
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			this.getService().delete(entidade);
			beanHelper.exibirMensagemDeletePadrao();
		} catch (Exception e) {
			beanHelper.exibirMensagemErroInesperado();
			e.printStackTrace();
		}
	}

	public void exportar() {

	}

	public abstract void cancel();

	public void clearFields() {
		this.entidade = this.createNovaEntidade();
		// valida se a tela tem filtos
		if (this.lazyDataModel != null) {
			this.lazyDataModel.setFilters(new HashMap<String, Object>());
		}
	}

	public AbstractLazyModel<M, T> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(AbstractLazyModel<M, T> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public M getEntidade() {
		return entidade;
	}

	public void setEntidade(M entidade) {
		this.entidade = entidade;
	}

	public Boolean getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}

}
