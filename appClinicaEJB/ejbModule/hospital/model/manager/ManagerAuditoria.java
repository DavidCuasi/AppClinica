package hospital.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hospital.model.entities.Auditoria;

@Stateless
@LocalBean
public class ManagerAuditoria {

	@PersistenceContext(unitName = "HeleyMedDS")
	private EntityManager em;
	
	public void registrarAccion(String usuario,String tipoMensaje,String mensaje) {
		Auditoria audit = new Auditoria();
		audit.setUsuario(usuario);
		audit.setTipoMensaje(tipoMensaje);
		audit.setMensaje(mensaje);
		audit.setFechaOperacion(new Date());
		
		em.persist(audit);
	}
	
	public List<Auditoria> findAll(){
		Query query = em.createQuery("select o from Auditoria o order by o.fechaOperacion desc");
		return query.getResultList();
	}
}
