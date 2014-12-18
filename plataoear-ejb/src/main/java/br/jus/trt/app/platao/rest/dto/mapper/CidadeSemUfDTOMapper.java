package br.jus.trt.app.platao.rest.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.deltaspike.data.api.mapping.QueryInOutMapper;

import br.jus.trt.app.platao.business.domain.Cidade;
import br.jus.trt.app.platao.rest.dto.CidadeSemUfDTO;


public class CidadeSemUfDTOMapper implements QueryInOutMapper<Cidade> {

	@Override
	public Object mapParameter(Object parameter) {
		return parameter;
	}

	@Override
	public Object mapResult(Cidade cidade) {
		CidadeSemUfDTO cidadeSemUfDTO = new CidadeSemUfDTO();
		cidadeSemUfDTO.setId(cidade.getId());
		cidadeSemUfDTO.setNome(cidade.getNome());
		
		return cidadeSemUfDTO;
	}

	@Override
	public Object mapResultList(List<Cidade> cidades) {
		List<CidadeSemUfDTO> result = new ArrayList<CidadeSemUfDTO>();
		for (Cidade cidade : cidades) {
			result.add((CidadeSemUfDTO) mapResult(cidade));
		}
		return result;
	}

	@Override
	public boolean mapsParameter(Object arg0) {
		return false;
	}
	
}
