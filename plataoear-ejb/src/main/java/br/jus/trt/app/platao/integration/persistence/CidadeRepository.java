package br.jus.trt.app.platao.integration.persistence;


import java.util.List;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.NamedQueries;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.app.platao.rest.dto.CidadeSemUfDTO;
import br.jus.trt.app.platao.rest.dto.mapper.CidadeSemUfDTOMapper;
import br.jus.trt.lib.common_core.integration.persistence.CrudRepositoryBase;

/**
 * Classe de negócio da entidade {@link Cidade}
 * @author augusto
 */
@Repository(forEntity = Cidade.class)
public abstract class CidadeRepository extends CrudRepositoryBase<Cidade, Long> { 
    
	@MappingConfig(CidadeSemUfDTOMapper.class)
	@Query(named= NamedQueries.Cidade.CIDADE_POR_UF)
    public abstract List<CidadeSemUfDTO> findByUfDTO(@QueryParam("uf") Uf uf);
	
    public abstract List<Cidade> findByUf(Uf uf);
}
