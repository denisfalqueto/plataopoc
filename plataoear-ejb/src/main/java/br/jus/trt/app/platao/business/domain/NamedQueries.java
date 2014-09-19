package br.jus.trt.app.platao.business.domain;

/**
 * Contém as contantes com nomes e queries de NamedQueries 
 * @author augusto
 */
public interface NamedQueries {

	public interface Cidade {
		
		public static final String CIDADE_POR_UF = "Cidade.cidadesPorUf";
		public static final String CIDADE_POR_UF_QUERY = "select c from Cidade c where c.uf = :uf"; 
		
	}
}
