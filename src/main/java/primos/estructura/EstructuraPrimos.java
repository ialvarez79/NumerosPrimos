package primos.estructura;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EstructuraPrimos {
	private Long primoMaximo = obtenerPrimoMaximo();
	private ValidadorPrimos vp = new ValidadorPrimos(this);
	public static final int TOPE_DESPLIEGUE_RESULTADOS = 10;
	private static final Long topeInferior = Long.valueOf(2);
	
	public EstructuraPrimos(){
		agregarPrimoAEstructura(1);
		agregarPrimoAEstructura(2);
		agregarPrimoAEstructura(3);
	}
	
	private Long obtenerPrimoMaximo() {
		Primo p = Primo.obtenerMaximo();
		if (p == null){
			return new Long(-1);
		} else {
			return p.getNumero();
		}
	}

	private void agregarPrimoAEstructura(long l) {	
		if (l > primoMaximo){ //sino no hago nada, tengo todos los primos hasta ese número
			Primo.insertar(new Primo(l));
			primoMaximo = l;
			listaPrimosAlmacenada = null;
		}		
	}

	public boolean agregarPrimosALista(Long mayoresQue, Long menoresQue, List<Long> listaAAgregar){
		return false;
	}

	public long getPrimoMaximo() {
		return primoMaximo;
	}

	public boolean cargarPrimos(long tope) {
		while(primoMaximo < tope){
			agregarProximoPrimo();
		}
		return true;
	}

	private void agregarProximoPrimo() {
		long numeroActual = primoMaximo + 1;
		while(!vp.esPrimo(numeroActual)){
			numeroActual++;			
		}
		//NumeroActual es primo
		agregarPrimoAEstructura(numeroActual);
	}

	private List<Primo> listaPrimosAlmacenada = null;
	@SuppressWarnings("unchecked")
	public List<Primo> findAll(){
		if (listaPrimosAlmacenada == null){
			List listaPrimos = findAll(primoMaximo);
			listaPrimosAlmacenada = new ArrayList<Primo>(listaPrimos.size());
			listaPrimosAlmacenada.addAll(listaPrimos);
		}
		return listaPrimosAlmacenada; 
	}

	public Iterator iterator(Long tope){
		return findAll(tope).iterator();
	}

	
	public Iterator<Primo> iterator(){
		return findAll().iterator();
	}
	
	@SuppressWarnings("unchecked")
	public List findAll(Long numeroMaximo) {
		return Primo.findAll(topeInferior,numeroMaximo);
	}

	public void imprimirListaPrimos() {
		for (Iterator<Primo> i = iterator(); i.hasNext();) {
			Primo p = i.next();
			System.out.println(p);
		}
		
	}
	
	
	
}
