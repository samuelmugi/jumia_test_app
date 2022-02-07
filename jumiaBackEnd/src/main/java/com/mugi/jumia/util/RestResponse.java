package com.mugi.jumia.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author @Samuel Mugi
 */
public class RestResponse extends ResponseEntity<RestResponseObject> {

    public RestResponse(RestResponseObject body, HttpStatus statusCode) {
        super(body, statusCode);
    }

}
