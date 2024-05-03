package cl.tbd.proyecto.models.geo;

import ch.qos.logback.core.joran.sanity.Pair;

import java.util.List;

public class GeoVolunteer {
    private Long id_voluntario;
    private String rut;
    private String nombre;
    private String direccion;

    private String email;
    private String telefono;

    private boolean participa;

    public GeoVolunteer(Long id_voluntario, String rut, String nombre, String direccion, String email, String telefono, boolean participa, String geoText) {
        this.id_voluntario = id_voluntario;
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.participa = participa;
        this.geoText = geoText;
    }

    private String geoText;

    private List<Double> coordenadas;

    public Long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(Long id_voluntario) {
        this.id_voluntario = id_voluntario;
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

    public boolean isParticipa() {
        return participa;
    }

    public void setParticipa(boolean participa) {
        this.participa = participa;
    }
}
