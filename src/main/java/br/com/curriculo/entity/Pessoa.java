package br.com.curriculo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@Table(name = "CLIENTE", schema = "cv")
@NamedQueries({ @NamedQuery(name = Pessoa.NAMED_QUERY_FIND_ALL, query = "SELECT p FROM Pessoa p") })
public class Pessoa extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String NAMED_QUERY_FIND_ALL = "Cliente.findAll";

	@Column(name = "DS_NAME")
	private String dsName;

	public String getDsName() {
		return dsName;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsName == null) ? 0 : dsName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (dsName == null) {
			if (other.dsName != null)
				return false;
		} else if (!dsName.equals(other.dsName))
			return false;
		return true;
	}
	
	
	
}
