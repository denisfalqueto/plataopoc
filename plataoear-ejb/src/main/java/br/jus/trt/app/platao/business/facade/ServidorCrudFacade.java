package br.jus.trt.app.platao.business.facade;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.integration.persistence.ServidorRepository;
import br.jus.trt.lib.common_core.business.facade.CrudFacadeBase;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServidorCrudFacade extends CrudFacadeBase<ServidorRepository, Servidor, Long> {
	 
}
