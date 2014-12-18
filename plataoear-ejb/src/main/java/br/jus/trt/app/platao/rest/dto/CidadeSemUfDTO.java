package br.jus.trt.app.platao.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CidadeSemUfDTO  {

	private Long id;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
