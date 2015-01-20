package br.jus.trt.app.platao.business.facade;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;

import br.jus.trt.app.platao.business.ExceptionCodes;
import br.jus.trt.app.platao.business.PlataoDeployableTestBase;
import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.lib.common_core.exception.BusinessException;

/**
 * Testes de integração da fachada {@link ServidorCrudFacade}
 * @author Augusto
 *
 */
public class ServidorCrudFacadeTest extends PlataoDeployableTestBase {

	@Inject
	private ServidorCrudFacade servidorFacade;
	
	@Test
	public void salvarCpfDuplicadoTest() {
		
		Servidor servidor = new Servidor();
		servidor.setNome("Servidor 1");
		servidor.setCpf("99999999999");
		servidorFacade.saveAndFlush(servidor);
		
		// tenta salvar outro servidor com mesmo cpf
		servidor = new Servidor();
		servidor.setNome("Servidor 2");
		servidor.setCpf("99999999999");
		
		try {
			servidorFacade.saveAndFlush(servidor);
		} catch (BusinessException e) {
			Assert.assertTrue(e.hasCode(ExceptionCodes.SERVIDOR.RN1_CPF_DUPLICADO));
		}	
		
	}
	
}
