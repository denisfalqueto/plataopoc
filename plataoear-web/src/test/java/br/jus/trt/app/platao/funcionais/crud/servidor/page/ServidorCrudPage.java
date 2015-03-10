package br.jus.trt.app.platao.funcionais.crud.servidor.page;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.jus.trt.lib.common_tests.arquillian.graphene.findby.FindByJsfId;

@Location("servidor/servidor.xhtml")
public class ServidorCrudPage {
	
	@FindByJsfId("nome")
	private WebElement searchInputText;

	@FindBy(xpath="//input[contains(@value, 'Pesquisar')]")
	private WebElement pesquisarButton;
	
	@Drone
	private WebDriver driver;
	
//	@FindByJsfId("table")
//	private DataTable tabelaResultados;
	
	public void pesquisarPorNome(String nome) {
		searchInputText.sendKeys(nome);
		
	    Graphene.guardHttp(pesquisarButton).click();
	}

	public void assertResultadoPesquisaContem(String string) {
		// TODO implementar
		//Assert.assertFalse(tabelaResultados.linha(1).containsText("Nenhum registro foi encontrado."));
	}

	public void assertResultadoPesquisaVazio() {
		// TODO implementar
		//Assert.assertFalse(tabelaResultados.linha(1).containsText("Nenhum registro foi encontrado."));
	}

}
