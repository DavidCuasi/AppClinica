package hospital.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the area_trabajo database table.
 * 
 */
@Entity
@Table(name="area_trabajo")
@NamedQuery(name="AreaTrabajo.findAll", query="SELECT a FROM AreaTrabajo a")
public class AreaTrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AREA_TRABAJO_IDAREA_GENERATOR", sequenceName="SEQ_AREA_TRABAJO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AREA_TRABAJO_IDAREA_GENERATOR")
	@Column(name="id_area", unique=true, nullable=false, precision=15)
	private long idArea;

	@Column(name="descripcion_area", nullable=false, length=2147483647)
	private String descripcionArea;

	@Column(name="estado_area", nullable=false, length=50)
	private String estadoArea;

	@Column(name="gerencia_area", nullable=false, length=200)
	private String gerenciaArea;

	@Column(name="nombre_area", nullable=false, length=150)
	private String nombreArea;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="areaTrabajo")
	private List<Empleado> empleados;

	public AreaTrabajo() {
	}

	public long getIdArea() {
		return this.idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getDescripcionArea() {
		return this.descripcionArea;
	}

	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}

	public String getEstadoArea() {
		return this.estadoArea;
	}

	public void setEstadoArea(String estadoArea) {
		this.estadoArea = estadoArea;
	}

	public String getGerenciaArea() {
		return this.gerenciaArea;
	}

	public void setGerenciaArea(String gerenciaArea) {
		this.gerenciaArea = gerenciaArea;
	}

	public String getNombreArea() {
		return this.nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setAreaTrabajo(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setAreaTrabajo(null);

		return empleado;
	}

}