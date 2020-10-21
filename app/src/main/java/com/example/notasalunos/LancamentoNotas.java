package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.notasalunos.model.Aluno;
import com.example.notasalunos.model.Disciplina;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LancamentoNotas extends AppCompatActivity {

    private List<String> disciplinas =  null;
    private String disciplina = null;
    private Double notaA1 = null;
    private Double notaA2 = null;
    private Double notaAf = null;
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



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            EditText notaAf = (EditText) findViewById(R.id.editTxtNotaAf);
            TextView txtNotaAf = (TextView) findViewById(R.id.txtNotaAF);
            Spinner spinner1 = (Spinner) findViewById(R.id.spinnerDisciplina);
            EditText nota1 = (EditText) findViewById(R.id.editTxtNotaA1);
            EditText nota2 = (EditText) findViewById(R.id.editTxtNotaA2);
            EditText notaaf = (EditText) findViewById(R.id.editTxtNotaAf);
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                for(int i = 0; i < aluno.getCurso().getDisciplinas().size(); i ++ ){
                    if(aluno.getCurso().getDisciplinas().get(i).getDsDisciplina() == spinner1.getSelectedItem().toString()){
                        nota1.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getNotaA1()));
                        nota2.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getNotaA2()));
                        notaaf.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getNotaAf()));
                        if((aluno.getCurso().getDisciplinas().get(i).getMedia() > 0 && aluno.getCurso().getDisciplinas().get(i).getMedia() <= 6)||aluno.getCurso().getDisciplinas().get(i).getNotaAf() > 0){
                            notaAf.setVisibility(View.VISIBLE);
                            txtNotaAf.setVisibility(View.VISIBLE);
                        }else{
                            notaAf.setVisibility(View.GONE);
                            txtNotaAf.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                for(int i = 0; i < aluno.getCurso().getDisciplinas().size(); i ++ ){
                    if(aluno.getCurso().getDisciplinas().get(i).getDsDisciplina() == spinner1.getSelectedItem().toString()){
                        nota1.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getNotaA1()));
                        nota2.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getNotaA2()));
                        notaaf.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getNotaAf()));
                        if((aluno.getCurso().getDisciplinas().get(i).getMedia() > 0 && aluno.getCurso().getDisciplinas().get(i).getMedia() <= 6)||aluno.getCurso().getDisciplinas().get(i).getNotaAf() > 0){
                            notaAf.setVisibility(View.VISIBLE);
                            txtNotaAf.setVisibility(View.VISIBLE);
                        }else{
                            notaAf.setVisibility(View.GONE);
                            txtNotaAf.setVisibility(View.GONE);
                        }
                    }
                }
            }

        });

    }


    public void gravar(View view){
        Spinner spinnerDisciplina = (Spinner) findViewById(R.id.spinnerDisciplina);
        EditText nota1 = (EditText) findViewById(R.id.editTxtNotaA1);
        EditText nota2 = (EditText) findViewById(R.id.editTxtNotaA2);
        EditText notaaf = (EditText) findViewById(R.id.editTxtNotaAf);

        disciplina = spinnerDisciplina.getSelectedItem().toString();
        if(!nota1.getText().toString().isEmpty() && !nota2.getText().toString().isEmpty()){
            Double media;
            notaA1 = Double.parseDouble(nota1.getText().toString());
            notaA2 = Double.parseDouble(nota2.getText().toString());
            if(!notaaf.getText().toString().isEmpty()){
                notaAf = Double.parseDouble(notaaf.getText().toString());
                 media = notaA1 + notaA2 + notaAf;
            }else {
                 media = notaA1 + notaA2;
            }
            for(int i = 0; i < aluno.getCurso().getDisciplinas().size(); i ++ ){
                if(aluno.getCurso().getDisciplinas().get(i).getDsDisciplina() == spinnerDisciplina.getSelectedItem().toString()){
                    aluno.getCurso().getDisciplinas().get(i).setNotaA1(notaA1);
                    aluno.getCurso().getDisciplinas().get(i).setNotaA2(notaA2);
                    aluno.getCurso().getDisciplinas().get(i).setNotaAf(notaAf);
                    aluno.getCurso().getDisciplinas().get(i).setMedia(media);
                }
            }



            Bundle bundle = new Bundle();
            bundle.putDouble("media", media);
            bundle.putString("disciplina", disciplina);
            Intent intent = new Intent(this, MediaNota.class);
            intent.putExtra("aluno", new Gson().toJson(aluno));
            intent.putExtras(bundle);
            finish();
            startActivity(intent);
        }

    }
}