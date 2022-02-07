package com.mugi.jumia.service;

import com.mugi.jumia.entity.Country;
import com.mugi.jumia.entity.Customer;
import com.mugi.jumia.entity.CustomerCategory;
import com.mugi.jumia.repos.CustomerRepository;
import com.mugi.jumia.util.Category;
import com.mugi.jumia.util.RestResponse;
import com.mugi.jumia.util.RestResponseObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author @Samuel Mugi
 */
@Service
public class CustomerService {

    private final List< Country> countrys = Arrays.asList(
            new Country("Cameroon", 237),
            new Country("Ethiopia", 251),
            new Country("Morocco", 212),
            new Country("Mozambique", 258),
            new Country("Uganda", 256)
    );
    @Autowired
    CustomerRepository customerRepository;

    public RestResponse Categorize(String category) {
        RestResponseObject resp = new RestResponseObject();
        resp.setMessage("");
        resp.setRequestStatus(false);
        resp.setPayload(null);

        try {// country, state, code, number
            if (Category.country.toString().equalsIgnoreCase(category)) {
                resp.setPayload(categorizeByCountryName());
            } else if (Category.state.toString().equalsIgnoreCase(category)) {
                resp.setPayload(categorizeByState());
            } else if (Category.code.toString().equalsIgnoreCase(category)) {
                resp.setPayload(categorizeByCode());
            } else if (Category.number.toString().equalsIgnoreCase(category)) {
                resp.setPayload(categorizeByPhoneNumber());
            }
            resp.setRequestStatus(true);
            resp.setMessage("Success");
            return new RestResponse(resp, HttpStatus.OK);
        } catch (Exception er) {
            System.err.println(" ERROR:- " + er.getLocalizedMessage());
            resp.setMessage("Error categorizing by " + category);
            resp.setRequestStatus(false);
            return new RestResponse(resp, HttpStatus.BAD_REQUEST);
        }
    }

    private List<CustomerCategory> categorizeByCountryName() {
        //The country list is already ordered by Alphabetical order
        List<CustomerCategory> countryCustomers = new ArrayList<>();
        countrys.stream()
                .forEach(country -> {
                    List<Customer> countryList = customerRepository.filterByCountry("(" + country.getCode());
                    countryCustomers.add(new CustomerCategory(country.getName(), countryList));
                });
        return countryCustomers;
    }

    private List<CustomerCategory> categorizeByState() {
        //The country list is already ordered by Alphabetical order
        List<CustomerCategory> countryCustomers = new ArrayList<>();
//        countrys.stream()
//                .forEach(country -> {
//                    List<Customer> countryList = customerRepository.filterByCountry("(" + country.getCode().replaceAll("[^0-9]", ""));
//                    countryCustomers.add(new CustomerCategory(country.getName(), countryList));
//                });
        return countryCustomers;
    }

    private List<CustomerCategory> categorizeByCode() {
        //Order the country list by code
        List< Country> sortedCountriesListByCode = countrys.stream()
                .sorted(Comparator.comparingInt(Country::getCode))
                .collect(Collectors.toList());

        List<CustomerCategory> countryCustomers = new ArrayList<>();
        sortedCountriesListByCode.stream()
                .forEach(country -> {
                    List<Customer> countryList = customerRepository.filterByCountry("(" + country.getCode());
                    countryCustomers.add(new CustomerCategory(country.getCode() + "-" + country.getName(), countryList));
                });
        return countryCustomers;
    }

    private List<CustomerCategory> categorizeByPhoneNumber() {
        List<Customer> findByOrderByPhoneAsc = customerRepository.findByOrderByPhoneAsc();

        List<CustomerCategory> countryCustomers = new ArrayList<>();
        countryCustomers.add(new CustomerCategory("Sorted By Phone No", findByOrderByPhoneAsc));
        return countryCustomers;
    }

    public RestResponse filterByState(String state) {
        RestResponseObject resp = new RestResponseObject();
        resp.setMessage("");
        resp.setRequestStatus(false);
        resp.setPayload(null);

        try {
            resp.setRequestStatus(true);
            resp.setMessage("Success");
            return new RestResponse(resp, HttpStatus.OK);
        } catch (Exception er) {
            System.err.println(" ERROR:- " + er.getLocalizedMessage());
            resp.setMessage("Error filtering by state ");
            resp.setRequestStatus(false);
            return new RestResponse(resp, HttpStatus.BAD_REQUEST);
        }
    }

    public RestResponse filterByCountry(String country) {
        RestResponseObject resp = new RestResponseObject();
        resp.setMessage("");
        resp.setRequestStatus(false);
        resp.setPayload(null);

        try {
            resp.setPayload(customerRepository.filterByCountry("(" + country));
            resp.setRequestStatus(true);
            resp.setMessage("Success");
            return new RestResponse(resp, HttpStatus.OK);
        } catch (Exception er) {
            System.err.println(" ERROR:- " + er.getLocalizedMessage());
            resp.setMessage("Error filtering by country  ");
            resp.setRequestStatus(false);
            return new RestResponse(resp, HttpStatus.BAD_REQUEST);
        }
    }

    public RestResponse listAll(Pageable pageable) {
        RestResponseObject resp = new RestResponseObject();
        resp.setMessage("");
        resp.setRequestStatus(false);
        resp.setPayload(null);

        try {
            resp.setPayload(customerRepository.findAll(pageable));
            resp.setRequestStatus(true);
            resp.setMessage("Success");
            return new RestResponse(resp, HttpStatus.OK);
        } catch (Exception er) {
            System.err.println(" ERROR:- " + er.getLocalizedMessage());
            resp.setMessage("Error fetching list of all  ");
            resp.setRequestStatus(false);
            return new RestResponse(resp, HttpStatus.BAD_REQUEST);
        }
    }

    public RestResponse listAllCountries() {
        RestResponseObject resp = new RestResponseObject();
        resp.setMessage("");
        resp.setRequestStatus(false);
        resp.setPayload(null);

        try {
            resp.setPayload(countrys);
            resp.setRequestStatus(true);
            resp.setMessage("Success");
            return new RestResponse(resp, HttpStatus.OK);
        } catch (Exception er) {
            System.err.println(" ERROR:- " + er.getLocalizedMessage());
            resp.setMessage("Error fetching list of all  ");
            resp.setRequestStatus(false);
            return new RestResponse(resp, HttpStatus.BAD_REQUEST);
        }
    }

}
