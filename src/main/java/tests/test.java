/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entity.Order;
import facade.FacadeORAJPA;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author stein
 */
public class test {

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        FacadeORAJPA facade = FacadeORAJPA.getCustomerFacade(emf);
        
        facade.createItemType("Item1", "aaaa", 5);
        facade.createItemType("Item2", "bbbb", 8);
        
        System.out.println(facade.getItemType(1).getName());

        facade.createCustomer("Customer1", "Email1");
        System.out.println("Customer" + facade.getCustomer(1).getName());
       
    }
}
