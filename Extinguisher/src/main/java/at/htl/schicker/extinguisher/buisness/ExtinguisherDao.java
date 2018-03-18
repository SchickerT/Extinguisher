package at.htl.schicker.extinguisher.buisness;

import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Extinguisher;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
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
}
