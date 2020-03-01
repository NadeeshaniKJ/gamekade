/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author nadee
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    
private String customer_id;
private String customer_title;
private String customer_name;
private String customer_nic;
private String customer_bday;
private String customer_contact;
private String customer_email;
private String customer_address;
private String customer_city;
private String customer_province;
private String customer_postalcode;

   
}
