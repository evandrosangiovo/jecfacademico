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


import bemajava.AnalisarRetornos;
import bemajava.Bematech;
import bemajava.BemaString;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BrowseVenda extends javax.swing.JDialog {

    //Propriedades privadas da classe
    //Venda
    private int data;
    private int codigoCliente;
    private String nomeCliente;
    private String enderecoCliente;
    //Venda Itens
    private int codigoCupom;
    private int codigoItem;
    private int codigoProduto;
    private String descricaoProduto;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;
    private double icms;
    private double valorIcms;

    /**
     * Este método seta o código do cliente e atribui ao text.
     * @param codigoCliente Passe por parâmetro o código do cliente
     */
    public void setCodigoCliente(int codigoCliente){
        this.codigoCliente = codigoCliente;
        txtCodCliente.setText(String.valueOf(this.codigoCliente));
    }

    /**
     * Este método seta o nome do cliente e atribui ao text.
     * @param nomeCliente Passe por parâmetro o nome do cliente
     */
    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
        txtNomeCliente.setText(this.nomeCliente);
    }

    /**
     * Este método seta o endereço do cliente e atribui ao text.
     * @param enderecoCliente Passe por parâmetro o endereço do cliente
     */
    public void setEnderecoCliente(String enderecoCliente){
        this.enderecoCliente = enderecoCliente;
        txtEndereco.setText(this.enderecoCliente);
        
    }
    /**
     * Este método seta o código do Produto e atribui ao text.
     * @param codigoProduto Passe por parâmetro o código do produto a ser vendido
     */

    public void setCodigoProduto(int codigoProduto){
        this.codigoProduto = codigoProduto;
        txtCodigoProduto.setText(String.valueOf(this.codigoProduto));
        this.quantidade = 1;
    }

    /**
     * Este método seta a descrição do produto e atribui ao text.
     * @param descricaoProduto Passe por parâmetro a descrição do produto a ser vendido
     */
    public void setDescricaoProduto(String descricaoProduto){
        this.descricaoProduto = descricaoProduto;
        jLabelDescricaoProduto.setText(this.descricaoProduto);
    }

    /**
     * Este método seta o preço unitário do produto e atribui ao text.
     * @param precoUnitario passe por parâmetro o valor unitário do produto a ser vendido
     */
    public void setPrecoUnitario(double precoUnitario){
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoUnitario * quantidade;
        txtValorUnitario.setText(Util.mascaraDinheiro(this.precoUnitario, Util.DINHEIRO_REAL));
        txtValorTotal.setText(Util.mascaraDinheiro(this.precoTotal, Util.DINHEIRO_REAL));
        //String.valueOf(this.precoUnitario
    }

    /**
     * Este método seta a % icms do produto e atribui ao text.
     * @param icms Passe por parâmetro a % icms do produto a ser vendido
     */
    public void setIcms(double icms){
        this.icms = icms;
        this.valorIcms = this.precoTotal * (this.icms / 100);
        txtIcms.setText(Util.mascaraDinheiro(this.icms, Util.DINHEIRO_REAL));
        valorIcms =  precoTotal * (this.icms / 100);
        txtValorIcms.setText(Util.mascaraDinheiro(this.valorIcms, Util.DINHEIRO_REAL));
    }



    /**
     * Construtor do Formulário
     * @param parent Indica quem é o pai do formulário
     * @param modal Indica se o formulário será modal
     */
    public BrowseVenda(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        //JOptionPane.showMessageDialog(this,Util.dataAtual());
        this.data = Util.dataBanco(Util.dataAtual());
        BuscaNumeroCupom();
        btnSelecionarCliente.requestFocus();
    }

    /**
     * Método responsável pela busca do ultimo número do cupom Fiscal
     */
    private void BuscaNumeroCupom()
    {
        BemaString b = new BemaString();
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.NumeroCupom(b);
        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);

        if(!Retorno.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Erro na busca do número do Cupom Fiscal:\n" + Retorno, "Erro", 0);
        }
        else
        {
            codigoCupom = Integer.parseInt(b.getBuffer()) + 1;
            txtCodCupom.setText(String.valueOf(codigoCupom));
        }
    }

    /**
     * Método responsável pela busca do ultimo número do ítem vendido
     */
    private void BuscaUltimoItemVendido()
    {
        jButton1ActionPerformed(null);
        if(jTable1.getRowCount() > 0)
        {
            BemaString b = new BemaString();
            String Retorno = "";
            int iRetorno;

            iRetorno = Bematech.UltimoItemVendido(b);
            Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);
            if(!Retorno.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Erro na busca do número do ítem do cupom fiscal:\n" + Retorno, "Erro", 0);
            }
            else
            {
                codigoItem = Integer.parseInt(b.getBuffer()) + 1;
                txtSequenciaItemVenda.setText(String.valueOf(codigoItem));
            }
        }
        else
        {
            codigoItem = 1;
            txtSequenciaItemVenda.setText(String.valueOf(codigoItem));
        }
    }

    /**
     * Métodos responsáveis pela inclusão dos dados da venda(Cabeçalho) no banco de dados
     */
    private void IncluirVenda()
    {
        Venda v = new Venda(codigoCupom, data, codigoCliente, nomeCliente, enderecoCliente);
        try
        {
            v.Incluir();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Erro ao incluir o registro...\n" +
                    "Devido o erro o Cupom Fiscal será cancelado.\n" + ex.getMessage(), "Erro", 0);
            CancelaCupom();
        }
    }

    /**
     * Métodos responsáveis pela inclusão dos dados do produto (Ítem) no banco de dados
     */
    private void IncluirItem()
    {
        VendaItens vi = new VendaItens(codigoCupom, codigoItem, codigoProduto, descricaoProduto,
                                       quantidade, precoUnitario, 0, 0, icms, valorIcms);
        try
        {
            vi.Incluir();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Erro ao incluir o registro...\n" +
                    "Devido o erro o Cupom Fiscal será cancelado.\n" + ex.getMessage(), "Erro", 0);
            CancelaCupom();
        }
        jButton1ActionPerformed(null);
    }
    /**
     * Método responsávela por limpar o jtable
     */
    private void LimparTable()
    {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setNumRows(0);
        jTable1.repaint();
        jTable1.updateUI();
        this.repaint();
        this.pack();
        this.doLayout();
    }

    /**
     * Método responsável pela busca de todos os ítens vendidos e incluir no jTable
     */
    private void BuscaItens()
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

            jTable1.setModel(modeloTabela);

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

    /**
     * Método responsável pela exclusão de um ítem no banco de dados.
     */
    private void ExcluirUmItem()
    {
        VendaItens vi = new VendaItens();
        try
        {
            vi.Excluir(codigoCupom, codigoItem);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir o registro...\n" +
                                          ex.getMessage(), "Erro", 0);
        }
    }

    /**
     * Método responsável pela exclusão de todos os ítens de uma venda no banco de dados
     */
    private void ExcluirItens()
    {
        VendaItens vi = new VendaItens();
        try
        {
            if(!txtCodCliente.getText().isEmpty())
            {
                vi.Excluir(codigoCupom);
            }
            else
            {
                vi.Excluir(codigoCupom - 1);

            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir o registro...\n" +
                                          ex.getMessage(), "Erro", 0);
        }
    }

    /**
     * Método responsável pela exclusão do cabeçalho da venda no banco de dados
     */
    private void ExcluirCabecalho()
    {
        Venda v = new Venda();
        try
        {
            if(!txtCodCliente.getText().isEmpty())
            {
                ExcluirItens();
                v.Excluir(codigoCupom);
            }
            else
            {
                ExcluirItens();
                v.Excluir(codigoCupom - 1);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir o registro...\n" +
                                          ex.getMessage(), "Erro", 0);
        }
    }


    //Métodos Responsáveis pela execução dos comandos na Impressora Fiscal
    /**
     * Método responsável pela venda de um ítem na impressora fiscal
     */
    private void VendeItem()
    {
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.VendeItem(String.valueOf(codigoProduto),
                                                 descricaoProduto,
                                                 Util.mascaraDinheiro(icms, Util.DINHEIRO_REAL), "I",
                                                 "1",2,Util.mascaraDinheiro(precoUnitario, Util.DINHEIRO_REAL),
                                                 "%","0");

        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);
        
        if(!Retorno.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Erro ao vender o ítem \n" +
                                                "Devido o erro o Cupom Fiscal será cancelado.\n" +
                                                Retorno, "Erro", 0);
            CancelaCupom();
        }
        else
        {
            IncluirItem();
        }
        jButton1ActionPerformed(null);
    }

    /**
     * Método responsável pelo fechamento do cupom na impressora fiscal
     */
    private void FechaCupom()
    {
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.FechaCupomResumido("Dinheiro", "Volte Sempre. Obrigado!");

        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);
        if(!Retorno.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar o cupom fiscal\n" +
                                                "Devido o erro o Cupom Fiscal será cancelado.\n" +
                                                Retorno, "Erro", 0);
            CancelaCupom();
        }
        else
        {
            LimparTable();
            LimparTodosText();
        }
    }

    /**
     * Método responsável pelo cancelamento de um ítem do cupom fiscal na impressora fiscal
     */
    private void CancelaItem()
    {
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.CancelaItemAnterior();

        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);

        if(!Retorno.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Erro: \n" + Retorno, "Erro", 0);
        }
        else
        {
            ExcluirUmItem();
            JOptionPane.showMessageDialog(this, "Sucesso no Cancelamento do Ítem!", "Sucesso", 1);
        }
    }

    /**
     * Método responsável pelo cancelamento do cupom fiscal na impressora fiscal
     */
    private void CancelaCupom()
    {
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.CancelaCupom();

        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);
        
        if(!Retorno.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Devido ao(s) erro(s) abaixo, " +
                                         "\nnão será possivel Cancelar o Cupom Fiscal...\n\n" +
                                         Retorno, "Erro", 0);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Sucesso no Cancelamento do Cupom Fiscal!", "Sucesso", 1);
            //ExcluirItens();
            ExcluirCabecalho();
            LimparTable();
            LimparTodosText();
        }
    }

    /**
     * Método responsável pelo abertura do cupom fiscal na impressora fiscal
     */
    private void AbreCupom()
    {
        String Retorno = "";
        int iRetorno;

        iRetorno = Bematech.AbreCupom("");

        Retorno = AnalisarRetornos.Analisa_iRetorno(iRetorno);

        if(!Retorno.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Erro na abertura do cupom fiscal\n" + Retorno, "Erro", 0);
        }
        else
        {
            IncluirVenda();
        }
    }

    //Métodos Responsáveis pela iteração dos controles na UI
    /**
     * Método responsável por limpar todos os txt na UI.
     */
    private void LimparTodosText()
    {
        txtCodCliente.setText("");
        txtNomeCliente.setText("");
        txtEndereco.setText("");

        txtCodigoProduto.setText("");
        jLabelDescricaoProduto.setText("Descrição do Produto");
        txtValorUnitario.setText("0,00");
        txtValorTotal.setText("0,00");
        txtIcms.setText("0,00");
        txtValorIcms.setText("0,00");
        btnSelecionarProduto.setEnabled(false);

        BuscaNumeroCupom();
        
        btnSelecionarCliente.requestFocus();

        this.pack();
        this.doLayout();
    }

    /**
     *  Método responsável por limpar os txt referente ao produto na UI.
    private void LimparProdutoText()
    {
        txtCodigoProduto.setText("");
        jLabelDescricaoProduto.setText("Descrição do Produto");
        txtValorUnitario.setText("0,00");
        txtValorTotal.setText("0,00");
        txtIcms.setText("0,00");
        txtValorIcms.setText("0,00");

        btnSelecionarProduto.requestFocus();

        jPanel1.repaint();
        jPanel1.doLayout();
        jPanel1.updateUI();

        jPanel2.repaint();
        jPanel2.doLayout();
        jPanel2.updateUI();

        jPanel3.repaint();
        jPanel3.doLayout();
        jPanel3.updateUI();

        jPanel4.repaint();
        jPanel4.doLayout();
        jPanel4.updateUI();

        this.pack();
        this.doLayout();
    }
     */






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnFinalizar = new javax.swing.JButton();
        btnCancelarItem = new javax.swing.JButton();
        btnCancelarCupom = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodCupom = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        btnSelecionarCliente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCodigoProduto = new javax.swing.JTextField();
        jLabelDescricaoProduto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        txtValorUnitario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnSelecionarProduto = new javax.swing.JButton();
        txtIcms = new javax.swing.JTextField();
        txtValorIcms = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSequenciaItemVenda = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emissão de Cupons Fiscais");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Vendidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(50, 79, 168))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções p/ Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(50, 79, 168))); // NOI18N

        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/accept.png"))); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnCancelarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btnCancelarItem.setText("Cancelar Ítem");
        btnCancelarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarItemActionPerformed(evt);
            }
        });

        btnCancelarCupom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/14_layer_deletelayer.png"))); // NOI18N
        btnCancelarCupom.setText("Cancelar Cupom");
        btnCancelarCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCupomActionPerformed(evt);
            }
        });

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/cross.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarCupom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(btnFechar))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnCancelarItem)
                    .addComponent(btnCancelarCupom)
                    .addComponent(btnFechar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(50, 79, 168))); // NOI18N

        jLabel1.setText("Código");

        txtCodCliente.setEditable(false);

        jLabel6.setText("Nome");

        txtNomeCliente.setEditable(false);

        jLabel7.setText("Nº Cupom");

        txtCodCupom.setEditable(false);
        txtCodCupom.setFont(new java.awt.Font("Tahoma", 1, 12));
        txtCodCupom.setForeground(new java.awt.Color(204, 0, 0));
        txtCodCupom.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setText("Endereço");

        txtEndereco.setEditable(false);

        btnSelecionarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filefind.png"))); // NOI18N
        btnSelecionarCliente.setText("...");
        btnSelecionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecionarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtCodCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addGap(162, 162, 162))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addContainerGap())))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSelecionarCliente)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodCupom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(50, 79, 168))); // NOI18N

        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 1, 18));
        txtCodigoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProduto.setEnabled(false);

        jLabelDescricaoProduto.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelDescricaoProduto.setText("Descrição do Produto");
        jLabelDescricaoProduto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel2.setText("Código Reduzido");

        jLabel3.setText("Quant. Ítens");

        txtQuantidade.setEditable(false);
        txtQuantidade.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtQuantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantidade.setText("001");

        txtValorUnitario.setEditable(false);
        txtValorUnitario.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtValorUnitario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorUnitario.setText("0,00");

        jLabel4.setText("Preço Unitário");

        txtValorTotal.setEditable(false);
        txtValorTotal.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtValorTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorTotal.setText("0,00");

        jLabel5.setText("Valor Total");

        btnSelecionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filefind.png"))); // NOI18N
        btnSelecionarProduto.setText("...");
        btnSelecionarProduto.setEnabled(false);
        btnSelecionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarProdutoActionPerformed(evt);
            }
        });

        txtIcms.setEditable(false);
        txtIcms.setText("0,00");

        txtValorIcms.setEditable(false);
        txtValorIcms.setText("0,00");

        jLabel8.setText("%");

        jLabel9.setText("R$");

        txtSequenciaItemVenda.setEditable(false);
        txtSequenciaItemVenda.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtSequenciaItemVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSequenciaItemVenda.setText("1");

        jLabel11.setText("Seq. Item Venda");

        jButton1.setForeground(new java.awt.Color(212, 208, 200));
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtSequenciaItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIcms, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(62, 62, 62))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSelecionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelDescricaoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(634, 634, 634)
                        .addComponent(txtValorIcms, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDescricaoProduto)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelecionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIcms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValorIcms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSequenciaItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSelecionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarProdutoActionPerformed

        BrowseProdutos bp = new BrowseProdutos(null, true, true, this);
        txtCodigoProduto.setText("");
        BuscaUltimoItemVendido();
        bp.setVisible(true);
        if(!txtCodigoProduto.getText().isEmpty())
        {
            VendeItem();
        }
        jButton1ActionPerformed(evt);
            
}//GEN-LAST:event_btnSelecionarProdutoActionPerformed

    private void btnSelecionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarClienteActionPerformed
        BrowseClientes bc = new BrowseClientes(null, true, true, this);
        bc.setVisible(true);
        if(!txtCodCliente.getText().isEmpty())
        {
            btnSelecionarProduto.setEnabled(true);
            btnSelecionarProduto.requestFocus();
            AbreCupom();
        }
        else
            btnSelecionarProduto.setEnabled(false);
    }//GEN-LAST:event_btnSelecionarClienteActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        FechaCupom();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnCancelarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarItemActionPerformed
        CancelaItem();
        jButton1ActionPerformed(null);
    }//GEN-LAST:event_btnCancelarItemActionPerformed

    private void btnCancelarCupomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCupomActionPerformed
        CancelaCupom();
    }//GEN-LAST:event_btnCancelarCupomActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LimparTable();
        BuscaItens();
        pack();
        this.validate();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarCupom;
    private javax.swing.JButton btnCancelarItem;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnSelecionarCliente;
    private javax.swing.JButton btnSelecionarProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDescricaoProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodCupom;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtIcms;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtSequenciaItemVenda;
    private javax.swing.JTextField txtValorIcms;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables

}