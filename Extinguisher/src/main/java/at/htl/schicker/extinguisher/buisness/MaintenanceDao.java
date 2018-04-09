package at.htl.schicker.extinguisher.buisness;

import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Maintenance;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MaintenanceDao
{
    @PersistenceContext
    EntityManager em;

    public Maintenance save(Maintenance p) {
        em.persist(p);
        return p;
    }

    public List<Maintenance> getAll() {
        return em.createNamedQuery("Maintenance.findAll").getResultList();
    }

    public Maintenance getById(Long id) {
        return (Maintenance) em.createNamedQuery("Maintenance.findById").setParameter("ID", id).getSingleResult();
    }

    public List<Maintenance> getByExtinguisher(Long id)
    {
        return (List<Maintenance>) em.createNamedQuery("Maintenance.findByExtinguisher").setParameter("ID", id).getResultList();
    }
}
