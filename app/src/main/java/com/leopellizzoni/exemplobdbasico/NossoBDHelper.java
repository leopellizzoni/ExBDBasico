package com.leopellizzoni.exemplobdbasico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NossoBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "bdexemplo"; // Nome do banco de dados
    private static final int DB_VERSION = 1; //Versão do banco de dados. Testar 1 e 2.
    private static final String DB_TABELA = "TESTE";

    public NossoBDHelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        criarEstrutura(db);
    }

    public long adicionarNovaInformacao(String nome, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues inf = new ContentValues();
        inf.put("NOME", nome);
        inf.put("DESCRICAO", desc);

        long r = db.insert(DB_TABELA, null, inf);

        db.close();

        return r;
    }

    public long atualizar(String chave, String nome, String desc){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues inf = new ContentValues();
        inf.put("NOME", nome);
        inf.put("DESCRICAO", desc);

        long r = db.update(DB_TABELA, inf, "_id = ?", new String[]{ chave });

        db.close();

        return r;
    }

    public long deletar(String chave){
        SQLiteDatabase db = this.getWritableDatabase();

        long r = db.delete(DB_TABELA, "_id = ?", new String[]{ chave });

        db.close();

        return r;
    }

    public String obterTodasChaves(){
        SQLiteDatabase db = this.getWritableDatabase();

        String todasChaves = "";
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABELA, null);

        if (cursor.moveToFirst()) {
            do {
                todasChaves += " " + cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return todasChaves;
    }

    private void criarEstrutura(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABELA);

        String query = "CREATE TABLE " + DB_TABELA + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NOME TEXT,"
                + "DESCRICAO TEXT)";

        db.execSQL(query);
    }

    private void modificacoesVersao2(SQLiteDatabase db){
        String query = "ALTER TABLE " + DB_TABELA + " ADD COLUMN ANOTACOES TEXT ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2){
            modificacoesVersao2(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2){
            //Executa comandos para regredir o BD
        }
    }
}