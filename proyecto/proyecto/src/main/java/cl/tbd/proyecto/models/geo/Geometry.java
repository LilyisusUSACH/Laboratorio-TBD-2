package cl.tbd.proyecto.models.geo;

import ch.qos.logback.core.joran.sanity.Pair;

import java.util.List;

public class Geometry {
    // Idealmente Point (para los casos q se necesitan)
    private String type;
    private List<Double> coordinates;

    public Geometry(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
