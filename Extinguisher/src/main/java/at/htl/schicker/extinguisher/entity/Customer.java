package at.htl.schicker.extinguisher.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
})
public class Customer implements Serializable
{
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String companyname;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    List<Building> buildings;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    List<Extinguisher> extinguisherList;

    public List<Building> getAllBuilding() {
        return buildings;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
        building.customer = this;
    }

    public List<Extinguisher> getExtinguisherList() {
        return extinguisherList;
    }

    public void setExtinguisherList(List<Extinguisher> extinguisherList) {
        this.extinguisherList = extinguisherList;
    }

    public String getCompanyname() {
        return companyname;
    }

    public Long getId() {
        return id;
    }

    public Customer() {
    }

    public Customer(String companyname) {
        this.buildings = new LinkedList<>();
        this.companyname = companyname;
        this.extinguisherList = new LinkedList<>();
    }
}
