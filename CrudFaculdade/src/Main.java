import View.MenuPrincipal;

import ConexaoBD.ConexaoBD;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection conexaoComBancoDeDados = ConexaoBD.ConectarBancoDeDados();

        if(conexaoComBancoDeDados != null){
            System.out.println("A conexão com banco de dados foi feita com suceso! ");
        } else{
            System.out.println("A tentativa de conxão com o banco de dados falhou. Tente Novamente! ");
        }

        MenuPrincipal.exibirMenu();

      }
    }
