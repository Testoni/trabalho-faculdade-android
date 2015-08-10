package com.br.atps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.br.atps.Util.DataBase;
import com.br.atps.Util.DesejoAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Desejo;


public class MainActivity extends Activity {

    private ListView listDesejos;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase db = new DataBase(this);

        listDesejos = (ListView) findViewById(R.id.listDesejos);

        List<Desejo> ls = new ArrayList<Desejo>();

        ls.addAll(db.getAllDesejos());

        DesejoAdapter adapter = new DesejoAdapter(this, android.R.layout.simple_list_item_1, ls);
        listDesejos.setAdapter(adapter);

        listDesejos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Desejo desejo = (Desejo) parent.getItemAtPosition(position);
                String oid = desejo.getId().toString();
                Intent i = new Intent(MainActivity.this, DetalhesDesejo.class);
                i.putExtra("item", oid);
                startActivity(i);

            }
        });

        listDesejos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, AlterarDesejo.class);
                startActivity(i);
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.custom_action_bar, menu);
        getMenuInflater().inflate(R.menu.custom_action_bar, menu);

        mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.menu_item_share).getActionProvider();
        mShareActionProvider.setShareIntent(getDefaultShareIntent());
        return super.onCreateOptionsMenu(menu);
    }

    private Intent getDefaultShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_novo_Item:
                Intent i = new Intent(this, InserirDesejos.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
