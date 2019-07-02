package hospital.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the historias database table.
 * 
 */
@Entity
@Table(name="historias")
@NamedQuery(name="Historia.findAll", query="SELECT h FROM Historia h")
public class Historia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_emp", unique=true, nullable=false, length=13)
	private String cedulaEmp;

	@Column(name="alcohol_h", length=2)
	private String alcoholH;

	@Column(name="alimentacion_h", length=2147483647)
	private String alimentacionH;

	@Column(name="causas_h_h", length=2147483647)
	private String causasHH;

	@Column(name="causas_m_h", length=2147483647)
	private String causasMH;

	@Column(name="causas_p_h", length=2147483647)
	private String causasPH;

	@Column(name="causas_son_h", length=2147483647)
	private String causasSonH;

	@Column(name="dipsia_h", length=2)
	private String dipsiaH;

	@Column(name="drogas_h", length=2)
	private String drogasH;

	@Column(name="fallecido_m_h", length=2)
	private String fallecidoMH;

	@Column(name="fallecido_p_h", length=2)
	private String fallecidoPH;

	@Column(name="frecuencia_alc_h", length=2147483647)
	private String frecuenciaAlcH;

	@Column(name="frecuencia_dro_h", length=2147483647)
	private String frecuenciaDroH;

	@Column(name="frecuencia_insom_h", length=2147483647)
	private String frecuenciaInsomH;

	@Column(name="frecuencia_paradipsia", length=2147483647)
	private String frecuenciaParadipsia;

	@Column(name="frecuencia_tab_h", length=2147483647)
	private String frecuenciaTabH;

	@Column(name="insomnio_h", length=2)
	private String insomnioH;

	@Column(name="madre_h", length=200)
	private String madreH;

	@Column(name="muerte_h_h", precision=2)
	private BigDecimal muerteHH;

	@Column(name="muertos_son_h", precision=2)
	private BigDecimal muertosSonH;

	@Column(name="num_h_h", precision=2)
	private BigDecimal numHH;

	@Column(name="otro_h", length=2147483647)
	private String otroH;

	@Column(name="padre_h", length=200)
	private String padreH;

	@Column(length=2)
	private String paradipsia;

	@Column(name="son_h", precision=2)
	private BigDecimal sonH;

	@Column(name="tabaco_h", length=2)
	private String tabacoH;

	@Column(name="vivo_h_h", precision=2)
	private BigDecimal vivoHH;

	@Column(name="vivo_son_h", precision=2)
	private BigDecimal vivoSonH;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="cedula_emp", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	public Historia() {
	}

	public String getCedulaEmp() {
		return this.cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public String getAlcoholH() {
		return this.alcoholH;
	}

	public void setAlcoholH(String alcoholH) {
		this.alcoholH = alcoholH;
	}

	public String getAlimentacionH() {
		return this.alimentacionH;
	}

	public void setAlimentacionH(String alimentacionH) {
		this.alimentacionH = alimentacionH;
	}

	public String getCausasHH() {
		return this.causasHH;
	}

	public void setCausasHH(String causasHH) {
		this.causasHH = causasHH;
	}

	public String getCausasMH() {
		return this.causasMH;
	}

	public void setCausasMH(String causasMH) {
		this.causasMH = causasMH;
	}

	public String getCausasPH() {
		return this.causasPH;
	}

	public void setCausasPH(String causasPH) {
		this.causasPH = causasPH;
	}

	public String getCausasSonH() {
		return this.causasSonH;
	}

	public void setCausasSonH(String causasSonH) {
		this.causasSonH = causasSonH;
	}

	public String getDipsiaH() {
		return this.dipsiaH;
	}

	public void setDipsiaH(String dipsiaH) {
		this.dipsiaH = dipsiaH;
	}

	public String getDrogasH() {
		return this.drogasH;
	}

	public void setDrogasH(String drogasH) {
		this.drogasH = drogasH;
	}

	public String getFallecidoMH() {
		return this.fallecidoMH;
	}

	public void setFallecidoMH(String fallecidoMH) {
		this.fallecidoMH = fallecidoMH;
	}

	public String getFallecidoPH() {
		return this.fallecidoPH;
	}

	public void setFallecidoPH(String fallecidoPH) {
		this.fallecidoPH = fallecidoPH;
	}

	public String getFrecuenciaAlcH() {
		return this.frecuenciaAlcH;
	}

	public void setFrecuenciaAlcH(String frecuenciaAlcH) {
		this.frecuenciaAlcH = frecuenciaAlcH;
	}

	public String getFrecuenciaDroH() {
		return this.frecuenciaDroH;
	}

	public void setFrecuenciaDroH(String frecuenciaDroH) {
		this.frecuenciaDroH = frecuenciaDroH;
	}

	public String getFrecuenciaInsomH() {
		return this.frecuenciaInsomH;
	}

	public void setFrecuenciaInsomH(String frecuenciaInsomH) {
		this.frecuenciaInsomH = frecuenciaInsomH;
	}

	public String getFrecuenciaParadipsia() {
		return this.frecuenciaParadipsia;
	}

	public void setFrecuenciaParadipsia(String frecuenciaParadipsia) {
		this.frecuenciaParadipsia = frecuenciaParadipsia;
	}

	public String getFrecuenciaTabH() {
		return this.frecuenciaTabH;
	}

	public void setFrecuenciaTabH(String frecuenciaTabH) {
		this.frecuenciaTabH = frecuenciaTabH;
	}

	public String getInsomnioH() {
		return this.insomnioH;
	}

	public void setInsomnioH(String insomnioH) {
		this.insomnioH = insomnioH;
	}

	public String getMadreH() {
		return this.madreH;
	}

	public void setMadreH(String madreH) {
		this.madreH = madreH;
	}

	public BigDecimal getMuerteHH() {
		return this.muerteHH;
	}

	public void setMuerteHH(BigDecimal muerteHH) {
		this.muerteHH = muerteHH;
	}

	public BigDecimal getMuertosSonH() {
		return this.muertosSonH;
	}

	public void setMuertosSonH(BigDecimal muertosSonH) {
		this.muertosSonH = muertosSonH;
	}

	public BigDecimal getNumHH() {
		return this.numHH;
	}

	public void setNumHH(BigDecimal numHH) {
		this.numHH = numHH;
	}

	public String getOtroH() {
		return this.otroH;
	}

	public void setOtroH(String otroH) {
		this.otroH = otroH;
	}

	public String getPadreH() {
		return this.padreH;
	}

	public void setPadreH(String padreH) {
		this.padreH = padreH;
	}

	public String getParadipsia() {
		return this.paradipsia;
	}

	public void setParadipsia(String paradipsia) {
		this.paradipsia = paradipsia;
	}

	public BigDecimal getSonH() {
		return this.sonH;
	}

	public void setSonH(BigDecimal sonH) {
		this.sonH = sonH;
	}

	public String getTabacoH() {
		return this.tabacoH;
	}

	public void setTabacoH(String tabacoH) {
		this.tabacoH = tabacoH;
	}

	public BigDecimal getVivoHH() {
		return this.vivoHH;
	}

	public void setVivoHH(BigDecimal vivoHH) {
		this.vivoHH = vivoHH;
	}

	public BigDecimal getVivoSonH() {
		return this.vivoSonH;
	}

	public void setVivoSonH(BigDecimal vivoSonH) {
		this.vivoSonH = vivoSonH;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}