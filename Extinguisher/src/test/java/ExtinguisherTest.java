import at.htl.schicker.extinguisher.buisness.BuildingDao;
import at.htl.schicker.extinguisher.buisness.CustomerDao;
import at.htl.schicker.extinguisher.buisness.ExtinguisherDao;
import at.htl.schicker.extinguisher.buisness.MaintenanceDao;
import at.htl.schicker.extinguisher.entity.Building;
import at.htl.schicker.extinguisher.entity.Customer;
import at.htl.schicker.extinguisher.entity.Extinguisher;
import at.htl.schicker.extinguisher.entity.Maintenance;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Startup
public class ExtinguisherTest
{
    @Inject
    BuildingDao buildingDao;

    @Inject
    CustomerDao customerDao;

    @Inject
    ExtinguisherDao extinguisherDao;

    @Inject
    MaintenanceDao maintenanceDao;

    private static int expectedExtinguishers;
    private static int expectedMaintenances;
    private static int expectedCustomers;
    private static int expectedBuildings;




    @BeforeClass
    public static void init()
    {
        expectedBuildings = 2;
        expectedMaintenances = 3;
        expectedExtinguishers = 2;
        expectedCustomers = 2;
    }

    @Test
    public void t010TestDbInit()
    {
        //Building(String zipcode, String street)
        //Maintenance(String description, String solution, double costs,Extinguisher extinguisher)
        //Extinguisher(int capacity, Customer customer, Building building)
        //Customer(String companyname, Building building)
        //---------------------------------------------------------------------------
        Building b = new Building("4664","Lutmweg 44");
        Building b2 = new Building("4663","Zingring 3");
        buildingDao.save(b);
        buildingDao.save(b2);
        Customer c = new Customer("Test AG",b);
        Customer c2 = new Customer("Test OG",b2);
        customerDao.save(c);
        customerDao.save(c2);
        Extinguisher e = new Extinguisher(100,c,b);
        Extinguisher e2 = new Extinguisher(120,c2,b2);
        extinguisherDao.save(e);
        extinguisherDao.save(e2);
        Maintenance m = new Maintenance("Testrun1","none",1000,e);
        Maintenance m2 = new Maintenance("Testrun2","none",2000,e);
        Maintenance m3 = new Maintenance("Testrun3","none",230,e2);
        maintenanceDao.save(m);
        maintenanceDao.save(m2);
        maintenanceDao.save(m3);

        assertThat(buildingDao.getAll().size(),is(2));
        assertThat(customerDao.getAll().size(),is(2));
        assertThat(maintenanceDao.getAll().size(),is(2));
        assertThat(extinguisherDao.getAll().size(),is(2));

    }
}
