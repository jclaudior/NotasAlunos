package com.example.notasalunos.model;

public class Disciplina {
    private String dsDisciplina;
    private double notaA1;
    private double notaA2;
    private double notaAf;
    private double media;

    public String getDsDisciplina() {
        return dsDisciplina;
    }

    public void setDsDisciplina(String dsDisciplina) {
        this.dsDisciplina = dsDisciplina;
    }

    public double getNotaA1() {
        return notaA1;
    }

    public void setNotaA1(double notaA1) {
        this.notaA1 = notaA1;
    }

    public double getNotaA2() {
        return notaA2;
    }

    public void setNotaA2(double notaA2) {
        this.notaA2 = notaA2;
    }

    public double getNotaAf() {
        return notaAf;
    }

    public void setNotaAf(double notaAf) {
        this.notaAf = notaAf;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return dsDisciplina;
    }

}
