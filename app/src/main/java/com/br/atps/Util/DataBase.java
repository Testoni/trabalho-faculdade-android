package com.br.atps.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Desejo;

/**
 * Created by ismael on 26/06/2015.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE = "ATPS";
    private static final int VERSAO = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_PRODUTO = "produto";
    private static final String KEY_CATEGORIA = "categoria";
    private static final String KEY_LOJAS = "lojas";
    private static final String KEY_PMINIMO = "preco_minimo";
    private static final String KEY_PMAXIMO = "preco_maximo";

    public DataBase(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE DESEJO(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_PRODUTO + " TEXT NOT NULL," +
                KEY_CATEGORIA + " TEXT," +
                KEY_LOJAS + " TEXT," +
                KEY_PMINIMO + " DOUBLE PRECISION,"+
                KEY_PMAXIMO +" DOUBLE PRECISION);";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DESEJO");
        this.onCreate(db);
    }

    public void novoDesejo(Desejo desejo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUTO, desejo.getProduto());
        values.put(KEY_CATEGORIA, desejo.getCategoria());
        values.put(KEY_LOJAS, desejo.getLojas());
        values.put(KEY_PMINIMO, desejo.getpMinimo());
        values.put(KEY_PMAXIMO, desejo.getpMaximo());

        db.insert("DESEJO", null, values);
        db.close();
    }

    public Desejo getDesejo(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Desejo desejo = null;

        Cursor cursor = db.rawQuery("SELECT * FROM DESEJO WHERE id = '"+id+"'", null);

        if (cursor != null)
            if(cursor.moveToFirst()) {

                try {
                    desejo = new Desejo();
                    desejo.setId(cursor.getInt(0));
                    desejo.setProduto(cursor.getString(1));
                    desejo.setCategoria(cursor.getString(2));
                    desejo.setLojas(cursor.getString(3));
                    desejo.setpMinimo(cursor.getDouble(4));
                    desejo.setpMaximo(cursor.getDouble(5));


                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        return desejo;
    }

    public List<Desejo> getAllDesejos() {
        List<Desejo> desejoList = new ArrayList<Desejo>();

        String selectQuery = "SELECT  * FROM DESEJO";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Desejo desejo = new Desejo();
                desejo.setId(cursor.getInt(0));
                desejo.setProduto(cursor.getString(1));
                desejo.setCategoria(cursor.getString(2));
                desejo.setLojas(cursor.getString(3));
                desejo.setpMinimo(cursor.getDouble(4));
                desejo.setpMaximo(cursor.getDouble(5));

                desejoList.add(desejo);
            } while (cursor.moveToNext());
        }

        return desejoList;
    }

    public int updateDesejo(Desejo desejo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUTO, desejo.getProduto());
        values.put(KEY_CATEGORIA, desejo.getCategoria());
        values.put(KEY_LOJAS, desejo.getLojas());
        values.put(KEY_PMINIMO, desejo.getpMinimo());
        values.put(KEY_PMAXIMO, desejo.getpMaximo());
        return db.update("DESEJO", values, KEY_ID + " = ?",
                new String[] { String.valueOf(desejo.getId()) });
    }

    public void deleteDesejo(Desejo desejo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DESEJO", KEY_ID + " = ?",
                new String[] { String.valueOf(desejo.getId()) });
        db.close();
    }
}
