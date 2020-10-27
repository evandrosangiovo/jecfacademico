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
 * Está classe representa a tabela de Ítens das Vendas
 */



import Banco.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evandro
 * @version 1.0
 */
public class VendaItens
{
    private int codigoCupom;
    private int codigoItem;
    private int codigoProduto;
    private String descricaoProduto;
    private int quantidade;
    private double precoUnitario;
    private double acrescimos;
    private double descontos;
    private double icms;
    private double valorIcms;

    //Construtores da classe VendaItens
    /**
     * Construtor padrão
     */
    public VendaItens()
    {
    }

    /**
     * Construtor opcional, para instânciar basta passar por parâmetro os dados do Itens da Venda.
     * @param codigoCupom Número do Cupom Fiscal
     * @param codigoItem Sequencia do ítem no Cupom Fiscal.
     * @param codigoProduto Código do Produto.
     * @param descricaoProduto Descrição do Produto.
     * @param quantidade Quantidade a ser vendido do Produto.
     * @param precoUnitario Preço unitário do Produto.
     * @param acrescimos Acréscimos sobre o Produto.
     * @param descontos Descontos sobre o Produto.
     * @param icms Porcentagem de Icms.
     * @param valorIcms Valor do Icms do Ítem.
     */
    public VendaItens(int codigoCupom, int codigoItem, int codigoProduto, String descricaoProduto, int quantidade, double precoUnitario, double acrescimos, double descontos, double icms, double valorIcms)
    {
        this.codigoCupom = codigoCupom;
        this.codigoItem = codigoItem;
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.acrescimos = acrescimos;
        this.descontos = descontos;
        this.icms = icms;
        this.valorIcms = valorIcms;
    }

    public int getCodigoCupom()
    {
        return codigoCupom;
    }
    public void setCodigoCupom(int codigoCupom)
    {
        this.codigoCupom = codigoCupom;
    }

    public int getCodigoItem()
    {
        return codigoItem;
    }
    public void setCodigoItem(int codigoItem)
    {
        this.codigoItem = codigoItem;
    }

    public int getCodigoProduto()
    {
        return codigoProduto;
    }
    public void setCodigoProduto(int codigoProduto)
    {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto()
    {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto)
    {
        this.descricaoProduto = descricaoProduto;
    }

    public int getQuantidade()
    {
        return quantidade;
    }
    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario()
    {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario)
    {
        this.precoUnitario = precoUnitario;
    }

    public double getAcrescimos()
    {
        return acrescimos;
    }

    public void setAcrescimos(double acrescimos)
    {
        this.acrescimos = acrescimos;
    }
    public double getDescontos()
    {
        return descontos;
    }
    public void setDescontos(double descontos)
    {
        this.descontos = descontos;
    }

    public double getIcms()
    {
        return icms;
    }
    public void setIcms(double icms)
    {
        this.icms = icms;
    }

    public double getValorIcms()
    {
        return valorIcms;
    }
    public void setValorIcms(double valorIcms)
    {
        this.valorIcms = valorIcms;
    }

     /**
     * Método responsável pela inclusão de um novo Ítem de Venda
     * @throws java.lang.Exception
     */
    public void Incluir() throws Exception
    {
        String sql = "INSERT INTO VENDAITENS (CODIGOCUPOM, " +
                     "CODIGOITEM, CODIGOPRODUTO, DESCRICAOPRODUTO, " +
                     "QUANTIDADE, PRECOUNITARIO, ACRESCIMOS, DESCONTOS, " +
                     "ICMS, VALORICMS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigoCupom);
            pStatement.setObject(2, this.codigoItem);
            pStatement.setObject(3, this.codigoProduto);
            pStatement.setObject(4, this.descricaoProduto);
            pStatement.setObject(5, this.quantidade);
            pStatement.setObject(6, this.precoUnitario);
            pStatement.setObject(7, this.acrescimos);
            pStatement.setObject(8, this.descontos);
            pStatement.setObject(9, this.icms);
            pStatement.setObject(10, this.valorIcms);
            pStatement.executeUpdate();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela alteraçao de um Ítem de Venda.
     * @throws java.lang.Exception
     */
    public void Alterar() throws Exception
    {
        String sql = "UPDATE VENDAITENS SET CODIGOPRODUTO = ?, DESCRICAOPRODUTO = ?, " +
                     "QUANTIDADE = ?, PRECOUNITARIO = ?, ACRESCIMOS = ?, DESCONTOS = ?, " +
                     "ICMS = ?, VALORICMS = ? WHERE CODIGOCUPOM = ? AND CODIGOITEM = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigoProduto);
            pStatement.setObject(2, this.descricaoProduto);
            pStatement.setObject(3, this.quantidade);
            pStatement.setObject(4, this.precoUnitario);
            pStatement.setObject(5, this.acrescimos);
            pStatement.setObject(6, this.descontos);
            pStatement.setObject(7, this.icms);
            pStatement.setObject(8, this.valorIcms);
            pStatement.setObject(9, this.codigoCupom);
            pStatement.setObject(10, this.codigoItem);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela exclusão de um Ítem de Venda.
     * @param codigoCupom Passe por parâmetro o código do cupom.
     * @param codigoItem Passe por parâmetro o código do Item no Cupom a ser excluído.
     * @throws java.lang.Exception
     */
    public void Excluir(int codigoCupom, int codigoItem) throws Exception
    {
        this.codigoCupom = codigoCupom;
        this.codigoItem = codigoItem;
        String sql = "DELETE FROM VENDAITENS WHERE CODIGOCUPOM = ? AND CODIGOITEM = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigoCupom);
            pStatement.setObject(2, this.codigoItem);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela exclusão de todos os Ítens de uma Venda
     * @throws java.lang.Exception
     */
    public void Excluir(int codigoCupom) throws Exception
    {
        this.codigoCupom = codigoCupom;
        String sql = "DELETE FROM VENDAITENS WHERE CODIGOCUPOM = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigoCupom);
            b.ExecutaSqlUpdate(pStatement);

        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela busca de Ítens de Venda (Todos os Ítens de todos os Cupons)
     * @return Retorna um Objeto ResultSet com os registros da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaTodosItens() throws Exception
    {
        String sql = "SELECT * FROM VENDAITENS ORDER BY CODIGOCUPOM, CODIGOITEM";
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
     * Método responsável pela busca de um único Ítem de Venda.
     * @param codigoCupom Passe por Parâmetro o Número do Cupom.
     * @param CodigoItem Passe por Parâmetro o código do Itens na venda
     * @return Retorna um Objeto ResultSet com o registro da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaUmItem(int codigoCupom, int codigoItem) throws Exception
    {
        this.codigoCupom = codigoCupom;
        this.codigoItem = codigoItem;
        String sql = "SELECT * FROM VENDAITENS WHERE CODIGOCUPOM = ? AND CODIGOITEM = ?";
        Banco b = new Banco();
        ResultSet rSet;
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigoCupom);
            pStatement.setObject(2, this.codigoItem);
            rSet = b.ExecutaSqlQuery(pStatement);
            return rSet;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

        /**
     * Método responsável pela busca de todos os Ítens de Venda de um único Cupom Fiscal.
     * @param codigoCupom Passe por Parâmetro o Número do Cupom.
     * @return Retorna um Objeto ResultSet com o registro da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaItensCupom(int codigoCupom) throws Exception
    {
        this.codigoCupom = codigoCupom;
        String sql = "SELECT * FROM VENDAITENS WHERE CODIGOCUPOM = ? ORDER BY CODIGOITEM";
        Banco b = new Banco();
        ResultSet rSet;
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.codigoCupom);
            rSet = b.ExecutaSqlQuery(pStatement);
            return rSet;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
