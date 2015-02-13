package br.jus.trt.app.platao.funcionais.util;

import java.util.Random;

public class TestUtils {

	/**
	 * Gera numero de CPF randomico.
	 * 
	 * @return Novo CPF
	 * @see generateDigits(String digitsBase)
	 */
	public static String gerarCpfAleatorio() {
		Random r = new Random();
		StringBuilder sbCpfNumber = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			sbCpfNumber.append(r.nextInt(9));
		}
		return generateDigits(sbCpfNumber.toString());

	}

	/**
	 * Gera digitos validadores.
	 * 
	 * @param digitsBase
	 *            9 digitos iniciais.
	 * @return CPF com digitos validadores sem pontuacao.
	 */
	private static String generateDigits(String digitsBase) {
		StringBuilder sbCpfNumber = new StringBuilder(digitsBase);
		int total = 0;
		int multiple = digitsBase.length() + 1;

		for (char digit : digitsBase.toCharArray()) {
			long parcial = Integer.parseInt(String.valueOf(digit))
					* (multiple--);
			total += parcial;
		}

		int resto = Integer.parseInt(String.valueOf(Math.abs(total % 11)));

		if (resto < 2) {
			resto = 0;
		} else {
			resto = 11 - resto;
		}

		sbCpfNumber.append(resto);
		if (sbCpfNumber.length() < 11) {
			return generateDigits(sbCpfNumber.toString());
		}
		return sbCpfNumber.toString();
	}

}
