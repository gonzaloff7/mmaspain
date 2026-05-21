package mma.mmaspain;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionTest {

    public static void probarConexion() {

        try {

            Connection con =
                    DriverManager.getConnection(

                            "jdbc:mariadb://localhost:3306/mma_spain",

                            "mma",

                            "1234"
                    );

            System.out.println(
                    "✅ CONEXIÓN A MARIA DB CORRECTA"
            );

        } catch (Exception e) {

            System.out.println(
                    "❌ ERROR CONECTANDO"
            );

            e.printStackTrace();
        }
    }
}