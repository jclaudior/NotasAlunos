package com.example.notasalunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notasalunos.model.Aluno;
import com.example.notasalunos.model.Disciplina;
import com.google.gson.Gson;

import java.util.List;

public class LancamentoNotasAf extends AppCompatActivity {

    private List<String> disciplinas =  null;
    private String disciplina = null;
    private Double notaA1 = null;
    private Double notaA2 = null;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_notas);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String  jsonMyObject = b.getString("aluno");
        this.aluno = new Gson().fromJson(jsonMyObject, Aluno.class);
        this.disciplinas = b.getStringArrayList("disciplina");
        Spinner spinner = (Spinner) findViewById(R.id.spinnerDisciplina);
        ArrayAdapter<Disciplina> adapter =
                new ArrayAdapter<Disciplina>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, aluno.getCurso().getDisciplinas());
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, disciplinas);
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
            for(int i = 0; i < aluno.getCurso().getDisciplinas().size(); i ++ ){
                if(aluno.getCurso().getDisciplinas().get(i).getDsDisciplina() == spinnerDisciplina.getSelectedItem().toString()){
                    aluno.getCurso().getDisciplinas().get(i).setNotaA1(notaA1);
                    aluno.getCurso().getDisciplinas().get(i).setNotaA2(notaA2);
                    aluno.getCurso().getDisciplinas().get(i).setMedia(media);
                }
            }



            Bundle bundle = new Bundle();
            bundle.putDouble("media", media);
            bundle.putString("disciplina", disciplina);
            Intent intent = new Intent(this, MediaNota.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}