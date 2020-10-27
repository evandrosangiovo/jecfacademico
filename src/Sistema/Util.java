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


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;


public final class Util {

    private static final Locale BRAZIL = new Locale("pt","BR");
    /**
     * Símbolos especificos do Real Brasileiro
     */
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    /**
     * Mascara de dinheiro para Real Brasileiro
     */
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00",REAL);

    /**
     * Mascara texto com formatacao monetaria
     * @param valor Valor a ser mascarado
     * @param moeda Padrao monetario a ser usado
     * @return Valor mascarado de acordo com o padrao especificado
     */
    public static String mascaraDinheiro(double valor, DecimalFormat moeda)
    {
        return moeda.format(valor);
    }

    /**
     * Transforma para Double uma mascara de Dinheiro
     * @param valor Valor a ser convertido
     * @return Retorna um Double...
     */
    public static double dinheiroDouble(String valor)
    {
        return Double.parseDouble(valor.replaceAll("\\.", "").replaceAll(",", "."));
    }

    /**
     * Pega a data atual no formato DD/MM/AAAA
     * @return Retorna a data atual
     */
    public static String dataAtual()
    {
        Date d = new Date();
        String data = "";

        if(String.valueOf(d.getDate()).length() == 1)
            data = "0" + String.valueOf(d.getDate()) + "/";
        else
            data = String.valueOf(d.getDate()) + "/";

        if(String.valueOf(d.getMonth()).length() == 1)
            data += "0" + String.valueOf(d.getMonth() + 1) + "/";
        else
            data += String.valueOf(d.getMonth() + 1) + "/";

        data += String.valueOf(d.getYear() + 1900);

        return data;
        /*
        return String.valueOf(d.getDate()) + "/" +
               String.valueOf(d.getMonth() + 1) + "/" +
               String.valueOf(d.getYear() + 1900);
         */
    }


    /**
     * Passe a data no formato DD/MM/AAAA
     * @param data Data a ser convertida
     * @return data no formato AAAAMMDD (Inteiro)
     */
    public static int dataBanco(String data)
    {
        //1/12/2009
        //10/12/2009
        int novaData = 0;
        if(data.length() == 9)
        {
            novaData = Integer.valueOf(data.substring(5) + data.substring(2, 4) + data.substring(0, 1));
            return novaData;
        }
        if(data.length() == 10)
        {
            novaData = Integer.valueOf(data.substring(6) + data.substring(3, 5) + data.substring(0, 2));
            return novaData;
        }
        return 1011900;
    }

    /**
     * Passe a data no formato AAAA/MM/DD
     * @param data Data a ser convertida
     * @return data no formato DD/MM/AAAA
     */
    public static String dataText(int data)
    {
        //2009121
        //20091210
        String novaData = "";
        String dataString = String.valueOf(data);
        if(dataString.length() == 7)
        {
            novaData = dataString.substring(6, 7) + "/" + dataString.substring(4, 6) + "/" + dataString.substring(0, 4);
            return novaData;
        }
        if(dataString.length() == 8)
        {
            novaData = dataString.substring(6, 8) + "/" + dataString.substring(4, 6) + "/" + dataString.substring(0, 4);
            return novaData;
        }
        return "1/01/1900";
    }
}
