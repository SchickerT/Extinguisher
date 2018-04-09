package at.htl.schicker.extinguisher.buisness;


import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Customer;
import at.htl.schicker.extinguisher.entity.Extinguisher;
import at.htl.schicker.extinguisher.entity.Maintenance;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitBean
{
    @Inject
    BuildingDao buildingDao;

    @Inject
    CustomerDao customerDao;

    @Inject
    ExtinguisherDao extinguisherDao;

    @Inject
    MaintenanceDao maintenanceDao;

    @PostConstruct
    public void init()
    {
        Building b = new Building("4664","Lutmweg 44");
        Building b2 = new Building("4663","Zingring 3");
        Building b3 = new Building("4664","Lummweg 3125");
        Building b4 = new Building("4663","Wilgring 33");
        buildingDao.save(b);
        buildingDao.save(b2);
        buildingDao.save(b3);
        buildingDao.save(b4);
        Customer c = new Customer("Test AG");
        Customer c2 = new Customer("Test OG");
        customerDao.save(c);
        customerDao.save(c2);
        c.addBuilding(b);
        c.addBuilding(b2);
        c2.addBuilding(b3);
        c2.addBuilding(b4);
        //int capacity, Customer customer, Building buildin
        Extinguisher e1;
        Extinguisher e2;
        Extinguisher e3;
        Extinguisher e4;
        Maintenance m1;
        Maintenance m2;
        Maintenance m3;
        Maintenance m4;

        for(int i = 1; i< 11;i++)
        {
            e1 = new Extinguisher(100+(i*20),c,b);
            e2 = new Extinguisher(100+(i*20),c2,b2);
            e3 = new Extinguisher(100+(i*20),c,b3);
            e4 = new Extinguisher(100+(i*20),c2,b4);
            extinguisherDao.save(e1);
            extinguisherDao.save(e2);
            extinguisherDao.save(e3);
            extinguisherDao.save(e4);
            maintenanceDao.save(new Maintenance("Testrun" +i,"none",1000.00,e1));
            maintenanceDao.save(new Maintenance("Testrun" +(i+1),"none",400.00*(i+1),e1));
            maintenanceDao.save(new Maintenance("Testrun" +(i+2),"none",2300.00*(i+2),e1));
            maintenanceDao.save(new Maintenance("Testrun" +(i+3),"none",4500.00*(i+3),e1));
            maintenanceDao.save(new Maintenance("Testrun" +i*i,"none",10.00,e2));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i+1),"none",670.00*(i+1),e2));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i+2),"none",2400.00*(i+2),e2));
            maintenanceDao.save(new Maintenance("Testrun" +(i*1+3),"none",3300.00*(i+3),e2));
            maintenanceDao.save(new Maintenance("Testrun" +i*i*i,"none",990.00,e3));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i*i+1),"none",80.00*(i+1),e3));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i*i+2),"none",270.00*(i+2),e3));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i*i+3),"none",40.00*(i+3),e3));
            maintenanceDao.save(new Maintenance("Testrun" +i*i*i*i,"none",56.00,e4));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i*i*i+1),"none",920.00*(i+1),e4));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i*i*i+2),"none",1.00*(i+2),e4));
            maintenanceDao.save(new Maintenance("Testrun" +(i*i*i*i+3),"none",9.00*(i+3),e4));
        }
    }
}
