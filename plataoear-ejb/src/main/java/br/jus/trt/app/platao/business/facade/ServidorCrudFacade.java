package br.jus.trt.app.platao.business.facade;

import javax.ejb.Stateless;

import br.jus.trt.app.platao.business.ExceptionCodes;
import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.integration.persistence.ServidorRepository;
import br.jus.trt.lib.common_core.business.facade.CrudFacadeBase;
import br.jus.trt.lib.common_core.exception.BusinessException;
import br.jus.trt.lib.qbe.QBEFilter;
import br.jus.trt.lib.qbe.api.Filter;
import br.jus.trt.lib.qbe.api.operator.Operators;

@SuppressWarnings("serial")
@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServidorCrudFacade extends CrudFacadeBase<ServidorRepository, Servidor, Long> {
	 
	@Override
	public Servidor saveAndFlush(Servidor entity) {
		validarCpfDuplicado(entity);
		return super.saveAndFlush(entity);
	}

	private void validarCpfDuplicado(Servidor entity) {
		if (entity != null && entity.getCpf() != null) {
			Filter<Servidor> filter = new QBEFilter<Servidor>(Servidor.class);
			filter.filterBy("cpf", Operators.equal(), entity.getCpf());
			filter.filterBy("id", Operators.notEqual(), entity.getId());

			if (count(filter) > 0) {
				throw new BusinessException(ExceptionCodes.SERVIDOR.RN1_CPF_DUPLICADO, "Já existe um Servidor cadastrado com este CPF: {0}.", entity.getCpf());
			}
		}	
	}
	
}
