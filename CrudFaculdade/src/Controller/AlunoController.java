package Controller;

import Dao.AlunoDAO;
import Dao.CursoDAO;
import Model.AlunoModel;
import Model.CursoModel;
import Model.DisciplinaModel;

import javax.swing.*;
import java.util.List;

public class AlunoController {

    public static void cadastrarAlunoViaSubMenu() {
        try {
            // Verificar se existem cursos cadastrados
            List<CursoModel> cursos = CursoDAO.getAll();
            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há cursos cadastrados. Cadastre um curso primeiro.");
                return;
            }

            // Exibir cursos disponíveis
            StringBuilder listaCursos = new StringBuilder("Escolha um curso para o aluno:\n");
            for (CursoModel curso : cursos) {
                listaCursos.append("Código: ").append(curso.getCodCurso())
                        .append(" - ").append(curso.getNomeCurso()).append("\n");
            }

            // Selecionar curso
            int codigoCurso = Integer.parseInt(JOptionPane.showInputDialog(listaCursos.toString()));
            CursoModel cursoSelecionado = CursoController.buscarCursoPorCodigo(codigoCurso);

            if (cursoSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                return;
            }

            // Cadastro do aluno
            String nomeAluno = JOptionPane.showInputDialog("Informe o nome do aluno:");
            int idadeAluno = Integer.parseInt(JOptionPane.showInputDialog("Informe a idade do aluno:"));
            int matriculaAluno = Integer.parseInt(JOptionPane.showInputDialog("Informe a matrícula do aluno:"));

            AlunoModel aluno = new AlunoModel(nomeAluno, idadeAluno, matriculaAluno, cursoSelecionado);
            AlunoDAO.cadastrarAluno(aluno);

            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno. Dados inválidos: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno: " + ex.getMessage());
        }
    }

    public static void consultarAlunosViaSubMenu() {
        List<AlunoModel> alunos = AlunoDAO.getAll();

        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }

        StringBuilder lista = new StringBuilder("Lista de Alunos:\n\n");
        for (AlunoModel aluno : alunos) {
            lista.append("Nome: ").append(aluno.getNomeAluno()).append("\n")
                    .append("Idade: ").append(aluno.getIdadeAluno()).append("\n")
                    .append("Matrícula: ").append(aluno.getMatriculaAluno()).append("\n")
                    .append("Curso: ").append(aluno.getCurso().getNomeCurso()).append("\n")
                    .append("Disciplinas do Curso:\n");

            for (DisciplinaModel disciplina : aluno.getCurso().getDisciplinas()) {
                lista.append(" - ").append(disciplina.getNomeDisciplina()).append("\n");
            }

            lista.append("\n");
        }

        JOptionPane.showMessageDialog(null, lista.toString());
    }

    public static void removerAlunoViaSubMenu() {
        try {
            int matricula = Integer.parseInt(JOptionPane.showInputDialog("Informe a matrícula do aluno que deseja remover:"));
            boolean removido = removerAluno(matricula);

            if (removido) {
                JOptionPane.showMessageDialog(null, "Aluno removido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Matrícula inválida.");
        }
    }

    public static void atualizarAlunoViaSubMenu() {
        try {
            int matricula = Integer.parseInt(JOptionPane.showInputDialog("Informe a matrícula do aluno que deseja atualizar:"));

            AlunoModel alunoExistente = buscarAlunoPorMatricula(matricula);
            if (alunoExistente == null) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                return;
            }

            String novoNome = JOptionPane.showInputDialog("Informe o novo nome do aluno:", alunoExistente.getNomeAluno());
            int novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Informe a nova idade do aluno:", alunoExistente.getIdadeAluno()));

            boolean atualizado = atualizarAluno(matricula, novoNome, novaIdade);
            if (atualizado) {
                JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Idade inválida.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno: " + ex.getMessage());
        }
    }

    // Métodos auxiliares

    public static List<AlunoModel> listarAlunos() {
        return AlunoDAO.getAll();
    }

    public static boolean removerAluno(int matricula) {
        return AlunoDAO.removerAluno(matricula);
    }

    public static boolean atualizarAluno(int matricula, String novoNome, int novaIdade) {
        return AlunoDAO.atualizarAluno(matricula, novoNome, novaIdade);
    }

    public static AlunoModel buscarAlunoPorMatricula(int matricula) {
        return AlunoDAO.buscarAlunoPorMatricula(matricula);
    }

    public static void cadastrarAluno(String nome, int idade, int matricula, CursoModel curso) {
        AlunoDAO.cadastrarAluno(new AlunoModel(nome, idade, matricula, curso));
    }
}
