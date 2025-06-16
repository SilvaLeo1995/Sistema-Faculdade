package View;

import Model.CursoModel;
import java.util.List;


public class AlunoView {

    public static void NomeAluno(){
        System.out.println("Informe o nome do aluno: ");
    }

    public static void IdadeAluno(){
        System.out.println("Informe a idade do aluno: ");
    }


    public static void MatriculaAluno(){
        System.out.println("Informe a matricula do aluno: ");
    }

    public static void CursoAluno(List<CursoModel> cursos){
        System.out.println("Informe o curso do aluno: ");
        int cont = 1;
        for(CursoModel curso : cursos){
            System.out.println(cont + " - " + curso.getNomeCurso());
            cont++;
        }
    }
}
