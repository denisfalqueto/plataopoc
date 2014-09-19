package br.jus.trt.app.platao.integration.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.NamedQueries;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.lib.common_core.integration.persistence.JpaDao;
import br.jus.trt.lib.common_core.util.Parameter;

/**
 * DAO customizado para a entidade {@link Cidade}.
 * @author augusto
 *
 */
@SuppressWarnings("serial")
public class CidadeDao extends JpaDao<Cidade>{

	@Inject
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	protected Class<Cidade> getEntityType() {
		return Cidade.class;
	}

	/**
	 * Consulta as cidades por UF.
	 * @param uf Uf para filtro das cidades consultadas.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cidade> searchCidades(Uf uf) {
		return search(NamedQueries.Cidade.CIDADE_POR_UF, new Parameter("uf", uf));
	}
}
