package com.example.notasalunos.model;

import java.util.List;

public class Curso {
    private String dsCurso;
    private List<Disciplina> disciplinas;

    public String getDsCurso() {
        return dsCurso;
    }

    public void setDsCurso(String dsCurso) {
        this.dsCurso = dsCurso;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "dsCurso='" + dsCurso + '\'' +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
