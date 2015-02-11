package br.jus.trt.app.platao.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import br.jus.trt.app.platao.business.domain.QServidor;
import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.business.facade.ServidorCrudFacade;
import br.jus.trt.lib.qbe.QBEFilter;
import br.jus.trt6.lib.common_web.action.CrudRestBase;

/**
 * Serviço JAX-RS de consultas relacionadas à entidade {@link Servidor}.
 */
@Path(ServidorRESTService.PATH)
@RequestScoped
public class ServidorRESTService extends CrudRestBase<Servidor, Long, ServidorCrudFacade> {

	public static final String PATH = "/servidores";

	@Override
	public String getPath() {
		return PATH;
	}
	
	@Override
	protected void configureFilterFindById(QBEFilter<Servidor> filter) {
		filter.addFetch(QServidor.servidor.cidade.uf);
	}
	
	@Override
	protected void configureFilterListAll(QBEFilter<Servidor> filter) {
		filter.addFetch(QServidor.servidor.cidade.uf);
	}
	
}
