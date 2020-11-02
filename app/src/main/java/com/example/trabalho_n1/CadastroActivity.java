package com.example.trabalho_n1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {
    private EditText etMarca, etPlaca;
    private Button btnCadastrar;
    private String acao;
    private Carro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        acao = getIntent().getExtras().getString("acao");
        etMarca = findViewById(R.id.etMarca);
        etPlaca = findViewById(R.id.etPlaca);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        if( acao.equals("editar") ) {
            int id = getIntent().getExtras().getInt("idCarro");
            carro = CarroDAO.getCarroById(CadastroActivity.this, id);
            etMarca.setText( carro.getMarca() );
            etPlaca.setText( String.valueOf( carro.getPlaca() ) );

        }
    }

    private void salvar(){
        if( carro == null ){
            carro = new Carro();
        }

        String marca = etMarca.getText().toString();
        if( marca.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atenção!");
            alerta.setMessage("A marca do carro deve ser preenchido!");
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else{
            carro.setMarca( marca );
            String sPlaca = etPlaca.getText().toString();
            if( sPlaca.isEmpty() ){
                carro.setPlaca( "Não registrado" );
            }else {
                carro.setPlaca( sPlaca );
            }

            if( acao.equals("inserir")){
                CarroDAO.inserir(this, carro);
                carro = null;
                etMarca.setText("");
                etPlaca.setText("");
            }else{
                CarroDAO.editar(this, carro);
                finish();
            }
        }
    }
}