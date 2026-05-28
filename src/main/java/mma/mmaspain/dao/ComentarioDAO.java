package mma.mmaspain.dao;

import mma.mmaspain.model.Comentario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ComentarioDAO {

    public static ArrayList<Comentario> obtenerComentarios(
            int idCombate
    ) {

        ArrayList<Comentario> lista =
                new ArrayList<>();

        try {

        	Connection con =
        	        DriverManager.getConnection(

        	                System.getenv("MYSQL_URL")
        	        );
            String sql =
                    "SELECT u.nombre, c.texto " +

                    "FROM comentarios c " +

                    "JOIN usuarios u " +

                    "ON c.id_usuario = u.id_usuario " +

                    "WHERE c.id_combate = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idCombate);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

            	Comentario c =
            	        new Comentario(

            	                rs.getInt("id_comentario"),

            	                rs.getString("nombre"),

            	                rs.getString("texto")
            	        );

                lista.add(c);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public static void insertarComentario(

            int idUsuario,

            int idCombate,

            String texto
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "INSERT INTO comentarios " +

                    "(id_usuario, id_combate, texto) " +

                    "VALUES (?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    idUsuario
            );

            ps.setInt(
                    2,
                    idCombate
            );

            ps.setString(
                    3,
                    texto
            );

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static void borrarComentario(

            int id
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "DELETE FROM comentarios " +

                    "WHERE id_comentario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static ArrayList<Comentario> obtenerTodos() {

        ArrayList<Comentario> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT c.id_comentario, " +

                    "u.nombre, " +

                    "c.texto " +

                    "FROM comentarios c " +

                    "JOIN usuarios u " +

                    "ON c.id_usuario = u.id_usuario";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Comentario c =
                        new Comentario(

                                rs.getInt("id_comentario"),

                                rs.getString("nombre"),

                                rs.getString("texto")
                        );

                lista.add(c);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    
}