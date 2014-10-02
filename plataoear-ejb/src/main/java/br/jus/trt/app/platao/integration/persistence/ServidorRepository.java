package br.jus.trt.app.platao.integration.persistence;


import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.lib.common_core.integration.persistence.CrudRepositoryBase;
import org.apache.deltaspike.data.api.Repository;

/**
 * Classe de negócio da entidade {@link Servidor}
 * @author augusto
 */
@Repository(forEntity = Servidor.class)
public abstract class ServidorRepository extends CrudRepositoryBase<Servidor, Long> { 
}
