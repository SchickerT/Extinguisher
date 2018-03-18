package at.htl.schicker.extinguisher.buisness;


import at.htl.schicker.extinguisher.entity.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.interceptor.ExcludeClassInterceptors;
import java.util.LinkedList;
import java.util.List;

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
    public void init() {

    }
}
