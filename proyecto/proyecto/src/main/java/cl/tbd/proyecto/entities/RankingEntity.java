package cl.tbd.proyecto.entities;

public class RankingEntity {
    private Long id_ranking;
    private Long id_voluntario;
    private Long id_tarea;
    public int participa;
    private int puntuacion;

    public RankingEntity(Long id, Long id_voluntario, int puntuacion, Long id_tarea) {
        this.id_ranking = id;
        this.id_voluntario = id_voluntario;
        this.puntuacion = puntuacion;
        this.id_tarea = id_tarea;
    }

    public Long getId() {
        return id_ranking;
    }

    public void setId(Long id) {
        this.id_ranking = id;
    }

    public Long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(Long id_voluntario) {
        this.id_voluntario = id_voluntario;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long id_tarea) {
        this.id_tarea = id_tarea;
    }

    public int getParticipa() {
        return participa;
    }

    public void setParticipa(int participa) {
        this.participa = participa;
    }
}
