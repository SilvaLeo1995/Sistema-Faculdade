package Model;

public class AlunoModel {

    private String nomeAluno;

    private int idadeAluno;

    private int matriculaAluno;

    private CursoModel curso;

    public AlunoModel(String nomeAluno, int idadeAluno, int matriculaAluno, CursoModel curso) {
        this.nomeAluno = nomeAluno;
        this.idadeAluno = idadeAluno;
        this.matriculaAluno = matriculaAluno;
        this.curso = curso;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getIdadeAluno() {
        return idadeAluno;
    }

    public void setIdadeAluno(int idadeAluno) {
        this.idadeAluno = idadeAluno;
    }

    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public CursoModel getCurso() {
        return curso;
    }

    public void setCursoModel(CursoModel curso) {
        this.curso = curso;
    }
}

