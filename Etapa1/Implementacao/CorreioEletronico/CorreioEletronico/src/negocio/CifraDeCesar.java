package negocio;

public class CifraDeCesar {

	public static String cifrar(String texto, int deslocamento) {
		StringBuilder resultado = new StringBuilder();

		for (int i = 0; i < texto.length(); i++) {
			char caractereAtual = texto.charAt(i);

			if (caractereAtual >= 'A' && caractereAtual <= 'Z') {
				char caractereCifrado = (char) ((caractereAtual - 'A' + deslocamento) % 26 + 'A');
				resultado.append(caractereCifrado);
			}

			else if (caractereAtual >= 'a' && caractereAtual <= 'z') {
				char caractereCifrado = (char) ((caractereAtual - 'a' + deslocamento) % 26 + 'a');
				resultado.append(caractereCifrado);
			}

			else {
				resultado.append(caractereAtual);
			}
		}

		return resultado.toString();
	}

	public static String decifrar(String textoCifrado, int deslocamento) {
		return cifrar(textoCifrado, 26 - (deslocamento % 26));
	}
}