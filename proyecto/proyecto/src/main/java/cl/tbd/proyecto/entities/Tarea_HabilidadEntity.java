package cl.tbd.proyecto.entities;

public class Tarea_HabilidadEntity {
    private Long id_tarea_habilidad;
    private Long id_habilidad;
    private Long id_tarea;

    public Tarea_HabilidadEntity(Long id, Long id_habilidad, Long id_tarea) {
        this.id_tarea_habilidad = id;
        this.id_habilidad = id_habilidad;
        this.id_tarea = id_tarea;
    }

    public Long getId() {
        return id_tarea_habilidad;
    }

    public void setId(Long id) {
        this.id_tarea_habilidad = id;
    }

    public Long getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Long id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long id_tarea) {
        this.id_tarea = id_tarea;
    }

}
