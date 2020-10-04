package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MediaNota extends AppCompatActivity {

    private Double media = null;
    private String disciplina = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_nota);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        this.disciplina = b.getString("disciplina");
        this.media = b.getDouble("media");

        TextView txtDiciplina = (TextView) findViewById(R.id.txtDisciplinaView);
        TextView txtFinal = (TextView) findViewById(R.id.txtNotaView);
        TextView txtStatus = (TextView) findViewById(R.id.txtStatus);


        txtDiciplina.setText(this.disciplina);
        txtFinal.setText(String.valueOf(this.media));

        if(media >= 6){
            txtStatus.setText("Parabéns, Você foi Aprovado!");
            txtStatus.setTextColor(Color.GREEN);
        }else{
            txtStatus.setText("Infelizmente, você esta reprovado!");
            txtStatus.setTextColor(Color.RED);
        }
    }

    public void voltar(View view) {
        finish();
    }
}