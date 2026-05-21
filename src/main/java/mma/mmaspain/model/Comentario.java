package mma.mmaspain.model;

public class Comentario {

    private int id;

    private String usuario;

    private String texto;

    public Comentario(

            int id,

            String usuario,

            String texto
    ) {

        this.id = id;

        this.usuario = usuario;

        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTexto() {
        return texto;
    }
}