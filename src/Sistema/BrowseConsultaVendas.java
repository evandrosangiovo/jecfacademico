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

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evandro
 */
public class BrowseConsultaVendas extends javax.swing.JDialog {

    public void setCodigoCliente(int codigoCliente) {
        txtCodigoCliente.setText(String.valueOf(codigoCliente));
    }

    public BrowseConsultaVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDataFinal.setText(Util.dataAtual());
    }

    private void VerificaText()
    {
        if(txtDataInicial.getText().trim().isEmpty())
            txtDataInicial.setText(Util.dataAtual());
        if(txtDataFinal.getText().trim().isEmpty())
            txtDataFinal.setText(Util.dataAtual());
        if(txtCodigoCliente.getText().trim().isEmpty())
            txtCodigoCliente.setText("0");
    }


    private void BuscarVendas()
    {
        VerificaText();
        ResultSet rs = null;
        Venda v = new Venda();
        try
        {
            if(Integer.valueOf(txtCodigoCliente.getText()) == 0)
            {
                rs = v.RetornaVenda(0, Util.dataBanco(txtDataInicial.getText()), Util.dataBanco(txtDataFinal.getText()));
            }
            else
            {
                rs = v.RetornaVenda(Integer.valueOf(txtCodigoCliente.getText()),
                                                    Util.dataBanco(txtDataInicial.getText()),
                                                    Util.dataBanco(txtDataFinal.getText()));
            }
            String[] colunasTabela = new String[]{"Nº Cupom", "Data da Venda", "Cod. Cliente", "Nome do Cliente",
                                                  "Endereço", "Valor da Venda"};

            DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela)
                    { public boolean isCellEditable (int row, int col) { return false; }};

            jTableCupons.setModel(modeloTabela);

            while(rs.next())
            {
                modeloTabela.addRow(new String [] { Integer.toString(rs.getInt("Codigo")),
                Util.dataText(rs.getInt("Data")),
                rs.getString("CodigoCliente"),
                rs.getString("NomeCliente"),
                rs.getString("Endereco"),
                Util.mascaraDinheiro(rs.getDouble("ValorTotal"), Util.DINHEIRO_REAL)});
            }
            modeloTabela.fireTableDataChanged();
            this.repaint();
            this.doLayout();
            this.pack();
            rs.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage() + '\n' + ex.getStackTrace(), "Erro", 1);
        }
    }

    private void BuscaItens(int codigoCupom)
    {
        ResultSet rs = null;
        VendaItens p = new VendaItens();
        try
        {
            rs = p.RetornaItensCupom(codigoCupom);
            String[] colunasTabela = new String[]{"Seq", "Cod. Produto", "Descrição",
                                                  "Qtd", "Preço Unitário", "Acréscimos",
                                                  "Descontos", "% ICMS", "Vlr. Icms"};

            DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela)
                    { public boolean isCellEditable (int row, int col) { return false; }};

            jTableItens.setModel(modeloTabela);

            while(rs.next())
            {
                modeloTabela.addRow(new String [] { Integer.toString(rs.getInt("CodigoItem")),
                Integer.toString(rs.getInt("CodigoProduto")),
                rs.getString("DescricaoProduto"),
                Integer.toString(rs.getInt("Quantidade")),
                Util.mascaraDinheiro(rs.getDouble("PrecoUnitario"), Util.DINHEIRO_REAL),
                Util.mascaraDinheiro(rs.getDouble("Acrescimos"), Util.DINHEIRO_REAL),
                Util.mascaraDinheiro(rs.getDouble("Descontos"), Util.DINHEIRO_REAL),
                Util.mascaraDinheiro(rs.getDouble("Icms"), Util.DINHEIRO_REAL),
                Util.mascaraDinheiro(rs.getDouble("ValorIcms"), Util.DINHEIRO_REAL)});
            }
            modeloTabela.fireTableDataChanged();
            this.repaint();
            this.doLayout();
            this.pack();
            rs.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage() + '\n' + ex.getStackTrace(), "Erro", 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDataInicial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDataFinal = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCupons = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItens = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros p/ Consulta"));

        jLabel1.setText("Cód. Cliente");

        jLabel2.setText("Data Incial");

        jLabel3.setText("Data Final");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filefind.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filefind.png"))); // NOI18N
        btnBuscarCliente.setText("...");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCliente)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado da Pesquisa"));

        jTableCupons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCupons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCuponsMouseClicked(evt);
            }
        });
        jTableCupons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableCuponsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCupons);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Cupons Fiscais", jPanel2);

        jTableItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableItens);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ítens do Cupom Fiscal Selecionado", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cancel1.png"))); // NOI18N
        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        BuscarVendas();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        BrowseClientes c = new BrowseClientes(null, true, true, this);
        c.setVisible(true);
        if(!txtCodigoCliente.getText().isEmpty())
            txtDataInicial.requestFocus();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void jTableCuponsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCuponsMouseClicked

        if(jTableCupons.getSelectedRow() != -1)
        {
            int codigo = Integer.parseInt(jTableCupons.getModel().getValueAt(jTableCupons.getSelectedRow(), 0).toString());
            BuscaItens(codigo);
        }
    }//GEN-LAST:event_jTableCuponsMouseClicked

    private void jTableCuponsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCuponsKeyReleased


        if(jTableCupons.getSelectedRow() != -1)
        {
            int codigo = Integer.parseInt(jTableCupons.getModel().getValueAt(jTableCupons.getSelectedRow(), 0).toString());
            BuscaItens(codigo);
        }
    }//GEN-LAST:event_jTableCuponsKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCupons;
    private javax.swing.JTable jTableItens;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtDataFinal;
    private javax.swing.JTextField txtDataInicial;
    // End of variables declaration//GEN-END:variables

}
