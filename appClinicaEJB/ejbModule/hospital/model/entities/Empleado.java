package hospital.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(name="empleado")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_emp", unique=true, nullable=false, length=13)
	private String cedulaEmp;

	@Column(name="activo_emp", nullable=false, length=50)
	private String activoEmp;

	@Column(name="cargo_us", nullable=false, length=150)
	private String cargoUs;

	@Column(name="pasword_us", nullable=false, length=26)
	private String paswordUs;

	//bi-directional many-to-one association to AreaTrabajo
	@ManyToOne
	@JoinColumn(name="id_area", nullable=false)
	private AreaTrabajo areaTrabajo;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="cedula_emp", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	public Empleado() {
	}

	public String getCedulaEmp() {
		return this.cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public String getActivoEmp() {
		return this.activoEmp;
	}

	public void setActivoEmp(String activoEmp) {
		this.activoEmp = activoEmp;
	}

	public String getCargoUs() {
		return this.cargoUs;
	}

	public void setCargoUs(String cargoUs) {
		this.cargoUs = cargoUs;
	}

	public String getPaswordUs() {
		return this.paswordUs;
	}

	public void setPaswordUs(String paswordUs) {
		this.paswordUs = paswordUs;
	}

	public AreaTrabajo getAreaTrabajo() {
		return this.areaTrabajo;
	}

	public void setAreaTrabajo(AreaTrabajo areaTrabajo) {
		this.areaTrabajo = areaTrabajo;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}