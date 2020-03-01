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
public class CustomerOrderModel {

    private String customer_order_number;
    private String customer_id;
    private String customer_order_datetime;
    private String customer_order_sub_total;
    private String customer_order_discount;
    private String customer_order_net_total;
}
