package mma.mmaspain.model;

public class Combate {

    private int id;

    private String peleador1;

    private String peleador2;

    public Combate(
            int id,
            String peleador1,
            String peleador2
    ) {

        this.id = id;

        this.peleador1 = peleador1;

        this.peleador2 = peleador2;
    }

    public int getId() {
        return id;
    }

    public String getPeleador1() {
        return peleador1;
    }

    public String getPeleador2() {
        return peleador2;
    }
}