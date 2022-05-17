import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 * Class for a Java Swing calculator application.
 * Referenced from Test.java provided in class.
 * 
 * @author Alicia Le
 * @date 5/9/2022
 */
public class Calculator extends JFrame {
	private JLabel myLabel;
	private JTextField outputField;
	private ArrayList<JButton> digits;
	private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private JButton decimal, add, sub, mult, div, equal, c;
	private String currentOp;
	private Double firstOp;
	
	/**
	 * The calculator application
	 */
	public Calculator() {
		this.currentOp = "";
		this.firstOp = 0.0;
		
		JPanel displayPanel = new JPanel(new FlowLayout());

		JMenuBar menuBar = new JMenuBar();
		JMenu opMenu = new JMenu("Operator Menu");
		menuBar.add(opMenu);
		
		JMenuItem menuAdd = new JMenuItem("Add");
		JMenuItem menuSub = new JMenuItem("Subtract");
		JMenuItem menuMult = new JMenuItem("Multiply");
		JMenuItem menuDiv = new JMenuItem("Divide");
		JMenuItem menuClear = new JMenuItem("Clear");
		
		opMenu.add(menuAdd);
		opMenu.add(menuSub);
		opMenu.add(menuMult);
		opMenu.add(menuDiv);
		opMenu.add(menuClear);
		
		ActionListener menuAL = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JMenuItem source = (JMenuItem)event.getSource();
				
				if(source == menuAdd) {
					currentOp = "+";
				}
				else if(source == menuSub) {
					currentOp = "-";
				}
				else if(source == menuMult) {
					currentOp = "*";
				}
				else if(source == menuDiv) {
					currentOp = "/";
				}
				else if(source == menuClear) {
					resetValues();
				}
				String currentText = outputField.getText();
				try {
					Double currentTextDouble = new Double(currentText);
					firstOp = currentTextDouble;
					outputField.setText("0");
				} catch(NumberFormatException e) {
					resetValues();
				}
			}
		};
		
		menuAdd.addActionListener(menuAL);
		menuSub.addActionListener(menuAL);
		menuMult.addActionListener(menuAL);
		menuDiv.addActionListener(menuAL);
		menuClear.addActionListener(menuAL);
		
		displayPanel.add(menuBar);


		outputField = new JTextField("0", 20);
		displayPanel.add(outputField);


		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		
		JPanel digitButtonPanel = new JPanel(new GridLayout(4, 3));
		digits = new ArrayList<JButton>();
		
		//Buttons
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		decimal = new JButton(".");
		c = new JButton("C");

		ImageIcon addIcon = new ImageIcon("./resources/add.png");
		Image addImage = addIcon.getImage();
		Image newAdd = addImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		addIcon = new ImageIcon(newAdd);

		ImageIcon subIcon = new ImageIcon("./resources/sub.png");
		Image subImage = subIcon.getImage();
		Image newSub = subImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		subIcon = new ImageIcon(newSub);

		ImageIcon multIcon = new ImageIcon("./resources/mult.png");
		Image multImage = multIcon.getImage();
		Image newMult = multImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		multIcon = new ImageIcon(newMult);

		ImageIcon divIcon = new ImageIcon("./resources/div.png");
		Image divImage = divIcon.getImage();
		Image newDiv = divImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		divIcon = new ImageIcon(newDiv);

		ImageIcon equalIcon = new ImageIcon("./resources/equal.png");
		Image equalImage = equalIcon.getImage();
		Image newEqual = equalImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		equalIcon = new ImageIcon(newEqual);

		add = new JButton("Add", addIcon);
		sub = new JButton("Subtract", subIcon);
		mult = new JButton("Multiply", multIcon);
		div = new JButton("Divide", divIcon);
		equal = new JButton("Equals", equalIcon);

		//add buttons
		digits.add(b0);
		digits.add(b1);
		digits.add(b2);
		digits.add(b3);
		digits.add(b4);
		digits.add(b5);
		digits.add(b6);
		digits.add(b7);
		digits.add(b8);
		digits.add(b9);
		digits.add(decimal);
		digits.add(c);

		digitButtonPanel.add(b0);
		digitButtonPanel.add(b1);
		digitButtonPanel.add(b2);
		digitButtonPanel.add(b3);
		digitButtonPanel.add(b4);
		digitButtonPanel.add(b5);
		digitButtonPanel.add(b6);
		digitButtonPanel.add(b7);
		digitButtonPanel.add(b8);
		digitButtonPanel.add(b9);
		digitButtonPanel.add(decimal);
		digitButtonPanel.add(c);
		buttonPanel.add(digitButtonPanel);
		
		JPanel operatorButtonPanel = new JPanel(new GridLayout(5, 1));
		operatorButtonPanel.add(add);
		operatorButtonPanel.add(sub);
		operatorButtonPanel.add(div);
		operatorButtonPanel.add(mult);
		operatorButtonPanel.add(equal);
		buttonPanel.add(operatorButtonPanel);
		
		displayPanel.add(buttonPanel);
		add(displayPanel);
		
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				resetValues();
			}
		} );
		
		decimal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String currentText = outputField.getText();
				if(currentText.indexOf(".") < 0) {
					outputField.setText(currentText + ".");
				}
			}
		});
		
		OperatorListener opListener = new OperatorListener();
		add.addActionListener(opListener);
		sub.addActionListener(opListener);
		mult.addActionListener(opListener);
		div.addActionListener(opListener);
		
		for(int i = 0; i <= 9; i++) {
			digits.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					String currentText = outputField.getText();
					JButton source = (JButton)event.getSource();
					String newDigit = "";
					if(source == b0) {
						newDigit = "0";
					}
					else if (source == b1) {
						newDigit = "1";
					}
					else if (source == b2) {
						newDigit = "2";
					}
					else if (source == b3) {
						newDigit = "3";
					}
					else if (source == b4) {
						newDigit = "4";
					}
					else if (source == b5) {
						newDigit = "5";
					}
					else if (source == b6) {
						newDigit = "6";
					}
					else if (source == b7) {
						newDigit = "7";
					}
					else if (source == b8) {
						newDigit = "8";
					}
					else if (source == b9) {
						newDigit = "9";
					}
					currentText = currentText + newDigit;
					currentText = currentText.replaceFirst("^0+(?!$)", "");
					outputField.setText(currentText);
				}
			});
		}
		
		equal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Double result = 0.0;
				String currentText = outputField.getText();
				try {
					Double secondOp = new Double(currentText);
					if(currentOp == "+") {
						result = firstOp + secondOp;
					}
					else if(currentOp == "-") {
						result = firstOp - secondOp;
					}
					else if(currentOp == "*") {
						result = firstOp * secondOp;
					}
					else if(currentOp == "/") {
						if(secondOp != 0.0) {
							result = firstOp / secondOp;
						}
						else {
							resetValues();
							outputField.setBackground(Color.PINK);
						}
					}
					outputField.setText(result.toString());
					firstOp = result;
				} catch(NumberFormatException e) {
					resetValues();
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent event) {
			System.exit(0);
			}
		} );
		
		setTitle("Calculator");
		setSize(500, 400);
		setLocation(10, 200);
		
		setVisible(true);
	}

	/**
	 * Method to reset values in output field
	 */
	private void resetValues() {
		currentOp = "";
		firstOp = 0.0;
		outputField.setText("0");
		outputField.setBackground(Color.WHITE);
	}
	
	/**
	 * ActionListener for operations
	 */
	private class OperatorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton)event.getSource();
			
			if(source == add) {
				currentOp = "+";
			}
			else if(source == sub) {
				currentOp = "-";
			}
			else if(source == mult) {
				currentOp = "*";
			}
			else if(source == div) {
				currentOp = "/";
			}
			String currentText = outputField.getText();
			try {
				Double currentTextDouble = new Double(currentText);
				firstOp = currentTextDouble;
				outputField.setText("0");
			} catch(NumberFormatException e) {
				resetValues();
			}
		}
	}
	
	/**
	 * Main method for running Calculator
	 */
	public static void main(String[] args) {
		new Calculator();
	}
}