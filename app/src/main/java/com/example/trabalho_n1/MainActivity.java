package com.example.trabalho_n1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvCarros = findViewById(R.id.lvCarros);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity( intent );
            }
        });

        carregarCarros();

        lvCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro prodSelecionado = (Carro) parent.getItemAtPosition( position );

                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idCarro", prodSelecionado.getId() );
                startActivity( intent );
            }
        });

        lvCarros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Carro prodSelecionado = (Carro) parent.getItemAtPosition( position );
                excluir(prodSelecionado);
                return true;
            }
        });
    }

    private void excluir(final Carro prod){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Carro");
        alerta.setMessage("Confirma a exclusão do carro" + prod.getMarca() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CarroDAO.excluir(MainActivity.this, prod.getId());
                carregarCarros();
            }
        });
        alerta.show();
    }

    private void carregarCarros(){
        List<Carro> lista = CarroDAO.getCarros(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvCarros.setAdapter( adapter );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarCarros();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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