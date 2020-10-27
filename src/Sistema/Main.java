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
 * Classe onde o Sistema começa sua execução
 */



import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * @author Evandro B. Sangiovo
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws IOException {

        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 1);
        }
        Menu m = new Menu();
        m.setVisible(true);

    }


    public String getApplicationPath()
    {
        String url = getClass().getResource(getClass().getSimpleName() + ".class").getPath();
        File dir = new File(url).getParentFile();
        String path = null;
        if (dir.getPath().contains(".jar"))
            path = findJarParentPath(dir);
            //JOptionPane.showMessageDialog(null, path);
            //System.out.println(path);
        else
            path = dir.getParent();
            //JOptionPane.showMessageDialog(null, path);
        try
        {
            return URLDecoder.decode(path, "UTF-8");
        }
        catch (UnsupportedEncodingException e) 
        {
            return path.replace("%20", " ");
        }
    }

        private String findJarParentPath(File jarFile) {
            while (jarFile.getPath().contains(".jar"))
                jarFile = jarFile.getParentFile();

            return jarFile.getPath().substring(6);
        }

}
