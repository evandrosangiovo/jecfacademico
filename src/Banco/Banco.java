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
package Banco;


/*
 * Está classe é responsável pela manipulação do banco de dados (Abrir, fechar conexões e executar comandos SQL)
 */

import Sistema.*;
import java.sql.*;


/**
 * @author Evandro B. Sangiovo
 * @version 1.0
 */
public class Banco
{
    Main m = new Main();

   //String dsn = "jdbc:odbc:Driver=" +
   //                  "{Microsoft Access Driver " +
   //                  "(*.mdb)};DBQ=" + getClass().getResource("/Banco/java.mdb");
    String dsn = "jdbc:odbc:Driver=" +
                     "{Microsoft Access Driver " +
                     "(*.mdb)};DBQ=" + m.getApplicationPath() + "/Banco/java.mdb";

      //String dsn = "jdbc:odbc:Driver=" +
      //               "{Microsoft Access Driver " +
      //               "(*.mdb)};DBQ=/Banco/java.mdb";

    /**
    * Este método é responsável pela abertura da conexão com o banco de dados.
     * @return Retorna um Objeto Connection com a conexão aberta.
    */
    private Connection AbreConexao() throws Exception
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            Connection con = DriverManager.getConnection(dsn);
            return con;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Este método é responsável pelo fechamento da conexão com o banco de dados.
     *@param conexao Passe por parâmentro o Objeto Connection para que seja efetuada o fechamento da conexão.
     */
    private void FechaConexao(Connection conexao) throws SQLException
    {
        try 
        {
            if (!conexao.isClosed())
            {
                conexao.commit();
                conexao.close();
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }

    /**
     * Este método é responsavel p/ construir um objeto PreparedStatement.
     * @param sql Passe por parâmetro a string SQL para ser constuído o Objeto PreparedStatement.
     * @return O método retorno um Objeto PreparedStatement.
     */
    public PreparedStatement RetornaStatement(String sql) throws SQLException, Exception
    {
        Connection conexao = AbreConexao();
        try
        {
            PreparedStatement pStatement = conexao.prepareStatement(sql);
            return pStatement;
        }
        catch(SQLException ex)
        {
             throw ex;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Este método é responsavel p/ executar os comandos (Insert, Update, Delete) no banco.
     * Para o comando "Select" use o método "ExecutaSqlQuery".
     * @param pStatement Passe por parâmentro o Objeto PreparedStatement para a execução do comando.
     */
    public void ExecutaSqlUpdate(PreparedStatement pStatement) throws SQLException
    {
        try
        {
            pStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            FechaConexao(pStatement.getConnection());
            pStatement.close();
        }
    }

    /**
     * Este método é responsavel p/ executar comandos que retornam registros do banco (Select).
     * Para outros comandos use o método "ExecutaSqlUpdate".
     * @param pStatement Passe por parâmentro o Objeto PreparedStatement para a execução do comando.
     * @return Este Método retorna um ResultSet com os registros da consulta efetuada.
     */
    public ResultSet ExecutaSqlQuery(PreparedStatement pStatement) throws SQLException
    {
        try
        {
            ResultSet resultSet = pStatement.executeQuery();
            return resultSet;
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally
        {
            //FechaConexao(pStatement.getConnection());
            //pStatement.close();
        }
    }
}
