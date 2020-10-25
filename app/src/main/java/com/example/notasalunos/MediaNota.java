package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.notasalunos.model.Aluno;
import com.google.gson.Gson;

public class MediaNota extends AppCompatActivity {

    private Double media = null;
    private String disciplina = null;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_nota);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String  jsonMyObject = b.getString("aluno");
        this.aluno = new Gson().fromJson(jsonMyObject, Aluno.class);
        this.disciplina = b.getString("disciplina");
        this.media = b.getDouble("media");

        TextView txtDiciplina = (TextView) findViewById(R.id.txtDisciplinaView);
        TextView txtFinal = (TextView) findViewById(R.id.txtNotaView);
        TextView txtStatus = (TextView) findViewById(R.id.txtStatus);

        for(int i = 0; i < this.aluno.getCurso().getDisciplinas().size(); i ++ ) {
            if (this.aluno.getCurso().getDisciplinas().get(i).getDsDisciplina().equals(disciplina)) {
                Button btnVoltar = (Button)findViewById(R.id.btnVoltar);
                txtDiciplina.setText(this.aluno.getCurso().getDisciplinas().get(i).getDsDisciplina());
                txtFinal.setText(String.valueOf(aluno.getCurso().getDisciplinas().get(i).getMedia()));

                if (aluno.getCurso().getDisciplinas().get(i).getMedia() >= 6) {
                    txtStatus.setText("Parabéns, Você foi Aprovado!");
                    txtStatus.setTextColor(Color.GREEN);
                    btnVoltar .setText("Voltar");
                } else if(aluno.getCurso().getDisciplinas().get(i).getMedia() < 6 && aluno.getCurso().getDisciplinas().get(i).getNotaAf() > 0){
                    txtStatus.setText("Infelizmente, você esta reprovado!");
                    txtStatus.setTextColor(Color.RED);
                }else{
                    txtStatus.setText("Necessita realizar AF!");
                    txtStatus.setTextColor(Color.RED);
                    btnVoltar .setText("Lançar Nota AF");
                }
            }
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, LancamentoNotas.class);
        intent.putExtra("aluno", new Gson().toJson(aluno));
        finish();
        startActivity(intent);
    }
}