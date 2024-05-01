package cl.tbd.proyecto.controllers.DTO;

public class VoluntarioDTO {
    private String nombre;
    private String rut;
    private int participa;

    public VoluntarioDTO(String nombre, String rut, int participa) {
        this.nombre = nombre;
        this.rut = rut;
        this.participa = participa;
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
