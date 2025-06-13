package Admin;



import Admin.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import Database.DatabaseManager;

public class AdminDashboard extends javax.swing.JFrame {
    private DefaultTableModel accountsTableModel;
    private JTable accountsTable;
    
    public AdminDashboard() {
        initComponents();
        loadDefaultAccounts();
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Admin Dashboard - Default Accounts Management");
        titleLabel.setFont(new Font("Chivo", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Account Name", "Created At"};
        accountsTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        accountsTable = new JTable(accountsTableModel);
        JScrollPane scrollPane = new JScrollPane(accountsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Form Panel
        JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Create/Update Panel
        JPanel createUpdatePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        createUpdatePanel.setBorder(BorderFactory.createTitledBorder("Account Operations"));

        JTextField accountNameField = new JTextField();
        JButton createButton = new JButton("Create");
        JButton updateButton = new JButton("Update");

        createUpdatePanel.add(new JLabel("Account Name:"));
        createUpdatePanel.add(accountNameField);
        createUpdatePanel.add(createButton);
        createUpdatePanel.add(updateButton);

        // Delete Panel
        JPanel deletePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Account"));

        JComboBox<String> accountComboBox = new JComboBox<>();
        JButton deleteButton = new JButton("Delete");

        deletePanel.add(new JLabel("Select Account:"));
        deletePanel.add(accountComboBox);
        deletePanel.add(deleteButton);

        controlPanel.add(createUpdatePanel);
        controlPanel.add(deletePanel);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Button Actions
        createButton.addActionListener(e -> {
            String accountName = accountNameField.getText().trim();
            if (!accountName.isEmpty()) {
                createDefaultAccount(accountName);
                accountNameField.setText("");
                loadDefaultAccounts();
                updateAccountComboBox(accountComboBox);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = accountsTable.getSelectedRow();
            if (selectedRow >= 0) {
                String newName = accountNameField.getText().trim();
                int accountId = (int) accountsTableModel.getValueAt(selectedRow, 0);
                if (!newName.isEmpty()) {
                    updateDefaultAccount(accountId, newName);
                    accountNameField.setText("");
                    loadDefaultAccounts();
                    updateAccountComboBox(accountComboBox);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            String selectedAccount = (String) accountComboBox.getSelectedItem();
            if (selectedAccount != null) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Delete account '" + selectedAccount + "'?\nThis will not affect existing user accounts.",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteDefaultAccount(selectedAccount);
                    loadDefaultAccounts();
                    updateAccountComboBox(accountComboBox);
                }
            }
        });

        // Init ComboBox
        updateAccountComboBox(accountComboBox);

        this.setContentPane(mainPanel);
        this.setTitle("Finova - Admin Dashboard");
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void loadDefaultAccounts() {
        accountsTableModel.setRowCount(0);
        try (ResultSet rs = DatabaseManager.executeQuery(
                "SELECT account_id, account_name, created_at FROM default_accounts ORDER BY account_name")) {
            while (rs.next()) {
                accountsTableModel.addRow(new Object[]{
                        rs.getInt("account_id"),
                        rs.getString("account_name"),
                        rs.getTimestamp("created_at")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading accounts: " + e.getMessage());
        }
    }

    private void updateAccountComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        try (ResultSet rs = DatabaseManager.executeQuery(
                "SELECT account_name FROM default_accounts ORDER BY account_name")) {
            while (rs.next()) {
                comboBox.addItem(rs.getString("account_name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading accounts: " + e.getMessage());
        }
    }

    private void createDefaultAccount(String accountName) {
        int accountId = 0;
        DatabaseManager.executeUpdate("INSERT INTO default_accounts (account_name) VALUES (?)",
                accountName, accountId
        );
        JOptionPane.showMessageDialog(this, "Account created successfully!");
    }

    private void updateDefaultAccount(int accountId, String newName) {
        DatabaseManager.executeUpdate(
                "UPDATE default_accounts SET account_name = ? WHERE account_id = ?",
                newName, accountId
        );
        JOptionPane.showMessageDialog(this, "Account updated successfully!");
    }

    private void deleteDefaultAccount(String accountName) {
        int accountId = 0;
        DatabaseManager.executeUpdate("DELETE FROM default_accounts WHERE account_name = ?",
                accountName, accountId
        );
        JOptionPane.showMessageDialog(this, "Account deleted successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
