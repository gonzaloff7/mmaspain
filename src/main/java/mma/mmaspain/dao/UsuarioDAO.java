package mma.mmaspain.dao;

import mma.mmaspain.model.Usuario;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

	public static boolean registrarUsuario(

	        String nombre,

	        String email,

	        String password
	) {

	    try {

	        Connection con =
	                DriverManager.getConnection(

	                        "jdbc:mariadb://localhost:3306/mma_spain",

	                        "mma",

	                        "1234"
	                );

	        String sql =
	                "INSERT INTO usuarios " +

	                "(nombre, email, password, xp, nivel) " +

	                "VALUES (?, ?, ?, 0, 'Beginner')";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1, nombre);

	        ps.setString(2, email);

	        ps.setString(3, password);

	        ps.executeUpdate();

	        return true;

	    } catch (Exception e) {

	        return false;
	    }
	}	
    public static Usuario login(
            String email,
            String password
    ) {

        Usuario usuario = null;

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT * FROM usuarios " +
                    "WHERE email = ? " +
                    "AND password = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);

            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

            	usuario =
            	        new Usuario(

            	                rs.getInt("id_usuario"),

            	                rs.getString("nombre"),

            	                rs.getString("email"),

            	                rs.getString("password"),

            	                rs.getInt("xp"),

            	                rs.getString("nivel"),

            	                rs.getString("bio"),

            	                rs.getString("bandera"),

            	                rs.getBoolean("premium"),
            	                
            	                rs.getBoolean("admin"),
            	                
            	                rs.getBoolean("baneado"),
            	                
            	                rs.getBoolean("notificaciones")
            	        );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return usuario;
    }
    
    public static void actualizarPerfil(

            int id,

            String nombre,

            String bio,

            String bandera
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "UPDATE usuarios " +

                    "SET nombre = ?, " +

                    "bio = ?, " +

                    "bandera = ? " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, nombre);

            ps.setString(2, bio);

            ps.setString(3, bandera);

            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static void activarPremium(
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
                    "UPDATE usuarios " +

                    "SET premium = true " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static ArrayList<Usuario> obtenerTodos() {

        ArrayList<Usuario> lista =
                new ArrayList<>();

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "SELECT * FROM usuarios";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Usuario u =
                        new Usuario(

                                rs.getInt("id_usuario"),

                                rs.getString("nombre"),

                                rs.getString("email"),

                                rs.getString("password"),

                                rs.getInt("xp"),

                                rs.getString("nivel"),

                                rs.getString("bio"),

                                rs.getString("bandera"),

                                rs.getBoolean("premium"),

                                rs.getBoolean("admin"),

                                rs.getBoolean("baneado"),
                                
                                rs.getBoolean("notificaciones")
                        );

                lista.add(u);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    
    public static void banearUsuario(

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
                    "UPDATE usuarios " +

                    "SET baneado = true " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static void darPremium(

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
                    "UPDATE usuarios " +

                    "SET premium = true " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static void quitarPremium(

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
                    "UPDATE usuarios " +

                    "SET premium = false " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
   
    public static void cambiarNotificaciones(

            int id,

            boolean valor
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            String sql =
                    "UPDATE usuarios " +

                    "SET notificaciones = ? " +

                    "WHERE id_usuario = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setBoolean(1, valor);

            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    
}