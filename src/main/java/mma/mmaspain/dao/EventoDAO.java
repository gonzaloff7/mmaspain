package mma.mmaspain.dao;

import mma.mmaspain.model.Evento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class EventoDAO {

    public static ArrayList<Evento> obtenerEventos() {

        ArrayList<Evento> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT e.id_evento, " +
                    "e.nombre, " +
                    "e.lugar, " +
                    "o.nombre AS organizacion " +
                    "FROM eventos e " +
                    "JOIN organizaciones o " +
                    "ON e.id_organizacion = o.id_organizacion";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Evento evento =
                        new Evento(

                                rs.getInt("id_evento"),

                                rs.getString("nombre"),

                                rs.getString("lugar"),

                                rs.getString("organizacion")
                        );

                lista.add(evento);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public static void crearEvento(

            String nombre,

            String organizacion,

            String lugar
    ) {

        try {

        	Connection con =
        	        DriverManager.getConnection(

        	                System.getenv("MYSQL_URL")
        	        );

            String sql =
                    "INSERT INTO eventos " +

                    "(nombre, organizacion, lugar) " +

                    "VALUES (?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, nombre);

            ps.setString(2, organizacion);

            ps.setString(3, lugar);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
}
