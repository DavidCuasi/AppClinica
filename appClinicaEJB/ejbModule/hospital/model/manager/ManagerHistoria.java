package hospital.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hospital.model.entities.AreaTrabajo;
import hospital.model.entities.Empleado;
import hospital.model.entities.Historia;
import hospital.model.entities.Persona;

/**
 * 
 * @author David Cuasapas
 *
 */

@Stateless
@LocalBean
public class ManagerHistoria {

	@PersistenceContext(unitName = "HeleyMedDS")
	private EntityManager em;

	public ManagerHistoria() {
	}

	public Empleado findEmpleadoById(String cedulaEmp) {
		Empleado e = em.find(Empleado.class, cedulaEmp);
		return e;
	}

	public List<Empleado> findAllEmpleados() {
		Query q;
		List<Empleado> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Empleado o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public Historia findHistoriaById(String cedulaEmp) {
		Historia h = em.find(Historia.class, cedulaEmp);
		return h;
	}

	public List<Historia> findAllHistorias() {
		Query q;
		List<Historia> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Historia o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public void insertHistoria(String cedulaEmp, String alcoholH, String alimentacionH, String causasHH,
			String causasMH, String causasPH, String causasSonH, String dipsiaH, String drogasH, String fallecidoMH,
			String fallecidoPH, String frecuenciaAlcH, String frecuenciaDroH, String frecuenciaInsomH,
			String frecuenciaParadipsia, String frecuenciaTabH, String insomnioH, String madreH, BigDecimal muerteHH,
			BigDecimal muertosSonH, BigDecimal numHH, String otroH, String padreH, String paradipsia, BigDecimal sonH,
			String tabacoH, BigDecimal vivoHH, BigDecimal vivoSonH) throws Exception {

		Historia h = findHistoriaById(cedulaEmp);
		if (h != null)
			throw new Exception("La historia ya esta registrada");
		h = new Historia();
		Empleado e = findEmpleadoById(cedulaEmp);

		if (e != null)
			h.setCedulaEmp(e.getCedulaEmp());
		h.setAlcoholH(alcoholH);
		h.setAlimentacionH(alimentacionH);
		h.setCausasHH(causasHH);
		h.setCausasMH(causasMH);
		h.setCausasPH(causasPH);
		h.setCausasSonH(causasSonH);
		h.setDipsiaH(dipsiaH);
		h.setDrogasH(drogasH);
		h.setFallecidoMH(fallecidoMH);
		h.setFallecidoPH(fallecidoPH);
		h.setFrecuenciaAlcH(frecuenciaAlcH);
		h.setFrecuenciaDroH(frecuenciaDroH);
		h.setFrecuenciaInsomH(frecuenciaInsomH);
		h.setFrecuenciaParadipsia(frecuenciaParadipsia);
		h.setFrecuenciaTabH(frecuenciaTabH);
		h.setInsomnioH(insomnioH);
		h.setMadreH(madreH);
		h.setMuerteHH(muerteHH);
		h.setMuertosSonH(muertosSonH);
		h.setNumHH(numHH);
		h.setOtroH(otroH);
		h.setPadreH(padreH);
		h.setParadipsia(paradipsia);
		h.setSonH(vivoSonH);
		h.setTabacoH(tabacoH);
		h.setVivoHH(vivoHH);
		h.setVivoSonH(vivoSonH);
		em.persist(h);
	}

	public void deleteHistoria(String cedulaEmp) throws Exception {
		Historia h = findHistoriaById(cedulaEmp);
		if (h != null) {
			em.remove(h);
		} else {
			throw new Exception("No existe la Historia " + cedulaEmp);
		}

	}

	public void UpdateHisyoria(String cedulaEmp, String alcoholH, String alimentacionH, String causasHH,
			String causasMH, String causasPH, String causasSonH, String dipsiaH, String drogasH, String fallecidoMH,
			String fallecidoPH, String frecuenciaAlcH, String frecuenciaDroH, String frecuenciaInsomH,
			String frecuenciaParadipsia, String frecuenciaTabH, String insomnioH, String madreH, BigDecimal muerteHH,
			BigDecimal muertosSonH, BigDecimal numHH, String otroH, String padreH, String paradipsia, BigDecimal sonH,
			String tabacoH, BigDecimal vivoHH, BigDecimal vivoSonH) throws Exception {
		Historia h = findHistoriaById(cedulaEmp);

		if (h == null)
			throw new Exception("No existe la Historia especificado");
		Empleado e = findEmpleadoById(cedulaEmp);
		if (e != null)
			h.setCedulaEmp(e.getCedulaEmp());
		h.setAlcoholH(alcoholH);
		h.setAlimentacionH(alimentacionH);
		h.setCausasHH(causasHH);
		h.setCausasMH(causasMH);
		h.setCausasPH(causasPH);
		h.setCausasSonH(causasSonH);
		h.setDipsiaH(dipsiaH);
		h.setDrogasH(drogasH);
		h.setFallecidoMH(fallecidoMH);
		h.setFallecidoPH(fallecidoPH);
		h.setFrecuenciaAlcH(frecuenciaAlcH);
		h.setFrecuenciaDroH(frecuenciaDroH);
		h.setFrecuenciaInsomH(frecuenciaInsomH);
		h.setFrecuenciaParadipsia(frecuenciaParadipsia);
		h.setFrecuenciaTabH(frecuenciaTabH);
		h.setInsomnioH(insomnioH);
		h.setMadreH(madreH);
		h.setMuerteHH(muerteHH);
		h.setMuertosSonH(muertosSonH);
		h.setNumHH(numHH);
		h.setOtroH(otroH);
		h.setPadreH(padreH);
		h.setParadipsia(paradipsia);
		h.setSonH(vivoSonH);
		h.setTabacoH(tabacoH);
		h.setVivoHH(vivoHH);
		h.setVivoSonH(vivoSonH);
		em.merge(e);
	}

}
