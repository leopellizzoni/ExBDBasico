package com.leopellizzoni.exemplobdbasico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    * REFERÊNCIAS
    * https://developer.android.com/training/data-storage/sqlite
    * https://developer.android.com/tools/adb#shellcommands
    * https://www.sqlite.org/datatype3.html
    * https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
    * https://developer.android.com/reference/android/database/sqlite/SQLiteCursor
    * https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase
    *
    * Software de visualização dos arquivos de banco de dados
    * https://sqlitebrowser.org/dl/
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NossoBDHelper nossoBDHelper = new NossoBDHelper(MainActivity.this);

        EditText edtChave = findViewById(R.id.edtChave);
        EditText edtNome = findViewById(R.id.edtNome);
        EditText edtDescricao = findViewById(R.id.edtDescricao);

        Button btnInserir = findViewById(R.id.btnTestBD);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long reg = nossoBDHelper.adicionarNovaInformacao(edtNome.getText().toString(), edtDescricao.getText().toString());
                Toast.makeText(MainActivity.this, "Reg. Adicionados: " + reg, Toast.LENGTH_LONG).show();
            }
        });

        Button btnAtualizar = findViewById(R.id.btnAtualizarBD);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long reg = nossoBDHelper.atualizar(edtChave.getText().toString(), edtNome.getText().toString(), edtDescricao.getText().toString());
                Toast.makeText(MainActivity.this, "Reg. Atualizados: " + reg, Toast.LENGTH_LONG).show();
            }
        });

        Button btnPKs = findViewById(R.id.btnBuscarChaves);
        btnPKs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todasChaves = nossoBDHelper.obterTodasChaves();
                Toast.makeText(MainActivity.this, "PKs: " + todasChaves, Toast.LENGTH_LONG).show();
            }
        });

        Button btnDeletar = findViewById(R.id.btnDeletar);
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long regAfetados = nossoBDHelper.deletar(edtChave.getText().toString());
                Toast.makeText(MainActivity.this, "Registros Deletados: " + regAfetados, Toast.LENGTH_LONG).show();
            }
        });
    }
}