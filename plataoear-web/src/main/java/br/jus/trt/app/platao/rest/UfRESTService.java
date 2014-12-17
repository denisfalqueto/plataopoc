package br.jus.trt.app.platao.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.app.platao.business.facade.CidadeUfFacade;
import br.jus.trt.app.platao.rest.dto.CidadeSemUfDTO;

/**
 * Serviço JAX-RS de consultas relacionadas à entidade {@link Uf}.
 */
@Path("/ufs")
@RequestScoped
public class UfRESTService {
	
	@Inject
	private CidadeUfFacade cidadeUfFacade;
	
	@GET
	@Produces("application/json")
	public List<Uf> listAll() {
		return cidadeUfFacade.listUfs();
	}

	@GET
	@Path("/{idUf}/cidades")
	@Produces("application/json")
	public Response findByIdUf(@PathParam("idUf") Long idUf) {
		
		Uf uf = new Uf();
		uf.setId(idUf);
		
		return Response.ok(cidadeUfFacade.searchCidadesSemUfDTO(uf)).build();
	}
}
