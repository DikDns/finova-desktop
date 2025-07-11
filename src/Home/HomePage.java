/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Home;

import Login.Login;
import finova.PdfExporter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import Database.UserSession;
import javax.swing.table.DefaultTableModel;
import Database.DatabaseManager;
import CurrencyAPI.CurrencyAPI.CurrencyInfo;

import java.util.Date;
import Chart.IncomeExpenseChart;
import CurrencyAPI.CurrencyAPI;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Lenovo
 */
public class HomePage extends javax.swing.JFrame {

  private javax.swing.JTextField balanceTextField;
  private javax.swing.JTextField liabilitiesTextField;

  UserSession s;

  ImageIcon logo = new ImageIcon(this.getClass().getResource("/Icon/finova-logo.png"));

  /**
   * Creates new form HomePage
   */
  public HomePage() {
    this.s = new UserSession();
    initComponents();
    
    populate_home_page();
    String currentBalance = balanceLabel.getText();
    AccountBalanceLabel.setText(currentBalance);
    IncomeBalanceLabel.setText(currentBalance);
    ExpenseBalanceLabel.setText(currentBalance);
    
    showGreeting();

    updateComponents();
  }

  private void updateComponents() {
    addAccountComboBox();
    populateIncomeSourcesComboBox();
    populateExpenseCategoryComboBox();
    populateTable();
    populate_income_table();
    populate_expense_table();
    populate_home_page();
    populate_transactions();
    fillRemoveBudgetComboBox();
    populate_budget();
    updateProgressBar();

    refreshButton.setIcon(new FlatSVGIcon("Icon/refresh.svg", refreshButton.getWidth(), refreshButton.getHeight()));
    jLabel20.setIcon(new FlatSVGIcon("Icon/empty-wallet.svg", refreshButton.getWidth(), refreshButton.getHeight()));
    jLabel16.setIcon(new FlatSVGIcon("Icon/presention-chart.svg", refreshButton.getWidth(), refreshButton.getHeight()));
    jLabel14.setIcon(new FlatSVGIcon("Icon/money-recive.svg", refreshButton.getWidth(), refreshButton.getHeight()));
    jLabel4.setIcon(new FlatSVGIcon("Icon/money-send.svg", refreshButton.getWidth(), refreshButton.getHeight()));
    setIconImage(logo.getImage());

    CurrencyInfo info = CurrencyAPI.fetchCurrencyDataWithFallback();

    if (info != null) {
      System.out.println("Data received in MyApplication:");
      System.out.println("Date: " + info.getDate());
      System.out.println("Full info: " + info);
      exchangeRatesLabel.setText("1 USD = " + info.getFormattedIdrRate());
    } else {
      System.out.println("MyApplication: Failed to retrieve currency data.");
    }

    expenseTable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow != -1) {
          // Ambil data dari tabel
          String account = expenseTable.getValueAt(selectedRow, 0).toString();
          String category = expenseTable.getValueAt(selectedRow, 1).toString();
          String amount = expenseTable.getValueAt(selectedRow, 2).toString();
          String date = expenseTable.getValueAt(selectedRow, 3).toString();
          String remark = expenseTable.getValueAt(selectedRow, 4).toString();

          // Isi data ke form
          expenseAccountName.setSelectedItem(account);
          expenseCategoryComboBox.setSelectedItem(category);
          expenseAmount.setText(amount);
          expenseRemark.setText(remark);

          try {
            java.util.Date parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            expenseDate.setDate(parsedDate);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

      }
    });
    incomeTable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = incomeTable.getSelectedRow();
        if (selectedRow != -1) {
          String account = incomeTable.getValueAt(selectedRow, 0).toString();
          String date = incomeTable.getValueAt(selectedRow, 1).toString();
          String source = incomeTable.getValueAt(selectedRow, 2).toString();
          String amount = incomeTable.getValueAt(selectedRow, 3).toString();

          incomeAccountName.setSelectedItem(account);
          incomeSourceComboBox.setSelectedItem(source);
          incomeAmount.setText(amount);

          try {
            java.util.Date parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            incomeDate.setDate(parsedDate);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });
    accountTable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = accountTable.getSelectedRow();
        if (row != -1) {
          jTextField1.setText(accountTable.getValueAt(row, 0).toString()); // hanya isi nama account
        }
      }
    });
    budgetTable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = budgetTable.getSelectedRow();
        if (selectedRow != -1) {
          String category = budgetTable.getValueAt(selectedRow, 0).toString();
          String amount = budgetTable.getValueAt(selectedRow, 1).toString();

          BudgetExpenseCategoryComboBox.setSelectedItem(category);
          BudgetAmount.setText(amount);
        }
      }
    });

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        gGPlot2Theme1 = new org.knowm.xchart.style.theme.GGPlot2Theme();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        nBudgetButton = new javax.swing.JToggleButton();
        refreshButton = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Home_tab_scrollpane = new javax.swing.JScrollPane();
        Home_tab = new javax.swing.JPanel();
        exchangeRatesLabel = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        balanceLabel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        liabilitiesLabel = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        incomeLabel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        expenseLabel = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        progressLabel = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        greetingLabel = new javax.swing.JLabel();
        Account_tab = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        deleteAccount = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        createAccount = new javax.swing.JButton();
        AccountUpdateButton = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        AccountBalanceLabel = new javax.swing.JTextField();
        Income_tab_scrollpane = new javax.swing.JScrollPane();
        Income_tab = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        incomeDate = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        incomeSourceComboBox = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        incomeAmount = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        incomeAccountName = new javax.swing.JComboBox<>();
        addIncomeButton = new javax.swing.JButton();
        resetIncomeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        addIncomeSource = new javax.swing.JButton();
        incomeSourceTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        incomeTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        updateIncomeButton = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        IncomeBalanceLabel = new javax.swing.JTextField();
        Expense_tab_scrollPane = new javax.swing.JScrollPane();
        Expense = new javax.swing.JPanel();
        Expense_Tab = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        expenseAccountName = new javax.swing.JComboBox<>();
        expenseCategoryComboBox = new javax.swing.JComboBox<>();
        expenseCategoryTextField = new javax.swing.JTextField();
        expenseRemark = new javax.swing.JTextField();
        ExpenseAddButton = new javax.swing.JButton();
        ExpenseResetButton = new javax.swing.JButton();
        expenseDate = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        expenseTable = new javax.swing.JTable();
        addExpenseCategory = new javax.swing.JButton();
        expenseAmount = new javax.swing.JTextField();
        ExpenseUpdateButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        ExpenseBalanceLabel = new javax.swing.JTextField();
        Budget_tab_scrollpane = new javax.swing.JScrollPane();
        Budget_tab = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        BudgetExpenseCategoryComboBox = new javax.swing.JComboBox<>();
        removeBudgetComboBox = new javax.swing.JComboBox<>();
        BudgetAmount = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        BudgetExpenseCategoryTextField = new javax.swing.JTextField();
        addBudgetExpenseCategoryButton = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        removeBudgetButton = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        addBudget = new javax.swing.JButton();
        targetAmountTextField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        addTargetAmountButton = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        budgetTable = new javax.swing.JTable();
        BudgetUpdateButton = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finova Desktop");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton2.setBackground(new java.awt.Color(0, 102, 102));
        jToggleButton2.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setText("Home");
        jToggleButton2.setBorder(null);
        jToggleButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton2.setName("Home"); // NOI18N
        jToggleButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseExited(evt);
            }
        });
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 60));

        jToggleButton3.setBackground(new java.awt.Color(0, 102, 102));
        jToggleButton3.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton3.setText("Accounts");
        jToggleButton3.setBorder(null);
        jToggleButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton3.setMaximumSize(new java.awt.Dimension(33, 16));
        jToggleButton3.setMinimumSize(new java.awt.Dimension(33, 16));
        jToggleButton3.setPreferredSize(new java.awt.Dimension(33, 16));
        jToggleButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseExited(evt);
            }
        });
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 140, 60));

        jToggleButton4.setBackground(new java.awt.Color(0, 102, 102));
        jToggleButton4.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton4.setText("Incomes");
        jToggleButton4.setBorder(null);
        jToggleButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton4.setMaximumSize(new java.awt.Dimension(33, 16));
        jToggleButton4.setMinimumSize(new java.awt.Dimension(33, 16));
        jToggleButton4.setPreferredSize(new java.awt.Dimension(33, 16));
        jToggleButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseExited(evt);
            }
        });
        jPanel1.add(jToggleButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 140, 60));

        jToggleButton5.setBackground(new java.awt.Color(0, 102, 102));
        jToggleButton5.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jToggleButton5.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton5.setText("Expenses");
        jToggleButton5.setBorder(null);
        jToggleButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseExited(evt);
            }
        });
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 140, 60));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Logout");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 80, 40));

        nBudgetButton.setBackground(new java.awt.Color(0, 102, 102));
        nBudgetButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        nBudgetButton.setForeground(new java.awt.Color(255, 255, 255));
        nBudgetButton.setText("Budget");
        nBudgetButton.setBorder(null);
        nBudgetButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nBudgetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nBudgetButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nBudgetButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nBudgetButtonMouseExited(evt);
            }
        });
        nBudgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nBudgetButtonActionPerformed(evt);
            }
        });
        jPanel1.add(nBudgetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 140, 60));

        refreshButton.setBackground(new java.awt.Color(0, 102, 102));
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setBorder(null);
        refreshButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshButtonMouseExited(evt);
            }
        });
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 60));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N

        Home_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exchangeRatesLabel.setEditable(false);
        exchangeRatesLabel.setFont(new java.awt.Font("Spectral", 0, 12)); // NOI18N
        exchangeRatesLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exchangeRatesLabelActionPerformed(evt);
            }
        });
        Home_tab.add(exchangeRatesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 140, 30));

        jPanel7.setBackground(new java.awt.Color(225, 225, 225));
        jPanel7.setLayout(null);

        balanceLabel.setEditable(false);
        balanceLabel.setFont(new java.awt.Font("Spectral", 1, 14)); // NOI18N
        balanceLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceLabelActionPerformed(evt);
            }
        });
        jPanel7.add(balanceLabel);
        balanceLabel.setBounds(30, 100, 130, 30);

        jLabel9.setFont(new java.awt.Font("Chivo", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Current Balance");
        jPanel7.add(jLabel9);
        jLabel9.setBounds(0, 60, 190, 40);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel20);
        jLabel20.setBounds(60, 10, 70, 60);

        Home_tab.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 190, 150));

        jPanel6.setBackground(new java.awt.Color(225, 225, 225));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        liabilitiesLabel.setEditable(false);
        liabilitiesLabel.setFont(new java.awt.Font("Spectral", 1, 14)); // NOI18N
        jPanel6.add(liabilitiesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 140, 30));

        jLabel10.setFont(new java.awt.Font("Chivo", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Liabilites");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 190, -1));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 70, 60));

        Home_tab.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 190, 150));

        jPanel8.setBackground(new java.awt.Color(225, 225, 225));
        jPanel8.setLayout(null);

        incomeLabel.setEditable(false);
        incomeLabel.setFont(new java.awt.Font("Spectral", 1, 14)); // NOI18N
        incomeLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeLabelActionPerformed(evt);
            }
        });
        jPanel8.add(incomeLabel);
        incomeLabel.setBounds(30, 100, 130, 30);

        jLabel11.setFont(new java.awt.Font("Chivo", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Month's Income");
        jPanel8.add(jLabel11);
        jLabel11.setBounds(0, 60, 190, 40);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel14);
        jLabel14.setBounds(60, 10, 70, 60);

        Home_tab.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 190, 150));

        jPanel9.setBackground(new java.awt.Color(225, 225, 225));
        jPanel9.setLayout(null);

        expenseLabel.setEditable(false);
        expenseLabel.setFont(new java.awt.Font("Spectral", 1, 14)); // NOI18N
        jPanel9.add(expenseLabel);
        expenseLabel.setBounds(30, 100, 130, 30);

        jLabel12.setFont(new java.awt.Font("Chivo", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Month's Expense");
        jPanel9.add(jLabel12);
        jLabel12.setBounds(0, 60, 190, 40);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel4);
        jLabel4.setBounds(60, 10, 70, 60);

        Home_tab.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 190, 150));

        jButton2.setBackground(new java.awt.Color(55, 98, 217));
        jButton2.setFont(new java.awt.Font("Chivo", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Generate Chart");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Home_tab.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 140, 40));

        jLabel31.setFont(new java.awt.Font("Chivo", 1, 16)); // NOI18N
        jLabel31.setText("Exchange Rate:");
        Home_tab.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 130, 30));

        transactionTable.setFont(new java.awt.Font("Spectral", 0, 12)); // NOI18N
        transactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Transaction Id", "Account Id", "Transaction Type", "Amount", "Statement", "Date"
            }
        ));
        transactionTable.setEnabled(false);
        jScrollPane3.setViewportView(transactionTable);

        Home_tab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 790, 150));

        jPanel2.setBackground(new java.awt.Color(225, 225, 225));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Spectral", 0, 14)); // NOI18N
        jLabel41.setText("Monthly Savings:");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jProgressBar1.setFont(new java.awt.Font("Spectral", 0, 10)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(33, 225, 65));
        jPanel2.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 180, 20));

        progressLabel.setFont(new java.awt.Font("Spectral", 0, 14)); // NOI18N
        progressLabel.setText("No Data Available");
        jPanel2.add(progressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 130, -1));

        Home_tab.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 450, 40));

        printButton.setBackground(new java.awt.Color(55, 98, 217));
        printButton.setFont(new java.awt.Font("Chivo", 1, 12)); // NOI18N
        printButton.setForeground(new java.awt.Color(255, 255, 255));
        printButton.setText("Export PDF");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        Home_tab.add(printButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 140, 40));

        jLabel43.setFont(new java.awt.Font("Spectral", 0, 12)); // NOI18N
        jLabel43.setText("(Income is the money you earn and Expenses are the money you spend.)");
        Home_tab.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        jLabel44.setFont(new java.awt.Font("Chivo", 1, 16)); // NOI18N
        jLabel44.setText("Transactions");
        Home_tab.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 110, 30));

        greetingLabel.setFont(new java.awt.Font("Chivo", 1, 18)); // NOI18N
        Home_tab.add(greetingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        Home_tab_scrollpane.setViewportView(Home_tab);

        jTabbedPane1.addTab("Home", Home_tab_scrollpane);

        Account_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel5.setText("Delete Account");
        Account_tab.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, 30));

        accountTable.setAutoCreateRowSorter(true);
        accountTable.setFont(new java.awt.Font("Spectral", 0, 14)); // NOI18N
        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Account Name", "Current Balance", "Total Expenses"
            }
        ));
        accountTable.setGridColor(new java.awt.Color(204, 204, 204));
        accountTable.setRowHeight(24);
        accountTable.setShowGrid(true);
        accountTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(accountTable);

        Account_tab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 770, 120));

        jLabel6.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel6.setText("Accounts");
        Account_tab.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 30));

        deleteAccount.setBackground(new java.awt.Color(55, 98, 217));
        deleteAccount.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        deleteAccount.setForeground(new java.awt.Color(255, 255, 255));
        deleteAccount.setText("Delete");
        deleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountActionPerformed(evt);
            }
        });
        Account_tab.add(deleteAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, -1, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        Account_tab.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 150, 30));

        jLabel2.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel2.setText("Name");
        Account_tab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, 30));

        jLabel7.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel7.setText("Create Account");
        Account_tab.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 30));

        jLabel8.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel8.setText("Name");
        Account_tab.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        jComboBox1.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        Account_tab.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 150, 30));

        createAccount.setBackground(new java.awt.Color(55, 98, 217));
        createAccount.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        createAccount.setForeground(new java.awt.Color(255, 255, 255));
        createAccount.setText("Create");
        createAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountActionPerformed(evt);
            }
        });
        Account_tab.add(createAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, 30));

        AccountUpdateButton.setBackground(new java.awt.Color(55, 98, 217));
        AccountUpdateButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        AccountUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        AccountUpdateButton.setText("Update");
        AccountUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountUpdateButtonActionPerformed(evt);
            }
        });
        Account_tab.add(AccountUpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, 30));

        jLabel45.setText("An account stores your money and tracks transactions across banks, wallets, or other financial sources.");
        Account_tab.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel46.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel46.setText("Current Balance");
        Account_tab.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        AccountBalanceLabel.setEditable(false);
        AccountBalanceLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountBalanceLabelActionPerformed(evt);
            }
        });
        Account_tab.add(AccountBalanceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 100, 30));

        jTabbedPane1.addTab("Accounts", Account_tab);

        Income_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel23.setText("Date");
        Income_tab.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 60, 30));
        Income_tab.add(incomeDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 210, 30));

        jLabel24.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel24.setText("Source");
        Income_tab.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 30));

        incomeSourceComboBox.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        incomeSourceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        incomeSourceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeSourceComboBoxActionPerformed(evt);
            }
        });
        Income_tab.add(incomeSourceComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 210, 30));

        jLabel25.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel25.setText("Account");
        Income_tab.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, 30));

        incomeAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeAmountActionPerformed(evt);
            }
        });
        Income_tab.add(incomeAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 210, 30));

        jLabel26.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel26.setText("Amount");
        Income_tab.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, 30));

        incomeAccountName.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        incomeAccountName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        incomeAccountName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeAccountNameActionPerformed(evt);
            }
        });
        Income_tab.add(incomeAccountName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 210, 30));

        addIncomeButton.setBackground(new java.awt.Color(55, 98, 217));
        addIncomeButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        addIncomeButton.setForeground(new java.awt.Color(255, 255, 255));
        addIncomeButton.setText("Add");
        addIncomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIncomeButtonActionPerformed(evt);
            }
        });
        Income_tab.add(addIncomeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 80, 30));

        resetIncomeButton.setBackground(new java.awt.Color(55, 98, 217));
        resetIncomeButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        resetIncomeButton.setForeground(new java.awt.Color(255, 255, 255));
        resetIncomeButton.setText("Reset");
        resetIncomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetIncomeButtonActionPerformed(evt);
            }
        });
        Income_tab.add(resetIncomeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 80, 30));

        jLabel1.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel1.setText("New Source");
        Income_tab.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, 30));

        addIncomeSource.setBackground(new java.awt.Color(55, 98, 217));
        addIncomeSource.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        addIncomeSource.setForeground(new java.awt.Color(255, 255, 255));
        addIncomeSource.setText("Add");
        addIncomeSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIncomeSourceActionPerformed(evt);
            }
        });
        Income_tab.add(addIncomeSource, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 70, 30));
        Income_tab.add(incomeSourceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 140, 30));

        incomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Account", "Date", "Source", "Amount"
            }
        ));
        jScrollPane2.setViewportView(incomeTable);

        Income_tab.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 780, 120));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Incomes");
        Income_tab.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, -1));

        updateIncomeButton.setBackground(new java.awt.Color(55, 98, 217));
        updateIncomeButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        updateIncomeButton.setForeground(new java.awt.Color(255, 255, 255));
        updateIncomeButton.setText("Update");
        updateIncomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateIncomeButtonActionPerformed(evt);
            }
        });
        Income_tab.add(updateIncomeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, -1, 30));

        jLabel21.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel21.setText("Income");
        Income_tab.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 170, 20));

        jLabel47.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel47.setText("Current Balance");
        Income_tab.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, 20));

        IncomeBalanceLabel.setEditable(false);
        IncomeBalanceLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncomeBalanceLabelActionPerformed(evt);
            }
        });
        Income_tab.add(IncomeBalanceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 100, 30));

        Income_tab_scrollpane.setViewportView(Income_tab);

        jTabbedPane1.addTab("Income", Income_tab_scrollpane);

        Expense.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Expense_Tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel15.setText("Account");
        Expense_Tab.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, -1));

        expenseAccountName.setFont(new java.awt.Font("Spectral", 0, 12)); // NOI18N
        expenseAccountName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        expenseAccountName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseAccountNameActionPerformed(evt);
            }
        });
        Expense_Tab.add(expenseAccountName, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 210, -1));

        expenseCategoryComboBox.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        expenseCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        expenseCategoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseCategoryComboBoxActionPerformed(evt);
            }
        });
        Expense_Tab.add(expenseCategoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 140, 30));
        Expense_Tab.add(expenseCategoryTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 130, 30));
        Expense_Tab.add(expenseRemark, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 150, 30));

        ExpenseAddButton.setBackground(new java.awt.Color(55, 98, 217));
        ExpenseAddButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        ExpenseAddButton.setForeground(new java.awt.Color(255, 255, 255));
        ExpenseAddButton.setText("Add");
        ExpenseAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpenseAddButtonActionPerformed(evt);
            }
        });
        Expense_Tab.add(ExpenseAddButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 80, 30));

        ExpenseResetButton.setBackground(new java.awt.Color(55, 98, 217));
        ExpenseResetButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        ExpenseResetButton.setForeground(new java.awt.Color(255, 255, 255));
        ExpenseResetButton.setText("Reset");
        ExpenseResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpenseResetButtonActionPerformed(evt);
            }
        });
        Expense_Tab.add(ExpenseResetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 80, 30));
        Expense_Tab.add(expenseDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 210, -1));

        jLabel22.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel22.setText("Date");
        Expense_Tab.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 60, 20));

        expenseTable.setFont(new java.awt.Font("Spectral", 0, 12)); // NOI18N
        expenseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Account", "Category", "Amount", "Date", "Remark"
            }
        ));
        jScrollPane4.setViewportView(expenseTable);

        Expense_Tab.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 760, 100));

        addExpenseCategory.setBackground(new java.awt.Color(55, 98, 217));
        addExpenseCategory.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        addExpenseCategory.setForeground(new java.awt.Color(255, 255, 255));
        addExpenseCategory.setText("Add");
        addExpenseCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExpenseCategoryActionPerformed(evt);
            }
        });
        Expense_Tab.add(addExpenseCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 70, 30));
        Expense_Tab.add(expenseAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 150, 30));

        ExpenseUpdateButton.setBackground(new java.awt.Color(55, 98, 217));
        ExpenseUpdateButton.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        ExpenseUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        ExpenseUpdateButton.setText("Update");
        ExpenseUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpenseUpdateButtonActionPerformed(evt);
            }
        });
        Expense_Tab.add(ExpenseUpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, 30));

        jLabel13.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel13.setText("Add Expenses");
        Expense_Tab.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 110, 20));

        jLabel48.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel48.setText("Expense");
        Expense_Tab.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        jLabel30.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel30.setText("Category");
        Expense_Tab.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 110, 30));

        jLabel17.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel17.setText("Add Category");
        Expense_Tab.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 110, 30));

        jLabel18.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel18.setText("Amount");
        Expense_Tab.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 100, 30));

        jLabel19.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel19.setText("Remark");
        Expense_Tab.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, 80, 30));

        jLabel49.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel49.setText("Current Balance");
        Expense_Tab.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        ExpenseBalanceLabel.setEditable(false);
        ExpenseBalanceLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpenseBalanceLabelActionPerformed(evt);
            }
        });
        Expense_Tab.add(ExpenseBalanceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 32, 90, 30));

        Expense.add(Expense_Tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        Expense_tab_scrollPane.setViewportView(Expense);

        jTabbedPane1.addTab("Expense", Expense_tab_scrollPane);

        Budget_tab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Spectral", 0, 13)); // NOI18N
        jLabel33.setText("A budget helps users allocate their income to different categories for effective financial management and goal achievement.");
        Budget_tab.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        BudgetExpenseCategoryComboBox.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N
        BudgetExpenseCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--", " " }));
        BudgetExpenseCategoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BudgetExpenseCategoryComboBoxActionPerformed(evt);
            }
        });
        Budget_tab.add(BudgetExpenseCategoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 140, 30));

        removeBudgetComboBox.setFont(new java.awt.Font("Spectral", 0, 13)); // NOI18N
        removeBudgetComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        Budget_tab.add(removeBudgetComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 140, 30));

        BudgetAmount.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N
        Budget_tab.add(BudgetAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 140, 30));

        jLabel34.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel34.setText("Allocate Budget");
        Budget_tab.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel35.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel35.setText("Expense Category");
        Budget_tab.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel36.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel36.setText("Add New Category");
        Budget_tab.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 140, 30));

        BudgetExpenseCategoryTextField.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N
        Budget_tab.add(BudgetExpenseCategoryTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 130, 30));

        addBudgetExpenseCategoryButton.setBackground(new java.awt.Color(55, 98, 217));
        addBudgetExpenseCategoryButton.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N
        addBudgetExpenseCategoryButton.setForeground(new java.awt.Color(255, 255, 255));
        addBudgetExpenseCategoryButton.setText("Add");
        addBudgetExpenseCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBudgetExpenseCategoryButtonActionPerformed(evt);
            }
        });
        Budget_tab.add(addBudgetExpenseCategoryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 70, 30));

        jLabel37.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel37.setText("Remove Budget");
        Budget_tab.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        removeBudgetButton.setBackground(new java.awt.Color(55, 98, 217));
        removeBudgetButton.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N
        removeBudgetButton.setForeground(new java.awt.Color(255, 255, 255));
        removeBudgetButton.setText("Remove");
        removeBudgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBudgetButtonActionPerformed(evt);
            }
        });
        Budget_tab.add(removeBudgetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, 30));

        jLabel38.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel38.setText("Amount");
        Budget_tab.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        addBudget.setBackground(new java.awt.Color(55, 98, 217));
        addBudget.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        addBudget.setForeground(new java.awt.Color(255, 255, 255));
        addBudget.setText("Allocate Budget");
        addBudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBudgetActionPerformed(evt);
            }
        });
        Budget_tab.add(addBudget, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, -1, 30));

        targetAmountTextField.setFont(new java.awt.Font("Spectral", 0, 13)); // NOI18N
        Budget_tab.add(targetAmountTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 140, 30));

        jLabel39.setFont(new java.awt.Font("Chivo", 0, 12)); // NOI18N
        jLabel39.setText("Target Amount");
        Budget_tab.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel40.setFont(new java.awt.Font("Spectral", 0, 12)); // NOI18N
        jLabel40.setText("(amount which you are targeting to save in this month)");
        Budget_tab.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        addTargetAmountButton.setBackground(new java.awt.Color(55, 98, 217));
        addTargetAmountButton.setFont(new java.awt.Font("Chivo", 0, 13)); // NOI18N
        addTargetAmountButton.setForeground(new java.awt.Color(255, 255, 255));
        addTargetAmountButton.setText("Add");
        addTargetAmountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTargetAmountButtonActionPerformed(evt);
            }
        });
        Budget_tab.add(addTargetAmountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, 30));

        jLabel42.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel42.setText("Budget's");
        Budget_tab.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        budgetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Expense Category", "Amount", "ID"
            }
        ));
        jScrollPane7.setViewportView(budgetTable);

        Budget_tab.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 770, 140));

        BudgetUpdateButton.setBackground(new java.awt.Color(55, 98, 217));
        BudgetUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        BudgetUpdateButton.setText("Update");
        BudgetUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BudgetUpdateButtonActionPerformed(evt);
            }
        });
        Budget_tab.add(BudgetUpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, -1, 30));

        jLabel32.setFont(new java.awt.Font("Chivo", 1, 14)); // NOI18N
        jLabel32.setText("Add Budget");
        Budget_tab.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 30));

        Budget_tab_scrollpane.setViewportView(Budget_tab);

        jTabbedPane1.addTab("Budget", Budget_tab_scrollpane);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 860, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IncomeBalanceLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncomeBalanceLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IncomeBalanceLabelActionPerformed

    private void AccountBalanceLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountBalanceLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountBalanceLabelActionPerformed

    private void ExpenseBalanceLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpenseBalanceLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExpenseBalanceLabelActionPerformed

    private void BudgetExpenseCategoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO: tambahkan aksi jika diperlukan
    }
    private void exchangeRatesLabelActionPerformed(java.awt.event.ActionEvent evt) {
    // Kosongkan atau beri logika jika perlu
    }

    private void balanceLabelActionPerformed(java.awt.event.ActionEvent evt) {
    // Kosongkan atau beri logika jika perlu
    }

    private void showGreeting(){
        try {
            DatabaseManager.connect();
            ResultSet rs = DatabaseManager.executeQuery(
            "Select username from user where user_id = " + UserSession.userId
            );
            
        if (rs.next()) {
            String username = rs.getString("username");
            String greeting = "Halo, " + username + "! Welcome back to Finova Desktop App.";
            greetingLabel.setText(greeting);
        }
    } catch (Exception e){
        System.out.println("Errot fetching greeting: " + e.getMessage());
    }
    }
            

  private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_printButtonActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save PDF");
    fileChooser.setSelectedFile(new File("FinovaReport.pdf"));
    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      PdfExporter exporter = new PdfExporter();
      exporter.exportToPdf(fileToSave.getAbsolutePath());
      JOptionPane.showMessageDialog(this, "PDF Exported Successfully!", "Export Success",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }// GEN-LAST:event_printButtonActionPerformed

  private void incomeLabelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_incomeLabelActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_incomeLabelActionPerformed

  private void nBudgetButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nBudgetButtonActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_nBudgetButtonActionPerformed

  private void ExpenseUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ExpenseUpdateButtonActionPerformed
    // TODO add your handling code here:
    int selectedRow = expenseTable.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(null, "Please select the data row you want to update.");
      return;
    }

    String oldAccount = expenseTable.getValueAt(selectedRow, 0).toString();
    String oldCategory = expenseTable.getValueAt(selectedRow, 1).toString();
    String oldAmountText = expenseTable.getValueAt(selectedRow, 2).toString();
    String oldDateText = expenseTable.getValueAt(selectedRow, 3).toString();
    String oldRemark = expenseTable.getValueAt(selectedRow, 4).toString();

    String expenseCategory = expenseCategoryComboBox.getSelectedItem().toString();
    String expenseAmountText = expenseAmount.getText();
    String expenseRemarkText = expenseRemark.getText();

    if (expenseDate.getDate() == null) {
      JOptionPane.showMessageDialog(null, "Please enter Expense Date.");
      return;
    }
    if (expenseAccountName.getSelectedItem() == "--select--") {
      JOptionPane.showMessageDialog(null, "Please select Account!");
      return;
    }

    java.sql.Date expensedate = new java.sql.Date(expenseDate.getDate().getTime());
    int accountId = 0;
    int oldAccountId = 0;

    if (!expenseCategory.isEmpty() && !expenseCategory.equals("--select--")) {
      if (!expenseAmountText.isEmpty()) {
        try {
          double newAmount = Double.parseDouble(expenseAmountText);
          double oldAmount = Double.parseDouble(oldAmountText);

          DatabaseManager.connect();

          // Get old account details
          String oldAccountQuery = "SELECT account_id, balance, liabilities FROM account WHERE account_type = ? and user_id = ?";
          PreparedStatement oldAccountStmt = DatabaseManager.getConnection().prepareStatement(oldAccountQuery);
          oldAccountStmt.setString(1, oldAccount);
          oldAccountStmt.setInt(2, UserSession.userId);
          ResultSet oldAccountResult = oldAccountStmt.executeQuery();

          if (oldAccountResult.next()) {
            oldAccountId = oldAccountResult.getInt("account_id");
            double oldAccountBalance = oldAccountResult.getDouble("balance");
            double oldAccountLiabilities = oldAccountResult.getDouble("liabilities");

            // Revert old transaction (add back the old amount to balance, subtract from
            // liabilities)
            double revertedBalance = oldAccountBalance + oldAmount;
            double revertedLiabilities = oldAccountLiabilities - oldAmount;

            String revertQuery = "UPDATE account SET balance = ?, liabilities = ? WHERE account_id = ?";
            PreparedStatement revertStmt = DatabaseManager.getConnection().prepareStatement(revertQuery);
            revertStmt.setDouble(1, revertedBalance);
            revertStmt.setDouble(2, revertedLiabilities);
            revertStmt.setInt(3, oldAccountId);
            revertStmt.executeUpdate();
            revertStmt.close();
          }
          oldAccountStmt.close();

          // Get new account details
          String accountQuery = "SELECT account_id, balance, liabilities FROM account WHERE account_type = ? and user_id = ?";
          PreparedStatement accountStmt = DatabaseManager.getConnection().prepareStatement(accountQuery);
          accountStmt.setString(1, expenseAccountName.getSelectedItem().toString());
          accountStmt.setInt(2, UserSession.userId);
          ResultSet accountResult = accountStmt.executeQuery();

          if (accountResult.next()) {
            accountId = accountResult.getInt("account_id");
            double currentBalance = accountResult.getDouble("balance");
            double currentTotalExp = accountResult.getDouble("liabilities");

            if (currentBalance < newAmount) {
              JOptionPane.showMessageDialog(null,
                  "Insufficient funds. Please choose a different account.");
              return;
            }

            double newExp = currentTotalExp + newAmount;
            double newBalance = currentBalance - newAmount;
            String updateQuery = "UPDATE account SET balance = ?, liabilities = ? WHERE account_id = ?";
            PreparedStatement updateStmt = DatabaseManager.getConnection().prepareStatement(updateQuery);
            updateStmt.setDouble(1, newBalance);
            updateStmt.setDouble(2, newExp);
            updateStmt.setInt(3, accountId);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
              // Update the expense record
              String updateExpenseQuery = "UPDATE expense SET account_id = ?, expense_date = ?, expense_category = ?, remark = ?, amount = ? WHERE account_id = ? AND expense_category = ? AND expense_date = ? AND amount = ? AND user_id = ?";
              PreparedStatement expenseStmt = DatabaseManager.getConnection().prepareStatement(updateExpenseQuery);
              expenseStmt.setInt(1, accountId);
              expenseStmt.setDate(2, expensedate);
              expenseStmt.setString(3, expenseCategory);
              expenseStmt.setString(4, expenseRemarkText);
              expenseStmt.setDouble(5, newAmount);
              expenseStmt.setInt(6, oldAccountId);
              expenseStmt.setString(7, oldCategory);
              expenseStmt.setString(8, oldDateText);
              expenseStmt.setDouble(9, oldAmount);
              expenseStmt.setInt(10, UserSession.userId);

              int expenseRowsUpdated = expenseStmt.executeUpdate();
              if (expenseRowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Expense updated successfully!");
                expenseDate.setDate(null);
                expenseCategoryComboBox.setSelectedIndex(0);
                expenseAmount.setText("");
                expenseRemark.setText("");
                expenseAccountName.setSelectedIndex(0);
                updateComponents();
              } else {
                JOptionPane.showMessageDialog(null, "Failed to update expense.");
              }

              expenseStmt.close();
            } else {
              JOptionPane.showMessageDialog(null, "Failed to update account liabilities.");
            }
            updateStmt.close();
          } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
          }

          accountStmt.close();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Please enter a valid expense amount (numbers only).");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error occurred while updating expense: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please enter an expense amount.");
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please select an expense category.");
    }

  }// GEN-LAST:event_ExpenseUpdateButtonActionPerformed

  private void updateIncomeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updateIncomeButtonActionPerformed
    // TODO add your handling code here:
    int selectedRow = incomeTable.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(null, "Please select the data row you want to update.");
      return;
    }

    String account = (String) incomeAccountName.getSelectedItem();
    String source = (String) incomeSourceComboBox.getSelectedItem();
    String amountText = incomeAmount.getText();
    java.util.Date date = incomeDate.getDate();

    if (account.equals("--select--") || source.equals("--select--") || amountText.isEmpty() || date == null) {
      JOptionPane.showMessageDialog(null, "Please complete all data before updating.");
      return;
    }

    try {
      double amount = Double.parseDouble(amountText);
      String formattedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);

      String query = "UPDATE income SET amount = ?, income_date = ?, income_source = ? "
          + "WHERE account_id = (SELECT account_id FROM account WHERE account_type = ? AND user_id = ?) "
          + "AND income_date = ?";

      DatabaseManager.connect();
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setDouble(1, amount);
      pstmt.setString(2, formattedDate);
      pstmt.setString(3, source);
      pstmt.setString(4, account);
      pstmt.setInt(5, UserSession.userId);
      pstmt.setString(6, formattedDate); // tanggal sebelumnya sebagai identifikasi

      int updated = pstmt.executeUpdate();
      if (updated > 0) {
        JOptionPane.showMessageDialog(null, "Income data successfully updated.");
        updateComponents(); // refresh tampilan tabel
      } else {
        JOptionPane.showMessageDialog(null, "Failed to update income data.");
      }

      pstmt.close();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid number format.");
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Error database: " + e.getMessage());
    }
  }// GEN-LAST:event_updateIncomeButtonActionPerformed

  private void AccountUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AccountUpdateButtonActionPerformed
    // TODO add your handling code here:
    String newAccountName = jTextField1.getText().trim();

    if (newAccountName.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Account name is required.");
      return;
    }

    try {
      int selectedRow = accountTable.getSelectedRow();
      if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Choose an account from the table before continuing..");
        return;
      }

      String oldAccountName = accountTable.getValueAt(selectedRow, 0).toString(); // nama lama
      int userId = UserSession.userId;

      DatabaseManager.connect();
      String sql = "UPDATE account SET account_type = ? WHERE user_id = ? AND account_type = ?";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql);
      pstmt.setString(1, newAccountName);
      pstmt.setInt(2, userId);
      pstmt.setString(3, oldAccountName);

      int result = pstmt.executeUpdate();
      if (result > 0) {
        JOptionPane.showMessageDialog(null, "Account name successfully updated.");
        updateComponents(); // refresh table
      } else {
        JOptionPane.showMessageDialog(null, "Failed to update account name.");
      }

      pstmt.close();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Error database: " + e.getMessage());
    }

  }// GEN-LAST:event_AccountUpdateButtonActionPerformed

  private void BudgetUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BudgetUpdateButtonActionPerformed
    // TODO add your handling code here:
    int selectedRow = budgetTable.getSelectedRow();

    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(null, "Please select the data row to update.");
      return;
    }
    
    int budgetId = Integer.parseInt (budgetTable.getValueAt(selectedRow, 2).toString());

    //String oldCategory = budgetTable.getValueAt(selectedRow, 0).toString(); // kategori lama (dari tabel)
    String newCategory = BudgetExpenseCategoryComboBox.getSelectedItem().toString(); // kategori baru
    String newAmountText = BudgetAmount.getText().trim();

    if (newCategory.equals("--select--")) {
      JOptionPane.showMessageDialog(null, "Please complete all data before updating.");
      return;
    }
    
    if (newAmountText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "please enter a budget amount.");
        return;
    }

    try {
      double newAmount = Double.parseDouble(newAmountText);

      DatabaseManager.connect();
      String updateQuery = "UPDATE budget SET expense_category = ?, amount = ? WHERE budget_id = ? AND user_id = ?";
      PreparedStatement updateStmt = DatabaseManager.getConnection().prepareStatement(updateQuery);
      updateStmt.setString(1, newCategory);
      updateStmt.setDouble(2, newAmount);
      updateStmt.setInt(3, budgetId);
      updateStmt.setInt(4, UserSession.userId);
      //pstmt.setString(4, oldCategory);

      int rowsUpdate = updateStmt.executeUpdate();
      if (rowsUpdate > 0) {
        JOptionPane.showMessageDialog(null, "Budget successfully updated.");
        BudgetAmount.setText("");
        BudgetExpenseCategoryComboBox.setSelectedIndex(0);
        updateComponents(); // refresh table
      } else {
        JOptionPane.showMessageDialog(null, "Failed to update budget.");
      }

      updateStmt.close();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Please enter a valid budget amount.");
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Error database: " + e.getMessage());
    }
  }// GEN-LAST:event_BudgetUpdateButtonActionPerformed

  private void nBudgetButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_nBudgetButtonMouseClicked
    // TODO add your handling code here:
    nBudgetButton.setBackground(new java.awt.Color(0, 68, 68));
    jToggleButton3.setSelected(false);
    jToggleButton2.setSelected(false);
    jToggleButton4.setSelected(false);
    jToggleButton5.setSelected(false);
    jTabbedPane1.setSelectedIndex(4);
  }// GEN-LAST:event_nBudgetButtonMouseClicked

  private void nBudgetButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_nBudgetButtonMouseEntered
    // TODO add your handling code here:
    nBudgetButton.setBackground(new java.awt.Color(0, 68, 68));
  }// GEN-LAST:event_nBudgetButtonMouseEntered

  private void nBudgetButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_nBudgetButtonMouseExited
    // TODO add your handling code here:
    nBudgetButton.setBackground(new java.awt.Color(0, 102, 102));
  }// GEN-LAST:event_nBudgetButtonMouseExited

  private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton3ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jToggleButton3ActionPerformed

  private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton5ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jToggleButton5ActionPerformed

  private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_refreshButtonMouseClicked
    // TODO add your handling code here:
  }// GEN-LAST:event_refreshButtonMouseClicked

  private void refreshButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_refreshButtonMouseEntered
    // TODO add your handling code here:
  }// GEN-LAST:event_refreshButtonMouseEntered

  private void refreshButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_refreshButtonMouseExited
    // TODO add your handling code here:
  }// GEN-LAST:event_refreshButtonMouseExited

  private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_refreshButtonActionPerformed
    // TODO add your handling code here:
    updateComponents();
    JOptionPane.showMessageDialog(null, "Everything has been refreshed!");
    refreshButton.setSelected(false);
  }// GEN-LAST:event_refreshButtonActionPerformed

  private void addBudgetExpenseCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addBudgetExpenseCategoryButtonActionPerformed
    // TODO add your handling code here:
    String expenseCategory = BudgetExpenseCategoryTextField.getText().trim();
    addExpenseCategoryFunction(expenseCategory);
  }// GEN-LAST:event_addBudgetExpenseCategoryButtonActionPerformed

  private void addBudgetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addBudgetActionPerformed
    // TODO add your handling code here:
    String expenseCategory = BudgetExpenseCategoryComboBox.getSelectedItem().toString();
    String budgetAmountText = BudgetAmount.getText();

    // Check if the budget category is selected
    if (!expenseCategory.isEmpty() && !expenseCategory.equals("--select--")) {
      // Check if the budget amount is provided
      if (!budgetAmountText.isEmpty()) {
        // Check if the budget amount is a valid number
        try {
          double amount = Double.parseDouble(budgetAmountText);

          DatabaseManager.connect();

          // Proceed with inserting budget record into the budget table
          String insertQuery = "INSERT INTO budget(user_id, expense_category, amount) VALUES (?, ?, ?)";
          PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery);
          pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession
          pstmt.setString(2, expenseCategory); // Get the expense category ID
          pstmt.setDouble(3, amount);

          int rowsInserted = pstmt.executeUpdate();
          if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Budget recorded successfully!");
            BudgetExpenseCategoryComboBox.setSelectedIndex(0);
            BudgetAmount.setText("");
            updateComponents(); // Update other components if needed
          } else {
            JOptionPane.showMessageDialog(null, "Failed to record budget.");
          }

          pstmt.close();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Please enter a valid budget amount (numbers only).");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error occurred while recording budget: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please enter a budget amount.");
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please select a budget category.");
    }
  }// GEN-LAST:event_addBudgetActionPerformed

  private void addTargetAmountButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addTargetAmountButtonActionPerformed
    // TODO add your handling code here:
    String targetAmountText = targetAmountTextField.getText();

    // Check if the target amount is provided
    if (!targetAmountText.isEmpty()) {
      try {
        double amount = Double.parseDouble(targetAmountText);

        // Connect to the database
        DatabaseManager.connect();

        // Check if a target amount already exists for the user
        String checkExistingQuery = "SELECT COUNT(*) AS count FROM target_amount WHERE user_id = ?";
        PreparedStatement checkStmt = DatabaseManager.getConnection().prepareStatement(checkExistingQuery);
        checkStmt.setInt(1, UserSession.userId);
        ResultSet existingResult = checkStmt.executeQuery();
        existingResult.next();
        int count = existingResult.getInt("count");
        checkStmt.close();

        // Prepare the SQL statement based on whether the target amount exists or not
        String sqlQuery;
        if (count > 0) {
          sqlQuery = "UPDATE target_amount SET amount = ? WHERE user_id = ?";
        } else {
          sqlQuery = "INSERT INTO target_amount(user_id, amount) VALUES (?, ?)";
        }

        // Execute the SQL statement
        PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sqlQuery);
        if (count > 0) {
          pstmt.setDouble(1, amount); // Set the target amount
          pstmt.setInt(2, UserSession.userId); // Set user_id
        } else {
          pstmt.setInt(1, UserSession.userId); // Set user_id
          pstmt.setDouble(2, amount); // Set the target amount
        }

        // Execute the statement
        int rowsAffected = pstmt.executeUpdate();

        // Check if the operation was successful
        if (rowsAffected > 0) {
          JOptionPane.showMessageDialog(null, "Target amount recorded successfully!");
          targetAmountTextField.setText(""); // Clear the input field
          updateComponents(); // Update other components if needed
        } else {
          JOptionPane.showMessageDialog(null, "Failed to record target amount.");
        }

        pstmt.close();
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Please enter a valid target amount (numbers only).");
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error occurred while recording target amount: " + ex.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please enter a target amount.");
    }
  }// GEN-LAST:event_addTargetAmountButtonActionPerformed

  private void removeBudgetButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_removeBudgetButtonActionPerformed
    // TODO add your handling code here:
    String budgetCategory = (String) removeBudgetComboBox.getSelectedItem();

    if (budgetCategory != null && !budgetCategory.equals("--select--")) {
      try {
        DatabaseManager.connect();

        String query = "DELETE FROM budget WHERE expense_category = ? AND user_id = ?";
        PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
        pstmt.setString(1, budgetCategory);
        pstmt.setInt(2, UserSession.userId);

        int rowsDeleted = pstmt.executeUpdate();
        if (rowsDeleted > 0) {
          JOptionPane.showMessageDialog(null, "Budget deleted successfully!");
          // Optionally, update other components or UI elements related to budgets
          updateComponents(); // Update other components if needed
        } else {
          JOptionPane.showMessageDialog(null, "Failed to delete budget.");
        }

        pstmt.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please select a budget category to delete.");
    }
  }// GEN-LAST:event_removeBudgetButtonActionPerformed

  private void addExpenseCategoryFunction(String expenseCategory) {

    if (!expenseCategory.isEmpty()) {
      try {
        DatabaseManager.connect();
        String checkQuery = "SELECT COUNT(*) AS count FROM expense_category WHERE user_id = ? AND category_name = ?";
        PreparedStatement checkStmt = DatabaseManager.getConnection().prepareStatement(checkQuery);
        checkStmt.setInt(1, UserSession.userId);
        checkStmt.setString(2, expenseCategory);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
          int count = rs.getInt("count");
          if (count > 0) {
            JOptionPane.showMessageDialog(null, "Expense category already exists.");
          } else {
            String insertQuery = "INSERT INTO expense_category (user_id, category_name) VALUES (?, ?)";
            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery);
            pstmt.setInt(1, UserSession.userId);
            pstmt.setString(2, expenseCategory);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
              JOptionPane.showMessageDialog(null, "Expense category added successfully!");
              expenseCategoryTextField.setText("");
              BudgetExpenseCategoryTextField.setText("");
              updateComponents(); // Assuming this method updates components including the expense
              // categories combo box
            } else {
              JOptionPane.showMessageDialog(null, "Failed to add expense category.");
            }
            pstmt.close();
          }
        }
        checkStmt.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error occurred while adding expense category: " + ex.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please enter an expense category name.");
    }
  }

  private void addExpenseCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addExpenseCategoryActionPerformed
    // TODO add your handling code here:
    String expenseCategory = expenseCategoryTextField.getText().trim();
    addExpenseCategoryFunction(expenseCategory);
  }// GEN-LAST:event_addExpenseCategoryActionPerformed

  private void ExpenseResetButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ExpenseResetButtonActionPerformed
    // TODO add your handling code here:
    expenseDate.setDate(null);
    expenseCategoryComboBox.setSelectedIndex(0);
    expenseAmount.setText("");
    expenseAccountName.setSelectedIndex(0);
    expenseRemark.setText("");
  }// GEN-LAST:event_ExpenseResetButtonActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
    // TODO add your handling code here:
    try {

      // Get monthly income and expenses from the database
      Map<String, Integer> monthlyIncomeMap = IncomeExpenseChart.getMonthlyIncome();
      Map<String, Integer> monthlyExpensesMap = IncomeExpenseChart.getMonthlyExpenses();

      // Convert maps to lists
      // Generate and display the chart
      IncomeExpenseChart.generateChart(monthlyIncomeMap, monthlyExpensesMap);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }// GEN-LAST:event_jButton2ActionPerformed

  private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox3ActionPerformed

  }// GEN-LAST:event_jComboBox3ActionPerformed

  // GEN-LAST:event_ExpenseAddButtonActionPerformed
  private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jTextField9ActionPerformed

  private void addIncomeSourceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addIncomeSourceActionPerformed
    // TODO add your handling code here:
    // Get the income source name from the input field
    String incomeSourceName = incomeSourceTextField.getText().trim();

    // Check if the income source name is not empty
    if (!incomeSourceName.isEmpty()) {
      try {
        DatabaseManager.connect();
        String checkQuery = "SELECT COUNT(*) AS count FROM income_source WHERE user_id = ? AND source_name = ?";
        PreparedStatement checkStmt = DatabaseManager.getConnection().prepareStatement(checkQuery);
        checkStmt.setInt(1, UserSession.userId);
        checkStmt.setString(2, incomeSourceName);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
          int count = rs.getInt("count");
          if (count > 0) {
            JOptionPane.showMessageDialog(null, "Income source already exists.");
          } else {
            String insertQuery = "INSERT INTO income_source (user_id, source_name) VALUES (?, ?)";
            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery);
            pstmt.setInt(1, UserSession.userId);
            pstmt.setString(2, incomeSourceName);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
              JOptionPane.showMessageDialog(null, "Income source added successfully!");
              incomeSourceTextField.setText("");
              updateComponents(); // Assuming this method updates components including the income sources
              // combo box
            } else {
              JOptionPane.showMessageDialog(null, "Failed to add income source.");
            }
            pstmt.close();
          }
        }
        checkStmt.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error occurred while adding income source: " + ex.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please enter an income source name.");
    }
  }// GEN-LAST:event_addIncomeSourceActionPerformed

  private void incomeAmountActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_incomeAmountActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_incomeAmountActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:
    Login LoginFrame = new Login();
    LoginFrame.setVisible(true);
    LoginFrame.pack();
    LoginFrame.setLocationRelativeTo(null);
    this.dispose();

  }// GEN-LAST:event_jButton1ActionPerformed

  private void jToggleButton2MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton2MouseEntered
    // TODO add your handling code here:
    jToggleButton2.setBackground(new java.awt.Color(0, 68, 68));
  }// GEN-LAST:event_jToggleButton2MouseEntered

  private void jToggleButton2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton2MouseClicked
    // TODO add your handling code here:
    jToggleButton2.setBackground(new java.awt.Color(0, 68, 68));
    jToggleButton3.setSelected(false);
    jToggleButton4.setSelected(false);
    jToggleButton5.setSelected(false);
    nBudgetButton.setSelected(false);
    jTabbedPane1.setSelectedIndex(0);
  }// GEN-LAST:event_jToggleButton2MouseClicked

  private void jToggleButton2MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton2MouseExited
    // TODO add your handling code here:
    jToggleButton2.setBackground(new java.awt.Color(0, 102, 102));
  }// GEN-LAST:event_jToggleButton2MouseExited

  private void jToggleButton3MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton3MouseEntered
    // TODO add your handling code here:
    jToggleButton3.setBackground(new java.awt.Color(0, 68, 68));
  }// GEN-LAST:event_jToggleButton3MouseEntered

  private void jToggleButton3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton3MouseClicked
    // TODO add your handling code here:
    System.out.println("UserID: " + s.userId);
    jToggleButton3.setBackground(new java.awt.Color(0, 68, 68));
    jToggleButton2.setSelected(false);
    jToggleButton4.setSelected(false);
    jToggleButton5.setSelected(false);
    nBudgetButton.setSelected(false);
    jTabbedPane1.setSelectedIndex(1);
  }// GEN-LAST:event_jToggleButton3MouseClicked

  private void jToggleButton3MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton3MouseExited
    // TODO add your handling code here:
    jToggleButton3.setBackground(new java.awt.Color(0, 102, 102));
  }// GEN-LAST:event_jToggleButton3MouseExited

  private void jToggleButton4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton4MouseEntered
    // TODO add your handling code here:
    jToggleButton4.setBackground(new java.awt.Color(0, 68, 68));
  }// GEN-LAST:event_jToggleButton4MouseEntered

  private void jToggleButton4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton4MouseClicked
    // TODO add your handling code here:
    jToggleButton4.setBackground(new java.awt.Color(0, 68, 68));
    jToggleButton3.setSelected(false);
    jToggleButton2.setSelected(false);
    jToggleButton5.setSelected(false);
    nBudgetButton.setSelected(false);
    jTabbedPane1.setSelectedIndex(2);
  }// GEN-LAST:event_jToggleButton4MouseClicked

  private void jToggleButton4MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton4MouseExited
    // TODO add your handling code here:
    jToggleButton4.setBackground(new java.awt.Color(0, 102, 102));
  }// GEN-LAST:event_jToggleButton4MouseExited

  private void jToggleButton5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton5MouseClicked
    // TODO add your handling code here:
    jToggleButton5.setBackground(new java.awt.Color(0, 68, 68));
    jToggleButton3.setSelected(false);
    jToggleButton2.setSelected(false);
    jToggleButton4.setSelected(false);
    nBudgetButton.setSelected(false);
    jTabbedPane1.setSelectedIndex(3);
  }// GEN-LAST:event_jToggleButton5MouseClicked

  private void jToggleButton5MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton5MouseEntered
    // TODO add your handling code here:
    jToggleButton5.setBackground(new java.awt.Color(0, 68, 68));
  }// GEN-LAST:event_jToggleButton5MouseEntered

  private void jToggleButton5MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jToggleButton5MouseExited
    // TODO add your handling code here:
    jToggleButton5.setBackground(new java.awt.Color(0, 102, 102));
  }// GEN-LAST:event_jToggleButton5MouseExited

  private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseEntered
    // TODO add your handling code here:
    jButton1.setBackground(new java.awt.Color(0, 68, 68));
  }// GEN-LAST:event_jButton1MouseEntered

  private void jButton1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseExited
    // TODO add your handling code here:
    jButton1.setBackground(new java.awt.Color(0, 102, 102));
  }// GEN-LAST:event_jButton1MouseExited

  private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton2ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jToggleButton2ActionPerformed

  private void expenseAccountNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton2ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jToggleButton2ActionPerformed

  private void deleteAccountActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteAccountActionPerformed
    // TODO add your handling code here:
    String accountName = (String) jComboBox1.getSelectedItem();

    if (accountName != null && !accountName.equals("--select--")) {
      try {
        DatabaseManager.connect();

        String query = "DELETE FROM account WHERE account_type = ? AND user_id = ?";
        PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
        pstmt.setString(1, accountName);
        pstmt.setInt(2, UserSession.userId); // Assuming userId is accessible from UserSession

        int rowsDeleted = pstmt.executeUpdate();
        if (rowsDeleted > 0) {
          JOptionPane.showMessageDialog(null, "Account deleted successfully!");
          jComboBox1.removeItem(accountName);
          // populateTable();
          updateComponents(); // updates all components after deleting account successfully
        } else {
          JOptionPane.showMessageDialog(null, "Delete account unsuccessfull.");
        }

        pstmt.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please select an account to delete.");
    }
  }// GEN-LAST:event_deleteAccountActionPerformed

  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jTextField1ActionPerformed

  private void expenseCategoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {

  }

  private void createAccountActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createAccountActionPerformed
    // TODO add your handling code here:
    String accountName = jTextField1.getText().trim(); // Trim the input to remove leading/trailing spaces

    if (!accountName.isEmpty()) {
      try {
        DatabaseManager.connect();

        String checkQuery = "SELECT * FROM account WHERE account_type = ? and user_id = ?";
        PreparedStatement checkStmt = DatabaseManager.getConnection().prepareStatement(checkQuery);
        checkStmt.setString(1, accountName);
        checkStmt.setInt(2, UserSession.userId);

        ResultSet resultSet = checkStmt.executeQuery();
        if (resultSet.next()) {
          JOptionPane.showMessageDialog(null, "Account already exists.");
        } else {
          String insertQuery = "INSERT INTO account(account_type, balance, user_id, liabilities) VALUES (?, ?, ?, ?)";
          PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery);
          pstmt.setString(1, accountName);
          pstmt.setDouble(2, 0); // Initial balance set to 0
          pstmt.setDouble(4, 0); // Initial liability set to 0
          pstmt.setInt(3, UserSession.userId); 

          int rowsInserted = pstmt.executeUpdate();
          if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Account created successfully!");
            jTextField1.setText(""); // Clear the jTextField1 after successful account creation
            addAccountComboBox();
            // populateTable();
            updateComponents(); // updated all components after creating account successfully
          } else {
            JOptionPane.showMessageDialog(null, "Failed to create account.");
          }

          pstmt.close();
        }

        resultSet.close();
        checkStmt.close();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error occurred while creating account: " + ex.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please enter an account name.");
    }

  }// GEN-LAST:event_createAccountActionPerformed

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jComboBox1ActionPerformed

  private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox2ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jComboBox2ActionPerformed

  private void incomeSourceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_incomeSourceComboBoxActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_incomeSourceComboBoxActionPerformed

  private void incomeAccountNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_incomeAccountNameActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_incomeAccountNameActionPerformed

  private void ExpenseAddButtonActionPerformed(java.awt.event.ActionEvent evt) {
    String expenseCategory = expenseCategoryComboBox.getSelectedItem().toString();
    String expenseAmountText = expenseAmount.getText();
    String expenseRemarkText = expenseRemark.getText();
    if (expenseDate.getDate() == null) {
      JOptionPane.showMessageDialog(null, "Please enter Expense Date.");
      return;
    }
    if (expenseAccountName.getSelectedItem() == "--select--") {
      JOptionPane.showMessageDialog(null, "Please select Account!");
      return;
    }
    java.sql.Date expensedate = new java.sql.Date(expenseDate.getDate().getTime());
    int accountId = 0;

    if (!expenseCategory.isEmpty() && !expenseCategory.equals("--select--")) {
      if (!expenseAmountText.isEmpty()) {
        try {
          double amount = Double.parseDouble(expenseAmountText);

          DatabaseManager.connect();

          String accountQuery = "SELECT account_id, balance, liabilities FROM account WHERE account_type = ? and user_id = ?";
          PreparedStatement accountStmt = DatabaseManager.getConnection().prepareStatement(accountQuery);
          accountStmt.setString(1, expenseAccountName.getSelectedItem().toString());
          accountStmt.setInt(2, UserSession.userId);
          ResultSet accountResult = accountStmt.executeQuery();

          if (accountResult.next()) {
            accountId = accountResult.getInt("account_id");
            double currentBalance = accountResult.getDouble("balance");
            double currentTotalExp = accountResult.getDouble("liabilities");

            if (currentBalance < amount) {
              JOptionPane.showMessageDialog(null,
                  "Insufficient funds. Please choose a different account.");
              return; // Stop further processing
            }

            double newExp = currentTotalExp + amount;
            double newBalance = currentBalance - amount;
            String updateQuery = "UPDATE account SET balance = ?, liabilities = ? WHERE account_id = ?";
            PreparedStatement updateStmt = DatabaseManager.getConnection().prepareStatement(updateQuery);
            updateStmt.setDouble(1, newBalance);
            updateStmt.setDouble(2, newExp);
            updateStmt.setInt(3, accountId);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
              String insertQuery = "INSERT INTO expense(user_id, account_id, expense_date, expense_category, remark, amount) VALUES (?, ?, ?, ?, ?, ?)";
              PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery);
              pstmt.setInt(1, UserSession.userId);
              pstmt.setInt(2, accountId);
              pstmt.setDate(3, expensedate);
              pstmt.setString(4, expenseCategory);
              pstmt.setString(5, expenseRemarkText);
              pstmt.setDouble(6, amount);

              int rowsInserted = pstmt.executeUpdate();
              if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Expense recorded successfully!");
                expenseDate.setDate(null);
                expenseCategoryComboBox.setSelectedIndex(0);
                expenseAmount.setText("");
                expenseRemark.setText("");
                expenseAccountName.setSelectedIndex(0);
                // populate_expense_table();
                // populateTable();
                updateComponents(); // updates all components after recording expense successfully
              } else {
                JOptionPane.showMessageDialog(null, "Failed to record expense.");
              }

              pstmt.close();
            } else {
              JOptionPane.showMessageDialog(null, "Failed to update account liabilities.");
            }
            updateStmt.close();
          } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
          }

          accountStmt.close();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Please enter a valid expense amount (numbers only).");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error occurred while recording expense: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please enter an expense amount.");
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please select an expense category.");
    }
  }

  private void addIncomeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addIncomeButtonActionPerformed
    // TODO add your handling code here:
    // TODO add your handling code here:
    String incomeSource = incomeSourceComboBox.getSelectedItem().toString();
    String incomeAmountText = incomeAmount.getText();
    if (incomeDate.getDate() == null) {
      JOptionPane.showMessageDialog(null, "Please enter Income Date.");
      return;
    }
    if (incomeAccountName.getSelectedItem() == "--select--") {
      JOptionPane.showMessageDialog(null, "Please select Account!");
      return;
    }
    java.sql.Date incomedate = new java.sql.Date(incomeDate.getDate().getTime()); // Assuming incomeDateChooser is a
    // date chooser component
    int accountId = 0; // Initialize accountId

    // Check if income source is selected
    if (!incomeSource.isEmpty() && !incomeSource.equals("--select--")) {
      // Check if income amount is provided
      if (!incomeAmountText.isEmpty()) {
        // Check if income amount is a valid number
        try {
          double amount = Double.parseDouble(incomeAmountText);

          DatabaseManager.connect();

          // Retrieve the accountId based on the account name
          String accountQuery = "SELECT account_id, balance FROM account WHERE account_type = ? and user_id = ?";
          PreparedStatement accountStmt = DatabaseManager.getConnection().prepareStatement(accountQuery);
          accountStmt.setString(1, incomeAccountName.getSelectedItem().toString());
          accountStmt.setInt(2, UserSession.userId);
          ResultSet accountResult = accountStmt.executeQuery();

          // Check if the account exists
          if (accountResult.next()) {
            accountId = accountResult.getInt("account_id");
            double currentBalance = accountResult.getDouble("balance");

            // Update balance with the income amount
            double newBalance = currentBalance + amount;
            String updateQuery = "UPDATE account SET balance = ? WHERE account_id = ?";
            PreparedStatement updateStmt = DatabaseManager.getConnection().prepareStatement(updateQuery);
            updateStmt.setDouble(1, newBalance);
            updateStmt.setInt(2, accountId);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
              // Proceed with inserting income record into the income table
              // Insert income record into the database
              String insertQuery = "INSERT INTO income(user_id, account_id, income_date, income_source, amount) VALUES (?, ?, ?, ?, ?)";
              PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(insertQuery);
              pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession
              pstmt.setInt(2, accountId);
              pstmt.setDate(3, incomedate); // Assuming current date is used
              pstmt.setString(4, incomeSource);
              pstmt.setDouble(5, amount);

              int rowsInserted = pstmt.executeUpdate();
              if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Income recorded successfully!");
                incomeDate.setDate(null);
                incomeSourceComboBox.setSelectedIndex(0);
                incomeAmount.setText("");
                incomeAccountName.setSelectedIndex(0);
                // populate_income_table();
                // populateTable();
                updateComponents(); // updates all components after recording income successfully
              } else {
                JOptionPane.showMessageDialog(null, "Failed to record income.");
              }

              pstmt.close();
            } else {
              JOptionPane.showMessageDialog(null, "Failed to update account balance.");
            }
            updateStmt.close();
          } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
          }

          accountStmt.close();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Please enter a valid income amount (numbers only).");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error occurred while recording income: " + ex.getMessage());
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please enter an income amount.");
      }
    } else {
      JOptionPane.showMessageDialog(null, "Please select an income source.");
    }
  }

  private void resetIncomeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_resetIncomeButtonActionPerformed
    incomeDate.setDate(null);
    incomeSourceComboBox.setSelectedIndex(0);
    incomeAmount.setText("");
    incomeAccountName.setSelectedIndex(0);
  }// GEN-LAST:event_resetIncomeButtonActionPerformed

  // All the updateComponents functions are present below, kindly add the new
  // function in updateComponents if created.
  private void addAccountComboBox() {
    try {
      DatabaseManager.connect();

      String query = "SELECT account_type FROM account WHERE user_id = ?";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      jComboBox1.removeAllItems();
      expenseAccountName.removeAllItems();
      incomeAccountName.removeAllItems();

      jComboBox1.addItem("--select--");
      expenseAccountName.addItem("--select--");
      incomeAccountName.addItem("--select--");

      while (rs.next()) {
        String accountName = rs.getString("account_type");
        jComboBox1.addItem(accountName);
        expenseAccountName.addItem(accountName);
        incomeAccountName.addItem(accountName);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  private void populateIncomeSourcesComboBox() {
    try {
      // Clear existing items from the combo box
      incomeSourceComboBox.removeAllItems();

      // Connect to the database
      DatabaseManager.connect();

      // Prepare the SQL statement to retrieve income sources for the current user
      String selectQuery = "SELECT source_name FROM income_source WHERE user_id = ?";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(selectQuery);

      // Set the user_id parameter
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      // Execute the query to retrieve income sources
      ResultSet resultSet = pstmt.executeQuery();
      incomeSourceComboBox.removeAllItems();
      incomeSourceComboBox.addItem("--select--");

      // Add income sources to the combo box
      while (resultSet.next()) {
        String sourceName = resultSet.getString("source_name");
        incomeSourceComboBox.addItem(sourceName);
      }

      // Close the prepared statement
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Error occurred while fetching income sources: " + ex.getMessage());
    }
  }

  private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
    model.setRowCount(0); // Clear previous data from the table

    try {
      DatabaseManager.connect();

      String query = "SELECT account_type, balance, liabilities FROM account WHERE user_id = ?";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      // Iterate over the ResultSet and add data to the table model
      while (rs.next()) {
        String accountName = rs.getString("account_type");
        double liabilities = rs.getDouble("liabilities");
        double currentBalance = rs.getDouble("balance");

        // Add a row to the table model with both liabilities and currentBalance
        model.addRow(new Object[] { accountName, currentBalance, liabilities });
      }

      rs.close();
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "An error occurred while populating the table: " + ex.getMessage());
    }
  }

  private void populateExpenseCategoryComboBox() {
    try {
      // Clear existing items from the combo box
      expenseCategoryComboBox.removeAllItems();
      BudgetExpenseCategoryComboBox.removeAllItems();

      // Connect to the database
      DatabaseManager.connect();

      // Prepare the SQL statement to retrieve income sources for the current user
      String selectQuery = "SELECT category_name FROM expense_category WHERE user_id = ?";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(selectQuery);

      // Set the user_id parameter
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      // Execute the query to retrieve income sources
      ResultSet resultSet = pstmt.executeQuery();
      expenseCategoryComboBox.removeAllItems();
      expenseCategoryComboBox.addItem("--select--");
      BudgetExpenseCategoryComboBox.removeAllItems();
      BudgetExpenseCategoryComboBox.addItem("--select--");

      // Add income sources to the combo box
      while (resultSet.next()) {
        String categoryName = resultSet.getString("category_name");
        expenseCategoryComboBox.addItem(categoryName);
        BudgetExpenseCategoryComboBox.addItem(categoryName);
      }

      // Close the prepared statement
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Error occurred while fetching expense categories: " + ex.getMessage());
    }
  }

  private void populate_income_table() {
    DefaultTableModel model = (DefaultTableModel) incomeTable.getModel();
    model.setRowCount(0); // Clear previous data from the table

    try {
      DatabaseManager.connect();

      String query = "SELECT i.income_date, i.income_source, i.amount, a.account_type "
          + "FROM income i "
          + "INNER JOIN account a ON i.account_id = a.account_id "
          + "WHERE i.user_id = ? order by i.income_date desc";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      // Iterate over the ResultSet and add data to the table model
      while (rs.next()) {
        Date incomeDate = rs.getDate("income_date");
        String incomeSource = rs.getString("income_source");
        double amount = rs.getDouble("amount");
        String accountName = rs.getString("account_type");

        // Add a row to the table model
        model.addRow(new Object[] { accountName, incomeDate, incomeSource, amount });
      }

      rs.close();
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
          "An error occurred while populating the income table: " + ex.getMessage());
    }
  }

  private void populate_expense_table() {
    DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
    model.setRowCount(0); // Clear previous data from the table

    try {
      String query = "SELECT e.expense_date, e.expense_category, e.amount, e.remark, a.account_type "
          + "FROM expense e "
          + "INNER JOIN account a ON e.account_id = a.account_id "
          + "WHERE e.user_id = ? order by e.expense_date desc";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      // Iterate over the ResultSet and add data to the table model
      while (rs.next()) {
        Date expense_Date = rs.getDate("expense_date");
        String expenseCategory = rs.getString("expense_category");
        double amount = rs.getDouble("amount");
        String accountName = rs.getString("account_type");
        String expenseRemarks = rs.getString("remark");

        // Add a row to the table model
        model.addRow(new Object[] { accountName, expenseCategory, amount, expense_Date, expenseRemarks });
      }

      rs.close();
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
          "An error occurred while populating the income table: " + ex.getMessage());
    }
  }

  private void populate_home_page() {
    try {
      // Prepare the SQL statements to retrieve data for the current user
      String balanceQuery = "SELECT COALESCE(SUM(balance), 0) AS total_balance FROM account WHERE user_id = ?";
      String liabilitiesQuery = "SELECT COALESCE(SUM(liabilities), 0) AS total_liabilities FROM account WHERE user_id = ?";
      String incomeQuery = "SELECT COALESCE(SUM(amount), 0) AS total_income FROM income WHERE user_id = ? AND MONTH(income_date) = MONTH(NOW()) AND YEAR(income_date) = YEAR(NOW())";
      String expenseQuery = "SELECT COALESCE(SUM(amount), 0) AS total_expense FROM expense WHERE user_id = ? AND MONTH(expense_date) = MONTH(NOW()) AND YEAR(expense_date) = YEAR(NOW())";

      // Execute the queries for balance, liabilities, income, and expense
      PreparedStatement balanceStmt = DatabaseManager.getConnection().prepareStatement(balanceQuery);
      balanceStmt.setInt(1, UserSession.userId);
      ResultSet balanceRs = balanceStmt.executeQuery();

      PreparedStatement liabilitiesStmt = DatabaseManager.getConnection().prepareStatement(liabilitiesQuery);
      liabilitiesStmt.setInt(1, UserSession.userId);
      ResultSet liabilitiesRs = liabilitiesStmt.executeQuery();

      PreparedStatement incomeStmt = DatabaseManager.getConnection().prepareStatement(incomeQuery);
      incomeStmt.setInt(1, UserSession.userId);
      ResultSet incomeRs = incomeStmt.executeQuery();

      PreparedStatement expenseStmt = DatabaseManager.getConnection().prepareStatement(expenseQuery);
      expenseStmt.setInt(1, UserSession.userId);
      ResultSet expenseRs = expenseStmt.executeQuery();

      // Get the values from the result sets
      double totalBalance = balanceRs.next() ? balanceRs.getDouble("total_balance") : 0;
      double totalLiabilities = liabilitiesRs.next() ? liabilitiesRs.getDouble("total_liabilities") : 0;
      double totalIncome = incomeRs.next() ? incomeRs.getDouble("total_income") : 0;
      double totalExpense = expenseRs.next() ? expenseRs.getDouble("total_expense") : 0;

      // Populate the home page UI components
      balanceLabel.setText(String.valueOf(totalBalance));
      liabilitiesLabel.setText(String.valueOf(totalLiabilities));
      incomeLabel.setText(String.valueOf(totalIncome));
      expenseLabel.setText(String.valueOf(totalExpense));

      // Close result sets and statements
      balanceRs.close();
      balanceStmt.close();
      liabilitiesRs.close();
      liabilitiesStmt.close();
      incomeRs.close();
      incomeStmt.close();
      expenseRs.close();
      expenseStmt.close();
    } catch (SQLException ex) {
      // Handle any SQL errors
      ex.printStackTrace();
    }
  }

  private void populate_transactions() {
    DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
    model.setRowCount(0); // Clear previous data from the table

    try {
      String query = "SELECT t.transaction_id, t.type, t.amount, t.statement, t.time, a.account_type "
          + "FROM transaction t "
          + "INNER JOIN account a ON t.account_id = a.account_id "
          + "WHERE a.user_id = ? order by t.time desc";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      // Iterate over the ResultSet and add data to the table model
      while (rs.next()) {
        int transactionId = rs.getInt("transaction_id");
        String type = rs.getString("type");
        double amount = rs.getDouble("amount");
        String statement = rs.getString("statement");
        String time = rs.getString("time");
        String accountName = rs.getString("account_type");

        // Add a row to the table model
        model.addRow(new Object[] { transactionId, accountName, type, amount, statement, time });
      }

      rs.close();
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
          "An error occurred while populating the transaction table: " + ex.getMessage());
    }
  }

  private void fillRemoveBudgetComboBox() {
    try {
      DatabaseManager.connect();

      // Query to retrieve distinct expense categories from the budget table for the
      // current user
      String query = "SELECT DISTINCT expense_category FROM budget WHERE user_id = ?";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      // Clear existing items in the removeBudgetComboBox
      removeBudgetComboBox.removeAllItems();
      removeBudgetComboBox.addItem("--select--");

      // Add expense categories to the removeBudgetComboBox
      while (rs.next()) {
        String expenseCategory = rs.getString("expense_category");
        removeBudgetComboBox.addItem(expenseCategory);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
          "Error occurred while filling removeBudgetComboBox: " + ex.getMessage());
    }
  }

  private void populate_budget() {
    DefaultTableModel model = (DefaultTableModel) budgetTable.getModel();
    model.setRowCount(0); // Clear previous data from the table

    try {
      String query = "SELECT expense_category, amount, budget_id FROM budget WHERE user_id = ? order by expense_category";
      PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query);
      pstmt.setInt(1, UserSession.userId); // Assuming userId is accessible from UserSession

      ResultSet rs = pstmt.executeQuery();

      // Iterate over the ResultSet and add data to the table model
      while (rs.next()) {
        String expenseCategory = rs.getString("expense_category");
        double amount = rs.getDouble("amount");
        int budgetId = rs.getInt("budget_id");

        // Add a row to the table model
        model.addRow(new Object[] { expenseCategory, amount, budgetId });
      }

      rs.close();
      pstmt.close();
      if (budgetTable.getColumnCount() > 2) {
          budgetTable.getColumnModel().getColumn(2).setMinWidth(0);
          budgetTable.getColumnModel().getColumn(2).setMaxWidth(0);
          budgetTable.getColumnModel().getColumn(2).setWidth(0);
      }
 
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,
          "An error occurred while populating the budget table: " + ex.getMessage());
    }
  }

  public void updateProgressBar() {
    try {
      // Get total income for this month
      String incomeQuery = "SELECT COALESCE(SUM(amount), 0) AS total_income "
          + "FROM income "
          + "WHERE user_id = ? "
          + "AND MONTH(income_date) = MONTH(CURRENT_DATE()) "
          + "AND YEAR(income_date) = YEAR(CURRENT_DATE())";
      PreparedStatement incomeStmt = DatabaseManager.getConnection().prepareStatement(incomeQuery);
      incomeStmt.setInt(1, UserSession.userId);
      ResultSet incomeResult = incomeStmt.executeQuery();

      double totalIncome = 0;
      if (incomeResult.next()) {
        totalIncome = incomeResult.getDouble("total_income");
      }

      // Get total expense for this month
      String expenseQuery = "SELECT COALESCE(SUM(amount), 0) AS total_expense "
          + "FROM expense "
          + "WHERE user_id = ? "
          + "AND MONTH(expense_date) = MONTH(CURRENT_DATE()) "
          + "AND YEAR(expense_date) = YEAR(CURRENT_DATE())";
      PreparedStatement expenseStmt = DatabaseManager.getConnection().prepareStatement(expenseQuery);
      expenseStmt.setInt(1, UserSession.userId);
      ResultSet expenseResult = expenseStmt.executeQuery();

      double totalExpense = 0;
      if (expenseResult.next()) {
        totalExpense = expenseResult.getDouble("total_expense");
      } // Calculate saved money
      double savedMoney = totalIncome - totalExpense;

      // Get target amount for this month
      String targetAmountQuery = "SELECT amount FROM target_amount "
          + "WHERE user_id = ?";
      PreparedStatement targetAmountStmt = DatabaseManager.getConnection().prepareStatement(targetAmountQuery);
      targetAmountStmt.setInt(1, UserSession.userId);
      ResultSet targetAmountResult = targetAmountStmt.executeQuery();

      double targetAmount = 0;
      if (targetAmountResult.next()) {
        targetAmount = targetAmountResult.getDouble("amount");
      } // Calculate percentage achieved towards the target amount
      double percentage = 0;
      if (targetAmount != 0) {
        percentage = (savedMoney / targetAmount) * 100;
      } // Update progress bar
      int progress = (int) Math.round(percentage);
      jProgressBar1.setValue(progress);
      progressLabel.setText(savedMoney + "/" + targetAmount);

      // Close result sets and statements
      incomeResult.close();
      incomeStmt.close();
      expenseResult.close();
      expenseStmt.close();
      targetAmountResult.close();
      targetAmountStmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace(); // Handle SQL exception
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default
     * look and feel.
     * For details see
     * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;

        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(HomePage.class
          .getName()).log(java.util.logging.Level.SEVERE, null, ex);

    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(HomePage.class
          .getName()).log(java.util.logging.Level.SEVERE, null, ex);

    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(HomePage.class
          .getName()).log(java.util.logging.Level.SEVERE, null, ex);

    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(HomePage.class
          .getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    // </editor-fold>
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new HomePage().setVisible(true);
      }
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AccountBalanceLabel;
    private javax.swing.JButton AccountUpdateButton;
    private javax.swing.JPanel Account_tab;
    private javax.swing.JTextField BudgetAmount;
    private javax.swing.JComboBox<String> BudgetExpenseCategoryComboBox;
    private javax.swing.JTextField BudgetExpenseCategoryTextField;
    private javax.swing.JButton BudgetUpdateButton;
    private javax.swing.JPanel Budget_tab;
    private javax.swing.JScrollPane Budget_tab_scrollpane;
    private javax.swing.JPanel Expense;
    private javax.swing.JButton ExpenseAddButton;
    private javax.swing.JTextField ExpenseBalanceLabel;
    private javax.swing.JButton ExpenseResetButton;
    private javax.swing.JButton ExpenseUpdateButton;
    private javax.swing.JPanel Expense_Tab;
    private javax.swing.JScrollPane Expense_tab_scrollPane;
    private javax.swing.JPanel Home_tab;
    private javax.swing.JScrollPane Home_tab_scrollpane;
    private javax.swing.JTextField IncomeBalanceLabel;
    private javax.swing.JPanel Income_tab;
    private javax.swing.JScrollPane Income_tab_scrollpane;
    private javax.swing.JTable accountTable;
    private javax.swing.JButton addBudget;
    private javax.swing.JButton addBudgetExpenseCategoryButton;
    private javax.swing.JButton addExpenseCategory;
    private javax.swing.JButton addIncomeButton;
    private javax.swing.JButton addIncomeSource;
    private javax.swing.JButton addTargetAmountButton;
    private javax.swing.JTextField balanceLabel;
    private javax.swing.JTable budgetTable;
    private javax.swing.JButton createAccount;
    private javax.swing.JButton deleteAccount;
    private javax.swing.JTextField exchangeRatesLabel;
    private javax.swing.JComboBox<String> expenseAccountName;
    private javax.swing.JTextField expenseAmount;
    private javax.swing.JComboBox<String> expenseCategoryComboBox;
    private javax.swing.JTextField expenseCategoryTextField;
    private com.toedter.calendar.JDateChooser expenseDate;
    private javax.swing.JTextField expenseLabel;
    private javax.swing.JTextField expenseRemark;
    private javax.swing.JTable expenseTable;
    private org.knowm.xchart.style.theme.GGPlot2Theme gGPlot2Theme1;
    private javax.swing.JLabel greetingLabel;
    private javax.swing.JComboBox<String> incomeAccountName;
    private javax.swing.JTextField incomeAmount;
    private com.toedter.calendar.JDateChooser incomeDate;
    private javax.swing.JTextField incomeLabel;
    private javax.swing.JComboBox<String> incomeSourceComboBox;
    private javax.swing.JTextField incomeSourceTextField;
    private javax.swing.JTable incomeTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JTextField liabilitiesLabel;
    private javax.swing.JToggleButton nBudgetButton;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel progressLabel;
    private javax.swing.JToggleButton refreshButton;
    private javax.swing.JButton removeBudgetButton;
    private javax.swing.JComboBox<String> removeBudgetComboBox;
    private javax.swing.JButton resetIncomeButton;
    private javax.swing.JTextField targetAmountTextField;
    private javax.swing.JTable transactionTable;
    private javax.swing.JButton updateIncomeButton;
    // End of variables declaration//GEN-END:variables
}
