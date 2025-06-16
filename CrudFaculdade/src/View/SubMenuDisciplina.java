package View;

import Controller.DisciplinaController;

import javax.swing.*;

public class SubMenuDisciplina {

    public static void exibir() {
        boolean voltar = false;

        while (!voltar) {
            String menu = "Submenu DISCIPLINA\n\n"
                    + "1. - Cadastrar DISCIPLINA\n"
                    + "2. - Consultar DISCIPLINA\n"
                    + "3. - Remover DISCIPLINA\n"
                    + "4. - Atualizar DISCIPLINA\n"
                    + "5. - Voltar ao Menu PRINCIPAL\n";

            String op = JOptionPane.showInputDialog(menu);

            if (op == null || op.equals("5")) {
                voltar = true;
                break;
            }

            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Cadastrar Disciplina selecionado.");
                    DisciplinaController.cadastrarDisciplinaViaSubMenu();
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Consultar Disciplina selecionado.");
                    DisciplinaController.consultarDisciplinasViaSubMenu();
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Remover Disciplina selecionado.");
                    DisciplinaController.removerDisciplinaViaSubMenu();
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Atualizar Disciplina selecionado.");
                    DisciplinaController.atualizarDisciplinaViaSubMenu();
                    break;

                case "5":
                    JOptionPane.showMessageDialog(null, "Voltar ao Menu Principal.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Operação inválida.");
            }
        }
    }
}