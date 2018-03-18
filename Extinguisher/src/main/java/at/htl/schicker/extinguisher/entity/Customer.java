package at.htl.schicker.extinguisher.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
})
public class Customer implements Serializable
{
    @Id
    String companyname;

    Building building;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    List<Extinguisher> extinguisherList;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
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

    public Customer() {
    }

    public Customer(String companyname, Building building) {
        this.companyname = companyname;
        this.building = building;
        this.extinguisherList = new LinkedList<>();
    }
}
