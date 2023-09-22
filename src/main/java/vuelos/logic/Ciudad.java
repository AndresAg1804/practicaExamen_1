package vuelos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class Ciudad {
    @XmlID
    String id;
    String nombre;
    int gmt;

    public Ciudad(String id, String nombre, int gmt) {
        this.id = id;
        this.nombre = nombre;
        this.gmt = gmt;
    }

    public Ciudad(){
        this(" "," ",0);
    }

    public int getGmt() {
        return gmt;
    }

    public void setGmt(int gmt) {
        this.gmt = gmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(id, ciudad.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
