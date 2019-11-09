/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.event;

import Modelo.DAO.AreaDAO;
import Modelo.DAO.CommissionerDAO;
import Modelo.DAO.EquipmentDAO;
import Modelo.DAO.SportComplexDAO;
import Modelo.Event;
import Modelo.SportComplex;
import Modelo.auxiliary.DAOException;
import Modelo.auxiliary.DNIException;
import Vista.frames.sportcenter.SportComplexComboModel;
import Vista.frames.sportcenter.SportComplexComboView;
import java.util.Date;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EventDetailsPanel extends javax.swing.JPanel {

    private Event event;
    
    private boolean edit;
    
    private SportComplexComboModel comboComplex;
    private CommissionerComboModel comboCommissioner;
    private EquipmentComboModel comboEquip;
    private AreaComboModel comboArea;
    
    public EventDetailsPanel() {
        initComponents();
        comboComplex = new SportComplexComboModel(null);
        comboCommissioner = new CommissionerComboModel(null);
        comboEquip = new EquipmentComboModel(null);
        comboArea = new AreaComboModel(null);
    }
    
    public EventDetailsPanel(SportComplexDAO complex, CommissionerDAO comm,
           EquipmentDAO equip, AreaDAO area ) throws DAOException, DNIException {
        initComponents();
        comboComplex = new SportComplexComboModel(complex);
        comboComplex.update();
        ComboBoxSportComplex.setModel(comboComplex);
        comboCommissioner = new CommissionerComboModel(comm);
        comboCommissioner.update();
        ComboBoxCommissioner.setModel(comboCommissioner);
        comboEquip = new EquipmentComboModel(equip);
        comboEquip.update();
        ComboBoxEquipment.setModel(comboEquip);
        comboArea = new AreaComboModel(area);
        comboArea.update();
        ComboBoxArea.setModel(comboArea);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        TextName.setEditable(edit);
        ComboBoxSportComplex.setEnabled(edit);
        FormattedTextDate.setEditable(edit);
        ComboBoxArea.setEnabled(edit);
        ComboBoxEquipment.setEnabled(edit);
        ComboBoxCommissioner.setEnabled(edit);
        ComboBoxRol.setEnabled(edit);
    }

    public SportComplexComboModel getComboComplex() {
        return comboComplex;
    }

    public void setComboComplex(SportComplexComboModel comboComplex) 
            throws DAOException {
        this.comboComplex = comboComplex;
        ComboBoxSportComplex.setModel(comboComplex);
        comboComplex.update();
    }

    public CommissionerComboModel getComboCommissioner() {
        return comboCommissioner;
    }

    public void setComboCommissioner(CommissionerComboModel comboCommissioner) 
            throws DAOException, DNIException {
        this.comboCommissioner = comboCommissioner;
        ComboBoxCommissioner.setModel(comboCommissioner);
        comboCommissioner.update();
    }

    public EquipmentComboModel getComboEquip() {
        return comboEquip;
    }

    public void setComboEquip(EquipmentComboModel comboEquip) 
            throws DAOException {
        this.comboEquip = comboEquip;
        ComboBoxEquipment.setModel(comboEquip);
        comboEquip.update();
    }

    public AreaComboModel getComboArea() {
        return comboArea;
    }

    public void setComboArea(AreaComboModel comboArea) throws DAOException {
        this.comboArea = comboArea;
        ComboBoxArea.setModel(comboArea);
        comboArea.update();
    }
    
    public void loadData(){
        if (event != null) {
            TextName.setText(event.getName());
            FormattedTextDate.setValue(event.getDate());
            SelectItemComboBox();
        }else{
            TextName.setText("");
            FormattedTextDate.setText("");
        }
        TextName.requestFocus();
    }
    
    public void SelectItemComboBox() {
        boolean encontrado=false;
        for (int i = 0; i < ComboBoxSportComplex.getItemCount() && !encontrado; i++) {
            if (ComboBoxSportComplex.getItemAt(i).getId() == 
                    event.getComplex().getId()) {
                ComboBoxSportComplex.setSelectedIndex(i);
                encontrado=true;
                
                
            }
        }
    }
    
    
    
    public void saveData(){
        if (event == null) {
            event = new Event();
        }
        event.setName(TextName.getText());
        SportComplexComboView sccv =
                (SportComplexComboView) ComboBoxSportComplex.getSelectedItem();
        event.setComplex((SportComplex)sccv);
        event.setDate((Date)FormattedTextDate.getValue());
        AreaComboView acv = (AreaComboView) ComboBoxArea.getSelectedItem();
        event.setArea(acv.getArea());
        EquipmentComboView ecv = 
                (EquipmentComboView) ComboBoxEquipment.getSelectedItem();
        event.getEquip().add(ecv.getEquipment());
        CommissionerComboView ccv = 
                (CommissionerComboView) ComboBoxCommissioner.getSelectedItem();
        String rol = (String) ComboBoxRol.getSelectedItem();
        event.getCommissioners().put(ccv.getCommissioner(), rol);
    }
    
    public boolean checkData(){
        boolean noEmpty = false;
        if (!TextName.getText().equals("") && !FormattedTextDate.getText().equals("")) {
            noEmpty = true;
        }
        return noEmpty;
    }
    
    

    /**
     * This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JLabel();
        SportComplex = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        Area = new javax.swing.JLabel();
        Equipments = new javax.swing.JLabel();
        Commissioners = new javax.swing.JLabel();
        TextName = new javax.swing.JTextField();
        ComboBoxSportComplex = new javax.swing.JComboBox<>();
        FormattedTextDate = new javax.swing.JFormattedTextField();
        ComboBoxArea = new javax.swing.JComboBox<>();
        ComboBoxEquipment = new javax.swing.JComboBox<>();
        ComboBoxCommissioner = new javax.swing.JComboBox<>();
        ComboBoxRol = new javax.swing.JComboBox<>();

        Name.setText("Nombre:");

        SportComplex.setText("Complejo:");

        Date.setText("Fecha:");

        Area.setText("Area:");

        Equipments.setText("Materiales:");

        Commissioners.setText("Comisarios:");

        ComboBoxSportComplex.setModel(new javax.swing.DefaultComboBoxModel<>(new SportComplexComboView[] { }));
        ComboBoxSportComplex.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxSportComplexItemStateChanged(evt);
            }
        });

        FormattedTextDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        ComboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OBSERVADOR", "JUEZ" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SportComplex)
                    .addComponent(Date)
                    .addComponent(Area)
                    .addComponent(Equipments)
                    .addComponent(Name)
                    .addComponent(Commissioners))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ComboBoxCommissioner, 0, 152, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(ComboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextName)
                    .addComponent(ComboBoxEquipment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FormattedTextDate)
                    .addComponent(ComboBoxSportComplex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SportComplex)
                    .addComponent(ComboBoxSportComplex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Date)
                    .addComponent(FormattedTextDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Area)
                    .addComponent(ComboBoxArea, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Equipments)
                    .addComponent(ComboBoxEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Commissioners)
                    .addComponent(ComboBoxCommissioner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxSportComplexItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxSportComplexItemStateChanged
        try {
            comboArea.update(((SportComplexComboView)evt.getItem()).getId());
            ComboBoxArea.setModel(comboArea);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_ComboBoxSportComplexItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Area;
    private javax.swing.JComboBox<AreaComboView> ComboBoxArea;
    private javax.swing.JComboBox<CommissionerComboView> ComboBoxCommissioner;
    private javax.swing.JComboBox<EquipmentComboView> ComboBoxEquipment;
    private javax.swing.JComboBox<String> ComboBoxRol;
    private javax.swing.JComboBox<SportComplexComboView> ComboBoxSportComplex;
    private javax.swing.JLabel Commissioners;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Equipments;
    private javax.swing.JFormattedTextField FormattedTextDate;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel SportComplex;
    private javax.swing.JTextField TextName;
    // End of variables declaration//GEN-END:variables
}

