package com.br.atps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.br.atps.Util.DataBase;

import model.Desejo;


public class DetalhesDesejo extends Activity {
    private String id;
    private DataBase db;

    private EditText produto;
    private EditText categoria;
    private EditText preco_minimo;
    private EditText preco_maximo;
    private EditText lojas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_desejo);
        db = new DataBase(this);

        produto = (EditText) findViewById(R.id.edit_nome_produto);
        categoria = (EditText) findViewById(R.id.edit_categoria);
        preco_minimo = (EditText) findViewById(R.id.edit_preco_min);
        preco_maximo = (EditText) findViewById(R.id.edit_preco_max);
        lojas = (EditText) findViewById(R.id.edit_lojas);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("item") != null){
            id = bundle.getString("item");
        }

        Desejo desejo = db.getDesejo(Integer.parseInt(id));

        if(desejo != null){
            produto.setText(desejo.getProduto());
            categoria.setText(desejo.getCategoria());
            preco_maximo.setText(desejo.getpMaximo().toString());
            preco_minimo.setText(desejo.getpMinimo().toString());
            lojas.setText(desejo.getLojas());
        }


    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhes_desejo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
