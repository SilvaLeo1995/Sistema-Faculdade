package Dao;

import ConexaoBD.ConexaoBD;
import Model.CursoModel;
import Model.DisciplinaModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public static void cadastrarCurso(CursoModel curso) {
        String sqlCurso = "INSERT INTO cursos (codCurso, nomeCurso, turnoCurso) VALUES (?, ?, ?)";
        String sqlRelacionamento = "INSERT INTO curso_disciplinas (codCurso, codDisciplina) VALUES (?, ?)";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statementCurso = conexao.prepareStatement(sqlCurso);
             PreparedStatement statementRelacao = conexao.prepareStatement(sqlRelacionamento)) {

            // Inserindo curso
            statementCurso.setInt(1, curso.getCodCurso());
            statementCurso.setString(2, curso.getNomeCurso());
            statementCurso.setString(3, curso.getTurnoCurso());
            statementCurso.executeUpdate();

            // Inserindo disciplinas do curso
            for (DisciplinaModel disciplina : curso.getDisciplinas()) {
                statementRelacao.setInt(1, curso.getCodCurso());
                statementRelacao.setInt(2, disciplina.getCodDisciplina());
                statementRelacao.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar curso: " + ex.getMessage(), ex);
        }
    }

    public static List<CursoModel> getAll() {
        List<CursoModel> listaCursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                CursoModel curso = new CursoModel(
                        rs.getString("nomeCurso"),
                        rs.getInt("codCurso"),
                        rs.getString("turnoCurso")
                );

                // Buscar disciplinas associadas
                curso.setDisciplinas(getDisciplinasDoCurso(curso.getCodCurso()));

                listaCursos.add(curso);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar cursos: " + ex.getMessage(), ex);
        }

        return listaCursos;
    }

    public static CursoModel buscarCursoPorCodigo(int codCurso) {
        String sql = "SELECT * FROM cursos WHERE codCurso = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, codCurso);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                CursoModel curso = new CursoModel(
                        rs.getString("nomeCurso"),
                        rs.getInt("codCurso"),
                        rs.getString("turnoCurso")
                );

                curso.setDisciplinas(getDisciplinasDoCurso(codCurso));

                return curso;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar curso: " + ex.getMessage(), ex);
        }

        return null;
    }

    public static boolean atualizarCurso(int codCurso, String novoNome, String novoTurno) {
        String sql = "UPDATE cursos SET nomeCurso = ?, turnoCurso = ? WHERE codCurso = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, novoNome);
            statement.setString(2, novoTurno);
            statement.setInt(3, codCurso);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar curso: " + ex.getMessage(), ex);
        }
    }

    public static boolean removerCurso(int codCurso) {
        String deleteRelacoes = "DELETE FROM curso_disciplinas WHERE codCurso = ?";
        String deleteCurso = "DELETE FROM cursos WHERE codCurso = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statementRelacoes = conexao.prepareStatement(deleteRelacoes);
             PreparedStatement statementCurso = conexao.prepareStatement(deleteCurso)) {

            statementRelacoes.setInt(1, codCurso);
            statementRelacoes.executeUpdate();

            statementCurso.setInt(1, codCurso);
            int linhasAfetadas = statementCurso.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover curso: " + ex.getMessage(), ex);
        }
    }

    private static List<DisciplinaModel> getDisciplinasDoCurso(int codCurso) {
        List<DisciplinaModel> disciplinas = new ArrayList<>();
        String sql = "SELECT d. * FROM disciplinas d " +
                "JOIN curso_disciplinas cd ON d.codDisciplina = cd.codDisciplina " +
                "WHERE cd.codCurso = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, codCurso);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                DisciplinaModel disciplina = new DisciplinaModel(
                        rs.getInt("codDisciplina"),
                        rs.getString("nomeDisciplina"),
                        rs.getInt("cargaHoraria")
                );
                disciplinas.add(disciplina);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar disciplinas do curso: " + ex.getMessage(), ex);
        }

        return disciplinas;
    }
}