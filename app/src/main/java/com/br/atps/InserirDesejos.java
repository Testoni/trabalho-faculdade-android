package com.br.atps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.atps.Util.DataBase;

import java.util.UUID;

import model.Desejo;


public class InserirDesejos extends Activity {

    private EditText nome_produto;
    private EditText categoria_produto;
    private EditText preco_minimo;
    private EditText preco_maximo;
    private EditText lojas;

    private Desejo desejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_desejos);

        nome_produto = (EditText) findViewById(R.id.nome_produto);
        categoria_produto = (EditText) findViewById(R.id.categoria);
        preco_minimo = (EditText) findViewById(R.id.preco_minimo);
        preco_maximo = (EditText) findViewById(R.id.preco_maximo);
        lojas = (EditText) findViewById(R.id.lojas_achar);
        desejo = new Desejo();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inserir_desejos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
        try {
            if(!nome_produto.getText().toString().isEmpty() && !categoria_produto.getText().toString().isEmpty()){
                desejo.setProduto(nome_produto.getText().toString());
                desejo.setCategoria(categoria_produto.getText().toString());
                desejo.setpMinimo(Double.valueOf(preco_minimo.getText().toString()));
                desejo.setpMaximo(Double.valueOf(preco_maximo.getText().toString()));
                desejo.setLojas(lojas.getText().toString());
                DataBase db = new DataBase(this);
                db.novoDesejo(desejo);
            }
        }catch (Throwable e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
            Toast.makeText(this, "Desejo Cadastrado Com Sucesso", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
