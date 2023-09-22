package vuelos.data;

import vuelos.logic.Ciudad;
import jakarta.xml.bind.annotation.*;
import vuelos.logic.Vuelo;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

        @XmlElementWrapper(name = "ciudades")
        @XmlElement(name = "ciudad")
        private List<Ciudad> ciudades;

        @XmlElementWrapper(name = "vuelos")
        @XmlElement(name = "viaje")
        private List<Vuelo> vuelos;

        public Data() {
            ciudades = new ArrayList<>();
            vuelos = new ArrayList<>();
        }

        public List<Ciudad> getCiudades() {
            return ciudades;
        }

        public List<Vuelo> getViajes() {
            return vuelos;
        }

}
