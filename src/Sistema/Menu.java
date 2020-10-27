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

import bemajava.*;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends javax.swing.JFrame
{
    public Menu()
    {
        initComponents();
        this.setResizable(false);

        this.setExtendedState(NORMAL);
        getContentPane().setLayout (new BorderLayout());
        JPanel pCentro = new JPanel();
        pCentro.setLayout(new BorderLayout());
        JLabel label = new JLabel(new ImageIcon(getClass().getResource("/Icones/ISistema.jpg")));
        pCentro.add(label, BorderLayout.CENTER);
        getContentPane().add(pCentro,BorderLayout.CENTER);
    }

    private void VerificaImpressora()
    {
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.VerificaImpressoraLigada();
        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);

        if(Retorno.length() != 0)
        {
           JOptionPane.showMessageDialog(this, "Verifique as configurações da impressora\n" +
                                               "Não será possível acessar esta tela.\n" + Retorno, "Erro", 0);
           return;
        }

        iRetorno = Bematech.LeituraX();
        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);

        if(!Retorno.isEmpty())
        {
           JOptionPane.showMessageDialog(this, "Transação em andamento\n" +
                                               "Não será possível acessar esta tela.\n" + Retorno, "Erro", 0);
           return;
        }

        BrowseVenda v = new BrowseVenda(this, true);
        v.setVisible(true);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        MenuCadastros = new javax.swing.JMenu();
        ItemMenuClientes = new javax.swing.JMenuItem();
        ItenMenuProdutos = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        MenuConsultas = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Emissor de Cupom Fiscais");
        setMinimumSize(new java.awt.Dimension(800, 600));

        MenuCadastros.setText("Cadastros");

        ItemMenuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/application_view_columns.png"))); // NOI18N
        ItemMenuClientes.setText("Clientes");
        ItemMenuClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemMenuClientesActionPerformed(evt);
            }
        });
        MenuCadastros.add(ItemMenuClientes);

        ItenMenuProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/application_view_columns.png"))); // NOI18N
        ItenMenuProdutos.setText("Produtos");
        ItenMenuProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItenMenuProdutosActionPerformed(evt);
            }
        });
        MenuCadastros.add(ItenMenuProdutos);

        jMenuBar1.add(MenuCadastros);

        jMenu2.setText("Emissão CF");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/printer.png"))); // NOI18N
        jMenuItem2.setText("Emissão de CF's");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator1);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cog.png"))); // NOI18N
        jMenuItem6.setText("Comandos/Testes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        MenuConsultas.setText("Consultas");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/14_layer_novisible.png"))); // NOI18N
        jMenuItem5.setText("Vendas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        MenuConsultas.add(jMenuItem5);

        jMenuBar1.add(MenuConsultas);

        jMenu1.setText("Sair");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cross.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemMenuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemMenuClientesActionPerformed
        BrowseClientes bp = new BrowseClientes(this, true);
        bp.setVisible(true);
    }//GEN-LAST:event_ItemMenuClientesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ItenMenuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItenMenuProdutosActionPerformed
        BrowseProdutos bc = new BrowseProdutos(this, true);
        bc.setVisible(true);
    }//GEN-LAST:event_ItenMenuProdutosActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        TesteComandos tc = new TesteComandos(this, true);
        tc.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        VerificaImpressora();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        BrowseConsultaVendas b = new BrowseConsultaVendas(this, true);
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ItemMenuClientes;
    private javax.swing.JMenuItem ItenMenuProdutos;
    private javax.swing.JMenu MenuCadastros;
    private javax.swing.JMenu MenuConsultas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
