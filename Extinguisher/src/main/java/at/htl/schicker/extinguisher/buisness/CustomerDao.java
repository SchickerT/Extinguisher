package at.htl.schicker.extinguisher.buisness;

import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Customer;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerDao
{
    @PersistenceContext
    EntityManager em;

    public Customer save(Customer p) {
        em.persist(p);
        return p;
    }

    public List<Customer> getAll() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }

    public Customer getById(Long id) {
        return (Customer) em.createNamedQuery("Customer.findById").setParameter("ID", id).getSingleResult();
    }

}
