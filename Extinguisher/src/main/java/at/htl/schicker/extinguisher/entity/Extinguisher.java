package at.htl.schicker.extinguisher.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Extinguisher.findAll", query = "select e from Extinguisher e"),
        @NamedQuery(name = "Extinguisher.findById", query = "select e from Extinguisher e where e.id = :ID"),
        @NamedQuery(name = "Extinguisher.findByBuilding", query = "select e from Extinguisher e where e.building.id = :ID")
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

    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }
    public void addMaintenance(Maintenance m){ this.maintenanceList.add(m);
        m.extinguisher = this;

    }

    public Customer getCustomer() {
        return customer;
    }

    public Building getBuilding() {
        return building;
    }

    public Extinguisher() {
    }

    public Extinguisher(int capacity, Customer customer, Building building) {
        this.capacity = capacity;
        this.customer = customer;
        this.building = building;
        building.addExtinguisher(this);
        this.maintenanceList = new LinkedList<>();
    }
}
