package br.jus.trt.app.platao.business.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.app.platao.integration.persistence.CidadeRepository;
import br.jus.trt.app.platao.integration.persistence.UfBO;
import br.jus.trt.app.platao.rest.dto.CidadeSemUfDTO;
import br.jus.trt.lib.common_core.business.facade.Facade;
import br.jus.trt.lib.common_core.domain.UF;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CidadeUfFacade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UfBO ufBO;
	
	@Inject
	private CidadeRepository cidadeBO;

	/**
	 * @return Todas as {@link UF}s cadastradas na base de dados, ordenadas.
	 */
	public List<Uf> listUfs() {
		return ufBO.findAll(true, "sigla");
	}
	
	/**
	 * @param uf {@link UF} para filtro de cidades.
	 * @return todas as cidades da {@link UF} informada.
	 */
	public List<Cidade> searchCidades(Uf uf) {
		return cidadeBO.findByUf(uf);
	}

	/**
	 * @param uf {@link UF} para filtro de cidades.
	 * @return todas as cidades da {@link UF} informada.
	 */
	public List<CidadeSemUfDTO> searchCidadesSemUfDTO(Uf uf) {
		return cidadeBO.findByUfDTO(uf);
	}
}
