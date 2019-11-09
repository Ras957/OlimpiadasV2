/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
import Vista.frames.area.ListAreaFrame;
import Vista.frames.commissioner.ListCommissionerFrame;
import Vista.frames.equipment.ListEquipmentFrame;
import Vista.frames.event.ListEventFrame;
import Vista.frames.headquarter.ListHeadquarterFrame;
import Vista.frames.multisportcenter.ListMultiSportCenterFrame;
import Vista.frames.sportcenter.ListSportCenterFrame;
import Vista.frames.sportcomplex.ListSportComplexFrame;
import javax.swing.JFrame;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MainMenu extends javax.swing.JFrame {
    
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Headquarters = new javax.swing.JButton();
        SportComplexes = new javax.swing.JButton();
        Events = new javax.swing.JButton();
        SportCenters = new javax.swing.JButton();
        MultiSportCenters = new javax.swing.JButton();
        Equipments = new javax.swing.JButton();
        Areas = new javax.swing.JButton();
        Commissioners = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de datos Olimpiadas");

        Headquarters.setText("Sedes");
        Headquarters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeadquartersActionPerformed(evt);
            }
        });

        SportComplexes.setText("Complejos");
        SportComplexes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SportComplexesActionPerformed(evt);
            }
        });

        Events.setText("Eventos");
        Events.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EventsActionPerformed(evt);
            }
        });

        SportCenters.setText("Unideportivos");
        SportCenters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SportCentersActionPerformed(evt);
            }
        });

        MultiSportCenters.setText("Polideportivos");
        MultiSportCenters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MultiSportCentersActionPerformed(evt);
            }
        });

        Equipments.setText("Materiales");
        Equipments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EquipmentsActionPerformed(evt);
            }
        });

        Areas.setText("Areas");
        Areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AreasActionPerformed(evt);
            }
        });

        Commissioners.setText("Comsarios");
        Commissioners.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommissionersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Headquarters, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SportComplexes, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SportCenters, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MultiSportCenters, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Events, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equipments, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Commissioners, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Areas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Headquarters, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SportComplexes, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SportCenters, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MultiSportCenters, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Events, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Equipments, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Commissioners, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Areas, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HeadquartersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeadquartersActionPerformed
        try{
            JFrame f = new ListHeadquarterFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }
    }//GEN-LAST:event_HeadquartersActionPerformed

    private void SportComplexesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SportComplexesActionPerformed
        try{
            JFrame f = new ListSportComplexFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }
    }//GEN-LAST:event_SportComplexesActionPerformed

    private void SportCentersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SportCentersActionPerformed
        try{
            JFrame f = new ListSportCenterFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }
    }//GEN-LAST:event_SportCentersActionPerformed

    private void MultiSportCentersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MultiSportCentersActionPerformed
        try{
            JFrame f = new ListMultiSportCenterFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }
    }//GEN-LAST:event_MultiSportCentersActionPerformed

    private void EventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EventsActionPerformed
        try{
            JFrame f = new ListEventFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }catch (DNIException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_EventsActionPerformed

    private void EquipmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EquipmentsActionPerformed
        try{
            JFrame f = new ListEquipmentFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }
    }//GEN-LAST:event_EquipmentsActionPerformed

    private void CommissionersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommissionersActionPerformed
        try{
            JFrame f = new ListCommissionerFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }catch (DNIException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_CommissionersActionPerformed

    private void AreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AreasActionPerformed
        try{
            JFrame f = new ListAreaFrame();
            f.setLocationRelativeTo(this);
            f.setVisible(true);
        }catch(DAOException ex){
            ex.getMessage();
        }
    }//GEN-LAST:event_AreasActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Areas;
    private javax.swing.JButton Commissioners;
    private javax.swing.JButton Equipments;
    private javax.swing.JButton Events;
    private javax.swing.JButton Headquarters;
    private javax.swing.JButton MultiSportCenters;
    private javax.swing.JButton SportCenters;
    private javax.swing.JButton SportComplexes;
    // End of variables declaration//GEN-END:variables
}

