/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugi.jumia.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Samuel Mugi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    String name;
    int code;
}
