package br.com.superquinto.sisprex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class SisprexWS {
	
	String web = "http://192.168.2.201:8080/SisPrex/SisprexWS?wsdl";
	public static boolean verificaConexao(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if ((ni != null) && (ni.isConnectedOrConnecting()) && (ni.isAvailable())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean imprimirEtiqueta(Context context, int id) {
		try {
			if (verificaConexao(context)) {
				SoapObject soap = new SoapObject("http://ws.sisprex.superquinto.com.br/", "imprimirEtiqueta");
				soap.addProperty("id", id);
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(soap);
				HttpTransportSE httpTrans = new HttpTransportSE(web,
						60000);
				httpTrans.call("imprimirEtiqueta", envelope);
				Object resultado = (Object) envelope.getResponse();
				return Boolean.parseBoolean(resultado.toString());
			}
		} catch (Exception e) {
			Log.e("SisprexWS", "Erro ao sugerir imprimir etiqueta: " + e);
		}
		return false;
	}

	public boolean sugerirEdicao(Context context, Edicao ed) {
		try {
			if (verificaConexao(context)) {
				SoapObject soap = new SoapObject("http://ws.sisprex.superquinto.com.br/", "sugerirEdicao");
				soap.addProperty("idProduto", ed.getidProduto());
				soap.addProperty("campo", ed.getCampo());
				soap.addProperty("valorAnterior", ed.getValorAnterior());
				soap.addProperty("valorAtual", ed.getValorAtual());
				soap.addProperty("sugeridoPor", ed.getSugeridoPor());
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(soap);
				HttpTransportSE httpTrans = new HttpTransportSE(web,
						60000);
				httpTrans.call("sugerirEdicao", envelope);
				Object resultado = (Object) envelope.getResponse();
				return Boolean.parseBoolean(resultado.toString());
			}
		} catch (Exception e) {
			Log.e("SisprexWS", "Erro ao sugerir edição: " + e);
		}
		return false;
	}

	public List<Usuario> listaUsuarios(Context context) {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			if (verificaConexao(context)) {
				SoapObject soap = new SoapObject("http://ws.sisprex.superquinto.com.br/", "listarUsuarios");
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(soap);
				HttpTransportSE httpTrans = new HttpTransportSE(web,
						60000);
				httpTrans.call("listarUsuarios", envelope);
				Vector<SoapObject> resultado = (Vector<SoapObject>) envelope.getResponse();
				for (SoapObject res : resultado) {
					Usuario u = new Usuario();
					u.setId(Integer.parseInt(res.getProperty("id").toString()));
					u.setNome(res.getProperty("nome").toString());
					u.setUsuario(res.getProperty("usuario").toString());
					u.setSenha(res.getProperty("senha").toString());
					lista.add(u);
				}
			}
		} catch (Exception e) {
			Log.e("SisprexWS", "Erro ao listarUsuarios: " + e);
			return null;
		}
		return lista;
	}

	public List<Produto> listaProdutos(Context context, String texto) {
		List<Produto> lista = new ArrayList<Produto>();
		try {
			if (verificaConexao(context)) {
				SoapObject soap = new SoapObject("http://ws.sisprex.superquinto.com.br/", "consultarProdutos");
				soap.addProperty("texto", texto);
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(soap);
				HttpTransportSE httpTrans = new HttpTransportSE(web,
						120000);
				httpTrans.call("consultarProdutos", envelope);
				Vector<SoapObject> resultado = (Vector<SoapObject>) envelope.getResponse();
				for (SoapObject res : resultado) {
					Produto p = new Produto();
					p.setId(Integer.parseInt(res.getProperty("id").toString()));
					p.setCodigo(res.getProperty("codigo").toString());
					p.setEan(res.getProperty("ean").toString());
					p.setDescricao(res.getProperty("descricao").toString());
					p.setResumida(res.getProperty("resumida").toString());
					p.setValor(Double.parseDouble(res.getProperty("valor").toString()));
					lista.add(p);
				}
			}
		} catch (Exception e) {
			Log.e("SisprexWS", "Erro ao listar Produtos: " + e);
			return null;
		}
		return lista;
	}
}
