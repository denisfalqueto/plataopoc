package br.jus.trt.app.platao.business;

/**
 * Estrutura para concentração dos códigos das exceções relativas às regras
 * de negócio da aplicação.
 * @author Augusto
 *
 */
public interface ExceptionCodes {

	public interface SERVIDOR {
		/** Código de exceção para a RN1 que confere CPF único.*/
		public static final String RN1_CPF_DUPLICADO = "RN1_CPF_DUPLICADO";
	}
}
