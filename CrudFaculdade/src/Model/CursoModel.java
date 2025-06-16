package Model;

import java.util.List;
import java.util.ArrayList;

public class CursoModel {

    private String nomeCurso;

    private int codCurso;

    private String turnoCurso;

    private List<DisciplinaModel> disciplinas;

    public CursoModel(String nomeCurso, int codCurso, String turnoCurso) {
        this.nomeCurso = nomeCurso;
        this.codCurso = codCurso;
        this.turnoCurso = turnoCurso;
        this.disciplinas = new ArrayList<>();
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getTurnoCurso() {
        return turnoCurso;
    }

    public void setTurnoCurso(String turnoCurso) {
        this.turnoCurso = turnoCurso;
    }

    public List<DisciplinaModel> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaModel> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
