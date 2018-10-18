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
	int[][] matriz_tablero;

	public Tablero(int dimensiones, int cant_reinas, ArrayList<Reina> reinas) {
		this.dimensiones = dimensiones;
		this.cant_reinas = cant_reinas;
		this.reinas = reinas;
		this.matriz_tablero = new int[dimensiones][dimensiones];
	}

	public static Tablero cargar(String input) throws IOException {
		int dim, cant_reinas;
		ArrayList<Reina> reinas = new ArrayList<Reina>();
		Scanner sc = new Scanner(new File(input));
		dim = sc.nextInt();
		cant_reinas = sc.nextInt();
		for (int i = 0; i < cant_reinas; i++)
			reinas.add(new Reina(sc.nextInt(), sc.nextInt()));
		sc.close();
		Tablero tab = new Tablero(dim, cant_reinas, reinas);
		for (int i = 0; i < cant_reinas; i++) {
			Reina r = reinas.get(i);
			tab.matriz_tablero[r.fil][r.col] = i + 1;
		}
		return tab;
	}

	public void resolver_conflictos() {
		int i,fil,col,conf;
		ArrayList<Integer> conf_enc = new ArrayList<Integer>();
		for(i=0;i<this.cant_reinas;i++){
		Reina act = this.reinas.get(i);
		this.dimensiones--;
		/*chequeo las 8 direcciones que rodean la reina, una x una, en la matriz donde las ubique*/
		/*cargo cada conflicto que encuentro en un array, y los proceso despues*/
		fil=act.fil-1;
		col=act.col+1;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			fil--;
			col++;
		}
		fil=act.fil;
		col=act.col+1;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			col++;
		}
		fil=act.fil+1;
		col=act.col+1;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			fil++;
			col++;
		}
		fil=act.fil+1;
		col=act.col;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			fil++;
		}
		fil=act.fil+1;
		col=act.col-1;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			fil++;
			col--;
		}
		fil=act.fil;
		col=act.col-1;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			col--;
		}
		fil=act.fil-1;
		col=act.col-1;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			fil--;
			col--;
		}
		fil=act.fil-1;
		col=act.col;
		while(fil<=this.dimensiones && col<= this.dimensiones && fil>=0 && col>=0)
		{
			conf=this.matriz_tablero[fil][col];
			if(conf>0) {
				conf_enc.add(conf);
				break;
			}
			fil--;
		}
		
		/*fin check*/
		/*proceso conflictos, mirando que no lo haya contado antes*/
		for(int x:conf_enc){
			Reina r = this.reinas.get(x-1);
			if(!r.existe_conflicto(i+1)) {
				act.conflicto_con(x);
			}
		}
		conf_enc.clear();
		}
	}

	public void escribir_salida(String output) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(output));
		int conf;
		this.resolver_conflictos();
		for (Reina x : this.reinas) {
			conf = x.conflictos.size();
			pw.print(conf + " ");
			if (conf != 0) {
				for (int a : x.conflictos)
					pw.print(a + " ");
			}
			pw.println();
		}
		pw.close();
	}

}
