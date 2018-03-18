package at.htl.schicker.extinguisher.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Building.findAll", query = "select b from Building b"),
        @NamedQuery(name = "Building.findById", query = "select b from Building b where b.id = :ID")
})
public class Building implements Serializable {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String zipcode;
    String street;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "building")
    List<Extinguisher> extinguisherList;

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
