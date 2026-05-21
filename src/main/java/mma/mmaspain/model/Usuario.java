package mma.mmaspain.model;

public class Usuario {

    private int id;

    private String nombre;

    private String email;

    private String password;

    private int xp;

    private String nivel;

    private String bio;

    private String bandera;
    
    private boolean premium;
    
    private boolean admin;
    
    private boolean baneado;
    
    boolean notificaciones;

    public Usuario(

            int id,

            String nombre,

            String email,

            String password,

            int xp,

            String nivel,

            String bio,

            String bandera,
            
            boolean premium,
            
            boolean admin,
            
            boolean baneado,
            
            boolean notificaciones
            
            
    ) {

        this.id = id;

        this.nombre = nombre;

        this.email = email;

        this.password = password;

        this.xp = xp;

        this.nivel = nivel;

        this.bio = bio;

        this.bandera = bandera;
        
        this.premium = premium;
        
        this.admin = admin;
        
        this.baneado = baneado;
        
        this.notificaciones = notificaciones;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getXp() {
        return xp;
    }

    public String getNivel() {
        return nivel;
    }

    public String getBio() {
        return bio;
    }

    public String getBandera() {
        return bandera;
    }
    public boolean isPremium() {
        return premium;
    }
    public boolean isAdmin() {
        return admin;
    }
    public boolean isBaneado() {
        return baneado;
    }
    
    public boolean isNotificaciones() {
        return notificaciones;
    }
    
}