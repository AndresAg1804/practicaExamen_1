package vuelos.presentation.vuelos;

import vuelos.Application;
import vuelos.logic.Ciudad;
import vuelos.logic.Vuelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
public class Model extends java.util.Observable{

    List<Vuelo> list;
    List<Ciudad> listCities;
    Vuelo current;
    int mode;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    int changedProps = NONE;

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(changedProps);
        changedProps = NONE;
    }

    public Model() {
    }

    public void init(List<Vuelo> list){
        setList(list);
        setCurrent(new Vuelo());
    }

    public List<Vuelo> getList() {
        return list;
    }
    public void setList(List<Vuelo> list){
        this.list = list;
        changedProps +=LIST;
    }

    public List<Ciudad> getListCities() {
        return listCities;
    }

    public void setListCities(List<Ciudad> listCities) {
        this.listCities = listCities;
        changedProps += CITIES;
    }

    public Vuelo getCurrent() {
        return current;
    }
    public void setCurrent(Vuelo current) {
        changedProps +=CURRENT;
        this.current = current;
    }

    public static int NONE=0;
    public static int LIST=1;
    public static int CURRENT=2;
    public static int CITIES=4;
}
