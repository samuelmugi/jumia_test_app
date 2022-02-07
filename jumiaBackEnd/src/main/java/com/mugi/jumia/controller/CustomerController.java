package com.mugi.jumia.controller;

import com.mugi.jumia.service.CustomerService;
import com.mugi.jumia.util.RestResponse;
import com.mugi.jumia.util.RestResponseObject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author @Samuel Mugi
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/Categorize")
    public RestResponse Categorize(@Valid @RequestParam(value = "category", required = true) String category) {
        if (!category.trim().isEmpty()) {
            return customerService.Categorize(category);
        } else {
            return new RestResponse(new RestResponseObject(false, "country parameter missing"), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/filterByCountry")
    public RestResponse filterByCountry(@Valid @RequestParam(value = "country", required = true) String country) {
        if (!country.trim().isEmpty()) {
            return customerService.filterByCountry(country);
        } else {
            return new RestResponse(new RestResponseObject(false, "country parameter missing"), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/filterByState")
    public RestResponse filterByState(@Valid @RequestParam(value = "state", required = true) String state) {
        if (!state.trim().isEmpty()) {
            return customerService.filterByState(state);
        } else {
            return new RestResponse(new RestResponseObject(false, "state parameter missing"), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/listAll")
    public RestResponse listAll(Pageable pageable) {
        return customerService.listAll(pageable);
    }

    @GetMapping("/listAllCountries")
    public RestResponse listAllCountries() {
        return customerService.listAllCountries();
    }

}
