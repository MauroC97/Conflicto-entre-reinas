package reinas;

import java.io.IOException;

public class Main {
	
	public static void main(String [] args) throws IOException {
		Tablero tab = Tablero.cargar("test/in/00_enunciado.in");
		tab.escribir_salida("test/out/00_enunciado.in");
		tab = Tablero.cargar("test/in/01_enunciado.in");
		tab.escribir_salida("test/out/01_enunciado.in");
	}

}
