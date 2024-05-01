package cl.tbd.proyecto.entities;

public class InstitucionEntity {
    private Long id_institucion;
    private String nombre;
    private String email;
    private String telefono;
    private String ubicacion;

    public InstitucionEntity(Long id, String nombre, String email, String telefono, String ubicacion) {
        this.id_institucion = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return id_institucion;
    }

    public void setId(Long id) {
        this.id_institucion = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}
