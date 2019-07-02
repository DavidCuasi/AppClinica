package hospital.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cabecera_consulta database table.
 * 
 */
@Entity
@Table(name="cabecera_consulta")
@NamedQuery(name="CabeceraConsulta.findAll", query="SELECT c FROM CabeceraConsulta c")
public class CabeceraConsulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CABECERA_CONSULTA_IDCON_GENERATOR", sequenceName="SEQ_CABECERA_CONSULTA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CABECERA_CONSULTA_IDCON_GENERATOR")
	@Column(name="id_con", unique=true, nullable=false, precision=15)
	private long idCon;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_con")
	private Date fechaCon;

	@Column(name="indicaciones_con", length=2147483647)
	private String indicacionesCon;

	@Column(name="motivo_con", length=2147483647)
	private String motivoCon;

	@Column(name="observaciones_con", length=2147483647)
	private String observacionesCon;

	@Column(name="peso_con", precision=5, scale=2)
	private BigDecimal pesoCon;

	@Column(name="presion_con", precision=3)
	private BigDecimal presionCon;

	@Column(name="sintomas_con", length=2147483647)
	private String sintomasCon;

	@Column(name="temperatura_con", precision=2)
	private BigDecimal temperaturaCon;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="cedula_emp")
	private Persona persona;

	//bi-directional many-to-one association to DetalleConsulta
	@OneToMany(mappedBy="cabeceraConsulta")
	private List<DetalleConsulta> detalleConsultas;

	public CabeceraConsulta() {
	}

	public long getIdCon() {
		return this.idCon;
	}

	public void setIdCon(long idCon) {
		this.idCon = idCon;
	}

	public Date getFechaCon() {
		return this.fechaCon;
	}

	public void setFechaCon(Date fechaCon) {
		this.fechaCon = fechaCon;
	}

	public String getIndicacionesCon() {
		return this.indicacionesCon;
	}

	public void setIndicacionesCon(String indicacionesCon) {
		this.indicacionesCon = indicacionesCon;
	}

	public String getMotivoCon() {
		return this.motivoCon;
	}

	public void setMotivoCon(String motivoCon) {
		this.motivoCon = motivoCon;
	}

	public String getObservacionesCon() {
		return this.observacionesCon;
	}

	public void setObservacionesCon(String observacionesCon) {
		this.observacionesCon = observacionesCon;
	}

	public BigDecimal getPesoCon() {
		return this.pesoCon;
	}

	public void setPesoCon(BigDecimal pesoCon) {
		this.pesoCon = pesoCon;
	}

	public BigDecimal getPresionCon() {
		return this.presionCon;
	}

	public void setPresionCon(BigDecimal presionCon) {
		this.presionCon = presionCon;
	}

	public String getSintomasCon() {
		return this.sintomasCon;
	}

	public void setSintomasCon(String sintomasCon) {
		this.sintomasCon = sintomasCon;
	}

	public BigDecimal getTemperaturaCon() {
		return this.temperaturaCon;
	}

	public void setTemperaturaCon(BigDecimal temperaturaCon) {
		this.temperaturaCon = temperaturaCon;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<DetalleConsulta> getDetalleConsultas() {
		return this.detalleConsultas;
	}

	public void setDetalleConsultas(List<DetalleConsulta> detalleConsultas) {
		this.detalleConsultas = detalleConsultas;
	}

	public DetalleConsulta addDetalleConsulta(DetalleConsulta detalleConsulta) {
		getDetalleConsultas().add(detalleConsulta);
		detalleConsulta.setCabeceraConsulta(this);

		return detalleConsulta;
	}

	public DetalleConsulta removeDetalleConsulta(DetalleConsulta detalleConsulta) {
		getDetalleConsultas().remove(detalleConsulta);
		detalleConsulta.setCabeceraConsulta(null);

		return detalleConsulta;
	}

}