package rubrica.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import rubrica.login.LoginGui;
import rubrica.model.DbLogin;

/**
 * Application for managing a phone book. To access you must enter the username and password chosen 
 * during registration.
 * If you are a new user, you can register a new account by entering your data 
 * and the username and password chosen.
 * @author cirod
 *
 */
public class AppMain {
	
	/**
	 * Start of application
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			DbLogin dbLogin = new DbLogin();
			new LoginGui(dbLogin);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to contact Database", "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
