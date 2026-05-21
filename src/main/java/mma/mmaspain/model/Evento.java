package mma.mmaspain.model;

public class Evento {

    private int id;

    private String nombre;

    private String lugar;

    private String organizacion;

    public Evento(
            int id,
            String nombre,
            String lugar,
            String organizacion
    ) {

        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.organizacion = organizacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public String getOrganizacion() {
        return organizacion;
    }
}