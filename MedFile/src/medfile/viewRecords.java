/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medfile;

import Classes.DBconnect;
import Classes.Doctor;
import Classes.Patient;
import Classes.Record;
import Classes.SearchModule;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class viewRecords extends javax.swing.JFrame {

    DefaultTableModel model = null;
    Patient patient;
    Record selectedRecord;
    SearchModule search = new SearchModule();
    DBconnect dbo = new DBconnect();

    /**
     * Creates new form viewRecords
     */
    public viewRecords() throws SQLException {
        initComponents();
        model = (DefaultTableModel) ResultTab.getModel();
        ArrayList<Doctor> docs = search.searchAllDocs();
        if (docs != null) {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (int i = 0; i < docs.size(); i++) {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        patientID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        retrieve = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultTab = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        listDoctor = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel1.setText("View/Edit Records");

        jLabel2.setText("Patient ID");

        jLabel3.setText("Doctor");

        retrieve.setText("Retrieve");
        retrieve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveActionPerformed(evt);
            }
        });

        ResultTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Doctor", "Location", "Reason For Visit", "Doctor Diagnosis", "Weight", "Height", "Blood Pressure", "Cholesterol", "Doctor Note", "Lab Note"
            }
        ));
        ResultTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResultTabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ResultTab);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addComponent(patientID, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(retrieve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(146, 146, 146)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(listDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(patientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(retrieve)
                    .addComponent(listDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void retrieveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrieveActionPerformed

        model.setRowCount(0);

        ArrayList<Record> records;
        try {
            patient = new Patient();
            patient.setPatientID(Integer.parseInt(patientID.getText()));
            records = patient.getAllRecords();

            String[] tmpString = listDoctor.getSelectedItem().toString().split(":");
            Doctor doctor = new Doctor(Integer.parseInt(tmpString[1]));

            doctor = dbo.selectDoctor(doctor);

            if (!(records.isEmpty())) {
                for (int i = 0; i < records.size(); i++) {
                    Record tmp = records.get(i);

                    model.addRow(new Object[]{
                        tmp.getServiceDate().toString(),
                        tmp.getDoctor().getFName() + " " + tmp.getDoctor().getLName(),
                        tmp.getDoctor().getAddress(),
                        tmp.getReason(),
                        tmp.getDiagnoses(),
                        tmp.getWeight(),
                        tmp.getHeight(),
                        tmp.getBloodPress(),
                        tmp.getCholest(),
                        tmp.getDocNotes(),
                        tmp.getLabNotes()
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(viewRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_retrieveActionPerformed

    private void ResultTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResultTabMouseClicked
        int row = ResultTab.getSelectedRow();
        try {
            this.selectedRecord = patient.getAllRecords().get(row);
            this.selectedRecord.setPatient(this.patient);
        } catch (SQLException ex) {
            Logger.getLogger(viewRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ResultTabMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        UpdateRecords update;
        try {
            update = new UpdateRecords(this.selectedRecord);
            update.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(viewRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editActionPerformed

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
            java.util.logging.Logger.getLogger(viewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new viewRecords().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(viewRecords.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ResultTab;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox listDoctor;
    private javax.swing.JTextField patientID;
    private javax.swing.JButton retrieve;
    // End of variables declaration//GEN-END:variables
}
