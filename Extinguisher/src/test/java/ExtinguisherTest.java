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
        /*

        */

        assertThat(buildingDao.getAll().size(),is(2));
        assertThat(customerDao.getAll().size(),is(2));
        assertThat(maintenanceDao.getAll().size(),is(2));
        assertThat(extinguisherDao.getAll().size(),is(2));

    }
}
