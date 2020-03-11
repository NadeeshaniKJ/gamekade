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
public class UserModel {

    private String user_ID;
    private String user_level;
    private String user_name;
    private String password;
    private String name;
    private String Q1;
    private String Q2;
    private String Q1_ans;
    private String Q2_ans;
}
