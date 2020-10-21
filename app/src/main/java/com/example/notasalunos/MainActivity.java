package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.notasalunos.model.Aluno;
import com.example.notasalunos.model.Curso;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Integer ra = null;
    private String nome = null;
    private String curso = null;
    private String turma = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = (Spinner) findViewById(R.id.spinnerCurso);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cursos, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);




    }

    public void avancar(View view) {
        EditText editTxtRa = (EditText) findViewById(R.id.editTxtRA);
        EditText editTxtNome = (EditText) findViewById(R.id.editTxtNome);
        Spinner spinnerCurso = (Spinner) findViewById(R.id.spinnerCurso);
        EditText editTxtTurma = (EditText) findViewById(R.id.editTxtTurma);

        if (!editTxtRa.getText().toString().isEmpty()) {
            this.ra = Integer.parseInt(editTxtRa.getText().toString());
        }
        this.nome = editTxtNome.getText().toString();
        this.curso = spinnerCurso.getSelectedItem().toString();
        this.turma = editTxtTurma.getText().toString();

        Aluno aluno = new Aluno();
        aluno.setRa(this.ra);
        aluno.setNome(this.nome);
        Curso curso = new Curso();
        curso.setDsCurso(this.curso);
        aluno.setCurso(curso);
        aluno.setTurma(this.turma);


        Intent intent = new Intent(this, Disciplinas.class);
        intent.putExtra("aluno", new Gson().toJson(aluno));
        startActivity(intent);

    }

    public void desistir(View view) {
        finishAffinity();
    }
}