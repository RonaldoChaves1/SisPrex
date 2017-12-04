package br.com.superquinto.sisprex;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EdicaoActivity extends Activity implements Runnable {

	int id;
	int idUser;
	TextView txtEan;
	TextView txtDescCompleta;
	TextView txtDescResumida;
	EditText txtNovoEan;
	EditText txtNovaDescCompleta;
	EditText txtNovaDescResumida;
	Handler handler = new Handler();
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edicao);

		txtEan = (TextView) findViewById(R.id.txtEan);
		txtDescCompleta = (TextView) findViewById(R.id.txtDescCompleta);
		txtDescResumida = (TextView) findViewById(R.id.txtDescResumida);
		txtNovoEan = (EditText) findViewById(R.id.txtNovoEan);
		txtNovaDescCompleta = (EditText) findViewById(R.id.txtNovaDescCompleta);
		txtNovaDescResumida = (EditText) findViewById(R.id.txtNovaDescResumida);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		id = extras.getInt("id");
		idUser = extras.getInt("idUser");
		txtEan.setText(extras.getString("ean"));
		txtDescCompleta.setText(extras.getString("descricao"));
		txtDescResumida.setText(extras.getString("resumida"));
	}

	public void enviar(View v) {
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
			if (!txtNovoEan.getText().toString().equals("")) {
				ws.sugerirEdicao(getApplicationContext(), new Edicao(0, id, "EAN", txtEan.getText().toString(),
						txtNovoEan.getText().toString(), idUser, null, 0, null));
			}
			if (!txtNovaDescCompleta.getText().toString().equals("")) {
				ws.sugerirEdicao(getApplicationContext(),
						new Edicao(0, id, "Descrição Completa", txtDescCompleta.getText().toString(),
								txtNovaDescCompleta.getText().toString(), idUser, null, 0, null));
			}
			if (!txtNovaDescResumida.getText().toString().equals("")) {
				ws.sugerirEdicao(getApplicationContext(),
						new Edicao(0, id, "Descrição Resumida", txtDescResumida.getText().toString(),
								txtNovaDescResumida.getText().toString(), idUser, null, 0, null));
			}
			handler.post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(EdicaoActivity.this, "Sugestões Enviadas.", Toast.LENGTH_LONG).show();
				}
			});
		} catch (Exception e) {
			Log.e("EdicaoActivity", "Erro: " + e);
		} finally {
			dialog.dismiss();
			finish();
		}
	}
}
