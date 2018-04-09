package at.htl.schicker.extinguisher.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Building.findAll", query = "select b from Building b"),
        @NamedQuery(name = "Building.findById", query = "select b from Building b where b.id = :ID"),
        @NamedQuery(name = "Building.findByCustomer", query = "select b from Building b where b.customer.id = :ID")
})
public class Building implements Serializable {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String zipcode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Customer_ID")
    Customer customer;

    String street;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "building")
    List<Extinguisher> extinguisherList;

    public Long getId() {
        return id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Extinguisher> getExtinguisherList() {
        return extinguisherList;
    }

    public void addExtinguisher(Extinguisher e){ this.extinguisherList.add(e);
    e.building = this;}

    public void setExtinguisherList(List<Extinguisher> extinguisherList) {
        this.extinguisherList = extinguisherList;
    }

    public Building() {
    }

    public Building(String zipcode, String street) {
        this.zipcode = zipcode;
        this.street = street;
        this.extinguisherList = new LinkedList<>();
    }
}
