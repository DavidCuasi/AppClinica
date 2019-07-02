package hospital.model.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hospital.model.entities.CabeceraConsulta;
import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;

/**
 * 
 * @author Santiago Lomas Almeida
 *
 */

@Stateless
@LocalBean
public class ManagerConsulta {

	@PersistenceContext(unitName = "HeleyMedDS")
	private EntityManager em;

	public ManagerConsulta() {
	}

	public Empleado findEmpleadoById(String cedulaEmp) {
		Empleado e = em.find(Empleado.class, cedulaEmp);
		return e;
	}

	public Persona findPersonaById(String cedulaEmp) {
		Persona p = em.find(Persona.class, cedulaEmp);
		return p;
	}

	public CabeceraConsulta findConsultaById(long idCon) {
		CabeceraConsulta co = em.find(CabeceraConsulta.class, idCon);
		return co;
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
	public List<Empleado> findAllEmpleados() {
		Query q;
		List<Empleado> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Empleado o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	@SuppressWarnings("unchecked")
	public List<CabeceraConsulta> findAllConsultas() {
		Query q;
		List<CabeceraConsulta> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM CabeceraConsulta o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	@SuppressWarnings("unchecked")
	public List<CabeceraConsulta> findConsultaByUsuario(String cedulaEmp) {
		String JPQL = "SELECT o FROM CabeceraConsulta o WHERE o.persona.cedulaEmp='" + cedulaEmp + "'";
		Query q;
		List<CabeceraConsulta> lista;
		q = em.createQuery(JPQL);
		lista = q.getResultList();
		return lista;
	}

	public void crearConsulta(String cedulaEmp, Date fechaCon, String indicacionesCon, String motivoCon,
			String observacionesCon, BigDecimal pesoCon, BigDecimal presionCon, String sintomasCon,
			BigDecimal temperaturaCon) throws Exception {

		Persona p = findPersonaById(cedulaEmp);
		if (p == null)
			throw new Exception("No existe el empleado" + cedulaEmp);
		CabeceraConsulta ca = new CabeceraConsulta();
		ca.setPersona(p);
		ca.setFechaCon(fechaCon);
		ca.setIndicacionesCon(indicacionesCon);
		ca.setMotivoCon(motivoCon);
		ca.setObservacionesCon(observacionesCon);
		ca.setPesoCon(pesoCon);
		ca.setPresionCon(presionCon);
		ca.setSintomasCon(sintomasCon);
		ca.setTemperaturaCon(temperaturaCon);
		em.persist(ca);
	}

	public void eliminarConsulta(long idCon) throws Exception {
		CabeceraConsulta co = findConsultaById(idCon);
		if (co == null) {
			throw new Exception("No existe la consulta " + idCon);
		} else {
			em.remove(co);
		}

	}

	public void editarBlog(long idCon, Date fechaCon, String indicacionesCon, String motivoCon, String observacionesCon,
			BigDecimal pesoCon, BigDecimal presionCon, String sintomasCon, BigDecimal temperaturaCon) throws Exception {

		CabeceraConsulta ca = findConsultaById(idCon);
		if (ca == null)
			throw new Exception("No existe la consulta especificado.");

		ca.setFechaCon(fechaCon);
		ca.setIndicacionesCon(indicacionesCon);
		ca.setMotivoCon(motivoCon);
		ca.setObservacionesCon(observacionesCon);
		ca.setPesoCon(pesoCon);
		ca.setPresionCon(presionCon);
		ca.setSintomasCon(sintomasCon);
		ca.setTemperaturaCon(temperaturaCon);
		em.merge(ca);
	}

}
