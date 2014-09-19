package br.jus.trt.app.platao.business.bobjects;

import javax.inject.Inject;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.lib.common_core.business.bobject.CrudBObject;
import br.jus.trt.lib.common_core.exception.BusinessException;
import br.jus.trt.lib.common_core.integration.persistence.Dao;
import br.jus.trt.lib.qbe.api.QBERepository;

/**
 * Classe de negócio da entidade {@link Servidor}
 * @author augusto
 */
@SuppressWarnings("serial")
public class ServidorBO extends CrudBObject<Servidor>{ 

	@Inject
	public ServidorBO(Dao<Servidor> dao, QBERepository qbeRepository) {
		super(dao, qbeRepository);
	}

	@Override
	public void validateSave(Servidor entity) {
		super.validateSave(entity);
		validateUnique(entity, new BusinessException(null, "Já existe um servidor cadastrado com o CPF informado"), "cpf");
	}
}
