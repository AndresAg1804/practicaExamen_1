package vuelos.presentation.vuelos;

import vuelos.Application;
import vuelos.logic.Ciudad;
import vuelos.logic.Vuelo;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
public class View implements Observer {
    private JPanel panel;
    private JTextField searchCiudad;
    private JButton search;
    private JButton save;

    public JTable getList() {
        return list;
    }

    private JTable list;
    private JLabel searchCiudadLbl;
    private JTextField numero;
    private JLabel numeroLbl;
    private JLabel origenLbl;
    private JButton clear;
    private JComboBox origenCombo;
    private JLabel salidaLbl;
    private JComboBox salidaCombo;
    private JLabel destinoLbl;
    private JComboBox destinoCombo;
    private JComboBox llegadaCombo;
    private JLabel llegadaLbl;

    public View() {

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vuelo filter = new Vuelo();
                    filter.getOrigen().setNombre(searchCiudad.getText());
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                try {
                    controller.edit(row);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                numero.setEnabled(false);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vuelo filter = new Vuelo();
//                filter.setNumero(nombre.getText());
//                filter.setCodigo(numero.getText());
//                filter.setUnidad(unidad.getText());
                try {
                    if(isValid()){
                        controller.save(filter);
                        clearTextFields();
                    }
                    else{
                        throw new Exception("Campos vacios");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearTextFields();
            }
        });

    }

    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.setMode(Application.MODE_CREATE);
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object properties) {
        int changedProps = (int) properties;
        if ((changedProps & Model.LIST) == Model.LIST) {
            int[] cols = {TableModel.NUMERO, TableModel.ORIGEN, TableModel.DESTINO, TableModel.SALIDA, TableModel.LLEGADA};
            list.setModel(new TableModel(cols, model.getList()));
            list.setRowHeight(30);
            TableColumnModel columnModel = list.getColumnModel();
            columnModel.getColumn(1).setPreferredWidth(200);
            columnModel.getColumn(2).setPreferredWidth(200);

        }
        if ((changedProps & Model.CURRENT) == Model.CURRENT) {
            numero.setText(String.valueOf(model.getCurrent().getNumero()));
            origenCombo.setSelectedItem(model.getCurrent().getOrigen());
            destinoCombo.setSelectedItem(model.getCurrent().getDestino());
            salidaCombo.setSelectedItem(model.getCurrent().getSalida());
            llegadaCombo.setSelectedItem(model.getCurrent().getLlegada());
        }
        if((changedProps & Model.CITIES)== Model.CITIES){
            origenCombo.setModel(new DefaultComboBoxModel(model.getListCities().toArray(new Ciudad[0])));
            destinoCombo.setModel(new DefaultComboBoxModel(model.getListCities().toArray(new Ciudad[0])));
            if(model.getMode() == Application.MODE_EDIT){
                origenCombo.setSelectedItem(model.getCurrent().getOrigen());
                destinoCombo.setSelectedItem(model.getCurrent().getDestino());
            }
        }
        this.panel.revalidate();
    }

    public void clearTextFields(){
        controller.clear();
        numero.setEnabled(true);
    }

    public boolean isValid(){
        if(numero.getText().equals("") || origenCombo.getSelectedItem().equals("") || destinoCombo.getSelectedItem().equals("") || salidaCombo.getSelectedItem().equals("") || llegadaCombo.getSelectedItem().equals("")){
            return false;
        }
        return true;
    }


}
