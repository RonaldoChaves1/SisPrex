package br.com.superquinto.sisprex;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CargaActivity extends Activity implements Runnable {

	Handler handler = new Handler();
	ProgressDialog dialog;
	Button btnSim;
	Button btnNao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carga);
		
		btnSim = (Button) findViewById(R.id.btnSim);
		btnNao = (Button) findViewById(R.id.btnNao);
	}
	
	public void fazerCarga(View v) {
		dialog = new ProgressDialog(this);
		dialog.setMessage("Aguarde alguns instantes...");
		dialog.setTitle("Carregando...");
		dialog.show();

		Thread t = new Thread(this);
		t.start();
	}

	public void semCarga(View v) {
		Intent intent = new Intent(CargaActivity.this, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);
		finish();
	}
	@Override
	public void run() {
		try {
			if (SisprexWS.verificaConexao(CargaActivity.this)) {
				final SisprexWS ws = new SisprexWS();
				Banco b = new Banco(getApplicationContext());
				final boolean ret = b.cargaUsuarios(ws.listaUsuarios(getApplicationContext()));
				final boolean ret2 = b.cargaProdutos(ws.listaProdutos(getApplicationContext(), null));
				handler.post(new Runnable() {

					@Override
					public void run() {
						if (ret) {
							Toast.makeText(CargaActivity.this, "Sucesso ao fazer carga da tabela usuário.",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(CargaActivity.this, "Erro ao fazer carga da tabela usuário.",
									Toast.LENGTH_LONG).show();
						}
						if (ret2) {
							Toast.makeText(CargaActivity.this, "Sucesso ao fazer carga da tabela produto.",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(CargaActivity.this, "Erro ao fazer carga da tabela produto.",
									Toast.LENGTH_LONG).show();
						}
					}
				});
			} else {
				Toast.makeText(CargaActivity.this, "Sem conexão com a internet.", Toast.LENGTH_SHORT).show();
				finish();
			}
		} catch (Exception e) {
			Log.e("Carga Activity", "Erro: " + e);
		} finally {
			dialog.dismiss();
			Intent intent = new Intent(CargaActivity.this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
			finish();
		}
	}
}
