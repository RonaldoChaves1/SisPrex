package br.com.superquinto.sisprex;

import java.text.NumberFormat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesActivity extends Activity implements Runnable {

	int id;
	int idUser;
	TextView txtCodigo;
	TextView txtEan;
	TextView txtDescCompleta;
	TextView txtDescResumida;
	TextView txtValor;
	Button btnLogar;
	TextView btnSugerirEdicao;
	Handler handler = new Handler();
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhes);
		
		txtCodigo = (TextView) findViewById(R.id.txtIdentificador);
		txtEan = (TextView) findViewById(R.id.txtEan);
		txtDescCompleta = (TextView) findViewById(R.id.txtDescCompleta);
		txtDescResumida = (TextView) findViewById(R.id.txtDescResumida);
		txtValor = (TextView) findViewById(R.id.txtValor);
		btnLogar = (Button) findViewById(R.id.btnLogar);
		btnSugerirEdicao = (TextView) findViewById(R.id.btnSugerirEdicao);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		idUser = extras.getInt("idUser");
		id = extras.getInt("id");
		txtCodigo.setText(extras.getString("codigo"));
		txtEan.setText(extras.getString("ean"));
		txtDescCompleta.setText(extras.getString("descricao"));
		txtDescResumida.setText(extras.getString("resumida"));
		txtValor.setText(Double.toString(extras.getDouble("valor")));
		Log.i("PesquisaActivity", Integer.toString(idUser));
		btnSugerirEdicao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DetalhesActivity.this, EdicaoActivity.class);
				Bundle extras = new Bundle();
				extras.putInt("id", id);
				extras.putInt("idUser", idUser);
				extras.putString("ean", txtEan.getText().toString());
				extras.putString("descricao", txtDescCompleta.getText().toString());
				extras.putString("resumida", txtDescResumida.getText().toString());
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
		
	}
	
	public void print(View v) {
		dialog = new ProgressDialog(this);
		dialog.setMessage("Aguarde alguns instantes...");
		dialog.setTitle("Carregando...");
		dialog.show();

		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			SisprexWS ws = new SisprexWS();
				final boolean ret = ws.imprimirEtiqueta(getApplicationContext(), id);
				handler.post(new Runnable() {
				
				@Override
				public void run() {
					if (ret) {
						Toast.makeText(DetalhesActivity.this, "Etiqueta Enviadas à impressora.", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(DetalhesActivity.this, "Erro ao tentar imprimir.", Toast.LENGTH_LONG).show();
					}
				}
			});
		} catch (Exception e) {
			Log.e("DetalhesActivity", "Erro: " + e);
		} finally {
			dialog.dismiss();
			finish();
		}
	}
}
