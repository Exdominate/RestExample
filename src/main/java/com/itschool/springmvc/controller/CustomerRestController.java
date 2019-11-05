package com.itschool.springmvc.controller;

import com.itschool.springmvc.DAO.CustomerDAO;
import com.itschool.springmvc.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerDAO customerDao;

    @GetMapping("/customers")
    public List getCustomers() {
        return customerDao.list();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable long id) {
        Customer customer = customerDao.get((int) id);
        if (customer == null) {
            return new ResponseEntity("No customer found for ID" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        Long returnId = customerDao.delete(id);
        if (returnId == null) {
            return new ResponseEntity("No customer found for ID" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Deleted id " + id, HttpStatus.OK);
    }

    @PostMapping("/customers/add")
    public ResponseEntity createCustomer(
            @RequestBody Customer customer) {
        boolean isSuccess = customerDao.create(customer);
        if (isSuccess == false) {
            return new ResponseEntity("Can't insert customer with ID " + customer.getId(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Inserted "+customer, HttpStatus.OK);
    }

    @PostMapping("/customers/update")
    public ResponseEntity updateCustomer(
            @RequestBody Customer customer) {
        boolean isSuccess = customerDao.update(customer);
        if (isSuccess == false) {
            return new ResponseEntity("Can't update customer with ID " + customer.getId(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Update customer " + customer, HttpStatus.OK);
    }
}
