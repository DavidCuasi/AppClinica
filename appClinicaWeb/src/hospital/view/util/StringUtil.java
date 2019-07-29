package hospital.view.util;

public class StringUtil {

	public static String generarClave() {
		String clave = "";
		for (int i = 0; i < 8; i++) {
			int lon = (int) (Math.random() * 2 + 1);// 1-> letra; 2-> número
			if (lon == 1) {
				int letra = (int) (Math.random() * (90 - 65) + 65);
				int mon = (int) (Math.random() * 2 + 1);// 1-> Mayúscula; 2-> minúscula
				String c = String.valueOf((char) letra);
				if (mon == 2) {
					c = c.toLowerCase();
				}
				clave += c;
			} else {
				int numero = (int) (Math.random() * 9);
				clave += numero;
			}
		}
		return clave;
	}

}
