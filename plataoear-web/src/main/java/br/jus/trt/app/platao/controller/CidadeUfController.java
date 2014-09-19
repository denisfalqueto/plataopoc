package br.jus.trt.app.platao.controller;

import java.util.List;

import javax.inject.Inject;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.app.platao.business.facade.CidadeUfFacade;
import br.jus.trt6.lib.common_web.action.ActionBase;

/**
 * Classe de controle genérica para auxiliar em associações com Cidade e UF.
 * @author augusto
 *
 */
@SuppressWarnings("serial")
public class CidadeUfController extends ActionBase {

	/** Fachada para consulta das informações de cidade e UF */
	@Inject
	private CidadeUfFacade cidadeUfFacade;
	
	/** Para preenchimento dos estados cadastrados */
	private List<Uf> ufs;
	
	/** Para preenchimento das cidades cadastradas (por estado) */
	private List<Cidade> cidades;

	private Uf selectedUf;

	@Override
	public void init() {
		super.init();
		ufs = cidadeUfFacade.listUfs();
	}
	
	/**
	 * Consulta as cidades quando {@link CidadeUfController#selectedUf} estiver preenchido.
	 */
	public void loadCidades() {
		if (selectedUf == null) {
			cidades = null;
		} else {
			cidades = cidadeUfFacade.searchCidades(selectedUf);
		}
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Uf> getUfs() {
		return ufs;
	}

	public void setUfs(List<Uf> ufs) {
		this.ufs = ufs;
	}
	
	public Uf getSelectedUf() {
		return selectedUf;
	}

	public void setSelectedUf(Uf selectedUf) {
		this.selectedUf = selectedUf;
	}
	
}
