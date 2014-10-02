package br.jus.trt.app.platao.integration.persistence;


import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.lib.common_core.integration.persistence.CrudRepositoryBase;
import org.apache.deltaspike.data.api.Repository;

/**
 * Classe de negócio da entidade {@link Uf}
 * @author augusto
 */
@Repository(forEntity = Uf.class)
public abstract class UfBO extends CrudRepositoryBase<Uf, Long> {
}
