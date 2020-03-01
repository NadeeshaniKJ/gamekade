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
public class CustomerOrderItemModel {

    private String customer_order_item_id;
    private String customer_order_number;
    private String item_id;
    private String customer_order_item_unit_price;
    private String customer_order_item_qty;

}
