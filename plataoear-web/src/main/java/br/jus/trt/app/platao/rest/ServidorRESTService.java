package br.jus.trt.app.platao.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.business.facade.ServidorCrudFacade;
import br.jus.trt.lib.qbe.QBEFilter;

/**
 * Serviço JAX-RS de consultas relacionadas à entidade {@link Servidor}.
 */
@Path("/servidores")
@RequestScoped
public class ServidorRESTService {

	@Inject
	private ServidorCrudFacade servidorCrudFacade;

	@GET
	@Produces("text/xml")
	public List<Servidor> listAllMembers() {
		QBEFilter<Servidor> filter = new QBEFilter<Servidor>(Servidor.class);
		filter.addFetch("cidade.uf");
		return servidorCrudFacade.findAllBy(filter);
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("text/xml")
	public Servidor lookupMemberById(@PathParam("id") long id) throws Exception {
		Servidor servidor = servidorCrudFacade.findBy(id, "cidade.uf");
		return servidor;
	}
}
