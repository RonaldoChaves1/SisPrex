/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.superquinto.sisprex.web;

import br.com.superquinto.sisprex.ws.Conexao;
import br.com.superquinto.sisprex.ws.Usuario;
import br.com.superquinto.sisprex.ws.Edicao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ronaldo Chaves
 */
public class WebDAO {
    Conexao con;
    
    public List<Edicao> listaAvaliar() {
         List<Edicao> lista = new ArrayList<Edicao>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Edicao e = null;
        try {
            con = new Conexao();
            String sql = "select * from edicao_sugerida WHERE avaliado_por is null;";
            pst = con.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                e = new Edicao();
                e.setId(rs.getInt("id"));
                e.setIdProduto(rs.getInt("nome"));
                e.setCampo(rs.getString("campo"));
                e.setValorAnterior(rs.getString("valor_anterior"));
                e.setValorAtual(rs.getString("valor_atual"));
                e.setSugeridoPor(rs.getInt("sugerido_por"));
                e.setTimeStampSugestao(rs.getTimestamp("timestamp_sugestao"));
                e.setAceito(rs.getBoolean("aceito"));
                e.setAvaliadoPor(rs.getInt("avaliado_por"));
                e.setTimestampAlteracao(rs.getTimestamp("timestamp_aceito"));
                lista.add(e);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro no banco de dados: " + ex);
        } finally {
            try {
                if (rs != null){
                    rs.close();
                    rs = null;
                }
                if (pst != null){
                    pst.close();
                    pst = null;
                }
                con.close();
            } catch (Exception ex) {
                System.out.println("Falha ao encerrar conexão: " + ex);
            }
        }
        return lista;
    }
}
