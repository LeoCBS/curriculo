package br.com.curriculo.mbean.lazymodel;

import java.util.List;

import br.com.curriculo.entity.Pearson;
import br.com.curriculo.service.PearsonService;

public class PearsonLazyDataModel extends AbstractLazyModel<Pearson, PearsonService> {

    private static final long serialVersionUID = 1L;

    private PearsonService managerBean;

    public PearsonLazyDataModel() {

    }

    public PearsonLazyDataModel(PearsonService managerBean) {
        super();
        this.managerBean = managerBean;
    }

    @Override
    public Long count() {
        return this.managerBean.countByFilters(super.getFilters());
    }

    @Override
    public List<Pearson> search(int first, int pageSize) {
        return this.managerBean.findByFilters(first, pageSize, super.getFilters());
    }

    public PearsonService getManagerBean() {
        return managerBean;
    }

    public void setManagerBean(PearsonService managerBean) {
        this.managerBean = managerBean;
    }

}
