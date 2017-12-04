package br.com.superquinto.sisprex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText txtUsuario;
	EditText txtSenha;
	Button btnLogar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtUsuario = (EditText) findViewById(R.id.txtEanDescricao);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		btnLogar = (Button) findViewById(R.id.btnLogar);
	} 
	public void logar(View v) {
		Banco b = new Banco(getApplicationContext());
		Usuario u = b.validarLogin(txtUsuario.getText().toString(), txtSenha.getText().toString());
		if (u != null) {
			Intent intent = new Intent(this, PesquisaActivity.class);
			Bundle extras = new Bundle();
			extras.putInt("idUser", u.getId());
			intent.putExtras(extras);
			startActivity(intent);
			finish();
		}else {
			Toast.makeText(this, "Usuário ou senha inválida.", Toast.LENGTH_SHORT).show();
		}
	}
}
