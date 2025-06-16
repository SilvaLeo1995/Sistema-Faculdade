package View;

import javax.swing.JOptionPane;
import Controller.AlunoController;

public class SubMenuAluno {

    public static void exibir() {
        boolean voltar = false;

        while (!voltar) {
            String menu = "Submenu ALUNO\n\n"
                    + "1. - Cadastrar ALUNO\n"
                    + "2. - Consultar ALUNO\n"
                    + "3. - Remover ALUNO\n"
                    + "4. - Atualizar ALUNO\n"
                    + "5. - Voltar ao MENU PRINCIPAL\n";

            String op = JOptionPane.showInputDialog(menu);

            if (op == null || op.equals("5")) {
                voltar = true;
                break;
            }

            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Cadastrar Aluno selecionado.");
                    AlunoController.cadastrarAlunoViaSubMenu();
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Consultar Aluno selecionado.");
                    AlunoController.consultarAlunosViaSubMenu();
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Remover Aluno selecionado.");
                    AlunoController.removerAlunoViaSubMenu();
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Atualizar Aluno selecionado.");
                    AlunoController.atualizarAlunoViaSubMenu();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Operação inválida.");
            }
        }
    }
}