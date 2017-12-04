/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.superquinto.sisprex.ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ronaldo Chaves
 */
@WebService(serviceName = "SisprexWS")
public class SisprexWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarUsuarios")
    public List<Usuario> ListarUsuarios() {
        List<Usuario> lista = new ArrayList<Usuario>();
        lista = new SisprexDAO().listaUsuarios();
        return lista;
    }
    @WebMethod(operationName = "consultarProdutos")
    public List<Produto> consultarProdutos(@WebParam(name = "texto") String txt) {
        List<Produto> lista = new ArrayList<Produto>();
        lista = new SisprexDAO().consultaProduto(txt);
        return lista;
    }
    @WebMethod(operationName = "imprimirEtiqueta")
    public Boolean imprimirEtiqueta(@WebParam(name = "id") int id) {
        return new SisprexDAO().imprimirEtiqueta(id);
    }
    @WebMethod(operationName = "sugerirEdicao")
    public Boolean sugerirEdicao(@WebParam(name = "idProduto") int idProduto, @WebParam(name = "campo") String campo, @WebParam(name = "valorAnterior") String valorAnterior, @WebParam(name = "valorAtual") String valorAtual, @WebParam(name = "sugeridoPo") int sugeridoPor) {
        return new SisprexDAO().sugerirEdicao(new Edicao(0, idProduto, campo, valorAnterior, valorAtual, sugeridoPor, null, false, 0, null));
    }
}
