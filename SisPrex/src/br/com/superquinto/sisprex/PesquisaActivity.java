package br.com.superquinto.sisprex;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import br.com.superquinto.sisprex.IntentIntegrator;
import br.com.superquinto.sisprex.IntentResult;

public class PesquisaActivity extends Activity {

	EditText txtEanDescricao;
	ImageButton btnLeitor;
	ImageButton btnConsultar;
	ListView listaProdutos;
	List<Produto> lista;
	int idUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pesquisa);
		txtEanDescricao = (EditText) findViewById(R.id.txtEanDescricao);
		btnLeitor = (ImageButton) findViewById(R.id.btnScanear);
		btnConsultar = (ImageButton) findViewById(R.id.btnPesquisar);
		listaProdutos = (ListView) findViewById(R.id.listaProdutos);
		listaProdutos.setOnItemClickListener(selecionado);
		
		Intent i = getIntent();
		Bundle b = i.getExtras();
		idUser = b.getInt("idUser");
		Log.i("PesquisaActivity", Integer.toString(idUser));
	}
	
	OnItemClickListener selecionado = new OnItemClickListener() {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Produto p = lista.get(position);
			Intent intent = new Intent(PesquisaActivity.this, DetalhesActivity.class);
			Bundle extras = new Bundle();
			extras.putInt("idUser", idUser);
			extras.putInt("id", p.getId());
			extras.putString("codigo", p.getCodigo());
			extras.putString("ean", p.getEan());
			extras.putString("descricao", p.getDescricao());
			extras.putString("resumida", p.getResumida());
			extras.putDouble("valor", p.getValor());
			intent.putExtras(extras);
			startActivity(intent);
		}
	};
	
	public void escanear(View v) {
		IntentIntegrator integrator = new IntentIntegrator(PesquisaActivity.this);
		integrator.initiateScan();
	}
	
	public void carregar(View v) {
		lista = consultar(txtEanDescricao.getText().toString());
		ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
		listaProdutos.setAdapter(adapter);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (result != null) {
		if (result.getContents() == null) {
			Log.d("MainActivity", "Cancelado");
			Toast.makeText(PesquisaActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
		} else {
			Log.d("MainActivity", "Escaneado");
			Toast.makeText(PesquisaActivity.this, "Escaneado: " + result.getContents(), Toast.LENGTH_SHORT).show();
			txtEanDescricao.setText(result.getContents());
			lista = consultar(result.getContents());
		}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
	
	public List<Produto> consultar(String texto) {
		Banco b = new Banco(getApplicationContext());
		return b.listarProdutos(texto);
	}
}
