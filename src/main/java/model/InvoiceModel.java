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
public class InvoiceModel {

    private String invoice_number;
    private String purchase_order_number;
    private String invoice_datetime;
    private String invoice_total;
    private String invoice_discount;

}
