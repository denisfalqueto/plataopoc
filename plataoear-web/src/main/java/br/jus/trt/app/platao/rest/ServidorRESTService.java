package br.jus.trt.app.platao.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.apache.logging.log4j.Logger;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.business.facade.ServidorCrudFacade;
import br.jus.trt.lib.qbe.QBEFilter;
import br.jus.trt.lib.qbe.api.Filter;
import br.jus.trt6.lib.common_web.action.CrudRestBase;
import br.jus.trt6.lib.common_web.action.ResultData;

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
		filter.addFetch("cidade.uf");
	}
	
	@Override
	protected void configureFilterListAll(QBEFilter<Servidor> filter) {
		filter.addFetch("cidade.uf");
	}
	
}
