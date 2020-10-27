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

package Sistema;


/*
 * Está classe representa a tabela das Vendas
 */



import Banco.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evandro
 * @version 1.0
 */
public class Venda
{
    private int codigo;
    private int data;
    private int codigoCliente;
    private String nomeCliente;
    private String enderecoCliente;

    //Construtores da classe Venda
    /**
     * Construtor padrão
     */
    public Venda()
    {
    }

    /**
     * Construtor opcional, para instânciar basta passar por parâmetro os dados da Venda.
     * @param codigo Número do Cupom Fiscal.
     * @param data Data da Venda.
     * @param codigoCliente Código do Cliente.
     * @param nomeCliente Nome do Cliente.
     * @param enderecoCliente Endereco do Cliente.
     */
    public Venda(int codigo, int data, int codigoCliente, String nomeCliente, String enderecoCliente)
    {
        this.codigo = codigo;
        this.data = data;
        this.codigoCliente = codigoCliente;
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
    }

    public int getCodigo()
    {
        return codigo;
    }
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public int getData()
    {
        return data;
    }
    public void setData(int data)
    {
        this.data = data;
    }

    public int getCodigoCliente()
    {
        return codigoCliente;
    }
    public void setCodigoCliente(int codigoCliente)
    {
        this.codigoCliente = codigoCliente;
    }

    public String getNomeCliente()
    {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente)
    {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente()
    {
        return enderecoCliente;
    }
    public void setEnderecoCliente(String enderecoCliente)
    {
        this.enderecoCliente = enderecoCliente;
    }

        /**
     * Método responsável pela inclusão de uma nova Venda.
     * @throws java.lang.Exception
     */
    public void Incluir() throws Exception
    {
        String sql = "INSERT INTO VENDA (CODIGO, DATA, CODIGOCLIENTE," +
                     " NOMECLIENTE, ENDERECO) VALUES (?,?,?,?,?)";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigo);
            pStatement.setObject(2, this.data);
            pStatement.setObject(3, this.codigoCliente);
            pStatement.setObject(4, this.nomeCliente);
            pStatement.setObject(5, this.enderecoCliente);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela alteraçao de uma venda
     * @throws java.lang.Exception
     */
    public void Alterar() throws Exception
    {
        String sql = "UPDATE VENDA SET DATA = ?, " +
                     "CODIGOCLIENTE = ?, NOMECLIENTE = ?, " +
                     "ENDERECOCLIENTE = ? WHERE CODIGO = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1,this.data);
            pStatement.setObject(2,this.codigoCliente);
            pStatement.setObject(3,this.nomeCliente);
            pStatement.setObject(4,this.enderecoCliente);
            pStatement.setObject(5,this.codigo);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela exclusão de uma venda
     * @throws java.lang.Exception
     */
    public void Excluir() throws Exception
    {
        String sql = "DELETE FROM VENDA WHERE CODIGO = ?";
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
     * Método responsável pela exclusão de uma venda
     * @param codigo Passe por parâmetro o código da venda a ser excluída
     * @throws java.lang.Exception
     */
    public void Excluir(int codigo) throws Exception
    {
        this.codigo = codigo;
        String sql = "DELETE FROM VENDA WHERE CODIGO = ?";
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
     * Método responsável pela busca das vendas (Todas as Vendas)
     * @return Retorna um Objeto ResultSet com os registros da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaVendas() throws Exception
    {
        String sql = "SELECT * FROM VENDA";
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
     * Método responsável pela busca de uma única venda
     * @param codigo Passe por Parâmetro o código da venda
     * @return Retorna um Objeto ResultSet com o registro da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaVenda(int codigo) throws Exception
    {
        this.codigo = codigo;
        String sql = "SELECT * FROM VENDA WHERE CODIGO = ?";
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

    /**
     * Método responsável pela busca de vendas em um determinado período de tempo ou cliente
     * @param codigoCliente Passe por parâmetro o código do cliente
     * @param dataInicial Passe por parâmetro a data inicial das vendas
     * @param dataFinal Passe por parâmetro a data final das vendas
     * @return
     * @throws Exception
     */
    public ResultSet RetornaVenda(int codigoCliente, int dataInicial, int dataFinal) throws Exception
    {
        String sql;
        if(codigoCliente == 0)
            sql = "SELECT *, (SELECT SUM(PRECOUNITARIO) FROM VENDAITENS WHERE" +
                    " VENDAITENS.CODIGOCUPOM = VENDA.CODIGO) AS VALORTOTAL FROM VENDA" +
                    " WHERE DATA BETWEEN " + dataInicial + " AND " + dataFinal;
        else
            sql = "SELECT *, (SELECT SUM(PRECOUNITARIO) FROM VENDAITENS WHERE" +
                    " VENDAITENS.CODIGOCUPOM = VENDA.CODIGO) AS VALORTOTAL FROM VENDA" +
                    " WHERE DATA BETWEEN " + dataInicial + " AND " + dataFinal + " AND CODIGOCLIENTE = " + codigoCliente;
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
}
