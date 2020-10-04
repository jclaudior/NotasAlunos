package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LancamentoNotas extends AppCompatActivity {

    private List<String> disciplinas =  null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_notas);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        this.disciplinas = b.getStringArrayList("disciplina");
        Spinner spinner = (Spinner) findViewById(R.id.spinnerDisciplina);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, disciplinas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}