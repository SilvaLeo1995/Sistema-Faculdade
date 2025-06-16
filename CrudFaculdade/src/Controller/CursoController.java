package Controller;

import Dao.CursoDAO;
import Dao.DisciplinaDAO;
import Model.CursoModel;
import Model.DisciplinaModel;

import java.util.List;

import javax.swing.*;



public class CursoController {

    public static void cadastrarCursoViaSubMenu() {
        try {
            String nomeCurso = JOptionPane.showInputDialog("Informe o nome do curso: ");
            int codCurso = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do curso: "));
            String turnoCurso = JOptionPane.showInputDialog("Informe o turno do curso (ex: Manhã, Tarde, Noite): ");

            CursoModel curso = new CursoModel(nomeCurso, codCurso, turnoCurso);

            List<DisciplinaModel> disciplinasDisponiveis = DisciplinaDAO.getAll();

            if(disciplinasDisponiveis.isEmpty()){
                JOptionPane.showMessageDialog(null, "Nenhuma disciplina disponível. Cadastre uma disciplina primeiro. ");
            } else{
                for(DisciplinaModel disciplina : disciplinasDisponiveis){ // esse método vai informar atrves de uma pergunta se o usuário quer cadastrar a disiciplina criada ao curso, através do SIM ou NÂO.
                    int opcaoUsuario = JOptionPane.showConfirmDialog(null, "Deseja adicionar a disciplina " + disciplina.getNomeDisciplina() + " ao curso? ", "Adicionar Disciplina", JOptionPane.YES_NO_OPTION);

                if(opcaoUsuario == JOptionPane.YES_OPTION){  // dentro do if, se o usuário optar por SIM, a disciplina é adicionada ao curso.
                    curso.getDisciplinas().add(disciplina);
                  }
                }
             }

            CursoDAO.cadastrarCurso(curso);

            JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar curso: " + ex.getMessage());
        }
    }


    public static void consultarCursoViaSubMenu() {
        List<CursoModel> cursos = CursoDAO.getAll();
        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Cursos:\n\n");
            for (CursoModel curso : cursos) {
                lista.append("Código: ").append(curso.getCodCurso())
                        .append(" - Nome: ").append(curso.getNomeCurso()).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }



    public static void removerCursoViaSubMenu() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do curso que deseja remover:"));
            boolean removido = CursoDAO.removerCurso(codigo);
            if (removido) {
                JOptionPane.showMessageDialog(null, "Curso removido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Curso não encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Código inválido.");
        }
    }



    public static void atualizarCursoViaSubMenu() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do curso que deseja atualizar:"));
            CursoModel cursoExistente = CursoDAO.buscarCursoPorCodigo(codigo);

            if (cursoExistente == null) {
                JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                return;
            }

            String novoTurno = JOptionPane.showInputDialog("Informe o novo turno do curso: ", cursoExistente.getTurnoCurso());
            String novoNome = JOptionPane.showInputDialog("Informe o novo nome do curso: ", cursoExistente.getNomeCurso());
            boolean atualizado = CursoDAO.atualizarCurso(codigo, novoNome, novoTurno);

            if (atualizado) {
                JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar curso.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar curso: " + ex.getMessage());
        }
    }

    // Métodos auxiliares
    public static List<CursoModel> listarCursos() {
        return CursoDAO.getAll();
    }

    public static boolean removerCurso(int codigo) {
        return CursoDAO.removerCurso(codigo);
    }

    public static boolean atualizarCurso(int codCurso, String novoNome, String novoTurno) {
        return CursoDAO.atualizarCurso(codCurso, novoNome, novoTurno);
    }

    public static CursoModel buscarCursoPorCodigo(int codigo) {
        return CursoDAO.buscarCursoPorCodigo(codigo);
    }

    public static void cadastrarCurso(String nomeCurso, int codCurso, String turnoCurso) {
        CursoDAO.cadastrarCurso(new CursoModel(nomeCurso, codCurso, turnoCurso));
    }
}
