package mma.mmaspain.model;

public class Puntuacion {

    private int idUsuario;

    private int idCombate;

    private int r1p1;

    private int r1p2;

    private int r2p1;

    private int r2p2;

    private int r3p1;

    private int r3p2;

    public Puntuacion(

            int idUsuario,

            int idCombate,

            int r1p1,

            int r1p2,

            int r2p1,

            int r2p2,

            int r3p1,

            int r3p2
    ) {

        this.idUsuario = idUsuario;

        this.idCombate = idCombate;

        this.r1p1 = r1p1;

        this.r1p2 = r1p2;

        this.r2p1 = r2p1;

        this.r2p2 = r2p2;

        this.r3p1 = r3p1;

        this.r3p2 = r3p2;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public int getR1p1() {
        return r1p1;
    }

    public int getR1p2() {
        return r1p2;
    }

    public int getR2p1() {
        return r2p1;
    }

    public int getR2p2() {
        return r2p2;
    }

    public int getR3p1() {
        return r3p1;
    }

    public int getR3p2() {
        return r3p2;
    }
}