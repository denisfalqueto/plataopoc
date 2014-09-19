package br.jus.trt.app.platao.business.bobjects;

import javax.inject.Inject;

import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.lib.common_core.business.bobject.QuerierObjectBase;
import br.jus.trt.lib.common_core.integration.persistence.Dao;
import br.jus.trt.lib.qbe.api.QBERepository;

/**
 * Classe de negócio da entidade {@link Uf}
 * @author augusto
 */
@SuppressWarnings("serial")
public class UfBO extends QuerierObjectBase<Uf>{

	@Inject
	public UfBO(Dao<Uf> dao, QBERepository qbeRepository) {
		super(dao, qbeRepository);
	}

}
