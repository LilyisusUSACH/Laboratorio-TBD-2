package cl.tbd.proyecto.entities;

public class UsuarioEntity {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long rol_id; // Llave foránea que apunta a RolEntity

    public UsuarioEntity() {
    }

    // Constructor, getters y setters.
    public UsuarioEntity(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRolId() { // Getter para rolId
        return rol_id;
    }

    public void setRolId(Long rolId) { // Setter para rolId
        this.rol_id = rolId;
    }
}
