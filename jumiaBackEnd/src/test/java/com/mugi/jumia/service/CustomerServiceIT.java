/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugi.jumia.service;

import com.mugi.jumia.entity.Country;
import com.mugi.jumia.entity.Customer;
import com.mugi.jumia.repos.CustomerRepository;
import com.mugi.jumia.util.Category;
import com.mugi.jumia.util.RestResponse;
import com.mugi.jumia.util.RestResponseObject;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Samuel Mugi
 */
@SpringBootTest
public class CustomerServiceIT {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerServiceIT() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of Categorize method, of class CustomerService.
     */
    @Test
    public void testCategorize() {
        System.out.println("Categorize");
        String category = Category.country.toString();
        RestResponse result = customerService.Categorize(category);
        RestResponseObject<List<Customer>> customers = result.getBody();
        Assertions.assertThat(customers.getPayload().isEmpty()).isFalse();
    }

    /**
     * Test of filterByState method, of class CustomerService.
     */
    @Test
    public void testFilterByState() {
        System.out.println("filterByState");
        String state = "Nairobi";
        RestResponse result = customerService.filterByState(state);
        RestResponseObject<List<Customer>> customers = result.getBody();
        System.out.println("customers.getPayload().isEmpty()==" + customers.getPayload());
        Assertions.assertThat(customers.getPayload()).isNull();

    }

    /**
     * Test of filterByCountry method, of class CustomerService.
     */
    @Test
    public void testFilterByCountry() {
        System.out.println("filterByCountry");
        String country = "258";
        RestResponse result = customerService.filterByCountry(country);
        RestResponseObject<List<Customer>> customers = result.getBody();
        Assertions.assertThat(customers.getPayload().isEmpty()).isFalse();
    }

    /**
     * Test of listAll method, of class CustomerService.
     */
    @Test
    public void testListAll() {
        System.out.println("listAll");
        Pageable pageable = PageRequest.of(0, 2);
        RestResponse result = customerService.listAll(pageable);
        RestResponseObject<Page<Customer>> customers = result.getBody();
        Assertions.assertThat(customers.getPayload().isEmpty()).isFalse();
    }

    /**
     * Test of listAllCountries method, of class CustomerService.
     */
    @Test
    public void testListAllCountries() {
        System.out.println("listAllCountries");
        RestResponse result = customerService.listAllCountries();
        RestResponseObject<List<Country>> countrys = result.getBody();
        Assertions.assertThat(countrys.getPayload().isEmpty()).isFalse();

    }

}
