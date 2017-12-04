/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.superquinto.sisprex.ws;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author Ronaldo Chaves
 */
public class SisprexDAO {

    Conexao con;

    public SisprexDAO() {

    }

    public boolean sugerirEdicao(Edicao edicao) {
        PreparedStatement pst = null;
        try {
            con = new Conexao();
            String sql = "insert into edicao_sugerida(id_produto, campo, valor_anterior, valor_atual, sugerido_por) values (?, ?, ?, ?, ?)";
            pst = con.getConnection().prepareStatement(sql);
            pst.setInt(1, edicao.getidProduto());
            pst.setString(2, edicao.getCampo());
            pst.setString(3, edicao.getValorAnterior());
            pst.setString(4, edicao.getValorAtual());
            pst.setInt(5, edicao.getSugeridoPor());
            if (pst.execute()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                con.close();
            } catch (Exception e) {
                System.out.println("Falha ao encerrar conexão: " + e);
            }
        }
        return false;
    }

    public List<Usuario> listaUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuario u = null;
        try {
            con = new Conexao();
            String sql = "select * from usuarios";
            pst = con.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                lista.add(u);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                con.close();
            } catch (Exception e) {
                System.out.println("Falha ao encerrar conexão: " + e);
            }
        }
        return lista;
    }

    public List<Produto> consultaProduto(String texto) {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Produto p = null;
        try {
            con = new Conexao();
            String sql = "select erp_produtos.id, erp_produtos.codigo, erp_produtos.ean, descricao, resumida, precovarejo from erp_precos join erp_produtos on erp_produtos.id = erp_precos.produto where ean like '%" + texto + "%' or descricao like '%" + texto + "%' or resumida like '%" + texto + "%';";
            pst = con.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setEan(rs.getString("ean"));
                p.setDescricao(rs.getString("descricao"));
                p.setResumida(rs.getString("resumida"));
                p.setValor(rs.getDouble("precovarejo"));
                lista.add(p);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                con.close();
            } catch (Exception e) {
                System.out.println("Falha ao encerrar conexão: " + e);
            }
        }
        return lista;
    }

    public Produto consultaProdutoPorId(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Produto p = null;
        try {
            con = new Conexao();
            String sql = "select erp_produtos.id, erp_produtos.codigo, erp_produtos.ean, descricao, resumida, precovarejo from erp_precos join erp_produtos on erp_produtos.id = erp_precos.produto where id = " + id + "";
            pst = con.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setEan(rs.getString("ean"));
                p.setDescricao(rs.getString("descricao"));
                p.setResumida(rs.getString("resumida"));
                p.setValor(rs.getDouble("precovarejo"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro no banco de dados: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pst != null) {
                    pst.close();
                    pst = null;
                }
                con.close();
            } catch (Exception e) {
                System.out.println("Falha ao encerrar conexão: " + e);
            }
        }
        return p;
    }

    public boolean imprimirEtiqueta(int id) {
        Produto p = consultaProdutoPorId(id);
        if (p != null) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(); 
            String layout = "L\n121100000800010" + p.getDescricao() + "\n122200400200170"+nf.format(p.getValor())+"\n1F2102500200010" + p.getEan() + "\nE\n";
            //Enviar layout diretamente para a porta da impressora padrão.
            System.out.println(layout);
            try {
                PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                DocPrintJob job = printService.createPrintJob();
                InputStream is = new ByteArrayInputStream(layout.getBytes());
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(is, flavor, null);
                job.print(doc, null);
                is.close();
                return true;
            } catch (Exception e) {
                System.out.println("Erro: " + e);
            }
        }
        return false;
    }
}
