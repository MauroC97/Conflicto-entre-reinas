package reinas;

import java.util.ArrayList;
import java.util.Collections;

public class Reina {
	int fil;
	int col;
	ArrayList<Integer> conflictos;

	public Reina(int fil, int col) {
		this.fil = fil;
		this.col = col;
		conflictos = new ArrayList<Integer>();
	}

	public boolean existe_conflicto(int nro_reina) {// usarlo llamando desde la reina encontrada preguntando por la reina que chequea el conflicto
		for (Integer x : this.conflictos)
			if (nro_reina == x)
				return true;
		return false;
	}

	public void conflicto_con(int nro_reina) {
			conflictos.add(nro_reina);
			Collections.sort(this.conflictos);
	}

}
