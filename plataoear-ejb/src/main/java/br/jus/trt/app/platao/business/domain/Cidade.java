package br.jus.trt.app.platao.business.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.jus.trt.lib.common_core.business.domain.EntityBase;

@SuppressWarnings("serial")

@NamedQuery(name=NamedQueries.Cidade.CIDADE_POR_UF,
			query=NamedQueries.Cidade.CIDADE_POR_UF_QUERY) 

@Entity
@Table
@XmlRootElement
public class Cidade extends EntityBase<Long> { 

	@NotNull
	@Size(min = 1, max = 25)
	private String nome;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Uf uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}