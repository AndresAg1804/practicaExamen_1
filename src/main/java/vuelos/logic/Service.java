package vuelos.logic;

import vuelos.data.Data;
import vuelos.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    private Data data;

    private Service(){
        try{
            data = XmlPersister.instance().load();
        }
        catch (Exception e){
            data = new Data();
        }

    }

    public void stop(){
        try{
            XmlPersister.instance().store(data);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //================= TIPOS DE INSTRUMENTO ============
    public void create(Vuelo e) throws Exception{
        Vuelo result = data.getViajes().stream()
                .filter(i->i.equals(e)).findFirst().orElse(null);
        if (result==null) data.getViajes().add(e);
        else throw new Exception("Vuelo ya existe");
    }

    public Vuelo read(Vuelo e) throws Exception{
        Vuelo result = data.getViajes().stream()
                .filter(i->i.equals(e)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Vuelo no existe");
    }

    public void update(Vuelo e) throws Exception{
        Vuelo result = null;
        try{
            result = this.read(e);
            data.getViajes().remove(result);
            data.getViajes().add(e);
        }catch (Exception ex) {
            throw new Exception("Vuelo no existe");
        }
    }

    public void delete(Vuelo e) throws Exception{
        data.getViajes().remove(e);
    }

    public List<Vuelo> search(Vuelo e){
        return data.getViajes().stream()
                .filter(i->i.equals(e))
                .sorted(Comparator.comparing(vuelos.logic.Vuelo::getNumero))
                .collect(Collectors.toList());
    }


}