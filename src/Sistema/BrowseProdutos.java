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


import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BrowseProdutos extends javax.swing.JDialog
{

    private BrowseVenda browse;

    public BrowseProdutos(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        BuscarProdutos();
        btnSelecionar.setVisible(false);
    }

    public BrowseProdutos(java.awt.Frame parent, boolean modal, boolean modeSelection, BrowseVenda browse)
    {
        super(parent, modal);
        initComponents();
        BuscarProdutos();
        if(modeSelection)
        {
            btnIncluir.setVisible(false);
            btnAlterar.setVisible(false);
            btnExcluir.setVisible(false);
            this.browse = browse;
        }
    }

    private void LimparTable()
    {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setNumRows(0);
        jTable1.updateUI();
        jTable1.repaint();
    }
    private void BuscarProdutos()
    {
        ResultSet rs = null;
        Produtos p = new Produtos();
        try
        {
            rs = p.RetornaProdutos();
            String[] colunasTabela = new String[]{"Código", "Descrição", "EAN",
                                                  "Preço", "Trib. ICMS %"};
            
            DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela)
                    { public boolean isCellEditable (int row, int col) { return false; }};

            jTable1.setModel(modeloTabela);

            while(rs.next())
            {
                modeloTabela.addRow(new String [] { Integer.toString(rs.getInt("Codigo")),
                                            rs.getString("Descricao"), rs.getString("EAN"),
                                            Util.mascaraDinheiro(rs.getDouble("Preco"), Util.DINHEIRO_REAL),
                                            Util.mascaraDinheiro(rs.getDouble("Icms"), Util.DINHEIRO_REAL)});
            }
            //jTable1.setAutoResizeMode(5);
            //jTable1.setAutoscrolls(true);
            rs.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro de conexão com o BD...\n" +
                                                "Devido ao erro não será possível continuar.\n" +
                                                ex.getMessage() + '\n' + ex.getStackTrace(), "Erro", 1);
            this.setVisible(false);
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções (Produto Selecionado)"));

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/14_layer_newlayer.png"))); // NOI18N
        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/14_pencil.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cross.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/accept.png"))); // NOI18N
        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSelecionar)
                .addGap(3, 3, 3)
                .addComponent(btnIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(btnFechar))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnIncluir)
                    .addComponent(btnSelecionar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        FormProdutos fp = new FormProdutos(null, true);
        fp.setVisible(true);
        LimparTable();
        BuscarProdutos();
}//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if(jTable1.getRowCount() > 0 && jTable1.getSelectedRow() != -1) {
            int codigo = Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString());
            FormProdutos fp = new FormProdutos(null, true, codigo);
            fp.setVisible(true);
            LimparTable();
            BuscarProdutos();
        }
        else
            JOptionPane.showMessageDialog(this, "Selecione um Produto p/ Alteração!", "Atenção", 2);
}//GEN-LAST:event_btnAlterarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_btnFecharActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
       if (jTable1.getSelectedRow() != -1)
       {
           int result = JOptionPane.showConfirmDialog(this, "Deseja Excluir?",
                                        "Excluir", JOptionPane.YES_NO_OPTION);
           if(result == JOptionPane.YES_OPTION)
           {
               Produtos p = new Produtos();
               try
               {
                   int codigo = Integer.parseInt(jTable1.getModel().getValueAt
                                    (jTable1.getSelectedRow(), 0).toString());
                   p.Excluir(codigo);
                   ((DefaultTableModel)jTable1.getModel()).removeRow(jTable1.getSelectedRow());
               }
               catch(Exception ex)
               {
                   JOptionPane.showMessageDialog(this,"Erro na tentativa de Exclusão do Registro \n" + ex.getMessage(), "Erro", 1);
               }
           }
       }
       else
           JOptionPane.showMessageDialog(this, "Selecione um Produto p/ Exclusão!", "Atenção", 2);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed

        if(jTable1.getRowCount() > 0 && jTable1.getSelectedRow() != -1) {
            browse.setCodigoProduto(Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString()));
            browse.setDescricaoProduto(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1).toString());
            browse.setPrecoUnitario(Util.dinheiroDouble(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 3).toString()));
            browse.setIcms(Util.dinheiroDouble(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 4).toString()));
            this.setVisible(false);
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(this, "Selecione um Produto...", "Atenção", 2);
}//GEN-LAST:event_btnSelecionarActionPerformed







    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
