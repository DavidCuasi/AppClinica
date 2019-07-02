package hospital.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_consulta database table.
 * 
 */
@Entity
@Table(name="detalle_consulta")
@NamedQuery(name="DetalleConsulta.findAll", query="SELECT d FROM DetalleConsulta d")
public class DetalleConsulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_det", unique=true, nullable=false, length=15)
	private String idDet;

	@Column(name="cantdes_det", precision=3)
	private BigDecimal cantdesDet;

	@Column(name="cantidad_det", precision=2)
	private BigDecimal cantidadDet;

	@Column(name="pvp_det", precision=4, scale=2)
	private BigDecimal pvpDet;

	//bi-directional many-to-one association to CabeceraConsulta
	@ManyToOne
	@JoinColumn(name="id_con")
	private CabeceraConsulta cabeceraConsulta;

	public DetalleConsulta() {
	}

	public String getIdDet() {
		return this.idDet;
	}

	public void setIdDet(String idDet) {
		this.idDet = idDet;
	}

	public BigDecimal getCantdesDet() {
		return this.cantdesDet;
	}

	public void setCantdesDet(BigDecimal cantdesDet) {
		this.cantdesDet = cantdesDet;
	}

	public BigDecimal getCantidadDet() {
		return this.cantidadDet;
	}

	public void setCantidadDet(BigDecimal cantidadDet) {
		this.cantidadDet = cantidadDet;
	}

	public BigDecimal getPvpDet() {
		return this.pvpDet;
	}

	public void setPvpDet(BigDecimal pvpDet) {
		this.pvpDet = pvpDet;
	}

	public CabeceraConsulta getCabeceraConsulta() {
		return this.cabeceraConsulta;
	}

	public void setCabeceraConsulta(CabeceraConsulta cabeceraConsulta) {
		this.cabeceraConsulta = cabeceraConsulta;
	}

}