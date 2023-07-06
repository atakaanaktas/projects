package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WordleBAU extends JFrame  {
	public static Timer time ;
	public static int second;
	public static int minute=0;
	public static JLabel timelabel;
	public static int temp = 0;
	private JTextField[][] letterBoxes = new JTextField[6][5];
	public String word = "";
	public static String chosenWord = wordsD.generateWord().toUpperCase();
	public int tries = 0;
	public int control = 0;
	public int timer2 = 0;
	public int a = 0;
	public int b = 0;
	public HashSet<String> olanHarfler = new HashSet<>();
	public HashSet<String> olmayanHarfler = new HashSet<>();
	public HashSet<String> kullanilmayanHarfler = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X" ,"Y", "Z"));

	
	public static void main(String[] args) {
		
		
		
			
		
				
				
				
		
				
					WordleBAU frame = new WordleBAU();
					frame.setVisible(true);
					System.out.println("Hidden Word is: " + chosenWord);
					//javax.swing.Timer t=new javax.swing.Timer(1000, frame);
					//t.start();
		
	
		
	}	
	
		
	
	
	public WordleBAU()  {
		
	
				 
		ClockExample clock = new ClockExample();
        clock.start();
        

        		
		// Main Frame Customization
		getContentPane().setBackground(new Color(140,155,170));
		setTitle("WORDLE BAU");
		setForeground(Color.LIGHT_GRAY);
		getContentPane().setForeground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 855);
		
		
        // Enter Button Customization
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setBounds(180, 455, 100, 30);
		getContentPane().add(btnEnter);
		

		JLabel lblUyari = new JLabel("");
		lblUyari.setForeground(Color.RED);
		lblUyari.setFont(new Font("Serif", Font.BOLD, 18));
		lblUyari.setHorizontalAlignment(SwingConstants.CENTER);
		lblUyari.setBounds(20, 35, 400, 30);
		getContentPane().add(lblUyari);

		// New Game Button Customization
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(180, 495, 100, 30);
		getContentPane().add(btnNewButton);
		
		// 1st Label Customization
		JLabel lettersExistentTitle = new JLabel("Letters which are in the word");
		lettersExistentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lettersExistentTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lettersExistentTitle.setBounds(50, 550, 355, 30);
		getContentPane().add(lettersExistentTitle);

		// Included letters Customization
		JLabel lettersExistent = new JLabel("");
		lettersExistent.setHorizontalAlignment(SwingConstants.CENTER);
		lettersExistent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lettersExistent.setBounds(20, 590, 400, 30);
		getContentPane().add(lettersExistent);

		
		// 2nd Label Customization
		JLabel lettersNonExistentTitle = new JLabel("Letters which are not in the word");
		lettersNonExistentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lettersNonExistentTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lettersNonExistentTitle.setBounds(40, 630, 375, 30);
		getContentPane().add(lettersNonExistentTitle);

		// Non-Included letters Customization
		JLabel lettersNonExistent = new JLabel("");
		lettersNonExistent.setHorizontalAlignment(SwingConstants.CENTER);
		lettersNonExistent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lettersNonExistent.setBounds(20, 670, 400, 30);
		getContentPane().add(lettersNonExistent);

		// 3rd Label Customization
		JLabel lettersRemainingTitle = new JLabel("Remaining letters");
		lettersRemainingTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lettersRemainingTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lettersRemainingTitle.setBounds(30, 710, 400, 30);
		getContentPane().add(lettersRemainingTitle);
		
        // Remaining Letters Customization
		JLabel lettersRemaining = new JLabel("");
		lettersRemaining.setVerticalAlignment(SwingConstants.BOTTOM);
		lettersRemaining.setHorizontalAlignment(SwingConstants.CENTER);
		lettersRemaining.setFont(new Font("Tahoma", Font.BOLD, 15));
		lettersRemaining.setBounds(0, 750, 460, 30);
		getContentPane().add(lettersRemaining);

		// Creating the textField
		int x = 0;
		int y = 0;
		for (int i = 0; i < 5; i++) {
			y = 0;
			for (int j = 0; j < 6; j++) {

				letterBoxes[j][i] = new JTextField(1);
				letterBoxes[j][i].setBounds(90 + x, 90 + y, 40, 40); // Creates new rows of letterboxes which are 90 units apart in x and y axis
				getContentPane().add(letterBoxes[j][i]);
				letterBoxes[j][i].setColumns(10);
				letterBoxes[j][i].setEnabled(false);
				letterBoxes[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				letterBoxes[j][i].setFont(new Font("Tahoma", Font.BOLD, 15));
				letterBoxes[j][i].setBackground(Color.WHITE);
				letterBoxes[j][i].setForeground(Color.BLACK);
				letterBoxes[0][i].setEnabled(true);
				// MAKING USER INPUTS UPPERCASE
				letterBoxes[j][i].addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 6; j++) {
								letterBoxes[j][i].setText(letterBoxes[j][i].getText().toUpperCase());
								if (letterBoxes[j][i].getText().length() >= 1)   // Makes sure that user cannot input more than 1 letter in the same box
								{
									letterBoxes[j][i].setText(letterBoxes[j][i].getText().substring(0,1)); 
								}
							}
						}
					}
				});
				y += 60; // Creates new columns of letterboxes which are 60 unit apart in the y-axis 
			}
			x += 60; // Creates new columns of letterboxes which are 60 unit apart in the x-axis 
		}

		// After clicked to enter button 
		btnEnter.addActionListener(new ActionListener() {     
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					word += letterBoxes[tries][i].getText();
				}
				// Checks if the word is in the list
				for (String k : wordsD.wordList) {
					if (k.equals(word.toLowerCase()))
						control = 1;
				}
				if (control == 1) {

					// Checks if the letter is in the world and its position

					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							letterBoxes[tries][j].setEnabled(false);
							if (tries < 5)
								letterBoxes[tries + 1][j].setEnabled(true);
							if (word.charAt(i) == chosenWord.charAt(j)) {
								timer2++;
								letterBoxes[tries][i].setBackground(new Color(204,102,0));
								olanHarfler.add(Character.toString(word.charAt(i)));

							}
							// If letter's position is correct then makes it green
							if (letterBoxes[tries][i].getText().charAt(0) == chosenWord.charAt(i)) {
								letterBoxes[tries][i].setBackground(Color.BLUE);
							}

						}

						if (timer2 == 0) {
							olmayanHarfler.add(Character.toString(word.charAt(i)));
						}
						timer2 = 0;
					}
					lettersNonExistent.setText("" + olmayanHarfler);
					lettersExistent.setText("" + olanHarfler);
					kullanilmayanHarfler.removeAll(olmayanHarfler);
					kullanilmayanHarfler.removeAll(olanHarfler);
					lettersRemaining.setText("" + kullanilmayanHarfler);
					tries++;
					control = 0;
					lblUyari.setText("");
				} else {
					lblUyari.setText("WORD IS NOT IN DICTIONARY");
					for (int j = 0; j < 5; j++) {
						letterBoxes[tries][j].setText("");
					}
				}

				if (tries == 6) {
					getContentPane().setBackground(Color.RED);
					lblUyari.setForeground(Color.BLACK);
					lblUyari.setText("YOU LOST!");

				}

				if (word.equals(chosenWord)) {
					lblUyari.setForeground(Color.BLACK);
					lblUyari.setText("YOU WIN");
					
              ImageIcon win= new ImageIcon("win.png");
			       
			     JOptionPane.showMessageDialog(null,"you win", "Display Image",JOptionPane.INFORMATION_MESSAGE,win);
					
					getContentPane().setBackground(new Color(123,50,250));
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 5; j++) {
							letterBoxes[i][j].setEnabled(false);
						}
					}
				}

				word = "";

			}
		});

		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosenWord = wordsD.generateWord().toUpperCase();
				lblUyari.setText("");
				word = "";
				tries = 0;
				olanHarfler.clear();
				olmayanHarfler.clear();
				kullanilmayanHarfler.addAll(Arrays.asList("A", "B", "C","D", "E", "F", "G", "H", "I", "J",
						"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W" , "X", "Y", "Z"));
				lettersExistent.setText("");
				lettersNonExistent.setText("");
				lettersRemaining.setText("");
				getContentPane().setBackground(Color.PINK);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 6; j++) {

						letterBoxes[j][i].setEnabled(false);
						letterBoxes[j][i].setBackground(Color.GRAY);
						letterBoxes[j][i].setText("");
						letterBoxes[0][i].setEnabled(true);

					}
				}

			}
		});

	}




	

	





}


