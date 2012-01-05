package primos.estructura;

import java.util.Iterator;

public class ValidadorPrimos {
	private EstructuraPrimos ep = null;

	public ValidadorPrimos(EstructuraPrimos estructuraPrimos) {
		ep = estructuraPrimos;
	}

	public boolean esPrimo(long numeroActual) {
		Double tope = Math.sqrt(numeroActual); // es el número tope hasta el que
												// voy a buscar
		Iterator primos = ep.iterator(tope.longValue());
		while (primos.hasNext()) {
			Primo p = (Primo) primos.next();
			Long primoActual = p.getNumero();
			if (primoActual > 1) {
				if (primoActual == numeroActual) {
					return true; // Lo tengo en la lista
				} else {
					if (numeroActual % primoActual == 0) {
						return false;
					}
				}
			}
		}
		return true; // no encontré un primo que fuera divisor de este número
	}
}
