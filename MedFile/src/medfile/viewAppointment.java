/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medfile;

import Classes.Appointment;
import Classes.Doctor;
import Classes.Patient;
import Classes.SearchModule;
import Classes.User;
import Classes.DBconnect;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ashwinrameshkumar
 */
public class viewAppointment extends javax.swing.JFrame {
        DefaultTableModel model = null;
        String patient=null;
        String doctor=null;
        String time = null;
        String created=null;
        String creator=null;
        SearchModule search = new SearchModule();
        DBconnect dbo = new DBconnect();
        ResultSet rs;
    /**
     * Creates new form viewAppointment
     */
    public viewAppointment() throws SQLException {
        initComponents();
        model = (DefaultTableModel) ResultTab.getModel();
        ArrayList<Doctor> docs = search.searchAllDocs();
        if(docs != null) {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for(int i = 0; i < docs.size(); i++) {
                Doctor tmp = docs.get(i);
                dbo.selectDoctor(tmp);
                model.addElement(tmp.getLName() + " :" + tmp.getDoctorID());
            }
            listDoctor.setModel(model);
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

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        patientid = new javax.swing.JTextField();
        viewAppointment = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultTab = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        listDoctor = new javax.swing.JComboBox();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel1.setText("View Appointments");

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Patient ID");

        jLabel3.setText("Doctor ID");

        viewAppointment.setText("View Appointments");
        viewAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAppointmentActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ResultTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientID", "Doctor", "AppointmentTime", "Time Created", "CreatorID"
            }
        ));
        ResultTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResultTabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ResultTab);

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Update");

        listDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(listDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(patientid, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(patientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(listDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        patientid.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void viewAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAppointmentActionPerformed
    try {
        model.setRowCount(0);
        Appointment appt = new Appointment();
        if(!patientid.getText().equals("")) {
            Patient tmpP = new Patient();
            tmpP.setPatientID(Integer.parseInt(patientid.getText()));
            appt.setPatient(tmpP);
        }
        String[] tmpString = listDoctor.getSelectedItem().toString().split(":");
        Doctor tmpD = new Doctor(Integer.parseInt(tmpString[1]));
        try {
            appt.setDoctor(dbo.selectDoctor(tmpD));
        } catch (SQLException ex) {
            Logger.getLogger(createAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Appointment> results = search.searchAppt(appt);
        String patientid=null;
        String doctorid=null;
        String appointmenttime = null;
        String timecreated=null;
        String creatorid=null;
        int z=0;
        for (int i = 0; i < results.size(); i++) {
            Appointment tmp = results.get(i);
            patientid = Integer.toString(tmp.getPatient().getPatientID());
            doctorid = Integer.toString(tmp.getDoctor().getId());
            appointmenttime = tmp.getApptTime().toString();
            timecreated = tmp.getCreatedTime().toString();
            creatorid = Integer.toString(tmp.getCreator().getId());
            
            model.addRow(new Object[]{
                patientid,
                doctorid,
                appointmenttime,
                timecreated,
                creatorid
                    
            });
            
            
            ResultTab.setValueAt(patientid, z, 0);
            ResultTab.setValueAt(doctorid, z, 1);
            ResultTab.setValueAt(appointmenttime, z, 2);
            ResultTab.setValueAt(timecreated, z, 3);
            ResultTab.setValueAt(creatorid, z, 4);
//             z++;
            z++;
        }
    } catch (SQLException ex) {
        Logger.getLogger(viewAppointment.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_viewAppointmentActionPerformed

    private void ResultTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResultTabMouseClicked
    try {
        // TODO add your handling code here:
        int selectedRow = 0;
        selectedRow = ResultTab.convertRowIndexToModel(ResultTab.getSelectedRow());
        System.out.println("Selected Row:"+selectedRow);
        //
        
        if(rs.absolute(selectedRow+1)){
            patient = rs.getString("patientID");
            doctor = rs.getString("doctorID");
            time = rs.getString("appointmentTime");
            created = rs.getString("timecreated");
            creator= rs.getString("creatorID");
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(viewAppointment.class.getName()).log(Level.SEVERE, null, ex);
    }
        
                

    }//GEN-LAST:event_ResultTabMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void listDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listDoctorActionPerformed

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
            java.util.logging.Logger.getLogger(viewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new viewAppointment().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(viewAppointment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ResultTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox listDoctor;
    public static javax.swing.JTextField patientid;
    private javax.swing.JButton viewAppointment;
    // End of variables declaration//GEN-END:variables
}
