package Sistema;

/**********************************************************************
	UNIFRA - Centro Universitário Franciscano
	Sistemas de Informação
	Linguagem de Programação II
	(Programação Orientada a Objetos - Linguagem Java)
	Prof. Elton R. C. Spode, MsC
	TRABALHO Final. - 4º Semestre 2009
	Aluno:Evandro Blanke Sangiovo

	COPYLEFT (Todos os direitos de reprodução autorizados deste que
	preservados o nome da instituição e dos autores.

**********************************************************************/



/*
 * Está classe representa a tabela de Produtos
 */

import Banco.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evandro
 * @version 1.0
 */
public class Produtos
{
    //Construtores da classe Produtos
    /**
     * Construtor padrão
     */
    public Produtos()
    {
    }

    /**
     * Construtor opcional, para instânciar basta passar por parâmetro as informações do produto
     * @param descricao Descrição do Produto
     * @param ean Código padrão EAN 13 do Produto
     * @param preco Preço de venda do Produto
     * @param icms Porcentagem de ICMS do Produto
     */
    public Produtos(String descricao, String ean, double preco, double icms)
    {
        this.descricao = descricao;
        this.ean = ean;
        this.preco = preco;
        this.icms = icms;
    }

    //Propriedades Privadas da classe Produtos
    private int codigo;
    private String descricao;
    private String ean;
    private double preco;
    private double icms;

    //Métodos modificadores e acessores da classe Produtos
    public int getCodigo()
    {
        return codigo;
    }
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getEan()
    {
        return ean;
    }
    public void setEan(String ean)
    {
        this.ean = ean;
    }

    public double getPreco()
    {
        return preco;
    }
    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public double getIcms()
    {
        return icms;
    }
    public void setIcms(double icms)
    {
        this.icms = icms;
    }

    /**
     * Método responsável pela inclusão de um novo Produto
     * @throws java.lang.Exception
     */
    public void Incluir() throws Exception
    {
        String sql = "INSERT INTO PRODUTOS (DESCRICAO, " +
                     "EAN, PRECO, ICMS) VALUES (?, ?, ?, ?)";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.descricao);
            pStatement.setObject(2, this.ean);
            pStatement.setObject(3, this.preco);
            pStatement.setObject(4, this.icms);
            pStatement.executeUpdate();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela alteraçao do Produto
     * @throws java.lang.Exception
     */
    public void Alterar() throws Exception
    {
        String sql = "UPDATE PRODUTOS SET DESCRICAO = ?, EAN = ?, " +
                     "PRECO = ?, ICMS = ? WHERE CODIGO = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.descricao);
            pStatement.setObject(2, this.ean);
            pStatement.setObject(3, this.preco);
            pStatement.setObject(4, this.icms);
            pStatement.setObject(5, this.codigo);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela exclusão do produto.
     * @param codigo Passe por parâmetro o código do produto a ser excluído
     * @throws java.lang.Exception
     */
    public void Excluir(int codigo) throws Exception
    {
        this.codigo = codigo;
        String sql = "DELETE FROM PRODUTOS WHERE CODIGO = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigo);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela exclusão do produto
     * @throws java.lang.Exception
     */
    public void Excluir() throws Exception
    {
        String sql = "DELETE FROM PRODUTOS WHERE CODIGO = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigo);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela busca de Produtos (Todos os Produtos)
     * @return Retorna um Objeto ResultSet com os registros da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaProdutos() throws Exception
    {
        String sql = "SELECT * FROM PRODUTOS";
        Banco b = new Banco();
        ResultSet rSet;
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            rSet = b.ExecutaSqlQuery(pStatement);
            return rSet;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela busca de um único Produto
     * @param codigo Passe por Parâmetro o código do Produto
     * @return Retorna um Objeto ResultSet com o registro da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaProduto(int codigo) throws Exception
    {
        this.codigo = codigo;
        String sql = "SELECT * FROM PRODUTOS WHERE CODIGO = ?";
        Banco b = new Banco();
        ResultSet rSet;
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigo);
            rSet = b.ExecutaSqlQuery(pStatement);
            return rSet;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    } 
}
