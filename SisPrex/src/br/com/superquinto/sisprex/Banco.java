package br.com.superquinto.sisprex;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco extends SQLiteOpenHelper {

	private static final String NOME_BANCO = "database.db";
	private static final int VERSION = 1;
	private Context context;

	public Banco(Context context) {
		super(context, NOME_BANCO, null, VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table usuario (id integer, nome varchar(50), usuario varchar(50), senha varchar(50))";
		db.execSQL(sql);
		sql = "create table produto (id integer, codigo varchar(20), ean varchar(20), descricao varchar(50), resumida varchar(50), valor decimal(10,2))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	public boolean cargaUsuarios(List<Usuario> list) {
		try {
			String sql = "delete from usuario";
			getWritableDatabase().execSQL(sql);
			for (int i = 0; i < list.size(); i++) {
				ContentValues values = new ContentValues();
				values.put("id", list.get(i).getId());
				values.put("nome", list.get(i).getNome());
				values.put("usuario", list.get(i).getUsuario());
				values.put("senha", list.get(i).getSenha());
				getWritableDatabase().insert("usuario", null, values);
			}
		} catch (Exception e) {
			Log.e("Banco", "Erro ao fazer a carga de usuários: " + e.getMessage());
			return false;
		} finally {
			close();
		}
		return true;
	}
	
	public boolean cargaProdutos(List<Produto> list) {
		try {
			String sql = "delete from produto";
			getWritableDatabase().execSQL(sql);
			for (int i = 0; i < list.size(); i++) {
				ContentValues values = new ContentValues();
				values.put("id", list.get(i).getId());
				values.put("codigo", list.get(i).getCodigo());
				values.put("ean", list.get(i).getEan());
				values.put("descricao", list.get(i).getDescricao());
				values.put("resumida", list.get(i).getResumida());
				values.put("valor", list.get(i).getValor());
				getWritableDatabase().insert("produto", null, values);
			}
			return true;
		} catch (Exception e) {
			Log.e("Banco", "Erro ao fazer a carga de produtos: " + e.getMessage());
		} finally {
			close();
		}
		return false;
	}
	
	public Usuario validarLogin(String usuario, String senha) {
		Usuario u = null;
		String sql = "select * from usuario where usuario = '" + usuario + "' and senha = '" + senha + "'";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		if (c.moveToNext()) {
			u = new Usuario();
			u.setId(c.getInt(c.getColumnIndex("id")));
			u.setNome(c.getString(c.getColumnIndex("nome")));
			u.setUsuario(c.getString(c.getColumnIndex("usuario")));
			u.setSenha(c.getString(c.getColumnIndex("senha")));
		}
		return u;
	}
	
	public List<Produto> listarProdutos(String texto) {
		List<Produto> lista = new ArrayList<Produto>();
		String sql = "select * from produto where ean like '%" + texto + "%' or descricao like '%" + texto + "%' or resumida like '%" + texto + "%'";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		while (c.moveToNext()) {
			Produto p = new Produto();
			p.setId(c.getInt(c.getColumnIndex("id")));
			p.setCodigo(c.getString(c.getColumnIndex("codigo")));
			p.setEan(c.getString(c.getColumnIndex("ean")));
			p.setDescricao(c.getString(c.getColumnIndex("descricao")));
			p.setResumida(c.getString(c.getColumnIndex("resumida")));
			p.setValor(c.getDouble(c.getColumnIndex("valor")));
			lista.add(p);
		}
		return lista;
	}
}
