package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.notasalunos.model.Aluno;
import com.example.notasalunos.model.Curso;
import com.example.notasalunos.model.Disciplina;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Disciplinas extends ListActivity {

    private ArrayList<String> disciplinas = new ArrayList<String>();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas);


        Bundle extras = getIntent().getExtras();
        String  jsonMyObject = extras.getString("aluno");
        this.aluno = new Gson().fromJson(jsonMyObject, Aluno.class);

        atualizaListaTarefas();
        limpaTarefa();

        final Button btnInsert = findViewById(R.id.btnGravar);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edtTarefa = (EditText) findViewById(R.id.editTxtDisciplina);
                String tarefa = edtTarefa.getText().toString();
                if ((tarefa != null) && (!tarefa.equals(""))) {
                    disciplinas.add(tarefa);
                    limpaTarefa();
                    atualizaListaTarefas();
                }
            }
        });

    }

    private void atualizaListaTarefas() {
        EditText edtTarefa = (EditText) findViewById(R.id.editTxtDisciplina);
        edtTarefa.setText("");
        edtTarefa.requestFocus();
    }

    public void limpaTarefa() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, disciplinas);
        setListAdapter(arrayAdapter);
    }

    public void voltar(View view){
        finish();
    }

    public void avancar(View view){
        if(!disciplinas.isEmpty()) {
            List<Disciplina> disciplinaList = new ArrayList<Disciplina>();
            for (String disciplina: disciplinas) {
                    Disciplina disciplina1 = new Disciplina();
                    disciplina1.setDsDisciplina(disciplina);
                    disciplinaList.add(disciplina1);
            }
            Curso curso = aluno.getCurso();
            aluno.getCurso().setDisciplinas(disciplinaList);
            System.out.println(aluno);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("disciplina", disciplinas);
            Intent intent = new Intent(this, LancamentoNotas.class);
            intent.putExtra("aluno", new Gson().toJson(aluno));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}