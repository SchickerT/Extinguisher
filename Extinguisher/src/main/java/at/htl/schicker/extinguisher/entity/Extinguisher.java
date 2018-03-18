package at.htl.schicker.extinguisher.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Table
@Entity
@NamedQueries({
        @NamedQuery(name = "Extinguisher.findAll", query = "select e from Extinguisher e"),
        @NamedQuery(name = "Extinguisher.findById", query = "select e from Extinguisher e where e.id = :ID")
})
public class Extinguisher implements Serializable
{
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int capacity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "extinguisher")
    List<Maintenance> maintenanceList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Customer_ID")
    Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Building_ID")
    Building building;

    public Extinguisher() {
    }

    public Extinguisher(int capacity, Customer customer, Building building) {
        this.capacity = capacity;
        this.customer = customer;
        this.building = building;
        this.maintenanceList = new LinkedList<>();
    }
}
