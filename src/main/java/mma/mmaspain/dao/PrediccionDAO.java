package mma.mmaspain.dao;

import mma.mmaspain.model.Prediccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mma.mmaspain.model.RankingUsuario;

public class PrediccionDAO {

    public static void insertarPrediccion(
            Prediccion p
    ) {

        try {

        	Connection con =
        	        DriverManager.getConnection(

        	                "jdbc:mysql://zephyr.proxy.rlwy.net:32502/railway",

        	                "root",

        	                "GmtBrajaURJZBSxwIkXStTVonMnUuDEu"
        	        );

            String sql =
                    "INSERT INTO predicciones " +

                    "(id_usuario, " +

                    "id_combate, " +

                    "ganador_predicho, " +

                    "metodo, " +

                    "round) " +

                    "VALUES (?, ?, ?, ?, ?)";

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
                    p.getGanadorPredicho()
            );
            ps.setString(
                    4,
                    p.getMetodo()
            );

            ps.setInt(
                    5,
                    p.getRound()
            );

            ps.executeUpdate();

            System.out.println(
                    "✅ Predicción guardada"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static int contarPredicciones(
            int idUsuario
    ) {

        int total = 0;

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT COUNT(*) total " +

                    "FROM predicciones " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                total =
                        rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return total;
    }
    
    public static int contarAciertos(
            int idUsuario
    ) {

        int total = 0;

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT COUNT(*) total " +

                    "FROM predicciones " +

                    "WHERE id_usuario = ? " +

                    "AND ganador_predicho = ganador";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                total =
                        rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return total;
    }
    
    public static String metodoFavorito(
            int idUsuario
    ) {

        String metodo =
                "Sin datos";

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT metodo, COUNT(*) total " +

                    "FROM predicciones " +

                    "WHERE id_usuario = ? " +

                    "GROUP BY metodo " +

                    "ORDER BY total DESC " +

                    "LIMIT 1";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                metodo =
                        rs.getString("metodo");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return metodo;
    }
    public static ArrayList<RankingUsuario> obtenerRanking() {

        ArrayList<RankingUsuario> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT u.nombre, " +

                    "(COUNT(CASE " +

                    "WHEN p.ganador_predicho = c.ganador " +

                    "THEN 1 END) * 100.0 / COUNT(*)) " +

                    "AS porcentaje " +

                    "FROM predicciones p " +

                    "JOIN usuarios u " +

                    "ON p.id_usuario = u.id_usuario " +

                    "JOIN combates c " +

                    "ON p.id_combate = c.id_combate " +

                    "GROUP BY u.nombre " +

                    "ORDER BY porcentaje DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                RankingUsuario r =
                        new RankingUsuario(

                                rs.getString("nombre"),

                                rs.getDouble("porcentaje")
                        );

                lista.add(r);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public static void validarPredicciones(

            int combate,

            int ganadorReal
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT * FROM predicciones " +

                    "WHERE id_combate = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, combate);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                int usuario =
                        rs.getInt("id_usuario");

                int ganadorPredicho =
                        rs.getInt("ganador");

                if (ganadorPredicho == ganadorReal) {

                    String xpSql =
                            "UPDATE usuarios " +

                            "SET xp = xp + 100 " +

                            "WHERE id_usuario = ?";

                    PreparedStatement xp =
                            con.prepareStatement(xpSql);

                    xp.setInt(1, usuario);

                    xp.executeUpdate();
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
}