// dikdns/finova-desktop/finova-desktop-7f5bf4bc31750cd4b585c0e7bdd9f03dfe50e29b/src/Chart/IncomeExpenseChart.java
package Chart;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List; // Import List
import java.util.Map;
import java.util.TreeMap;
import Database.DatabaseManager;
import Database.UserSession;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.knowm.xchart.SwingWrapper;

public class IncomeExpenseChart {

    // Array of month names in chronological order
    private static final String[] MONTHS = {
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
        "November", "December"
    };

    // getMonthlyIncome() method remains the same
    public static Map<String, Integer> getMonthlyIncome() throws SQLException {
        Map<String, Integer> monthlyIncome = new TreeMap<>(); // Use TreeMap to sort by month name initially
        // Ensure all months are in the map with 0 income if no records found
        for (String month : MONTHS) {
            monthlyIncome.put(month, 0); // Initialize with 0
        }
        DatabaseManager.connect();
        String query = "SELECT monthname(income_date) AS month, SUM(amount) AS income "
                + "FROM income "
                + "WHERE user_id = ? "
                + "GROUP BY month";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, UserSession.userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String month = rs.getString("month");
                int income = rs.getInt("income");
                if (month != null) { // Ensure month is not null
                    monthlyIncome.put(month, income); // This will overwrite the 0 if income exists
                }
            }
        }
        return monthlyIncome;
    }

    // getMonthlyExpenses() method remains the same
    public static Map<String, Integer> getMonthlyExpenses() throws SQLException {
        Map<String, Integer> monthlyExpenses = new TreeMap<>(); // Use TreeMap to sort by month name initially
        // Ensure all months are in the map with 0 expenses if no records found
        for (String month : MONTHS) {
            monthlyExpenses.put(month, 0); // Initialize with 0
        }
        DatabaseManager.connect();
        String query = "SELECT monthname(expense_date) AS month, SUM(amount) AS expense "
                + "FROM expense "
                + "WHERE user_id = ? "
                + "GROUP BY month";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, UserSession.userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String month = rs.getString("month");
                int expense = rs.getInt("expense");
                if (month != null) { // Ensure month is not null
                    monthlyExpenses.put(month, expense); // This will overwrite the 0 if expense exists
                }
            }
        }
        return monthlyExpenses;
    }

    public static void generateChart(Map<String, Integer> monthlyIncomeData, Map<String, Integer> monthlyExpensesData) {

        Thread chartThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Create XChart
                CategoryChart chart = new CategoryChartBuilder()
                        .width(1000)
                        .height(504)
                        .title("Monthly Income vs. Expenses")
                        .xAxisTitle("Month")
                        .yAxisTitle("Amount")
                        .build();

                // Prepare data in chronological order
                List<String> chronologicalMonths = new ArrayList<>();
                List<Integer> incomeValues = new ArrayList<>();
                List<Integer> expenseValues = new ArrayList<>();

                for (String month : MONTHS) {
                    chronologicalMonths.add(month);
                    incomeValues.add(monthlyIncomeData.getOrDefault(month, 0));
                    expenseValues.add(monthlyExpensesData.getOrDefault(month, 0));
                }

                // Add income and expense series to the chart
                chart.addSeries("Income", chronologicalMonths, incomeValues);
                chart.addSeries("Expenses", chronologicalMonths, expenseValues); // Use the same chronologicalMonths for x-axis consistency

                SwingWrapper<CategoryChart> wrapper = new SwingWrapper<>(chart);
                JFrame frame = wrapper.displayChart();

                frame.setIconImage(new ImageIcon(this.getClass().getResource("/Icon/finova-logo.png")).getImage());
                
                // Override the default close operation of the JFrame
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        // Start the thread
        chartThread.start();
    }
}
