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
public class ItemModel {

    private String item_id;
    private String item_category_id;
    private String item_name;
    private String item_unit;
    private String item_unit_price;
    private String qty_onhand;

}
