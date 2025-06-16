package View;

import javax.swing.JOptionPane;
import Controller.CursoController;

public class SubMenuCurso {

    public static void exibir() {
        boolean voltar = false;

        while (!voltar) { // enquanto o usuário não escolher sair (opção 5), o menu continua sendo exibido
            String menu = "Submenu CURSO\n\n"
                    + "1. - Cadastrar CURSO\n"
                    + "2. - Consultar CURSO\n"
                    + "3. - Remover CURSO\n"
                    + "4. - Atualizar CURSO\n"
                    + "5. - Voltar ao MENU PRINCIPAL\n";

            String op = JOptionPane.showInputDialog(menu); // o valor digitado é armazenado na variável op, que foi criada

            if (op == null || op.equals("5")) { // se o usuário clicar em "Cancelar" ou digitar 5, o loop termina e o submenu é fechado.
                voltar = true;
                break;
            }

            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Cadastrar Curso selecionado.");
                    CursoController.cadastrarCursoViaSubMenu(); // Chama o método de cadastro de curso na controller
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Consultar Curso selecionado.");
                    CursoController.consultarCursoViaSubMenu(); // Chama o método de consulta de curso na controller
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Remover Curso selecionado.");
                    CursoController.removerCursoViaSubMenu(); // Chama o método de remoção de curso na controller
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Atualizar Curso selecionado.");
                    CursoController.atualizarCursoViaSubMenu(); // Chama o método de atualização de curso na controller
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Operação Inválida.");
            }
        }
    }
}
