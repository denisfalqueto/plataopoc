package br.jus.trt.app.platao.funcionais.crud.servidor;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.jsf.AfterPhase;
import org.jboss.arquillian.warp.jsf.Phase;
import org.junit.Assert;
import org.junit.Test;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.funcionais.PlataoWebDeployableTestBase;
import br.jus.trt.app.platao.funcionais.crud.servidor.page.ServidorCrudPage;

/**
 * Testes funcionais na tela de CRUD de Servidor.
 * 
 * Foi utilizado o padrão page object para abstrair a complexidade da tela do teste em si.
 * 
 * No {@link TelaServidorCrudTest#pesquisarUsuarioInexistente(ServidorCrudPage)} é demonstrado
 * a execução de um teste em tela (browser executando o teste) com a asserção no servidor.
 * 
 * @author David Vieira
 *
 */
public class TelaServidorCrudTest extends PlataoWebDeployableTestBase {

	@Test
	public void pesquisarUsuarioExistente(@InitialPage ServidorCrudPage servidorCrudPage) {
		servidorCrudPage.pesquisarPorNome("Augusto");
		
		// assert via tela usando page object
		servidorCrudPage.assertResultadoPesquisaContem("Augusto");
	}
	
	@Test
	public void pesquisarUsuarioInexistente(@InitialPage final ServidorCrudPage servidorCrudPage) {
		Warp.initiate(new Activity() {

            @Override
            public void perform() {
            	servidorCrudPage.pesquisarPorNome("Augusto 123456");
            }

        })
        .inspect(new Inspection() {
            private static final long serialVersionUID = 1L;

            // assert no servidor
            @ManagedProperty("#{servidorAction.resultList}")
            private List<Servidor> resultado;
            
            @AfterPhase(Phase.RENDER_RESPONSE)
            public void verifyFromEjb() {
            	Assert.assertTrue(resultado.size() == 0);
            }
        });

	}
	
}