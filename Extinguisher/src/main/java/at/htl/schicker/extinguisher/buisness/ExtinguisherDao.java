package at.htl.schicker.extinguisher.buisness;

import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Extinguisher;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ExtinguisherDao
{
    @PersistenceContext
    EntityManager em;

    public Extinguisher save(Extinguisher p) {
        em.persist(p);
        return p;
    }

    public List<Extinguisher> getAll() {
        return em.createNamedQuery("Extinguisher.findAll").getResultList();
    }

    public Extinguisher getById(Long id) {
        return (Extinguisher) em.createNamedQuery("Extinguisher.findById").setParameter("ID", id).getSingleResult();
    }

    public List<Extinguisher> getByBuilding(Long id)
    {
        return (List<Extinguisher>) em.createNamedQuery("Extinguisher.findByBuilding").setParameter("ID", id).getResultList();
    }
}
