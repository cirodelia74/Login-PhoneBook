package rubrica.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rubrica.controller.RubricaGui;
import rubrica.model.DbLogin;

/**
 * Window for accessing the application for registered users
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener, WindowListener {
	
	private static final String LGN_CMD = "login";
	private static final String CLS_CMD = "close";
	
	private static final String IMG_USER = "images/UserIconApp.png";
	private static final String IMG_PASS = "images/PassIconApp.png";
	private static final String LBL_LOGIN = "click here tu create a new account";
	private static final String TITLE = "Rubrica - Login Form";
	
	private JPanel loginPanel, btnPanel, iconPanel, textPanel;
	
	private ImageIcon userIcon, passIcon;
	
	private JTextField userTxt;
	private JPasswordField passLogin;
	
	private JLabel userLabel, passLabel;

	private JLabel loginLabel;
	
	private JButton loginBtn, close1Btn;
	
	private DbLogin dbLogin;
	
	/**
	 * Constructor and database connection setup
	 */
	public Login(DbLogin dbLogin) {
		
		super(TITLE);
		this.dbLogin = dbLogin;
		createAndShowGui();		
	}
	
	/**
	 * Setting up and construction of panels and components 
	 */
	private void createAndShowGui() {
		
		createIconPanel();
		createTextPanel();
		createButtonPanel();
		createLoginPanel();
		setAndShow();
	}
	
	/**
	 * Setting up of Icon Panel
	 */
	private void createIconPanel() {
		
		userIcon = new ImageIcon(IMG_USER);
		passIcon = new ImageIcon(IMG_PASS);
		
		userLabel = new JLabel(userIcon, JLabel.CENTER);
		passLabel = new JLabel(passIcon, JLabel.CENTER);
		
		iconPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		iconPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		iconPanel.add(new JLabel(""));
		iconPanel.add(userLabel);
		iconPanel.add(passLabel);	
	}
	
	/**
	 * Setting up of Text Panel
	 */
	private void createTextPanel() {
		
		userTxt = new JTextField(10);
		passLogin = new JPasswordField(10);
		
		textPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		textPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		textPanel.add(new JLabel(""));
		textPanel.add(userTxt);
		textPanel.add(passLogin);
	}
	
	/**
	 * Setting up of Button Panel
	 */
	private void createButtonPanel() {
		
		loginBtn = new JButton("Login");
		loginBtn.setActionCommand(LGN_CMD);
		loginBtn.addActionListener(this);
		
		close1Btn = new JButton("Close");
		close1Btn.setActionCommand(CLS_CMD);
		close1Btn.addActionListener(this);
		
		loginLabel = new JLabel(LBL_LOGIN, JLabel.CENTER);
		loginLabel.setOpaque(true);
		loginLabel.addMouseListener(new MouseAdapter() {
			
			Color back = loginLabel.getForeground();
		      public void mouseClicked(MouseEvent me) {
		          
		    	  // apro la finestra di login
		    	  new Register(dbLogin);
		    	  dispose();
		        }
		      
		      @Override
		      public void mouseEntered(MouseEvent e) {
		    	  loginLabel.setForeground(Color.BLUE);
		      }
		      
		      @Override
		      public void mouseExited(MouseEvent e) {
		    	  loginLabel.setForeground(back);
		      }
		});
		
		btnPanel = new JPanel(new GridLayout(2, 1, 10, 10));

		JPanel row1 = new JPanel();		
		row1.add(loginBtn);
		row1.add(new JLabel(""));
		row1.add(close1Btn);
		
		JPanel row2 = new JPanel();		
		row2.add(loginLabel);
		
		btnPanel.add(row1);
		btnPanel.add(row2);
	}
	
	/**
	 * Setting up of main Panel
	 */
	private void createLoginPanel() {
				
		loginPanel = new JPanel(new BorderLayout(5, 5));		
		loginPanel.add(iconPanel, BorderLayout.LINE_START);
		loginPanel.add(textPanel, BorderLayout.CENTER);
		loginPanel.add(btnPanel, BorderLayout.PAGE_END);		
	}
	
	/**
	 * Final settings and show frame
	 */
	private void setAndShow() {
		
		this.setContentPane(loginPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 240);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Check the insertion of User and Password fields
	 * @return
	 */
	private boolean checkRequiredFields() {		
		return !userTxt.getText().equals("") && (passLogin.getPassword().length > 0);
	}
	
	/**
	 * Reset contents of fields 
	 */
	private void resetFields() {
		
		userTxt.setText("");
		passLogin.setText("");		
	}
	
	public static void main(String[] args) {
		try {
			DbLogin loginDbLogin = new DbLogin();
			new Login(loginDbLogin);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Action performed implementation 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		
		if (cmd.equals(CLS_CMD)) {
			closeApp();
		}
		
		if (cmd.equals(LGN_CMD)) {
			tryLogin();	
			return;
		}		
	}
	
	/**
	 * Check Username and Password to allow log-in
	 */
	private void tryLogin() {
		
		if (checkRequiredFields()) {
			try {
				if (dbLogin.checkUsername(userTxt.getText())) {
					if (dbLogin.login(userTxt.getText(), passLogin.getPassword())) {
						JOptionPane.showMessageDialog(null, "You have successfully logged in", "LOGIN OK", JOptionPane.INFORMATION_MESSAGE);
						int userID = dbLogin.getIDLogin(userTxt.getText());
						new RubricaGui(dbLogin.getCnn(), userID);
						resetFields();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong password. Check please", "Password Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong username. Check please", "Username Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Impossible to check log-in. Try later please", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Impossible to check password", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Check that User and Password are entered", "Check Fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Disconnecting from database
	 */
	private void closeApp() {
		try {
			dbLogin.close();
		} catch (SQLException exc) {
			JOptionPane.showMessageDialog(null, "Database disconnection failed", "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			dbLogin.close();
		} catch (SQLException exc) {
			JOptionPane.showMessageDialog(null, "DISCONNESSIONE AL DATABASE FALLITA", "Errore connessione", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
