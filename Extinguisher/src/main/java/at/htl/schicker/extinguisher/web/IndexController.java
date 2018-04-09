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

    Maintenance selectedMaintenance;
    String msg = "select a Extinguisher first";
    Long selectedMaintenanceId;
    Building selectedBuilding;
    Long selectedBuildingId;
    Customer selectedCustomer;
    Long selectedCustomerId;
    Extinguisher selectedExtinguisher;
    Long selectedExtinguisherId;

    public IndexController()
    {
        this.currBuilding = new Building();
        this.selectedMaintenance = null;

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

    public Maintenance getSelectedMaintenance() {
        return selectedMaintenance;
    }

    public void setSelectedMaintenance(Maintenance selectedMaintenance) {
        this.selectedMaintenance = selectedMaintenance;
    }

    public Long getSelectedMaintenanceId() {
        return selectedMaintenanceId;
    }

    public void setSelectedMaintenanceId(Long selectedMaintenanceId) {
        this.selectedMaintenanceId = selectedMaintenanceId;
    }
    public void setMaintenance(){
        if(selectedMaintenance != null && selectedExtinguisher != null) {
            maintenanceDao.update(selectedMaintenance);
        }
        else
        {
            msg = "Es muss ein Extinguisher und  eine Maintenance ausgewählt sein !";
        }

    }
    public void saveMaintenance(){
        if(selectedMaintenance != null && selectedExtinguisher != null) {
            if(selectedMaintenance.getId()==null) {
                maintenanceDao.save(new Maintenance(selectedMaintenance.getDescription(),selectedMaintenance.getSolution(),selectedMaintenance.getCosts(),selectedExtinguisher));
            }
            else
            {
                msg = "Es darf keine Maintenance ausgewählt sein";
            }
        }

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
    public void onRowSelectMaintenance(SelectEvent e) {
        FacesMessage msg = new FacesMessage("Extinguisher Selected", ((Maintenance) e.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedMaintenance = (Maintenance) e.getObject();
        selectedMaintenanceId = selectedMaintenance.getId();
    }
    public void onRowUnselect(SelectEvent e){
        FacesMessage msg = new FacesMessage("Maintenance Unselected", ((Maintenance) e.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        selectedMaintenance = null;
    }
}
