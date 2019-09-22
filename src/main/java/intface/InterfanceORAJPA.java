/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intface;

import entity.Customer;
import entity.ItemType;
import entity.Order;
import java.util.List;

/**
 *
 * @author stein
 */
 public interface InterfanceORAJPA {

    public Customer createCustomer(String name, String email);

    public Customer getCustomer(int id);

    public List<Customer> getAllCustomers();

    public ItemType createItemType(String name, String description, int price);

    public ItemType getItemType(int id);

    public Customer createOrder(Customer customer, Order order);

}
