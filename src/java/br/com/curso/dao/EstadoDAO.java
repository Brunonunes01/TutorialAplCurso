/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Estado;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 *
 * @author Bruno
 */
public class EstadoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public EstadoDAO() throws Exception{
       conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
       Estado oEstado = (Estado) objeto;
       Boolean retorno = false;
       if (oEstado.getIdEstado() == 0) {
           retorno = this.inserir(oEstado);
           
       }else {
           retorno = this.alterar(oEstado);
       }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
      Estado oEstado = (Estado) objeto;
      PreparedStatement stmt = null;
      String sql = "insert into estado (nomeestado, siglaestado) values (?,?)";
      try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oEstado.getNomeEstado());
          stmt.setString(2, oEstado.getSiglaEstado());
          stmt.execute();
          return true;
      } catch (Exception ex){
      try {
          System.out.println("Problemas ao cadastrar a Estado! Erro: "+ex.getMessage());
          ex.printStackTrace();
          conexao.rollback();
      } catch (SQLException e){
          System.out.println("Erro:"+e.getMessage());
          e.printStackTrace();
      }
      return false;
      }
    }

    @Override
    public Boolean alterar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean excluir(int numero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object carregar(int numero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from estado order by idEstado";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()) {
                Estado oEstado = new Estado();
                oEstado.setIdEstado(rs.getInt("idEstado"));
                oEstado.setNomeEstado(rs.getString("nomeestado"));
                oEstado.setSiglaEstado(rs.getString("siglaestado"));
                resultado.add(oEstado);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Estado! Erro: "
                    +ex.getMessage());
        }
        return resultado;
    }
    }
    

