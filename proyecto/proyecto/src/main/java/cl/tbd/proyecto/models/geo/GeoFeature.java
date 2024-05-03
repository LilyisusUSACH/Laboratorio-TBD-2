package cl.tbd.proyecto.models.geo;

import java.util.Map;

public class GeoFeature {

    // Idealmente Feature
    String type;

    Geometry geometry;

    Map<String,Object> properties;

    public GeoFeature(String type, Geometry geometry, Map<String, Object> properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
