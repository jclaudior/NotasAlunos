package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LancamentoNotas extends AppCompatActivity {

    private List<String> disciplinas =  null;
    private String disciplina = null;
    private Double notaA1 = null;
    private Double notaA2 = null;

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


    public void gravar(View view){
        Spinner spinnerDisciplina = (Spinner) findViewById(R.id.spinnerDisciplina);
        EditText nota1 = (EditText) findViewById(R.id.editTxtNotaA1);
        EditText nota2 = (EditText) findViewById(R.id.editTxtNotaA2);

        disciplina = spinnerDisciplina.getSelectedItem().toString();
        if(!nota1.getText().toString().isEmpty() && !nota2.getText().toString().isEmpty()){
            notaA1 = Double.parseDouble(nota1.getText().toString());
            notaA2 = Double.parseDouble(nota2.getText().toString());

            Double media = notaA1 + notaA2;

            Bundle bundle = new Bundle();
            bundle.putDouble("media", media);
            bundle.putString("disciplina", disciplina);
            Intent intent = new Intent(this, MediaNota.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}