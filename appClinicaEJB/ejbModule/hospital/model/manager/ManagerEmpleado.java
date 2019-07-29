package hospital.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hospital.model.entities.AreaTrabajo;
import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;

/**
 * 
 * @author David Cuasapas
 *
 */

@Stateless
@LocalBean
public class ManagerEmpleado {

	@PersistenceContext(unitName = "HeleyMedDS")
	private EntityManager em;

	public ManagerEmpleado() {
	}

	public Empleado findEmpleadoById(String cedulaEmp) {
		Empleado e = em.find(Empleado.class, cedulaEmp);
		return e;
	}

	public Persona findPersonaById(String cedulaEmp) {
		Persona p = em.find(Persona.class, cedulaEmp);
		return p;
	}

	public AreaTrabajo findAreById(long idArea) {
		AreaTrabajo a = em.find(AreaTrabajo.class, idArea);
		return a;
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findAllPersonas() {
		Query q;
		List<Persona> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Persona o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	@SuppressWarnings("unchecked")
	public List<AreaTrabajo> findAllAreas() {
		Query q;
		List<AreaTrabajo> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM AreaTrabajo o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public List<Empleado> findEmpleadoByPersona(String cedulaEmp) {
		String JPQL = "SELECT o FROM Empleado o WHERE o.persona.cedulaEmp='" + cedulaEmp + "'";
		Query q;
		List<Empleado> lista;
		q = em.createQuery(JPQL);
		lista = q.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	private void ELIMINAR(String cedulaEmp) {
		String JPQL = "DELETE  FROM Empleado o WHERE o.cedulaEmp='" + cedulaEmp + "'";
		Query q;
		q = em.createQuery(JPQL);
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> findAllEmpleados() {
		Query q;
		List<Empleado> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Empleado o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public void insertEmpleado(String cedulaEmp, long idArea, String cargoUs, String paswordUs, String activoEmp)
			throws Exception {

		Empleado e = findEmpleadoById(cedulaEmp);
		if (e != null)
			throw new Exception("El Empleado " + cedulaEmp + "Ya esta Registrado");
		e = new Empleado();

		Persona p = findPersonaById(cedulaEmp);
		if (p != null)
			e.setCedulaEmp(cedulaEmp);
		AreaTrabajo a = findAreById(idArea);
		if (a != null)
			e.setAreaTrabajo(a);
		e.setCargoUs(cargoUs);
		e.setPaswordUs(paswordUs);
		e.setActivoEmp(activoEmp);
		em.persist(e);
	}

	public void deleteEMPLEADO(String cedEmp) throws Exception {
		ELIMINAR(cedEmp);
		Empleado emp = findEmpleadoById(cedEmp);
		em.remove(emp);
		if (emp == null) {
			em.remove(emp);
			throw new Exception("No existe el Empleado " + cedEmp);
		} else {
			em.remove(emp);
		}

	}

	public void UpdateEmpleado(String cedulaEmp, long idArea, String cargoUs, String activoEmp) throws Exception {
		Empleado e = findEmpleadoById(cedulaEmp);
		if (e == null)
			throw new Exception("No existe el empleado especificado");
		Persona p = findPersonaById(cedulaEmp);
		if (p != null)
			e.setCedulaEmp(p.getCedulaEmp());
		AreaTrabajo a = findAreById(idArea);
		if (a != null)
			e.setAreaTrabajo(a);
		e.setCargoUs(cargoUs);
		e.setActivoEmp(activoEmp);
		em.merge(e);
	}

	public void UpdateEmpleado(Empleado e) {
		em.merge(e);
	}

}
