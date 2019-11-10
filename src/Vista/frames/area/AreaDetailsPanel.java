/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.area;

import Modelo.Area;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.MultiSportCenter;
import Modelo.auxiliary.DAOException;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaDetailsPanel extends javax.swing.JPanel {

    private Area area;
    
    private boolean edit;
    
    private MultiSportCenterComboModel comboModel;

    public AreaDetailsPanel() {
        initComponents();
        comboModel = new MultiSportCenterComboModel(null);
    }
    
    public AreaDetailsPanel(MultiSportCenterDAO dao) throws DAOException {
        initComponents();
        comboModel = new MultiSportCenterComboModel(null);
        comboModel.update();
        ComboMultiSportCenter.setModel(comboModel);
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        TextLocation.setEditable(edit);
        TextSport.setEditable(edit);
        ComboMultiSportCenter.setEnabled(edit);
    }

    public MultiSportCenterComboModel getComboModel() {
        return comboModel;
    }

    public void setComboModel(MultiSportCenterComboModel comboModel) throws DAOException {
        this.comboModel = comboModel;
        ComboMultiSportCenter.setModel(comboModel);
        comboModel.update();
    }
    
    public void loadData(){
        if (area != null){
            TextLocation.setText(area.getLocation());
            TextSport.setText(area.getSport());
            SelectItemComboBox();
        }else{
            TextLocation.setText("");
            TextSport.setText("");
        }
        TextLocation.requestFocus();
    }
    
    public void SelectItemComboBox() {
        boolean encontrado=false;
        for (int i = 0; i < ComboMultiSportCenter.getItemCount() && !encontrado; i++) {
            if (ComboMultiSportCenter.getItemAt(i).getId() == 
                    area.getMsc().getId()) {
                ComboMultiSportCenter.setSelectedIndex(i);
                encontrado=true;
            }
        }
    }
    
    public void saveData(){
        if (area == null){
            area = new Area();
        }
        area.setLocation(TextLocation.getText());
        area.setSport(TextSport.getText());
        MultiSportCenterComboView msccv = 
                (MultiSportCenterComboView) ComboMultiSportCenter.getSelectedItem();
        MultiSportCenter msc = (MultiSportCenter) msccv;
        area.setMsc(msc);
    }
    
    public boolean checkData(){
        boolean noEmpty = false;
        if (!TextSport.getText().equals("") && !TextLocation.getText().equals("")) {
            noEmpty = true;
        }
        return noEmpty;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Location = new javax.swing.JLabel();
        Sport = new javax.swing.JLabel();
        MultiSportCenter = new javax.swing.JLabel();
        TextLocation = new javax.swing.JTextField();
        TextSport = new javax.swing.JTextField();
        ComboMultiSportCenter = new javax.swing.JComboBox<>();

        Location.setText("Localización:");

        Sport.setText("Deporte:");

        MultiSportCenter.setText("Polideportivo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MultiSportCenter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboMultiSportCenter, 0, 278, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Location)
                            .addComponent(Sport))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextLocation)
                            .addComponent(TextSport, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Location)
                    .addComponent(TextLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sport)
                    .addComponent(TextSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MultiSportCenter)
                    .addComponent(ComboMultiSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<MultiSportCenterComboView> ComboMultiSportCenter;
    private javax.swing.JLabel Location;
    private javax.swing.JLabel MultiSportCenter;
    private javax.swing.JLabel Sport;
    private javax.swing.JTextField TextLocation;
    private javax.swing.JTextField TextSport;
    // End of variables declaration//GEN-END:variables
}

