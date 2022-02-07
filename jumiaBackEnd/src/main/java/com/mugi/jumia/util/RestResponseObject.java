package com.mugi.jumia.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author @Samuel Mugi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResponseObject<T> {

    private String message;
    private T payload;
    private boolean requestStatus;

    public RestResponseObject(boolean requestStatus, String message) {
        this.message = message;
        this.requestStatus = requestStatus;
    }
}
