package cl.tbd.proyecto.entities;

public class EstadoEntity {

    private Long id_estado;
    private String descripcion;

    public EstadoEntity(Long id, String descripcion) {
        this.id_estado = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id_estado;
    }

    public void setId(Long id) {
        this.id_estado = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId_estado() {
        return id_estado;
    }

    public void setId_estado(Long id_estado) {
        this.id_estado = id_estado;
    }
}
