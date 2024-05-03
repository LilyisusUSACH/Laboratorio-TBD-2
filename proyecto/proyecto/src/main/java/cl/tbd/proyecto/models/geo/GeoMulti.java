package cl.tbd.proyecto.models.geo;

import java.util.List;

public class GeoMulti {

    // Idealmente FeatureCollection
    private String type;

    private List<GeoFeature> features;

    public GeoMulti(String type, List<GeoFeature> features) {
        this.type = type;
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GeoFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<GeoFeature> features) {
        this.features = features;
    }
}
