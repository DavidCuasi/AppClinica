package hospital.model.manager;

import java.util.Date;
import java.util.List;



import hospital.model.entities.AreaTrabajo;
import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;

/**
 * 
 * @author Santiago Lomas Almeida
 *
 */

@
@LocalBean
public class ManagerAreaTrabajo {
	@PersistenceContext(unitName = "HeleyMedDS")
	private EntityManager em;

	public ManagerAreaTrabajo() {
	}

	public AreaTrabajo findAreById(long idArea) {
		AreaTrabajo a = em.find(AreaTrabajo.class, idArea);
		return a;
	}

	public List<Empleado> findEmpleadoByArea(long idArea) {
		String JPQL = "SELECT o FROM Empleado o WHERE o.areaTrabajo.idArea='" + idArea + "'";
		Query q;
		List<Empleado> lista;
		q = em.createQuery(JPQL);
		lista = q.getResultList();
		return lista;
	}

	public List<AreaTrabajo> findAllAreas() {
		Query q;
		List<AreaTrabajo> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM AreaTrabajo o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public void insertAreaTrabajo(String nombreArea, String gerenciaArea, String descripcionArea, String estadoArea)
			throws Exception {
		AreaTrabajo a = new AreaTrabajo();
		a.setNombreArea(nombreArea);
		a.setGerenciaArea(gerenciaArea);
		a.setDescripcionArea(descripcionArea);
		a.setEstadoArea(estadoArea);
		em.persist(a);
	}

	public void deleteAreaTrabajo(long idArea) throws Exception {

		AreaTrabajo a = findAreById(idArea);
		if (a == null) {
			throw new Exception("No existe el Area " + idArea);
		} else if (findEmpleadoByArea(idArea).size() == 0) {
			em.remove(a);
		} else {
			throw new Exception(
					"El Area " + a.getNombreArea() + "Esta asignada a uno o mas empleado por lo cual no es posible eliminar");

		}

	}

	public void UpdateAreaTrabajo(long idArea, String nombreArea, String gerenciaArea, String descripcionArea,
			String estadoArea) throws Exception {
		AreaTrabajo a = findAreById(idArea);
		if (a == null)
			throw new Exception("No existe el area especificada");
		a.setNombreArea(nombreArea);
		a.setGerenciaArea(gerenciaArea);
		a.setDescripcionArea(descripcionArea);
		a.setEstadoArea(estadoArea);
		em.merge(a);
	}

}