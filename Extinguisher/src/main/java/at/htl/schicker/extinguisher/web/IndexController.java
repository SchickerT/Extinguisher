package at.htl.schicker.extinguisher.web;

import at.htl.schicker.extinguisher.buisness.BuildingDao;
import at.htl.schicker.extinguisher.buisness.CustomerDao;
import at.htl.schicker.extinguisher.buisness.ExtinguisherDao;
import at.htl.schicker.extinguisher.buisness.MaintenanceDao;
import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Customer;
import at.htl.schicker.extinguisher.entity.Extinguisher;
import at.htl.schicker.extinguisher.entity.Maintenance;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Named
public class IndexController implements Serializable
{

    public Building getCurrBuilding() {
        return currBuilding;
    }

    public void setCurrBuilding(Building currBuilding) {
        this.currBuilding = currBuilding;
    }


    List<String> parts=new LinkedList<String>();

    Building currBuilding;
    Customer currCustomer;
    @Inject
    BuildingDao buildingDao;
    @Inject
    CustomerDao customerDao;
    @Inject
    ExtinguisherDao extinguisherDao;
    @Inject
    MaintenanceDao maintenanceDao;

    Building selectedBuilding;
    Long selectedBuildingId;
    Customer selectedCustomer;
    Long selectedCustomerId;
    Extinguisher selectedExtinguisher;
    Long selectedExtinguisherId;

    public IndexController()
    {
        this.currBuilding = new Building();

        parts.add("YES");
        parts.add("NO");
        parts.add("MAYBE");
    }
    public List<String> getParts() {
        return parts;
    }

    public List<Building> getAllBuildings(){
    return  buildingDao.getAll();
}
    public List<Extinguisher> getAllExtinguishers(){
        return  extinguisherDao.getAll();
    }
    public List<Customer> getAllCustomers(){
        List<Customer> test =  customerDao.getAll();
        return  test;
    }
    public List<Building> getAllBuildingsByCustomer() {
        if(selectedCustomer != null) {
            List<Building> test = buildingDao.getByCustomer(selectedCustomer.getId());
            return test;
        }

        List<Building> list = new LinkedList<>();
        return list;
    }
    public List<Extinguisher> getAllExtinguisherByBuilding() {
        if(selectedBuilding != null) {
            List<Extinguisher> test = extinguisherDao.getByBuilding(selectedBuilding.getId());
            return test;
        }

        List<Extinguisher> list = new LinkedList<>();
        return list;
    }
    public List<Maintenance> getAllMaintaincesByExtinguisher()
    {
        if(selectedExtinguisher != null) {
            List<Maintenance> test = maintenanceDao.getByExtinguisher(selectedExtinguisher.getId());
            return test;
        }

        List<Maintenance> list = new LinkedList<>();
        return list;
    }

    public Extinguisher getSelectedExtinguisher() {
        return selectedExtinguisher;
    }

    public void setSelectedExtinguisher(Extinguisher selectedExtinguisher) {
        this.selectedExtinguisher = selectedExtinguisher;
    }

    public void setSelectedExtinguisherId(Long selectedExtinguisherId) {
        this.selectedExtinguisherId = selectedExtinguisherId;
    }

    public Long getSelectedExtinguisherId() {
        return selectedExtinguisherId;
    }

    public void setBuilding()
    {
        currBuilding = buildingDao.save(currBuilding);
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public Building getSelectedBuilding() {return selectedBuilding;}

    public void setCustomer()
    {
        currCustomer = customerDao.save(currCustomer);
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Customer getSelectedCustomer() {return selectedCustomer;}


    //region RowSelect
    public void onRowSelectBuilding(SelectEvent e) {
        FacesMessage msg = new FacesMessage("Building Selected", ((Building) e.getObject()).getStreet());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedBuilding = (Building) e.getObject();
        selectedBuildingId = selectedBuilding.getId();
    }
    public void onRowSelectCustomer(SelectEvent e) {
        FacesMessage msg = new FacesMessage("Customer Selected", ((Customer) e.getObject()).getCompanyname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedCustomer = (Customer) e.getObject();
        selectedCustomerId = selectedCustomer.getId();
    }
    public void onRowSelectExtinguisher(SelectEvent e) {
        FacesMessage msg = new FacesMessage("Extinguisher Selected", ((Extinguisher) e.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedExtinguisher = (Extinguisher) e.getObject();
        selectedExtinguisherId = selectedExtinguisher.getId();
    }
    //endregion

    /*public void onRowSelect(SelectEvent e){
        FacesMessage msg = new FacesMessage("Event Selected", ((Event) e.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedEvent =(Event)e.getObject();
        selectedEventId = selectedEvent.getId();
        System.out.println("Event:" + selectedEvent.getTitle());
    }
    public void onRowUnselect(SelectEvent e){
        FacesMessage msg = new FacesMessage("Event Unselected", ((Event) e.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedEvent =new Event();
    }*/


}
