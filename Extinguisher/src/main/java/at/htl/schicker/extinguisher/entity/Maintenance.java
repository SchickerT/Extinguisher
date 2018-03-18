package at.htl.schicker.extinguisher.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
@NamedQueries({
        @NamedQuery(name = "Maintenance.findAll", query = "select m from Maintenance m"),
        @NamedQuery(name = "Maintenance.findById", query = "select m from Maintenance m where m.id = :ID")
})
public class Maintenance implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    String solution;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Extinguisher_ID")
    Extinguisher extinguisher;

    double costs;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public Maintenance() {
    }

    public Maintenance(String description, String solution, double costs,Extinguisher extinguisher) {
        this.description = description;
        this.solution = solution;
        this.costs = costs;
        this.extinguisher = extinguisher;
    }
}
