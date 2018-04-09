package at.htl.schicker.extinguisher.buisness;

import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Customer;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BuildingDao
{
    @PersistenceContext
    EntityManager em;

    public Building save(Building p)
    {
        em.persist(p);
        return p;
    }

    public List<Building> getAll() {
        return em.createNamedQuery("Building.findAll").getResultList();
    }

    public Building getById(Long id) {
        return (Building) em.createNamedQuery("Building.findById").setParameter("ID", id).getSingleResult();
    }
    public List<Building> getByCustomer(Long id) {
        return (List<Building>) em.createNamedQuery("Building.findByCustomer").setParameter("ID", id).getResultList();
    }
}
