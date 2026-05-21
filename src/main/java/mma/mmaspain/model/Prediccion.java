package mma.mmaspain.model;

public class Prediccion {

    private int idUsuario;

    private int idCombate;

    private int ganadorPredicho;

    private String metodo;

    private int round;

    public Prediccion(

            int idUsuario,

            int idCombate,

            int ganadorPredicho,

            String metodo,

            int round
    ) {

        this.idUsuario = idUsuario;

        this.idCombate = idCombate;

        this.ganadorPredicho = ganadorPredicho;

        this.metodo = metodo;

        this.round = round;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public int getGanadorPredicho() {
        return ganadorPredicho;
    }

    public String getMetodo() {
        return metodo;
    }

    public int getRound() {
        return round;
    }
}