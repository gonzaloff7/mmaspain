package mma.mmaspain.dao;

import mma.mmaspain.model.Puntuacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PuntuacionDAO {

    public static void insertarPuntuacion(
            Puntuacion p
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "INSERT INTO puntuaciones " +

                    "(id_usuario, " +

                    "id_combate, " +

                    "r1p1, r1p2, " +

                    "r2p1, r2p2, " +

                    "r3p1, r3p2) " +

                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    p.getIdUsuario()
            );

            ps.setInt(
                    2,
                    p.getIdCombate()
            );

            ps.setInt(
                    3,
                    p.getR1p1()
            );

            ps.setInt(
                    4,
                    p.getR1p2()
            );

            ps.setInt(
                    5,
                    p.getR2p1()
            );

            ps.setInt(
                    6,
                    p.getR2p2()
            );

            ps.setInt(
                    7,
                    p.getR3p1()
            );

            ps.setInt(
                    8,
                    p.getR3p2()
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}