package reinas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Tablero {
	int dimensiones;
	int cant_reinas;
	ArrayList<Reina> reinas;
	int [][] matriz_tablero;
	
	public Tablero(int dimensiones, int cant_reinas, ArrayList<Reina> reinas) {
		this.dimensiones = dimensiones;
		this.cant_reinas = cant_reinas;
		this.reinas = reinas;
		this.matriz_tablero = new int [dimensiones][dimensiones];
	}
	
	public static Tablero cargar(String input) throws IOException {
		int dim,cant_reinas;
		ArrayList<Reina> reinas = new ArrayList<Reina>();
		Scanner sc = new Scanner(new File(input));
		dim=sc.nextInt();
		cant_reinas=sc.nextInt();
		for(int i=0;i<cant_reinas;i++)
			reinas.add(new Reina(sc.nextInt(),sc.nextInt()));
		sc.close();
		return new Tablero(dim,cant_reinas,reinas);
	}
	
	public void resolver_conflictos() {
		
	}
	
	public void escribir_salida(String output) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(output));
		int conf;
		this.resolver_conflictos();
		for (Reina x: this.reinas)
		{
			conf=x.conflictos.size();
			pw.print(conf + " ");
			if(conf!=0){
				for (int a: x.conflictos)
					pw.print(a + " ");
			}
			pw.println();
		}
		pw.close();
	}
	
}
