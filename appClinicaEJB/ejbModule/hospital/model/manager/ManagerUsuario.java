package hospital.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;

/**
 * 
 * @author David Cuasapas
 *
 */

@Stateless
@LocalBean
public class ManagerUsuario {

	@PersistenceContext(unitName = "HeleyMedDS")
	private EntityManager em;

	public ManagerUsuario() {
	}

	public boolean comprobarUsuario(String cedulaEmp, String paswordUs) throws Exception {
		Empleado u = em.find(Empleado.class, cedulaEmp);
		if (u == null)
			throw new Exception("No existe el usuario " + cedulaEmp);
		if (u.getActivoEmp().equals("INACTIVO"))
			throw new Exception("El usuario no está activo.");
		if (u.getPaswordUs().equals(paswordUs))
			return true;
		throw new Exception("Contraseña no válida.");
	}

	public boolean validadorDeCedula(String cedula) {
		boolean cedulaCorrecta = false;
		try {

			if (cedula.length() == 10) {
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepcion ocurrio en el proceso de validadcion");
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			System.out.println("La Cédula ingresada es Incorrecta");
		}
		return cedulaCorrecta;
	}

	public Persona findPersonaById(String cedulaEmp) {
		Persona p = em.find(Persona.class, cedulaEmp);
		return p;
	}

	public Empleado findEmpleadoById(String cedulaEmp) {
		Empleado e = em.find(Empleado.class, cedulaEmp);
		return e;
	}

	public List<Empleado> findEmpleadoByPersona(String cedulaEmp) {
		String JPQL = "SELECT o FROM Empleado o WHERE o.persona.cedulaEmp='" + cedulaEmp + "'";
		Query q;
		List<Empleado> lista;
		q = em.createQuery(JPQL);
		lista = q.getResultList();
		return lista;
	}

	public List<Persona> findAllPersonas() {
		Query q;
		List<Persona> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Persona o ";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public void insertPersona(String cedulaEmp, String ciudadnaciEmp, String direccioEmp, String emailEmp,
			String estadoEmp, Date nacifechaEmp, String nacionalidadEmp, String nombresEmp, String telefonoEmp,
			String tipoEmp) throws Exception {
		Persona p = findPersonaById(cedulaEmp);

		if (validadorDeCedula(cedulaEmp) == true) {
			if (p != null)
				throw new Exception("Ya existe un usuario " + cedulaEmp);
			p = new Persona();
			p.setCedulaEmp(cedulaEmp);
			p.setNombresEmp(nombresEmp);
			p.setNacifechaEmp(nacifechaEmp);
			p.setNacionalidadEmp(nacionalidadEmp);
			p.setDireccioEmp(direccioEmp);
			p.setTelefonoEmp(telefonoEmp);
			p.setEmailEmp(emailEmp);
			p.setEstadoEmp(estadoEmp);
			p.setTipoEmp(tipoEmp);
			p.setCiudadnaciEmp(ciudadnaciEmp);
			em.persist(p);
		} else {
			throw new Exception(cedulaEmp + "No es una cedula Valida");
		}

	}

	public void deletePersona(String cedulaEmp) throws Exception {

		Persona p = findPersonaById(cedulaEmp);
		if (p == null) {
			throw new Exception("No existe el Usuario " + cedulaEmp);
		} else if (findEmpleadoByPersona(p.getCedulaEmp()).size() == 0) {
			em.remove(p);
		} else {
			throw new Exception(
					"La Persona " + cedulaEmp + "Esta asignada como empleado por lo cual no es posible eliminar");
		}

	}

	public void UpdatePersona(String cedulaEmp, String ciudadnaciEmp, String direccioEmp, String emailEmp,
			String estadoEmp, Date nacifechaEmp, String nacionalidadEmp, String nombresEmp, String telefonoEmp,
			String tipoEmp) throws Exception {
		Persona p = findPersonaById(cedulaEmp);
		if (p == null)
			throw new Exception("No existe el usuario especificado");
		p.setCedulaEmp(cedulaEmp);
		p.setNombresEmp(nombresEmp);
		p.setNacifechaEmp(nacifechaEmp);
		p.setNacionalidadEmp(nacionalidadEmp);
		p.setDireccioEmp(direccioEmp);
		p.setTelefonoEmp(telefonoEmp);
		p.setEmailEmp(emailEmp);
		p.setEstadoEmp(estadoEmp);
		p.setTipoEmp(tipoEmp);
		p.setCiudadnaciEmp(ciudadnaciEmp);
		em.merge(p);
	}
}
