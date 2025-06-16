package View;

import javax.swing.JOptionPane;

public class MenuPrincipal {

    public static void exibirMenu() {
        boolean continuar = true;

        while (continuar) {
            String menu = " Menu Principal \n\n "
                    + " 1. - Gerenciar ALUNOS\n "
                    + " 2. - Gerenciar DISCIPLINAS\n "
                    + " 3. - Gerenciar CURSOS\n "
                    + " 4. - SAIR\n ";

            String op = JOptionPane.showInputDialog(menu);  //JOptionPane.showInputDialog(menu) - > Exibe uma janela para o usuÃ¡rio digitar a opÃ§Ã£o escolhida.



            if (op == null || op.equals("4")) {
                continuar = false;
                break;
            }

            switch (op) {
                case "1":
                    SubMenuAluno.exibir();
                    break;
                case "2":
                    SubMenuDisciplina.exibir();
                    break;
                case "3":
                    SubMenuCurso.exibir();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " Operação Inválida. ");
            }
        }

        JOptionPane.showMessageDialog(null, " Programa encerrado.");
    }
}

