package br.jus.trt.app.platao.integration.persistence;


import org.apache.deltaspike.data.api.Repository;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.lib.common_core.integration.persistence.CrudRepositoryBase;

/**
 * Classe de negócio da entidade {@link Servidor}
 * @author augusto
 */
@Repository(forEntity = Servidor.class)
public abstract class ServidorRepository extends CrudRepositoryBase<Servidor, Long> { 
}
