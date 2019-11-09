/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcenter;

import Modelo.DAO.SportComplexDAO;
import Modelo.SportCenter;
import Modelo.SportComplex;
import Modelo.auxiliary.DAOException;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportCenterDetailsPanel extends javax.swing.JPanel {

    private SportCenter sportCenter;
    
    private boolean edit;
    
    private SportComplexComboModel comboModel;
    
    public SportCenterDetailsPanel() {
        initComponents();
        comboModel = new SportComplexComboModel(null);
    }
    
    public SportCenterDetailsPanel(SportComplexDAO dao) throws DAOException {
        initComponents();
        comboModel = new SportComplexComboModel(dao);
        comboModel.update();
        ComboBoxSportComplex.setModel(comboModel);
    }

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        ComboBoxSportComplex.setEnabled(edit);
        TextSport.setEditable(edit);
        TextInformation.setEditable(edit);
    }

    public SportComplexComboModel getComboModel() {
        return comboModel;
    }

    public void setComboModel(SportComplexComboModel comboModel) throws DAOException {
        this.comboModel = comboModel;
        ComboBoxSportComplex.setModel(comboModel);
        comboModel.update();
    }
    
    public void loadData(){
        if (sportCenter != null) {
            SelectItemComboBox();
            TextSport.setText(sportCenter.getSport());
            TextInformation.setText(sportCenter.getInformation());
        }else{
            TextSport.setText("");
            TextInformation.setText("");
        }
        TextSport.requestFocus();
    }
    
    public void SelectItemComboBox() {
        boolean encontrado=false;
        for (int i = 0; i < ComboBoxSportComplex.getItemCount() && !encontrado; i++) {
            if (ComboBoxSportComplex.getItemAt(i).getId() == 
                    sportCenter.getId()) {
                ComboBoxSportComplex.setSelectedIndex(i);
                encontrado=true;
            }
        }
    }
    
    public void saveData(){
        if (sportCenter == null) {
            sportCenter = new SportCenter();
        }
        sportCenter.setSport(TextSport.getText());
        sportCenter.setInformation(TextInformation.getText());
        SportComplexComboView sccv =
                (SportComplexComboView) ComboBoxSportComplex.getSelectedItem();
        SportComplex sportComplex = (SportComplex) sccv;
        sportCenter.setId(sportComplex.getId());
        sportCenter.setLocation(sportComplex.getLocation());
        sportCenter.setBoss(sportComplex.getBoss());
    }
    
    public boolean checkData(){
        boolean noEmpty = false;
        if (!TextSport.getText().equals("") && !TextInformation.getText().equals("")) {
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

        Id = new javax.swing.JLabel();
        Sport = new javax.swing.JLabel();
        Information = new javax.swing.JLabel();
        ComboBoxSportComplex = new javax.swing.JComboBox<>();
        TextSport = new javax.swing.JTextField();
        TextInformation = new javax.swing.JTextField();

        Id.setText("Id:");

        Sport.setText("Deporte:");

        Information.setText("Información:");

        ComboBoxSportComplex.setModel(new javax.swing.DefaultComboBoxModel<>(new SportComplexComboView[] { }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Information)
                        .addGap(18, 18, 18)
                        .addComponent(TextInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Id)
                            .addComponent(Sport))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextSport, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxSportComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Id)
                    .addComponent(ComboBoxSportComplex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sport)
                    .addComponent(TextSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Information)
                    .addComponent(TextInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<SportComplexComboView> ComboBoxSportComplex;
    private javax.swing.JLabel Id;
    private javax.swing.JLabel Information;
    private javax.swing.JLabel Sport;
    private javax.swing.JTextField TextInformation;
    private javax.swing.JTextField TextSport;
    // End of variables declaration//GEN-END:variables
}
