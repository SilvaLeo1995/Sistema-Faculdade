package ConexaoBD;

import java.sql.*;

public class ConexaoBD {

     private static final String urlBD = "jdbc:mysql://localhost/crudfaculdade";

     private static final String usuario = "root";

     private static final String senha = "";


     public static Connection ConectarBancoDeDados(){

         try{
             return DriverManager.getConnection(urlBD, usuario, senha);
         } catch(SQLException ex){
             throw new RuntimeException("Operação Invália. Erro na conexão! " + ex.getMessage());
         }
     }
}
