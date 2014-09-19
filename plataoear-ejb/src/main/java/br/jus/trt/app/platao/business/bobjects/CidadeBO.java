package br.jus.trt.app.platao.business.bobjects;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.app.platao.integration.persistence.CidadeDao;
import br.jus.trt.lib.common_core.business.bobject.QuerierObjectBase;
import br.jus.trt.lib.qbe.QBEFilter;
import br.jus.trt.lib.qbe.api.QBERepository;

/**
 * Classe de negócio da entidade {@link Cidade}
 * @author augusto
 */
@SuppressWarnings("serial")
public class CidadeBO extends QuerierObjectBase<Cidade>{ 

	@Inject
	public CidadeBO(CidadeDao dao, QBERepository qbeRepository) {
		super(dao, qbeRepository);
	}

	/**
	 * Consulta as cidades por UF.
	 * @param uf Uf para filtro das cidades consultadas.
	 * @return
	 */
	public List<Cidade> searchCidades(Uf uf) {
		List<Cidade> cidades;
		if (uf == null) {
			cidades = Collections.emptyList();
			
		} else {
			Cidade cidade = new Cidade();
			cidade.setUf(uf);
			cidades = search(new QBEFilter<Cidade>(cidade));
		}
		
		return cidades;
	}
	
	@Override
	protected CidadeDao getDao() {
		return (CidadeDao) super.getDao();
	}
}
