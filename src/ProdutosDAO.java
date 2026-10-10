/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus()); // O status padrão ao cadastrar é "A Venda"
            
            prep.executeUpdate(); // Executa a gravação na base de dados
            
            // Mensagem de sucesso exigida pela atividade
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        // Limpa a lista para não duplicar dados caso seja chamada mais de uma vez
        listagem.clear(); 
        
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos"; // O mesmo comando que você usou no Workbench!
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery(); // Executa a consulta
            
            // Enquanto houver linhas na tabela do banco, ele vai ler e adicionar na lista
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        }
        
        return listagem;
    }
    
    
    
        
}

