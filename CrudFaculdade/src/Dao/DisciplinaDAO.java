package Dao;

import ConexaoBD.ConexaoBD;
import Model.DisciplinaModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    // Método para cadastrar uma disciplina
    public static void cadastrarDisciplina(DisciplinaModel disciplina) {
        String sql = "INSERT INTO disciplinas (codDisciplina, nomeDisciplina, cargaHoraria) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, disciplina.getCodDisciplina());
            statement.setString(2, disciplina.getNomeDisciplina());
            statement.setInt(3, disciplina.getCargaHoraria());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir disciplina: " + ex.getMessage(), ex);
        }
    }

    // Método para obter todas as disciplinas
    public static List<DisciplinaModel> getAll() {
        List<DisciplinaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM disciplinas";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                DisciplinaModel disciplina = new DisciplinaModel(
                        rs.getInt("codDisciplina"),
                        rs.getString("nomeDisciplina"),
                        rs.getInt("cargaHoraria")
                );
                lista.add(disciplina);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar disciplinas: " + ex.getMessage(), ex);
        }

        return lista;
    }

    // Método para buscar disciplina por código
    public static DisciplinaModel buscarDisciplinaPorCodigo(int codDisciplina) {
        String sql = "SELECT * FROM disciplinas WHERE codDisciplina = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, codDisciplina);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new DisciplinaModel(
                        rs.getInt("codDisciplina"),
                        rs.getString("nomeDisciplina"),
                        rs.getInt("cargaHoraria")
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar disciplina: " + ex.getMessage(), ex);
        }

        return null; // Retorna null caso não encontre
    }

    // Método para atualizar uma disciplina
    public static boolean atualizarDisciplina(int codDisciplina, String novoNome, String novaCargaHoraria) {
        String sql = "UPDATE disciplinas SET nomeDisciplina = ?, cargaHoraria = ? WHERE codDisciplina = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, novoNome);
            statement.setInt(2, Integer.parseInt(novaCargaHoraria));
            statement.setInt(3, codDisciplina);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se a atualização foi bem-sucedida

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar disciplina: " + ex.getMessage(), ex);
        }
    }

    // Método para remover uma disciplina
    public static boolean removerDisciplina(int codDisciplina) {
        String sql = "DELETE FROM disciplinas WHERE codDisciplina = ?";

        try (Connection conexao = ConexaoBD.ConectarBancoDeDados();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, codDisciplina);
            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se a disciplina foi removida com sucesso

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover disciplina: " + ex.getMessage(), ex);
        }
    }
}
