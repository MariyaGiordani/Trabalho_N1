package com.example.trabalho_n1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "listRegistros";
    private static final int VERSAO_BANCO = 1;

    public Banco(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE carro ( " +
                    " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    " marca TEXT NOT NULL, " +
                    " placa TEXT NOT NULL  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
