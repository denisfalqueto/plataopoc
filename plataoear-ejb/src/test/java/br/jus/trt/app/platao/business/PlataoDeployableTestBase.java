package br.jus.trt.app.platao.business;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import br.jus.trt.app.platao.PlataoAllTestsSuite;
import br.jus.trt.lib.common_tests.DeployableTestBase;

/**
 * Classe base (específica deste projeto) com configurações para rodar testes no Arquillian
 * @author Augusto
 *
 */
public class PlataoDeployableTestBase extends DeployableTestBase {

	/**
	 * Método que se integra ao ciclo de vida do Arquillian para realização do
	 * deploy dos arquivos necessários para execução dos testes no servidor de
	 * aplicação.
	 * 
	 * @return Arquivo elaborado para realização do deploy da aplicação + testes
	 *         no servidor de aplicação.
	 */
	@Deployment
	public static Archive<?> createDeployment() {

		JavaArchive ejb = ShrinkWrap.create(JavaArchive.class, "platao.jar")
				.addPackages(true, PlataoAllTestsSuite.class.getPackage())
				.addAsResource("test-arquillian-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("test-arquillian-beans.xml", "META-INF/beans.xml")
				.addAsResource("test-arquillian-log4j2.xml", "log4j.xml");
		installDataLoaderExtension(ejb);
		
		
		EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "platao.ear")
				.addAsResource("test-arquillian-application.xml", "application.xml");
		
		addDefaultJbossDeploymentStructure(ear);
		addLibsFromPom(ear);
		ear.addAsModule(ejb);
		
		
//		WebArchive war = ShrinkWrap
//				.create(WebArchive.class, "test.war")
//				.addPackages(true, PlataoAllTestsSuite.class.getPackage())
//				.addAsResource("test-arquillian-persistence.xml", "META-INF/persistence.xml")
//				.addAsWebInfResource("test-arquillian-beans.xml", "beans.xml");
//
//		addLibsFromPom(war);
//		installDataLoaderExtension(war);
//		addDefaultJbossDeploymentStructure(war);

		System.out.println(ejb.toString(true));
		System.out.println(ear.toString(true));
		return ear;
	}
	
}
