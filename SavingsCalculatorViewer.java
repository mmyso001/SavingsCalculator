package savingscalculatortester;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Meekaeel Mysorewala
 */
public class SavingsCalculatorViewer extends JFrame implements ActionListener {
     public JButton calcButton;
     public JLabel initialSavings;
     public JLabel annualInterest;
     public JLabel yearsAmount;
     public JLabel yearlyBalance;
     public String savings = "Initial Savings:";
     public String interest = "Interest Rate(%):";
     public String years = "Years:";
     public String balance = "Balance:";
     public JFormattedTextField savingsField;
     public JFormattedTextField interestField;
     public JFormattedTextField yearsField;
     public JTextArea balanceField;
     public double computedBal;
     /**
      * Creates a SavingsCalculatorViewer Constructor
      */
     public SavingsCalculatorViewer()
     {
     GridBagConstraints layout = null;
     JScrollPane scrollPane = null;
     setTitle("Savings Calculator"); // sets the frame name
     
     initialSavings = new JLabel(savings); // new JLabel Objects 
     annualInterest = new JLabel(interest);
     yearsAmount = new JLabel(years);
     yearlyBalance = new JLabel(balance);
     
     calcButton = new JButton("Calculate");
     calcButton.addActionListener(this);
     
     balanceField =  new JTextArea(10, 25);
     scrollPane = new JScrollPane(balanceField);
     balanceField.setEditable(false);
     
     savingsField = new JFormattedTextField(NumberFormat.getNumberInstance());
     savingsField.setEditable(true); // allows user to type information
     savingsField.setText("0");
     savingsField.setColumns(15); // sets width to 15
     
     interestField = new JFormattedTextField(NumberFormat.getNumberInstance());
     interestField.setEditable(true);
     interestField.setText("0");
     interestField.setColumns(15);
     
     yearsField = new JFormattedTextField(NumberFormat.getNumberInstance());
     yearsField.setEditable(true);
     yearsField.setText("0");
     yearsField.setColumns(15);
     
     setLayout(new GridBagLayout());
     
     layout = new GridBagConstraints();
     layout.gridx = 0; // sets to top left corner
     layout.gridy = 0;
     layout.insets = new Insets(10,10,10,10);
     add(initialSavings,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 1; // sets to top right corner
     layout.gridy = 0;
     layout.insets = new Insets(10,10,10,10);
     add(savingsField,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 0; // sets to second row, to the left
     layout.gridy = 1;
     layout.insets = new Insets(10,10,10,10);
     add(annualInterest,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 1; // sets second row to the right
     layout.gridy = 1;
     layout.insets = new Insets(10,10,10,10);
     add(interestField,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 0; // sets third row to the left
     layout.gridy = 2;
     layout.insets = new Insets(10,10,10,10);
     add(yearsAmount,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 1; // sets third row to the right
     layout.gridy = 2;
     layout.insets = new Insets(10,10,10,10);
     add(yearsField,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 0; // sets 4th row to the left 
     layout.gridy = 3;
     layout.insets = new Insets(10,10,10,10);
     add(yearlyBalance,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 0; // sets the calculator button all the way to the last row
     layout.gridy = 5;
     layout.insets = new Insets(10,10,10,10);
     add(calcButton,layout);
     
     layout = new GridBagConstraints();
     layout.gridx = 0; // sets a scroll pane for the yearly amounts after interest
     layout.gridy = 4;
     layout.insets = new Insets(1,10,10,10);
     layout.gridwidth = 3;
     add(scrollPane,layout);
     
     }
     /**
      * Sets the action options for the input, which uses the method computeBalance 
      * to solve the yearly balance
      * @param event 
      */
     @Override
     public void actionPerformed(ActionEvent event) {
      double yrSavings = 0.0;
      double intRate = 0.0;   
      double yrsSaved = 0.0; 
      double finalBalance = 0.0;
      
      yrSavings = ((Number) savingsField.getValue()).doubleValue();
      
      intRate = ((Number) interestField.getValue()).doubleValue();       
      
      yrsSaved = ((Number) yearsField.getValue()).doubleValue();
      
      balanceField.setText("");
      
      if ((yrSavings > 0.0) && (intRate > 0.0) && (yrsSaved > 0.0)) // uses a boolean to
      {                                                  // make sure the user enter an input over 0
         finalBalance = computeBalance(yrSavings,intRate);

         int i = 1;
         while (i <= yrsSaved)  // while loop to print the yearly balances
         {
            balanceField.append("Year " + i +
                           " Ending Balance : $" + 
                           finalBalance + "\n");
         
         finalBalance =  computeBalance(finalBalance,intRate);

         i++;
         }
      }
      
      else 
      {
         // Show failure dialog
         JOptionPane.showMessageDialog(this, "Please enter a number greater than Zero!");
      }

      return;
   }
     
     
     

      
     
     
     /**
      * The math to compute yearly interest
      * @param begSavings
      * @param rate
      * @return 
      */
     public double computeBalance(double begSavings, double rate)
     {
         computedBal = begSavings + (begSavings * (rate/100));
         computedBal = Math.round(computedBal * 100);
         computedBal = (computedBal / 100);
         return computedBal; 
     }
     
    
}
