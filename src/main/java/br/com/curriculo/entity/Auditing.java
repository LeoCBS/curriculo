package br.com.curriculo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Auditing extends AbstractEntity {

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

	@Column(name = "DT_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCreate;

	@Column(name = "DT_UPDATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUpdate;


	public Date getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Date getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}


//	@PrePersist
//	protected void onPrePersist() {
//		this.dtCreate = new Date();
//		this.dtUpdate = new Date();
//
//		this.usuarioCreate = (Usuario) AWUtils
//				.getAttributeFromSession(AWConstants.USUARIO_LOGADO);
//		this.usuarioUpdate = (Usuario) AWUtils
//				.getAttributeFromSession(AWConstants.USUARIO_LOGADO);
//
//		// TODO mudar para empresa do usuario logado
//		Empresa empresa = new Empresa();
//		empresa.setIdEmpresa(1L);
//		this.empresa = empresa;
//
//	}
//
//	@PreUpdate
//	protected void onPreUpdate() {
//		this.usuarioUpdate = (Usuario) AWUtils
//				.getAttributeFromSession(AWConstants.USUARIO_LOGADO);
//		this.dtUpdate = new Date();
//	}

}
