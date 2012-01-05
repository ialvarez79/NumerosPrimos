package primos.estructura;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;

@Entity
public class Primo {
    @Transient
    @PersistenceContext(unitName = "primos")
    private static EntityManager em;

    @Id
	private Long numero;

	public Primo(Long l) {
		numero = l;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
    public static Primo find(long id)
    {
        return em.find(Primo.class, new Long(id));
    }
    
    private static final String QUERY_OBTENER_MAXIMO = "SELECT p from Primo p where p.numero = (SELECT max(p.numero) from p)";
    public static Primo obtenerMaximo(){
    	Query q = em.createQuery(QUERY_OBTENER_MAXIMO);
    	return (Primo) q.getSingleResult();
    }
    
	
    public static EntityManager getEntityManager()
    {
        return em;
    }

    public static void setEntityManager(EntityManager entityManager)
    {
        Primo.em = entityManager;
    }

	public static void insertar(Primo primo) {
		em.persist(primo);
	}

	
	private static final String QUERY_OBTENER_TODOS = "SELECT p from Primo p";
	private static final String QUERY_OBTENER_TODOS_ENTRE = "SELECT p from Primo p where p.numero > ? and p.numero < ?";
	@SuppressWarnings("unchecked")
	public static List<Primo> findAll(Long mayorQue, Long menorQue) {
		Query q = em.createQuery(QUERY_OBTENER_TODOS_ENTRE);
		q.setParameter(1, mayorQue);
		q.setParameter(2, menorQue);
		return q.getResultList();
	}

	@Override
	public String toString() {
		return "Primo [numero=" + numero + "]";
	}

	
	
}