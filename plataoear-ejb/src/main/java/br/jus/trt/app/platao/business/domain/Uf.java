package br.jus.trt.app.platao.business.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.jus.trt.lib.common_core.business.domain.EntityBase;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "sigla"))
public class Uf extends EntityBase<Long> { 

	@NotNull
	@Size(min = 2, max = 2, message = "Deve ter exatamente 2 caracteres.")
	private String sigla;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}