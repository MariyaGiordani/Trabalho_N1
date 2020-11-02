package com.example.trabalho_n1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    public static void inserir(Context context, Carro carro) {
        ContentValues valores = new ContentValues();
        valores.put("marca", carro.getMarca());
        valores.put("placa", carro.getPlaca());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("carros", null, valores);
    }

    public static void editar(Context context, Carro carro) {
        ContentValues valores = new ContentValues();
        valores.put("marca", carro.getMarca());
        valores.put("placa", carro.getPlaca());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("carros", valores, " id = " + carro.getId(), null);
    }

    public static void excluir(Context context, int idCarro) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("carros", " id = " + idCarro, null);
    }

    public static List<Carro> getCarros(Context context){
        List<Carro> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM carros ORDER BY marca", null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Carro c = new Carro();
                c.setId(cursor.getInt(0));
                c.setMarca(cursor.getString(1));
                c.setPlaca(cursor.getString(2));
                lista.add(c);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public static Carro getCarroById(Context context, int idCarro){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM carros WHERE id = " + idCarro , null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Carro c = new Carro();
            c.setId(cursor.getInt(0));
            c.setMarca(cursor.getString(1));
            c.setPlaca(cursor.getString(2));
            return c;
        }else {
            return null;
        }
    }
}
