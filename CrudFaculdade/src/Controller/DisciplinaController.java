package Controller;

import Dao.DisciplinaDAO;
import Model.DisciplinaModel;

import javax.swing.*;
import java.util.List;

public class DisciplinaController {

    public static void cadastrarDisciplinaViaSubMenu() {
        try {
            String nomeDisciplina = JOptionPane.showInputDialog("Informe o nome da disciplina:");
            int codDisciplina = Integer.parseInt(JOptionPane.showInputDialog("Informe o código da disciplina:"));
            int cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog("Informe a carga horária da disciplina:"));

            // Criando o objeto Disciplina e salvando no banco
            DisciplinaDAO.cadastrarDisciplina(new DisciplinaModel(codDisciplina, nomeDisciplina, cargaHoraria));

            JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar disciplina: Código inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    public static void consultarDisciplinasViaSubMenu() {
        List<DisciplinaModel> disciplinas = DisciplinaDAO.getAll();
        if (disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Disciplinas:\n\n");
            for (DisciplinaModel d : disciplinas) {
                lista.append("Código: ").append(d.getCodDisciplina())
                        .append(" - Nome: ").append(d.getNomeDisciplina())
                        .append(" - Carga Horária: ").append(d.getCargaHoraria()).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }

    public static void removerDisciplinaViaSubMenu() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código da disciplina que deseja remover:"));
            boolean removido = DisciplinaDAO.removerDisciplina(codigo);

            if (removido) {
                JOptionPane.showMessageDialog(null, "Disciplina removida com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover disciplina: " + e.getMessage());
        }
    }

    public static void atualizarDisciplinaViaSubMenu() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código da disciplina que deseja atualizar:"));
            DisciplinaModel existente = DisciplinaDAO.buscarDisciplinaPorCodigo(codigo);

            if (existente == null) {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
                return;
            }

            String novoNome = JOptionPane.showInputDialog("Informe o novo nome da disciplina:", existente.getNomeDisciplina());
            int novoCodigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo código da disciplina:", existente.getCodDisciplina()));
            String novaCargaHoraria = JOptionPane.showInputDialog("Informe a nova carga horária:", existente.getCargaHoraria());

            boolean atualizado = DisciplinaDAO.atualizarDisciplina(novoCodigo, novoNome, novaCargaHoraria);

            if (atualizado) {
                JOptionPane.showMessageDialog(null, "Disciplina atualizada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar disciplina.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar disciplina: Formato de número inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    // Métodos auxiliares
    public static List<DisciplinaModel> listarDisciplinas() {
        return DisciplinaDAO.getAll();
    }

    public static boolean removerDisciplina(int codigo) {
        return DisciplinaDAO.removerDisciplina(codigo);
    }

    public static boolean atualizarDisciplina(int codigo, String novoNome, String novaCargaHoraria) {
        return DisciplinaDAO.atualizarDisciplina(codigo, novoNome, novaCargaHoraria);
    }

    public static DisciplinaModel buscarDisciplinaPorCodigo(int codigo) {
        return DisciplinaDAO.buscarDisciplinaPorCodigo(codigo);
    }

    public static void cadastrarDisciplina(int codigo, String nome, int cargaHoraria) {
        DisciplinaDAO.cadastrarDisciplina(new DisciplinaModel(codigo, nome, cargaHoraria));
    }
}
