package br.jus.trt.app.platao.business.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

import br.jus.trt.lib.common_core.business.domain.EntityBase;

@SuppressWarnings("serial")

@XmlRootElement
@Entity
@Table
public class Servidor extends EntityBase<Long> { 

	@NotNull
	@Size(min = 5, max = 100)
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cidade cidade;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Size(min = 11, max = 11)
	private String cpf;
	
	@Size(max = 30)
	@Email
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}