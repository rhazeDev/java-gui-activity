import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

class FoodHub {
  private static int validateInt(String quantity) {
    int num = 0;
    try {
      num = Integer.parseInt(quantity);
    } catch (NumberFormatException e) {
      System.out.println("Not a valid number");
    }
    return num;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Justine B. Agcanas");
    JPanel panel = new JPanel();
    JLabel header1 = new JLabel("FOOD ", SwingConstants.RIGHT);
    JLabel header2 = new JLabel("HUB");

    JLabel product1 = new JLabel("Hotdog Php.20");
    JButton minus1 = new JButton("-");
    JLabel quantity1 = new JLabel("0");
    JButton plus1 = new JButton("+");

    JLabel product2 = new JLabel("Adobo Php.40");
    JButton minus2 = new JButton("-");
    JLabel quantity2 = new JLabel("0");
    JButton plus2 = new JButton("+");

    JLabel product3 = new JLabel("Lechon Php.50");
    JButton minus3 = new JButton("-");
    JLabel quantity3 = new JLabel("0");
    JButton plus3 = new JButton("+");

    JLabel product4 = new JLabel("Sisig Php.45");
    JButton minus4 = new JButton("-");
    JLabel quantity4 = new JLabel("0");
    JButton plus4 = new JButton("+");

    JLabel product5 = new JLabel("Igado Php.35");
    JButton minus5 = new JButton("-");
    JLabel quantity5 = new JLabel("0");
    JButton plus5 = new JButton("+");

    JLabel totalText = new JLabel("Total: 0");

    JButton order = new JButton("ORDER");
    JTextArea receipt = new JTextArea();

    JLabel moneyLabel = new JLabel("Cash: ");
    JTextArea moneyInput = new JTextArea();

    panel.setLayout(null);
    panel.setBounds(0, 0, 500, 680);
    panel.setBackground(Color.black);

    header1.setFont(new Font("Calibri", Font.BOLD, 25));
    header1.setForeground(Color.white);
    header1.setBounds(140, 20, 80, 50);
    header1.setOpaque(false);

    header2.setFont(new Font("Calibri", Font.BOLD, 25));
    header2.setForeground(Color.black);
    header2.setBackground(Color.orange);
    header2.setBounds(220, 20, 80, 50);
    header2.setOpaque(true);

    JLabel[] products = { product1, product2, product3, product4, product5 };
    JButton[] minusBtn = { minus1, minus2, minus3, minus4, minus5 };
    JLabel[] quantities = { quantity1, quantity2, quantity3, quantity4, quantity5 };
    JButton[] plusBtn = { plus1, plus2, plus3, plus4, plus5 };
    int[] prices = { 20, 40, 50, 45, 35 };

    for (int i = 0; i < products.length; i++) {
      products[i].setBounds(50, 100 + (i * 40), 200, 30);
      products[i].setFont(new Font("Calibri", Font.PLAIN, 20));
      products[i].setForeground(Color.white);

      minusBtn[i].setBounds(270, 100 + (i * 40), 30, 30);
      minusBtn[i].setBackground(Color.orange);
      minusBtn[i].setFont(new Font("Calibri", Font.PLAIN, 15));
      minusBtn[i].setBorder(null);

      quantities[i].setBounds(320, 100 + (i * 40), 30, 30);
      quantities[i].setFont(new Font("Calibri", Font.PLAIN, 20));
      quantities[i].setForeground(Color.white);
      quantities[i].setOpaque(false);

      plusBtn[i].setBounds(350, 100 + (i * 40), 30, 30);
      plusBtn[i].setBackground(Color.orange);
      plusBtn[i].setFont(new Font("Calibri", Font.PLAIN, 15));
      plusBtn[i].setBorder(null);
    }

    for (int i = 0; i < plusBtn.length; i++) {
      int index = i;
      plusBtn[i].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int quantity = validateInt(quantities[index].getText());
          quantity++;
          quantities[index].setText(Integer.toString(quantity));
          int total = Integer.parseInt(totalText.getText().split(": ")[1]);
          total += prices[index];
          totalText.setText("Total: " + total);
        }
      });

      minusBtn[i].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int quantity = validateInt(quantities[index].getText());
          if (quantity > 0) {
            quantity--;
            quantities[index].setText(Integer.toString(quantity));
            int total = Integer.parseInt(totalText.getText().split(": ")[1]);
            total -= prices[index];
            totalText.setText("Total: " + total);
          }
        }
      });
    }

    totalText.setBounds(20, 300, 100, 40);
    totalText.setFont(new Font("Calibri", Font.PLAIN, 20));
    totalText.setForeground(Color.white);
    totalText.setOpaque(false);

    moneyLabel.setBounds(20, 345, 100, 40);
    moneyLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
    moneyLabel.setForeground(Color.white);
    moneyLabel.setOpaque(false);

    moneyInput.setBounds(80, 350, 120, 30);
    moneyInput.setFont(new Font("Calibri", Font.BOLD, 25));
    moneyInput.setBackground(Color.white);
    moneyInput.setOpaque(true);

    order.setBounds(250, 340, 130, 40);
    order.setFont(new Font("Calibri", Font.BOLD, 20));
    order.setBackground(Color.orange);
    order.setBorder(null);

    order.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int totalAmount = 0;
        int cash = validateInt(moneyInput.getText());
        StringBuilder resibo = new StringBuilder("\n                FOODHUB RECEIPT\n\n");

        for (int i = 0; i < quantities.length; i++) {
          int quantity = validateInt(quantities[i].getText());
          if (quantity > 0) {
            int total = prices[i] * quantity;
            String productName = products[i].getText().split(" Php")[0];
            resibo.append("     ").append(quantity).append("  ").append(productName)
                .append("\t\t").append(total).append("\n");
            totalAmount += total;
          }
        }

        int change = cash - totalAmount;

        resibo.append("  Total\t\t").append(totalAmount).append("\n  Cash\t\t").append(cash)
            .append("\n  Change\t\t").append(change);

        if (totalAmount > 0 && change >= 0) {
          receipt.setText(resibo.toString());

          int receiptHeight = 0;
          for (int i = 0; i < quantities.length; i++) {
            if (!quantities[i].getText().equals("0")) {
              receiptHeight += 20;
            }
            quantities[i].setText("0");
          }

          receipt.setBounds(60, 400, 275, (160 + receiptHeight));
          frame.setSize(420, (580 + receiptHeight));
          totalText.setText("Total: 0");
          moneyInput.setText("");
        } else if (totalAmount <= 0) {
          receipt.setBounds(60, 400, 275, 60);
          receipt.setText("\n   No Order Made");
          frame.setSize(420, 480);
        } else {
          receipt.setBounds(60, 400, 275, 60);
          receipt.setText("\n   Insufficient Cash");
          frame.setSize(420, 480);
        }
      }
    });

    receipt.setFont(new Font("Calibri", Font.PLAIN, 16));
    receipt.setEditable(false);

    frame.setSize(420, 440);
    frame.setVisible(true);
    frame.setLayout(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(header1);
    frame.add(header2);
    for (int i = 0; i < products.length; i++) {
      frame.add(products[i]);
      frame.add(minusBtn[i]);
      frame.add(quantities[i]);
      frame.add(plusBtn[i]);
    }
    frame.add(totalText);
    frame.add(moneyLabel);
    frame.add(moneyInput);
    frame.add(order);
    frame.add(receipt);
    frame.add(panel);
  }
}