package mma.mmaspain.dao;

import mma.mmaspain.model.Peleador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class PeleadorDAO {

    public static ArrayList<Peleador> buscarPeleadores(
            String texto
    ) {

        ArrayList<Peleador> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT * FROM peleadores " +

                    "WHERE nombre LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    "%" + texto + "%"
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Peleador p =
                        new Peleador(

                                rs.getInt(
                                        "id_peleador"
                                ),

                                rs.getString(
                                        "nombre"
                                ),

                                rs.getString(
                                        "apodo"
                                ),

                                rs.getString(
                                        "nacionalidad"
                                ),

                                rs.getString(
                                        "peso"
                                ),

                                rs.getInt(
                                        "victorias"
                                ),

                                rs.getInt(
                                        "derrotas"
                                ),

                                rs.getString(
                                        "organizacion"
                                )
                        );

                lista.add(p);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public static ArrayList<Peleador> obtenerPeleadores() {

        ArrayList<Peleador> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT * FROM peleadores";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

            	Peleador p =
            	        new Peleador(

            	                rs.getInt("id_peleador"),

            	                rs.getString("nombre"),

            	                rs.getString("apodo"),

            	                rs.getString("nacionalidad"),

            	                rs.getString("peso"),

            	                rs.getInt("victorias"),

            	                rs.getInt("derrotas"),

            	                rs.getString("organizacion")
            	        );

                lista.add(p);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}