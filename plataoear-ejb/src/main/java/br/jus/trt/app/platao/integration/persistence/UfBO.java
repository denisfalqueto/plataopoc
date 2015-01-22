package br.jus.trt.app.platao.integration.persistence;


import org.apache.deltaspike.data.api.Repository;

import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.lib.common_core.integration.persistence.CrudRepositoryBase;

/**
 * Classe de negócio da entidade {@link Uf}
 * @author augusto
 */
@Repository(forEntity = Uf.class)
public abstract class UfBO extends CrudRepositoryBase<Uf, Long> {
}
