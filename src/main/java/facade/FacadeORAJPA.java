/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import entity.ItemType;
import entity.Order;
import intface.InterfanceORAJPA;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author stein
 */
public class FacadeORAJPA implements InterfanceORAJPA {

    
    private static FacadeORAJPA instance;
    private static EntityManagerFactory emf;

    private FacadeORAJPA() {
    }

    public static FacadeORAJPA getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeORAJPA();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Customer createCustomer(String name, String email) {
            Customer customer = new Customer(name, email);
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(customer);
                em.getTransaction().commit();
                return customer;
            } finally {
                em.close();
            }
    }

    @Override
    public Customer getCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<Customer> customers = em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
            em.getTransaction().commit();
            return customers;
        } finally {
            em.close();
        }
    }

    @Override
    public ItemType createItemType(String name, String description, int price) {
            ItemType item = new ItemType(name, description, price);
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(item);
                em.getTransaction().commit();
                return item;
            } finally {
                em.close();
            }
    }

    @Override
    public ItemType getItemType(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ItemType item = em.find(ItemType.class, id);
            em.getTransaction().commit();
            return item;
        } finally {
            em.close();
        }
    }

    @Override
    public Customer createOrder(Customer customer, Order order) {
            customer.addOrder(order);
            order.setCustomer(customer);
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(customer);
                em.getTransaction().commit();
                return customer;
            } finally {
                em.close();
            }
    }

}
