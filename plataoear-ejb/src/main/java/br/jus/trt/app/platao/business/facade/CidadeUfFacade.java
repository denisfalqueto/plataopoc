package br.jus.trt.app.platao.business.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.trt.app.platao.business.bobjects.CidadeBO;
import br.jus.trt.app.platao.business.bobjects.UfBO;
import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.Uf;

@Stateless
@SuppressWarnings("serial")
public class CidadeUfFacade implements Serializable  {

	@Inject
	private UfBO ufBO;
	
	@Inject
	private CidadeBO cidadeBO;

	/**
	 * @return Todas as {@link UF}s cadastradas na base de dados, ordenadas.
	 */
	public List<Uf> listUfs() {
		return ufBO.list(true, "sigla");
	}
	
	/**
	 * @param uf {@link UF} para filtro de cidades.
	 * @return todas as cidades da {@link UF} informada.
	 */
	public List<Cidade> searchCidades(Uf uf) {
		return cidadeBO.searchCidades(uf);
	}

}
