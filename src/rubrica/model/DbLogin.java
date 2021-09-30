package rubrica.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rubrica.settings.AppSettings;
import rubrica.security.Security;

/**
 * Class that contains methods to manage Login table
 * @author cirod
 *
 */
public class DbLogin {
	
	private Connection cnn;
	
	/**
	 * Constructor of class to create te connection with database
	 * @throws SQLException
	 */
	public DbLogin() throws SQLException {
		
		// connessione al database dei contatti
		this.cnn = DriverManager.getConnection(AppSettings.URL +"/" +AppSettings.DATABASE, 
							AppSettings.USER, 
							AppSettings.PASS);
	}
	
	/**
	 * Check if the entered password is correct 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean login(String username, char[] pass) throws SQLException, IOException {
		
		PreparedStatement stmt = null;
		String query = "SELECT * FROM login WHERE username = ?";
		
		stmt = cnn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		// Check if account has been locked
		if (rs.getString("active").equals("0")) {
			rs.close();
			stmt.close();
			return false;
		}
		
		String password = new String(pass);

		// Check that the password entered matches the hash obtained during registration 
		if (rs.getString("hash_password").equals(Security.getInstance().getHashSHA512(password, rs.getString("salt")))) {
			rs.close();
			stmt.close();
			return true;
		}
		else {
			rs.close();
			stmt.close();
			return false;
		}
	}
	
	/**
	 * Check if chosen username is avaible
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean checkUsername(String username) throws SQLException {
		
		PreparedStatement stmt = null;
		String query = "SELECT * FROM login WHERE username = ?";
		
		stmt = cnn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();

		if (!rs.next()) {
			rs.close();
			stmt.close();
			return false;		
		}
		rs.close();
		stmt.close();
		return true;		
	}
	
	/**
	 * Check password strength 
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean checkPassword(char[] pass) throws IOException {
		
		String password = new String(pass);
		if (Security.getInstance().isCommonPsw(password) || !Security.getInstance().isStrongPass(password)) {
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Register a new user in the database
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean registrazione(Contatto newUser, String username, char[] pass) throws SQLException, IOException {
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO login (username, cognome, nome, indirizzo, citta, telefono, email, hash_password, salt) "
				+ "					VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		stmt = cnn.prepareStatement(query);
		
		String password = new String(pass);
		String salt = Security.getInstance().generateSalt();
		String hash = Security.getInstance().getHashSHA512(password, salt);
		stmt.setString(1, username);
		stmt.setString(2, newUser.getCognome());
		stmt.setString(3, newUser.getNome());
		stmt.setString(4, newUser.getIndirizzo());
		stmt.setString(5, newUser.getCitta());
		stmt.setString(6, newUser.getTelefono());
		stmt.setString(7, newUser.getEmail());
		stmt.setString(8, hash);
		stmt.setString(9, salt);
						
		// Check if the query produced a result 
		if (stmt.executeUpdate() == 1) {
			stmt.close();
			return true;
		}			
		else {
			stmt.close();
			return false;
		}
	}
	
	/**
	 * Select user logged ID
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public int getIDLogin(String username) throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet userLogged = null;
		String query = "SELECT id FROM login WHERE username = ?";

		stmt = cnn.prepareStatement(query);
		stmt.setString(1, username);
		
		userLogged = stmt.executeQuery();
		userLogged.next();
		
		return userLogged.getInt("id");
	}
	
	/** 
	 * Get the connection to database	
	 * @return
	 */
	public Connection getCnn() {
		return cnn;
	}

	/**
	 * Close database connection
	 */
	public void close() throws SQLException {
		
		if (this.cnn != null) {
			this.cnn.close();
		}
	}

}
