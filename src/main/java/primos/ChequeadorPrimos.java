package primos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import primos.estructura.EstructuraPrimos;
import primos.estructura.Primo;

public class ChequeadorPrimos {
	private EstructuraPrimos ep = new EstructuraPrimos();

	public boolean cargarPrimos(long tope) {
		if (ep.getPrimoMaximo() < tope) {
			return ep.cargarPrimos(tope);
		}
		return true;
	}

	public boolean validarPrimosExistentes() {
		List listaPrimosActual = ep.findAll();
		Iterator<Long> itPrimoActual = listaPrimosActual.iterator();
		int i = 0;
		while (itPrimoActual.hasNext()) {
			Long primoActual = itPrimoActual.next();
			if (!validarPrimo(primoActual)) {
				System.err.println("Error en " + primoActual);
				return false;
			} else {
				i++;
			}
			if (i % EstructuraPrimos.TOPE_DESPLIEGUE_RESULTADOS == 0) {
				imprimirResultadosValidacion();
			}
		}
		return false;
	}

	private List<Long[]> listaLog = new ArrayList<Long[]>();

	private boolean validarPrimo(Long primo) {
		long multiplicandoReferencia = 2;
		if (primo == 2) {
			multiplicandoReferencia = 3;
		}
		Iterator<Primo> itMultiplicandos = ep.iterator();
		while (itMultiplicandos.hasNext()) {
			Primo primoActual = (Primo) itMultiplicandos.next();
			Long multiplicandoActual = primoActual.getNumero(); 
			Iterator<Primo> itRestando = ep.iterator();
			while (itRestando.hasNext()) {
				Primo primoRestandoActual = itRestando.next();
				Long restandoActual = primoRestandoActual.getNumero();
				if (multiplicandoActual != restandoActual) {
					// descarto la validación trivial a*2 - a
					if (restandoActual < multiplicandoActual * multiplicandoReferencia) {
						if (((multiplicandoActual * multiplicandoReferencia) - restandoActual) == primo) {
							listaLog.add(new Long[] { primo, multiplicandoReferencia, multiplicandoActual, restandoActual });
							return true;
						}
					} else {
						// No lo encontré para el multiplicando actual
						break;
					}
				}
			}
		}
		System.err.println("Error al validar " + primo);
		return false;
	}

	public static void main(String[] args) {
		ChequeadorPrimos cp = new ChequeadorPrimos();
		cp.cargarPrimos(100000);
		cp.ep.imprimirListaPrimos();
		cp.validarPrimosExistentes();
	}

	private void imprimirResultadosValidacion() {
		int tope = listaLog.size();
		for (int i = 0; i < tope - 1; i++) {
			StringBuilder sb = generarLineaLog(i);
			sb.append("---");
			System.out.print(sb);
		}
		System.out.println(generarLineaLog(tope - 1));
		listaLog.clear();
	}

	private StringBuilder generarLineaLog(int i) {
		Long[] posActual = listaLog.get(i);
		StringBuilder sb = new StringBuilder();
		sb.append(posActual[0]);
		sb.append("=");
		sb.append(posActual[1]);
		sb.append("*");
		sb.append(posActual[2]);
		sb.append("-");
		sb.append(posActual[3]);
		return sb;
	}
}
