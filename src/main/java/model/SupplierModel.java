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
public class SupplierModel {

    private String supplier_id;
    private String supplier_category_id;
    private String supplier_title;
    private String supplier_name;
    private String supplier_address;
    private String supplier_contact_number;

}
