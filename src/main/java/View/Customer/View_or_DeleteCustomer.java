/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Customer;

import controller.dataUtilities.CustomerUtilities;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.CustomerModel;

/**
 *
 * @author nadee
 */
public class View_or_DeleteCustomer extends javax.swing.JFrame {

    /**
     * Creates new form DeleteCustomer
     */
    public View_or_DeleteCustomer() {
        initComponents();
    }

    public View_or_DeleteCustomer(String customer_id) {
        initComponents();
        jLbl_cusIDValue.setText(customer_id);
        try {
            CustomerModel cus = CustomerUtilities.getCustomer(customer_id);
            jLbl_cusTitleValue.setText(cus.getCustomer_title());
            jLbl_cusNameValue.setText(cus.getCustomer_name());
            jLbl_cusNICValue.setText(cus.getCustomer_nic());
            jLbl_cusContactNumValue.setText(cus.getCustomer_contact());
            jLbl_cusEmailValue.setText(cus.getCustomer_email());
            jLbl_cusAddressValue.setText(cus.getCustomer_address());
            jLbl_cusCityValue.setText(cus.getCustomer_city());
            jLbl_cusProvinceValue.setText(cus.getCustomer_province());
            jLbl_cusPostalCodeValue.setText(cus.getCustomer_postalcode());
        } catch (Exception ex) {
            Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLbl_addNewCus = new javax.swing.JLabel();
        jLbl_cusID = new javax.swing.JLabel();
        jLbl_cusName = new javax.swing.JLabel();
        jLbl_cusNIC = new javax.swing.JLabel();
        jLbl_cusContactNum = new javax.swing.JLabel();
        jLbl_cusEmail = new javax.swing.JLabel();
        jLbl_cusAddress = new javax.swing.JLabel();
        jLbl_cusCity = new javax.swing.JLabel();
        jLbl_cusProvince = new javax.swing.JLabel();
        jLbl_cusPostalCode = new javax.swing.JLabel();
        btn_deleteCustomer = new javax.swing.JButton();
        btn_cancle = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jLbl_cusTitle = new javax.swing.JLabel();
        jLbl_cusIDValue = new javax.swing.JLabel();
        jLbl_cusNameValue = new javax.swing.JLabel();
        jLbl_cusNICValue = new javax.swing.JLabel();
        jLbl_cusPostalCodeValue = new javax.swing.JLabel();
        jLbl_cusEmailValue = new javax.swing.JLabel();
        jLbl_cusCityValue = new javax.swing.JLabel();
        jLbl_cusContactNumValue = new javax.swing.JLabel();
        jLbl_cusAddressValue = new javax.swing.JLabel();
        jLbl_cusTitleValue = new javax.swing.JLabel();
        jLbl_cusProvinceValue = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(660, 460));

        jLbl_addNewCus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLbl_addNewCus.setForeground(new java.awt.Color(51, 102, 255));
        jLbl_addNewCus.setText("CUSTOMER DETAILS");

        jLbl_cusID.setText("CUSTOMER ID :");

        jLbl_cusName.setText("NAME :");

        jLbl_cusNIC.setText("NIC :");

        jLbl_cusContactNum.setText("CONTACT NUMBER :");

        jLbl_cusEmail.setText("E- MAIL :");

        jLbl_cusAddress.setText("ADDRESS :");

        jLbl_cusCity.setText("CITY :");

        jLbl_cusProvince.setText("PROVINCE :");

        jLbl_cusPostalCode.setText("POSTAL CODE :");

        btn_deleteCustomer.setText("DELETE CUSTOMER");
        btn_deleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteCustomerActionPerformed(evt);
            }
        });

        btn_cancle.setText("CANCEL");
        btn_cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancleActionPerformed(evt);
            }
        });

        btn_update.setText("UPDATE CUSTOMER");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLbl_cusTitle.setText("TITLE :");

        jLbl_cusIDValue.setText("ID");

        jButton1.setText("ADD ORDER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_deleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusNIC)
                            .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbl_cusProvinceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbl_cusPostalCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLbl_cusCityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbl_cusAddressValue, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusNICValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusIDValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusContactNumValue, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbl_cusTitleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLbl_addNewCus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusIDValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusTitleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusNICValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusNIC, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusContactNumValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusEmailValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_cusAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusAddressValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbl_cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbl_cusCityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLbl_cusProvinceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusPostalCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLbl_cusProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbl_cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_deleteCustomer)
                    .addComponent(btn_cancle)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteCustomerActionPerformed

        int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            String CusID = jLbl_cusIDValue.getText();
        try {
            CustomerUtilities.deleteCustomer(CusID);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        


    }//GEN-LAST:event_btn_deleteCustomerActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        String cusID = jLbl_cusIDValue.getText();
        UpdateCustomer update = new UpdateCustomer(cusID);
        update.setVisible(true);
        update.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancleActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_cancleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_or_DeleteCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_or_DeleteCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancle;
    private javax.swing.JButton btn_deleteCustomer;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLbl_addNewCus;
    private javax.swing.JLabel jLbl_cusAddress;
    private javax.swing.JLabel jLbl_cusAddressValue;
    private javax.swing.JLabel jLbl_cusCity;
    private javax.swing.JLabel jLbl_cusCityValue;
    private javax.swing.JLabel jLbl_cusContactNum;
    private javax.swing.JLabel jLbl_cusContactNumValue;
    private javax.swing.JLabel jLbl_cusEmail;
    private javax.swing.JLabel jLbl_cusEmailValue;
    private javax.swing.JLabel jLbl_cusID;
    private javax.swing.JLabel jLbl_cusIDValue;
    private javax.swing.JLabel jLbl_cusNIC;
    private javax.swing.JLabel jLbl_cusNICValue;
    private javax.swing.JLabel jLbl_cusName;
    private javax.swing.JLabel jLbl_cusNameValue;
    private javax.swing.JLabel jLbl_cusPostalCode;
    private javax.swing.JLabel jLbl_cusPostalCodeValue;
    private javax.swing.JLabel jLbl_cusProvince;
    private javax.swing.JLabel jLbl_cusProvinceValue;
    private javax.swing.JLabel jLbl_cusTitle;
    private javax.swing.JLabel jLbl_cusTitleValue;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
