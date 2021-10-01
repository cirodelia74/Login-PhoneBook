package rubrica.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rubrica.model.Contatto;
import rubrica.model.DbLogin;

/**
 * Window for registering a new user by entering personal data, username and password 
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class RegisterGui extends JFrame implements ActionListener {
	
	private static final String RGS_CMD = "register";
	private static final String RST_CMD = "reset";
	
	private static final String IMG_USER = "images/UserIconApp.png";
	private static final String IMG_PASS = "images/PassIconApp.png";
	private static final String IMG_MAIL = "images/MailIconApp.png";
	private static final String IMG_ADDR = "images/AddressIconApp.png";
	private static final String IMG_TEL = "images/TelephoneIconApp.png";
	private static final String LBL_REGISTER = "click here to login";
	private static final String TITLE = "Rubrica - New Account Form";
	
	private JPanel registerPanel, iconPanel, btnPanel, centerPanel, labelPanel, textPanel;
	
	private ImageIcon userIcon, passIcon, addrIcon, mailIcon, telIcon;
	
	private JTextField userTxt, nameTxt, surnameTxt, addrTxt, cityTxt, telTxt, mailTxt;
	
	private JPasswordField pass1Register, pass2Register;
	
	private JLabel userIconL, passIconL, addrIconL, mailIconL, telIconL;
	private JLabel userLabel, pass1Label, pass2Label;	
	private JLabel nameLabel, surnameLabel, addrLabel, cityLabel, telLabel, mailLabel;
	private JLabel registerLabel;
	
	private JButton resetBtn, registerBtn;
	
	private DbLogin dbLogin;
	
	/**
	 * Constructor and database connection setup
	 * @param dbLogin
	 */
	public RegisterGui(DbLogin dbLogin) {
		
		super(TITLE);
		this.dbLogin = dbLogin;
		createAndShowGui();
	}
	
	/**
	 * Setting up and construction of panels and components 
	 */
	private void createAndShowGui() {
		
		createIconPanel();
		createLabelPanel();
		createTextPanel();
		createButtonPanel();
		createRegisterPanel();
		setAndShow();
	}
	
	/**
	 * Setting up of Icon Panel
	 */
	private void createIconPanel() {
		
		userIcon = new ImageIcon(IMG_USER);
		passIcon = new ImageIcon(IMG_PASS);
		addrIcon = new ImageIcon(IMG_ADDR);
		mailIcon = new ImageIcon(IMG_MAIL);
		telIcon = new ImageIcon(IMG_TEL);
		
		userIconL = new JLabel(userIcon, JLabel.CENTER);
		passIconL = new JLabel(passIcon, JLabel.CENTER); 
		addrIconL = new JLabel(addrIcon, JLabel.CENTER);
		mailIconL = new JLabel(mailIcon, JLabel.CENTER);
		telIconL = new JLabel(telIcon, JLabel.CENTER);
		
		iconPanel = new JPanel(new GridLayout(9, 1, 10, 10));
		iconPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		iconPanel.add(userIconL);
		iconPanel.add(new JLabel(""));
		iconPanel.add(new JLabel(""));
		iconPanel.add(passIconL);
		iconPanel.add(new JLabel(""));
		iconPanel.add(addrIconL);
		iconPanel.add(new JLabel(""));
		iconPanel.add(telIconL);
		iconPanel.add(mailIconL);		
	}
	
	/**
	 * Setting up of Label Panel
	 */
	private void createLabelPanel() {
		
		userLabel = new JLabel("Username: * ");
		pass1Label = new JLabel("Password: * ");
		pass2Label = new JLabel("Confirm Password: * ");	
		nameLabel = new JLabel("Name: * ");
		surnameLabel = new JLabel("Surname: * ");
		addrLabel = new JLabel("Address: ");
		cityLabel = new JLabel("City: ");
		telLabel = new JLabel("Phone Number: * ");
		mailLabel = new JLabel("E-mail: ");
		
		labelPanel = new JPanel(new GridLayout(9, 1, 10, 10));
		labelPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		labelPanel.add(nameLabel);
		labelPanel.add(surnameLabel);
		labelPanel.add(userLabel);
		labelPanel.add(pass1Label);
		labelPanel.add(pass2Label);
		labelPanel.add(addrLabel);
		labelPanel.add(cityLabel);
		labelPanel.add(telLabel);
		labelPanel.add(mailLabel);	
	}
	
	/**
	 * Setting up of Text Panel
	 */
	private void createTextPanel() {
		
		userTxt = new JTextField(10);
		nameTxt = new JTextField(10);
		surnameTxt = new JTextField(10);
		addrTxt = new JTextField(10);
		cityTxt = new JTextField(10);
		telTxt = new JTextField(10);
		mailTxt = new JTextField(10);
		pass1Register = new JPasswordField(10);
		pass2Register = new JPasswordField(10);
		
		textPanel = new JPanel(new GridLayout(9, 1, 10, 10));
		textPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		textPanel.add(nameTxt);
		textPanel.add(surnameTxt);
		textPanel.add(userTxt);
		textPanel.add(pass1Register);
		textPanel.add(pass2Register);
		textPanel.add(addrTxt);
		textPanel.add(cityTxt);
		textPanel.add(telTxt);
		textPanel.add(mailTxt);
	}
	
	/**
	 * Setting up of Button Panel
	 */
	private void createButtonPanel() {
		
		registerBtn = new JButton("Register");
		registerBtn.setActionCommand(RGS_CMD);
		registerBtn.addActionListener(this);
		
		resetBtn = new JButton("Reset");
		resetBtn.setActionCommand(RST_CMD);
		resetBtn.addActionListener(this);
		
		registerLabel = new JLabel(LBL_REGISTER, JLabel.CENTER);
		registerLabel.setOpaque(true);
		registerLabel.addMouseListener(new MouseAdapter() {
			
			Color back = registerLabel.getForeground();
		      public void mouseClicked(MouseEvent me) {
		          
		    	  // apro la finestra di login
		    	  new LoginGui(dbLogin);
		    	  dispose();
		        }
		      
		      @Override
		      public void mouseEntered(MouseEvent e) {
		    	  registerLabel.setForeground(Color.BLUE);
		      }
		      
		      @Override
		      public void mouseExited(MouseEvent e) {
		    	  registerLabel.setForeground(back);
		      }
		});
		
		JPanel row1 = new JPanel();		
		row1.add(registerBtn);
		row1.add(new JLabel(""));
		row1.add(resetBtn);
		
		JPanel row2 = new JPanel();		
		row2.add(registerLabel);
		
		btnPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		btnPanel.add(row1);
		btnPanel.add(row2);
	}
	
	/**
	 * Setting up of main Panel
	 */
	private void createRegisterPanel() {
		
		registerPanel = new JPanel(new BorderLayout(5, 5));
		
		centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(labelPanel, BorderLayout.LINE_START);
		centerPanel.add(textPanel,BorderLayout.CENTER);
		
		registerPanel.add(new JLabel(" "), BorderLayout.PAGE_START);
		registerPanel.add(iconPanel, BorderLayout.LINE_START);
		registerPanel.add(centerPanel, BorderLayout.CENTER);
		registerPanel.add(btnPanel, BorderLayout.PAGE_END);		
	}
	
	/**
	 * Final settings and show frame
	 */
	private void setAndShow() {
		
		this.setContentPane(registerPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Reset contents of fields 
	 */
	private void resetFields() {
		
		userTxt.setText("");
		nameTxt.setText("");
		surnameTxt.setText("");
		addrTxt.setText("");
		cityTxt.setText("");
		telTxt.setText("");
		mailTxt.setText("");
		pass1Register.setText("");
		pass2Register.setText("");
		
	}
	
	/**
	 * Check the insertion of mandatory input fields
	 * @return
	 */
	private boolean checkRequiredFields() {
		
		return !userTxt.getText().equals("") 
				&& !nameTxt.getText().equals("")
				&& !surnameTxt.getText().equals("")
				&& !telTxt.getText().equals("")
				&& (pass1Register.getPassword().length > 0)
				&& (pass2Register.getPassword().length > 0);
	}
	
	/**
	 * Check that the passwords entered are identical 
	 * @param pass1
	 * @param pass2
	 * @return
	 */
    private static boolean isSamePassword(char[] pass1, char[] pass2) {

        if (pass1.length != pass2.length) 
        	return false;
        return Arrays.equals(pass1, pass2);
    }
	
	/**
	 * Action performed implementation 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		
		if (cmd.equals(RST_CMD)) {
			resetFields();
			return;
		}
		
		if  (cmd.equals(RGS_CMD)) {
			checkFieldsAndRegister();
			return;			
		}		
	}
	
	/**
	 * check availability of username and the password values
	 */
	private void checkFieldsAndRegister() {
		
		try {
			if (!dbLogin.checkUsername(userTxt.getText())) {
				if (checkRequiredFields()) {
					
					if (isSamePassword(pass1Register.getPassword(), pass2Register.getPassword())) {	
						
						if (dbLogin.checkPassword(pass1Register.getPassword())) {
							registerAccount();
						}
						else {
							JOptionPane.showMessageDialog(null, "Password too simple. It must be at least 7 characters long\nand better if it contains numbers and special characters", "Check Password", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Passwords do not match. Please check", "Check Password", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You must enter the required fields", "Required Fields", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Username not available. Try another one", "Username Error", JOptionPane.ERROR_MESSAGE);
			}			
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Impossible to check username. Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Unable to check password strength . Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Inserting the new account in the database 
	 */
	private void registerAccount() {
		
		String surname = surnameTxt.getText();
		String name = nameTxt.getText();
		String address = addrTxt.getText();
		String city = cityTxt.getText();
		String phone = telTxt.getText();
		String mail = mailTxt.getText();
		String username = userTxt.getText();
		char[] password = pass1Register.getPassword();
		Contatto newContact = new Contatto(surname, name, address, city, phone, mail);
		
		try {
			if (dbLogin.registrazione(newContact, username, password)) {
				JOptionPane.showMessageDialog(null, "New account created", "INSERT OK", JOptionPane.INFORMATION_MESSAGE);
				resetFields();
			}
		}
		catch (NoSuchAlgorithmException ex) {
			JOptionPane.showMessageDialog(null, "Impossible to save password. Please try later", "Connection Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Impossible to register new account", "Connection Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Account not created. Try again", "Password Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
