package bemajava;

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
//CLASSE DISPONIBILIZADA PELA EMPRESA BEMATECH

public class AnalisarRetornos {


    /**
     * Método responsável pelo tratamento de erros da impressora
     * @return Mensagem de erro
     */
    private static String Analisa_RetornoImpressora()
    {
        BemaInteger ACK,ST1,ST2;
	String Erros = "";
        ACK = new BemaInteger();
        ST1 = new BemaInteger();
        ST2 = new BemaInteger();

	Bematech.RetornoImpressora(ACK, ST1, ST2);

	//Tratando o ST1

	if ( ST1.number >= 128)
	{
            ST1.number = ST1.number - 128;
            Erros += "BIT 7 - Fim de Papel \n";
	}
	if ( ST1.number >= 64)
	{
            ST1.number = ST1.number - 64;
            Erros += "BIT 6 - Pouco Papel \n";
	}
	if ( ST1.number >= 32)
	{
            ST1.number = ST1.number - 32;
            Erros += "BIT 5 - Erro no Relógio \n";
	}
	if ( ST1.number >= 16)
	{
            ST1.number = ST1.number - 16;
            Erros += "BIT 4 - Impressora em ERRO \n";
	}
	if ( ST1.number >= 8)
	{
            ST1.number = ST1.number - 8;
            Erros += "BIT 3 - CMD não iniciado com ESC \n";
	}
	if ( ST1.number >= 4)
        {
            ST1.number = ST1.number - 4;
            Erros += "BIT 2 - Comando Inexistente \n";
	}
	if ( ST1.number >= 2)
        {
            ST1.number = ST1.number - 2;
            Erros += "BIT 1 - Cupom Aberto \n";
	}
        if ( ST1.number >= 1)
	{
            ST1.number = ST1.number - 1;
            Erros += "BIT 0 - Nº de Parâmetros  \n";
	}

	//Tratando o ST2
	if ( ST2.number >= 128)
	{
            ST2.number = ST2.number - 128;
            Erros += "BIT 7 - Tipo de Parâmetro Inválido \n";
	}
	if ( ST2.number >= 64)
	{
            ST2.number = ST2.number - 64;
            Erros += "BIT 6 - Memória Fiscal Lotada \n";
	}
	if ( ST2.number >= 32)
	{
            ST2.number = ST2.number - 32;
            Erros += "BIT 5 - CMOS não Volátil \n";
	}
	if ( ST2.number >= 16)
	{
            ST2.number = ST2.number - 16;
            Erros += "BIT 4 - Alíquota Não Programada \n";
	}
	if ( ST2.number >= 8)
	{
            ST2.number = ST2.number - 8;
            Erros += "BIT 3 - Alíquotas lotadas \n";
	}
	if ( ST2.number >= 4)
        {
            ST2.number = ST2.number - 4;
            Erros += "BIT 2 - Cancelamento ñ Permitido \n";
	}
	if ( ST2.number >= 2)
	{
            ST2.number = ST2.number - 2;
            Erros += "BIT 1 - CGC/IE não Programados \n";
	}
	if ( ST2.number >= 1)
	{
            ST2.number = ST2.number - 1;
            Erros += "BIT 0 - Comando não Executado \n";
	}
        if (Erros.length() != 0)
        {
            return Erros;
        }
        else
        {
            return "";
        }
    }

    /**
     * Método que analiza o retorno da função
     * @param IRetorno Inteiro com o valor a ser analizado
     * @return Mensagem de erro
     */
    public static String Analisa_iRetorno(int IRetorno)
    {
        String MSG = "";
        switch(IRetorno)
	{
            case  0:
                MSG = "Erro de Comunicação !";
		break;
            case 1:
                MSG = Analisa_RetornoImpressora();
                break;
            case -1:
                MSG = "Erro de Execução na Função. Verifique!";
                break;
            case -2:
                MSG = "Parâmetro Inválido !";
                break;
            case -3:
                MSG = "Alíquota não programada !";
		break;
            case -4:
                MSG = "Arquivo BemaFI32.INI não encontrado. Verifique!";
		break;
            case -5:
                MSG = "Erro ao Abrir a Porta de Comunicação";
		break;
            case -6:
                MSG = "Impressora Desligada ou Desconectada.";
		break;
            case -7:
                MSG = "Banco Não Cadastrado no Arquivo BemaFI32.ini";
		break;
            case -8:
                MSG = "Erro ao Criar ou Gravar no Arquivo Retorno.txt ou Status.txt.";
		break;
            case -18:
                MSG = "Não foi possível abrir arquivo INTPOS.001!";
		break;
            case -19:
                MSG = "Parâmetros diferentes!";
		break;
            case -20:
                MSG = "Transação cancelada pelo Operador!";
                break;
            case -21:
                MSG = "A Transação não foi aprovada!";
		break;
            case -22:
                MSG = "Não foi possível terminar a Impressão!";
		break;
            case -23:
                MSG = "Não foi possível terminar a Operação!";
                break;
            case -24:
                MSG = "Não foi possível terminal a Operação!";
                break;
            case -25:
                MSG = "Totalizador não fiscal não programado.";
                break;
            case -26:
                MSG = "Transação já Efetuada!";
                break;
            case -27:
                MSG = Analisa_RetornoImpressora();
		break;
            case -28:
                MSG = "Não há Informações para serem Impressas!";
                break;
        }
        if (MSG.length() != 0)
        {
            return MSG;
        }
        else
        {
            return "";
        }
    }
}
