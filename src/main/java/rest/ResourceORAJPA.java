/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Customer;
import facade.FacadeORAJPA;
import intface.InterfanceORAJPA;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author stein
 */
@Path("ORAJPA")
public class ResourceORAJPA {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final InterfanceORAJPA FACADE = FacadeORAJPA.getCustomerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String data() {
        EntityManager em = EMF.createEntityManager();
        return "{\"msg\": \"IT WORKED\"}";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCustomers()  {
        List<Customer> customers = FACADE.getAllCustomers();
        return GSON.toJson(customers);
    }
}