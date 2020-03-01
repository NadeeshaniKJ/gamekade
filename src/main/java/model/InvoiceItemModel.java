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
public class InvoiceItemModel {

    private String invoice_item_id;
    private String invoice_number;
    private String item_id;
    private String invoice_item_Unit_Price;
    private String invoice_item_Qty;
}
