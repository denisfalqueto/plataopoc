package br.jus.trt.app.platao.business.facade;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.trt.app.platao.integration.persistence.ServidorRepository;
import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.lib.common_core.business.bobject.CrudBObject;
import br.jus.trt.lib.common_core.business.facade.CrudFacadeBase;

@Stateless
@SuppressWarnings("serial")
public class ServidorCrudFacade extends CrudFacadeBase<Servidor> {
	
	@Inject
	private ServidorRepository servidorBO;
	
	@Override
	protected CrudBObject<Servidor> getBObject() {
		return servidorBO;
	}

}
