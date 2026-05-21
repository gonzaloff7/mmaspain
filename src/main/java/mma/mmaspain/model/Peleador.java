package mma.mmaspain.model;

public class Peleador {

    private int id;

    private String nombre;

    private String apodo;

    private String nacionalidad;

    private String peso;

    private int victorias;

    private int derrotas;

    private String organizacion;

    public Peleador(

            int id,

            String nombre,

            String apodo,

            String nacionalidad,

            String peso,

            int victorias,

            int derrotas,

            String organizacion
    ) {

        this.id = id;

        this.nombre = nombre;

        this.apodo = apodo;

        this.nacionalidad = nacionalidad;

        this.peso = peso;

        this.victorias = victorias;

        this.derrotas = derrotas;

        this.organizacion = organizacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getPeso() {
        return peso;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public String getOrganizacion() {
        return organizacion;
    }
}