package cl.tbd.proyecto.models.geo;

import ch.qos.logback.core.joran.sanity.Pair;

import java.util.List;

public class GeoVolunteer {
    private Long id;
    private String rut;
    private String nombre;
    private String direccion;

    private String email;
    private String telefono;

    private String geoText;

    private List<Double> coordenadas;

    public GeoVolunteer(Long id, String rut, String nombre, String direccion, String email, String telefono, String geoText) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.geoText = geoText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGeoText() {
        return geoText;
    }

    public void setGeoText(String geoText) {
        this.geoText = geoText;
    }

    public List<Double> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Double> coordenadas) {
        this.coordenadas = coordenadas;
    }
}
