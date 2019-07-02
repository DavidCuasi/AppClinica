package hospital.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name="persona")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_emp", unique=true, nullable=false, length=13)
	private String cedulaEmp;

	@Column(name="ciudadnaci_emp", length=150)
	private String ciudadnaciEmp;

	@Column(name="direccio_emp", nullable=false, length=150)
	private String direccioEmp;

	@Column(name="email_emp", nullable=false, length=100)
	private String emailEmp;

	@Column(name="estado_emp", nullable=false, length=30)
	private String estadoEmp;

	@Temporal(TemporalType.DATE)
	@Column(name="nacifecha_emp", nullable=false)
	private Date nacifechaEmp;

	@Column(name="nacionalidad_emp", nullable=false, length=50)
	private String nacionalidadEmp;

	@Column(name="nombres_emp", nullable=false, length=400)
	private String nombresEmp;

	@Column(name="telefono_emp", nullable=false, length=15)
	private String telefonoEmp;

	@Column(name="tipo_emp", nullable=false, length=3)
	private String tipoEmp;

	//bi-directional many-to-one association to CabeceraConsulta
	@OneToMany(mappedBy="persona")
	private List<CabeceraConsulta> cabeceraConsultas;

	//bi-directional one-to-one association to Empleado
	@OneToOne(mappedBy="persona")
	private Empleado empleado;

	//bi-directional one-to-one association to Historia
	@OneToOne(mappedBy="persona")
	private Historia historia;

	public Persona() {
	}

	public String getCedulaEmp() {
		return this.cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public String getCiudadnaciEmp() {
		return this.ciudadnaciEmp;
	}

	public void setCiudadnaciEmp(String ciudadnaciEmp) {
		this.ciudadnaciEmp = ciudadnaciEmp;
	}

	public String getDireccioEmp() {
		return this.direccioEmp;
	}

	public void setDireccioEmp(String direccioEmp) {
		this.direccioEmp = direccioEmp;
	}

	public String getEmailEmp() {
		return this.emailEmp;
	}

	public void setEmailEmp(String emailEmp) {
		this.emailEmp = emailEmp;
	}

	public String getEstadoEmp() {
		return this.estadoEmp;
	}

	public void setEstadoEmp(String estadoEmp) {
		this.estadoEmp = estadoEmp;
	}

	public Date getNacifechaEmp() {
		return this.nacifechaEmp;
	}

	public void setNacifechaEmp(Date nacifechaEmp) {
		this.nacifechaEmp = nacifechaEmp;
	}

	public String getNacionalidadEmp() {
		return this.nacionalidadEmp;
	}

	public void setNacionalidadEmp(String nacionalidadEmp) {
		this.nacionalidadEmp = nacionalidadEmp;
	}

	public String getNombresEmp() {
		return this.nombresEmp;
	}

	public void setNombresEmp(String nombresEmp) {
		this.nombresEmp = nombresEmp;
	}

	public String getTelefonoEmp() {
		return this.telefonoEmp;
	}

	public void setTelefonoEmp(String telefonoEmp) {
		this.telefonoEmp = telefonoEmp;
	}

	public String getTipoEmp() {
		return this.tipoEmp;
	}

	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
	}

	public List<CabeceraConsulta> getCabeceraConsultas() {
		return this.cabeceraConsultas;
	}

	public void setCabeceraConsultas(List<CabeceraConsulta> cabeceraConsultas) {
		this.cabeceraConsultas = cabeceraConsultas;
	}

	public CabeceraConsulta addCabeceraConsulta(CabeceraConsulta cabeceraConsulta) {
		getCabeceraConsultas().add(cabeceraConsulta);
		cabeceraConsulta.setPersona(this);

		return cabeceraConsulta;
	}

	public CabeceraConsulta removeCabeceraConsulta(CabeceraConsulta cabeceraConsulta) {
		getCabeceraConsultas().remove(cabeceraConsulta);
		cabeceraConsulta.setPersona(null);

		return cabeceraConsulta;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Historia getHistoria() {
		return this.historia;
	}

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

}