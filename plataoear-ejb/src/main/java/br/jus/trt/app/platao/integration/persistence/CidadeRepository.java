package br.jus.trt.app.platao.integration.persistence;


import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.business.domain.Uf;
import br.jus.trt.lib.common_core.integration.persistence.CrudRepositoryBase;
import java.util.List;
import org.apache.deltaspike.data.api.Repository;

/**
 * Classe de negócio da entidade {@link Cidade}
 * @author augusto
 */
@Repository(forEntity = Cidade.class)
public abstract class CidadeRepository extends CrudRepositoryBase<Cidade, Long> { 
    
    public abstract List<Cidade> findByUf(Uf uf);
}
