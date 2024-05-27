package cl.tbd.proyecto.controllers.DTO;

public class VoluntarioDTO {
    private String nombre;
    private String rut;
    private int participa;
    private double latitude;
    private double longitude;

    //Coordenadas

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public VoluntarioDTO(String nombre, String rut, int participa, double latitude, double longitude) {
        this.nombre = nombre;
        this.rut = rut;
        this.participa = participa;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getParticipa() {
        return participa;
    }

    public void setParticipa(int participa) {
        this.participa = participa;
    }
}
