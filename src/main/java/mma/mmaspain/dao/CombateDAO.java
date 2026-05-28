package mma.mmaspain.dao;

import mma.mmaspain.model.Combate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class CombateDAO {

    public static ArrayList<Combate> obtenerCombates(
            int idEvento
    ) {

        ArrayList<Combate> lista =
                new ArrayList<>();

        try {

        	Connection con =
        	        DriverManager.getConnection(

        	                System.getenv("MYSQL_URL")
        	        );

            String sql =
                    "SELECT " +
                    "c.id_combate, " +

                    "p1.nombre AS peleador1, " +

                    "p2.nombre AS peleador2 " +

                    "FROM combates c " +

                    "INNER JOIN peleadores p1 " +

                    "ON c.id_peleador1 = p1.id_peleador " +

                    "INNER JOIN peleadores p2 " +

                    "ON c.id_peleador2 = p2.id_peleador " +

                    "WHERE c.id_evento = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idEvento);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getString("peleador1")
                );

                Combate combate =
                        new Combate(

                                rs.getInt("id_combate"),

                                rs.getString("peleador1"),

                                rs.getString("peleador2")
                        );

                lista.add(combate);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public static void crearCombate(

            int evento,

            int peleador1,

            int peleador2
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "INSERT INTO combates " +

                    "(id_evento, id_peleador1, id_peleador2) " +

                    "VALUES (?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, evento);

            ps.setInt(2, peleador1);

            ps.setInt(3, peleador2);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static void guardarResultado(

            int combate,

            int ganador,

            String metodo,

            int round
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "UPDATE combates " +

                    "SET ganador_real = ?, " +

                    "metodo = ?, " +

                    "ganador = ? " +

                    "WHERE id_combate = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, round);

            ps.setString(2, metodo);

            ps.setInt(3, ganador);

            ps.setInt(4, combate);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static ArrayList<Combate> obtenerTodos() {

        ArrayList<Combate> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT c.id_combate, " +

                    "p1.nombre AS p1, " +

                    "p2.nombre AS p2 " +

                    "FROM combates c " +

                    "JOIN peleadores p1 " +

                    "ON c.id_peleador1 = p1.id_peleador " +

                    "JOIN peleadores p2 " +

                    "ON c.id_peleador2 = p2.id_peleador";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Combate c =
                        new Combate(

                                rs.getInt("id_combate"),

                                rs.getString("p1"),

                                rs.getString("p2")
                        );

                lista.add(c);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    
}