package mma.mmaspain.model;

public class RankingUsuario {

    private String nombre;

    private double porcentaje;

    public RankingUsuario(
            String nombre,
            double porcentaje
    ) {

        this.nombre = nombre;

        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}