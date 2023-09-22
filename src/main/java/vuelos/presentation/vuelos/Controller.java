package vuelos.presentation.vuelos;

import vuelos.Application;
import vuelos.logic.Service;
import vuelos.logic.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Controller{
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Vuelo()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(Vuelo filter) throws  Exception{
        List<Vuelo> rows = Service.instance().search(filter);
        if (rows.isEmpty()){
            throw new Exception("NINGUN REGISTRO COINCIDE");
        }
        model.setList(rows);
        model.setCurrent(rows.get(0));
        model.commit();
    }

    public void edit(int row) throws Exception{
        model.setMode(Application.MODE_EDIT);
        Vuelo e = model.getList().get(row);
        model.setCurrent(Service.instance().read(e));
        model.setMode(Application.MODE_EDIT);
        model.commit();
    }

    public void save(Vuelo e) throws Exception {
        if (model.getMode() == 1) {
            Service.instance().create(e);
            this.search(new Vuelo());
        }
        if(model.getMode() == 2) {
            Service.instance().update(e);
            this.search(new Vuelo());
        }
    }

    public void delete(int row) throws Exception{

        Vuelo e = model.getList().get(row);

        Service.instance().delete(e);

        // Verifica si el elemento se ha eliminado correctamente en el modelo local
        if (model.getList().remove(e)) {
            // Actualiza la vista con la lista modificada
            int[] cols = {TableModel.NUMERO, TableModel.ORIGEN, TableModel.DESTINO, TableModel.SALIDA, TableModel.LLEGADA};
            view.getList().setModel(new TableModel(cols, model.getList()));
        } else {
            throw new Exception("Error al eliminar el elemento...");
        }


    }
    public void clear(){
        model.setCurrent(new Vuelo());
        model.setMode(Application.MODE_CREATE);
        model.commit();
    }



}
