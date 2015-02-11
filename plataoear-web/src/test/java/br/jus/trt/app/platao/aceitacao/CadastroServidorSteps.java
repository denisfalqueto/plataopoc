package br.jus.trt.app.platao.aceitacao;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.jsf.AfterPhase;
import org.jboss.arquillian.warp.jsf.Phase;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.QServidor;
import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.business.facade.ServidorCrudFacade;
import br.jus.trt.app.platao.funcionais.PlataoWebDeployableTestBase;
import br.jus.trt.app.platao.funcionais.util.TestUtils;
import br.jus.trt.lib.qbe.QBEFilter;
import br.jus.trt.lib.qbe.api.Filter;
import br.jus.trt.lib.qbe.api.operator.Operators;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features("features/cadastro_servidor.feature")
/**
 * Classe que executará os testes de aceitação da funcionalidade "features/cadastro_servidor.feature".
 * 
 * Esse teste de aceitação possui métodos totalmente blackbox (ex. loginGestor) e métodos graybox confirmarCpfNaoCadastrado.
 * 
 * Como no método de geração de deployment foi usado o @Deployment(testable=true), todos os testes dessa classe tem implicitamente a
 * anotação @RunAsClient, o que significa que estarão rodando em modo cliente (teste blackbox).
 * 
 * No graybox, é necessário usar o Arquillian Warp para fazer a ponte entre as 2 VMs (servidor wildfly e cliente junit).
 * A passagem de dados é através de serialização de payload (mais informações em: https://github.com/lfryc/arquillian.github.com/blob/warp-docs/docs/warp.adoc)
 * 
 * @author David Vieira
 *
 */
public class CadastroServidorSteps extends PlataoWebDeployableTestBase {

	@Drone
	private WebDriver browser;
	
	@ArquillianResource
	private URL urlBase;

	private Servidor servidorSendoCadastrado;
	
	@Dado("que eu estou logado no sistema como gestor")
	public void loginGestor(){
		browser.get(urlBase + "servidor/servidor.xhtml");
	}
	
	@Dado("um servidor cadastrado com todas as informações")
	public void criarServidorTodasInformações(){
		servidorSendoCadastrado = new Servidor();
		
		SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyy");
		
		Cidade cidade = new Cidade();
		cidade.setNome("Recife");
		servidorSendoCadastrado.setCidade(cidade);
		servidorSendoCadastrado.setCpf(TestUtils.gerarCpfAleatorio());
		try {
			servidorSendoCadastrado.setDataNascimento(sf.parse("01/01/1980"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		servidorSendoCadastrado.setEmail(Math.random() + "@gmail.com");
		servidorSendoCadastrado.setNome(" Servidor Teste " + Math.random());
		
	}
	
	@Dado("que não existe outro servidor cadastrado com o mesmo CPF")
	public void confirmarCpfNaoCadastrado() {
		
		InspectionExtension inspect = Warp.initiate(new Activity() {

            @Override
            public void perform() {
            	browser.get(urlBase + "servidor/servidor.xhtml");
            }

        })
        //passagem de payload do cliente para o servidor ocorrendo aqui
        .inspect(new InspectionExtension(servidorSendoCadastrado.getCpf()));

		//passagem de payload do servidor para o cliente ocorrendo aqui
		String cpfPayloadBack = inspect.getCpf();
		servidorSendoCadastrado.setCpf(cpfPayloadBack);
		
	}
	
	@Quando("eu tento cadastrar este servidor")
	public void cadastrarServidor(){
		
	}
	
	@Então("o sistema salva o servidor sem nenhum erro")
	public void salvaSemErro(){
		
	}

	@Dado("^um servidor a ser cadastrado somente com as informações essenciais$")
	public void um_servidor_a_ser_cadastrado_somente_com_as_informações_essenciais() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Dado("^um servidor a ser cadastrado sem informações essenciais$")
	public void um_servidor_a_ser_cadastrado_sem_informações_essenciais() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Então("^o servidor não é salvo$")
	public void o_servidor_não_é_salvo() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Dado("^que existe um servidor recém cadastrado$")
	public void que_existe_um_servidor_recém_cadastrado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Quando("^tento alterar esse servidor$")
	public void tento_alterar_esse_servidor() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Então("^a alteração do bem é bem sucedida$")
	public void a_alteração_do_bem_é_bem_sucedida() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Então("^eu consigo conferir os dados do servidor alterado$")
	public void eu_consigo_conferir_os_dados_do_servidor_alterado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	//definindo um Inspection customizado para transportar o payload CPF(String) entre o cliente e servidor
	private final class InspectionExtension extends Inspection {
		
		//PAYLOAD
		private String cpf;
		
		private static final long serialVersionUID = 1L;
		
		public InspectionExtension(String cpf) {
			this.cpf = cpf;
		}
		
		@SuppressWarnings("unused")
		public InspectionExtension() {}

		@AfterPhase(Phase.RENDER_RESPONSE)
		// ATENÇÃO: Na versão atual, somente está injetando através de método os beans EJB/Managed beans
		public void verifyFromEjb(ServidorCrudFacade servidorCrudFacade) {
			Long countCpfIgual = -1L;
			String cpfNovo = cpf;
			do {
				
				Filter<Servidor> filter = new QBEFilter<Servidor>(Servidor.class);
				filter.filterBy(QServidor.servidor.cpf, Operators.equal(), cpfNovo);
				countCpfIgual = servidorCrudFacade.count(filter);
			} while (countCpfIgual != 0);
			
			cpf = cpfNovo;
		}

		public String getCpf() {
			return cpf;
		}

	}
}
