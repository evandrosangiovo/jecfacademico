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
 * Está classe representa a tabela de Clientes
 */

import Banco.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evandro B. Sangiovo
 * @version 1.0
 */

public class Clientes
{
    //Construtores da classe Cliente
    /**
     * Construtor padrão
     */
    public Clientes()
    {
    }

    /**
     * Construtor opcional, para instânciar basta passar por parâmetro os dados do cliente.
     * @param nome Nome do Cliente.
     * @param endereco Endereço do Cliente.
     * @param cep CEP do Cliente.
     * @param bairro Bairro do Cliente.
     * @param cpf CPF do Cliente.
     * @param identidade Identidade do Cliente.
     * @param nascimento Data de Nascimento do Cliente.
     */
    public Clientes(String nome, String endereco, String cep, String bairro, String cpf, String identidade, String nascimento)
    {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.bairro = bairro;
        this.cpf = cpf;
        this.identidade = identidade;
        this.nascimento = nascimento;
    }

    // Propriedades privadas da classe Cliente
    private int codigo;
    private String nome;
    private String endereco;
    private String cep;
    private String bairro;
    private String cpf;
    private String identidade;
    private String nascimento;
    private PreparedStatement statement;

    //Métodos modificadores e acessores da classe Clientes
    public int getCodigo()
    {
        return codigo;
    }
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEndereco()
    {
        return endereco;
    }
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getCep()
    {
        return cep;
    }
    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getBairro()
    {
        return bairro;
    }
    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCpf()
    {
        return cpf;
    }
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getIdentidade()
    {
        return identidade;
    }
    public void setIdentidade(String identidade)
    {
        this.identidade = identidade;
    }

    public String getNascimento()
    {
        return nascimento;
    }
    public void setNascimento(String nascimento)
    {
        this.nascimento = nascimento;
    }
    

    
    /**
     * Método responsável pela inclusão de uma novo cliente.
     * @throws java.lang.Exception
     */
    public void Incluir() throws Exception
    {
        String sql = "INSERT INTO CLIENTES (NOME, ENDERECO, CEP," +
                     " BAIRRO, CPF, IDENTIDADE, NASCIMENTO) VALUES " +
                     "(?,?,?,?,?,?,?)";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.nome);
            pStatement.setObject(2, this.endereco);
            pStatement.setObject(3, this.cep);
            pStatement.setObject(4, this.bairro);
            pStatement.setObject(5, this.cpf);
            pStatement.setObject(6, this.identidade);
            pStatement.setObject(7, this.nascimento);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela alteraçao de um cliente
     * @throws java.lang.Exception
     */
    public void Alterar() throws Exception
    {
        String sql = "UPDATE CLIENTES SET NOME = ?, ENDERECO = ?, " +
                     "CEP = ?, BAIRRO = ?, CPF = ?, IDENTIDADE = ?, " +
                     "NASCIMENTO = ? WHERE CODIGO = ?";
        Banco b = new Banco();
        try
        {
            PreparedStatement pStatement = b.RetornaStatement(sql);
            pStatement.setObject(1, this.nome);
            pStatement.setObject(2, this.endereco);
            pStatement.setObject(3, this.cep);
            pStatement.setObject(4, this.bairro);
            pStatement.setObject(5, this.cpf);
            pStatement.setObject(6, this.identidade);
            pStatement.setObject(7, this.nascimento);
            pStatement.setObject(8, this.codigo);
            b.ExecutaSqlUpdate(pStatement);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Método responsável pela exclusão de um cliente
     * @throws java.lang.Exception
     */
    public void Excluir() throws Exception
    {
        String sql = "DELETE FROM CLIENTES WHERE CODIGO = ?";
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
     * Método responsável pela exclusão de um cliente
     * @param codigo Passe por parâmetro o código do cliente a ser excluído
     * @throws java.lang.Exception
     */
    public void Excluir(int codigo) throws Exception
    {
        this.codigo = codigo;
        String sql = "DELETE FROM CLIENTES WHERE CODIGO = ?";
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
     * Método responsável pela busca de clientes (Todos os Clientes)
     * @return Retorna um Objeto ResultSet com os registros da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaClientes() throws Exception
    {
        String sql = "SELECT * FROM CLIENTES";
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
     * Método responsável pela busca de um único cliente
     * @param codigo Passe por Parâmetro o código do cliente
     * @return Retorna um Objeto ResultSet com o registro da consulta
     * @throws java.lang.Exception
     */
    public ResultSet RetornaCliente(int codigo) throws Exception
    {
        this.codigo = codigo;
        String sql = "SELECT * FROM CLIENTES WHERE CODIGO = ?";
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
