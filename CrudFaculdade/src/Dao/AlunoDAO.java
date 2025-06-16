package Dao;

import ConexaoBD.ConexaoBD;
import Model.AlunoModel;
import Model.CursoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static void cadastrarAluno(AlunoModel aluno) {
        String sql = "INSERT INTO alunos (matriculaAluno, nomeAluno, idadeAluno, codCurso) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, aluno.getMatriculaAluno());
            statement.setString(2, aluno.getNomeAluno());
            statement.setInt(3, aluno.getIdadeAluno());
            statement.setInt(4, aluno.getCurso().getCodCurso());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar aluno: " + ex.getMessage(), ex);
        }
    }

    public static List<AlunoModel> getAll() {
        List<AlunoModel> listaAlunos = new ArrayList<>();
        String sql = "SELECT a.matriculaAluno, a.nomeAluno, a.idadeAluno, " +
                "c.codCurso, c.nomeCurso, c.turnoCurso " +
                "FROM alunos a JOIN cursos c ON a.codCurso = c.codCurso";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                CursoModel curso = new CursoModel(
                        rs.getString("nomeCurso"),
                        rs.getInt("codCurso"),
                        rs.getString("turnoCurso")
                );


                AlunoModel aluno = new AlunoModel(
                        rs.getString("nomeAluno"),
                        rs.getInt("idadeAluno"),
                        rs.getInt("matriculaAluno"),
                        curso
                );

                listaAlunos.add(aluno);
            }


        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar alunos: " + ex.getMessage(), ex);
        }

        return listaAlunos;
    }


    public static boolean removerAluno(int matricula) {
        String sql = "DELETE FROM alunos WHERE matriculaAluno = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, matricula);
            int linhasAfetadas = statement.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover aluno: " + ex.getMessage(), ex);
        }
    }


    public static boolean atualizarAluno(int matricula, String novoNome, int novaIdade) {
        String sql = "UPDATE alunos SET nomeAluno = ?, idadeAluno = ? WHERE matriculaAluno = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, novoNome);
            statement.setInt(2, novaIdade);
            statement.setInt(3, matricula);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar aluno: " + ex.getMessage(), ex);
        }
    }


    public static AlunoModel buscarAlunoPorMatricula(int matricula) {
        String sql = "SELECT a.matriculaAluno, a.nomeAluno, a.idadeAluno, " +
                "c.codCurso, c.nomeCurso, c.turnoCurso " +
                "FROM alunos a JOIN cursos c ON a.codCurso = c.codCurso " +
                "WHERE a.matriculaAluno = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, matricula);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                CursoModel curso = new CursoModel(
                        rs.getString("nomeCurso"),
                        rs.getInt("codCurso"),
                        rs.getString("turnoCurso")
                );


                return new AlunoModel(
                        rs.getString("nomeAluno"),
                        rs.getInt("idadeAluno"),
                        rs.getInt("matriculaAluno"),
                        curso
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar aluno: " + ex.getMessage(), ex);
        }

        return null;
    }
}

