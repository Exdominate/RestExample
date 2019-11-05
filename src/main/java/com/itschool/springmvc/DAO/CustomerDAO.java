package com.itschool.springmvc.DAO;

import com.itschool.springmvc.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO {

    List<Customer> customers = new ArrayList<Customer>();

    public List<Customer> list() {
        return customers;
    }

    public Customer get(int id) {
        Customer customer = null;
        for (Customer tempCustomer : customers) {
            if (id == tempCustomer.getId()) {
                customer = tempCustomer;
                break;
            }
        }
        return customer;
    }

    public boolean create(Customer customer) {
        customers.add(customer);
        return true;
    }

    public boolean update(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customer.getId())) {
                customers.remove(i);
            }
        }
        return create(customer);
    }

    public Long delete(long id) {
        for (int i=0;i< customers.size();i++) {
            if (customers.get(i).getId()==id) {
                customers.remove(i);
                return id;
            }
        }
        return null;
    }

    {
        customers.add(new Customer(0, "Bob", "Bob@bob.by"));
        customers.add(new Customer(1, "Bill", "De@bill.ru"));
        customers.add(new Customer(2, "Idu", "idu@pesh.com"));
    }

}
