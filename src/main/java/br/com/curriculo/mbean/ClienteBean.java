package br.com.curriculo.mbean;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import br.com.attracti.aw.entity.Cidade;
import br.com.attracti.aw.entity.Cliente;
import br.com.attracti.aw.entity.Endereco;
import br.com.attracti.aw.entity.Estado;
import br.com.attracti.aw.entity.Vendedor;
import br.com.attracti.aw.mbean.lazymodel.ClienteLazyDataModel;
import br.com.attracti.aw.service.CidadeService;
import br.com.attracti.aw.service.ClienteService;
import br.com.attracti.aw.service.EstadoService;
import br.com.attracti.aw.service.VendedorService;

@ManagedBean
@ViewScoped
public class ClienteBean extends AbstractBean<Cliente, ClienteService> {

	@EJB
	private ClienteService clienteService;

	@EJB
	private VendedorService vendedorService;

	@EJB
	private CidadeService cidadeService;

	@EJB
	private EstadoService estadoService;

	private String idCliente;
	private List<Cidade> cidades;
	private List<Estado> estados;
	private Estado estadoSelecionado;
	private Cidade cidadeSelecionada;
	private Vendedor vendedorSelecionado;
	private List<Vendedor> vendedores;

	@Override
	protected void posConstruct() {
		super.service = this.clienteService;
		super.lazyDataModel = new ClienteLazyDataModel(this.clienteService);
		this.estados = this.estadoService.findAll();
		this.vendedores = this.vendedorService.findAll();
	}

	public void init() {
		if (StringUtils.isBlank(this.idCliente)) {
			super.isCreate = true;
		} else {
			this.setTabelasApoio();
			this.vendedorSelecionado = this.entidade.getVendedor();
			super.isCreate = false;
		}
	}

	public void exportarClientes() {
		OutputStream outputStream = null;
		FacesContext fc = null;
		try {
			fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			outputStream = ec.getResponseOutputStream();
			this.clienteService.geraExcel(fc, ec, outputStream);
		} catch (Exception e) {
			this.beanHelper.exibirMensagemErroInesperado();
			e.printStackTrace();
		}
	}

	private void setTabelasApoio() {
		this.entidade = this.clienteService.retrieve(Long.parseLong(idCliente));
		if (this.entidade.getEndereco().getCidade() != null) {
			this.cidadeSelecionada = this.entidade.getEndereco().getCidade();
			this.estadoSelecionado = this.cidadeSelecionada.getEstado();
			handleEstado();
		}
	}

	@Override
	public void cancel() {
		super.clearFields();
	}

	public void handleEstado() {
		if (this.estadoSelecionado != null) {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("estado", this.estadoSelecionado);
			this.cidades = this.cidadeService.findByNamedQuery(
					Cidade.FIND_BY_ESTADO, Cidade.class, parametros);
		}

	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	@Override
	public void preCreateUpdate() {
		this.entidade.getEndereco().setCidade(this.cidadeSelecionada);
		this.entidade.setVendedor(this.vendedorSelecionado);

	}

	@Override
	public void posCreateUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validaForm() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Cliente createNovaEntidade() {
		Cliente cliente = new Cliente();
		cliente.setEndereco(new Endereco());
		return cliente;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}

	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}

	@Override
	protected ClienteService getService() {
		return this.clienteService;
	}

	@Override
	protected String getBindingFormID() {
		// TODO Auto-generated method stub
		return null;

	}

}
