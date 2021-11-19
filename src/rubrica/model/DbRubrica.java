package rubrica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that contains methods to manage Rubricatic table
 * @author cirod
 *
 */
public class DbRubrica {
	
	private Connection cnn;
	private int user;
	
	/**
	 * Constructor of class to associate the connection with database
	 * for the logged user
	 * @throws SQLException
	 */
	public DbRubrica(Connection cnn, int user_id) throws SQLException {
		
		// get logged user account
		this.user = user_id;
		// get database connection
		this.cnn = cnn;
	}
	
	/** 
	 * Get the connection to database	
	 * @return
	 */
	public Connection getCnn() {
		return cnn;
	}

	/**
	 * Get the list of contacts whose last name begins with the passed character as a parameter 
	 * @param firstLetter
	 * @return arrayList of selected contacts
	 * @throws SQLException, NullPointerException 
	 */
	public ArrayList<Contatto> getListaRecords(char firstLetter) throws SQLException, NullPointerException {
		
		ArrayList<Contatto> listaContatti = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet listaDb = null;
		int id;
		String cognome, nome, indirizzo, citta, telefono, email;
		String query = "SELECT * FROM rubricatic WHERE cognome LIKE ? "
				+ "AND id_user = ? "
				+ "ORDER BY cognome ASC, nome ASC";
		
		stmt = cnn.prepareStatement(query);
		String segnaposto = Character.toString(firstLetter);
			
		stmt.setString(1, segnaposto + "%");
		stmt.setInt(2, user);
		listaDb = stmt.executeQuery();
			
		while (listaDb.next()) {
			id = listaDb.getInt("id");
			cognome = listaDb.getString("cognome");
			nome = listaDb.getString("nome");
			indirizzo = listaDb.getString("indirizzo");
			citta = listaDb.getString("citta");
			telefono = listaDb.getString("telefono");
			email = listaDb.getString("email");
				
			listaContatti.add(new Contatto(id, cognome, nome, indirizzo, citta, telefono, email));
		}
			
		listaDb.close();
		stmt.close();			
		
		return listaContatti;
	}
	
	/**
	 * Get the list of contact for the logged user
	 * @return arrayList of selected contacts
	 * @throws SQLException, NullPointerException 
	 */
	public ArrayList<Contatto> getAllRecords() throws SQLException, NullPointerException {
		
		ArrayList<Contatto> listaContatti = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet listaDb = null;
		int id;
		String cognome, nome, indirizzo, citta, telefono, email;
		String query = "SELECT * FROM rubricatic WHERE id_user = ? ORDER BY cognome ASC, nome ASC";
		
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, user);
		listaDb = stmt.executeQuery();
			
		while (listaDb.next()) {
			id = listaDb.getInt("id");
			cognome = listaDb.getString("cognome");
			nome = listaDb.getString("nome");
			indirizzo = listaDb.getString("indirizzo");
			citta = listaDb.getString("citta");
			telefono = listaDb.getString("telefono");
			email = listaDb.getString("email");

			listaContatti.add(new Contatto(id, cognome, nome, indirizzo, citta, telefono, email));
		}
		
		listaDb.close();
		stmt.close();			
					
		return listaContatti;
	}
	
	/**
	 * Get selected contact id
	 * @param cognome
	 * @param nome
	 * @param telefono
	 * @param user
	 * @return found id as string
	 * @throws SQLException, NullPointerException 
	 */
	public String getId(String cognome, String nome, String telefono) throws SQLException {
		
		String idSelected = "";
		PreparedStatement stmt = null;
		String query = "SELECT id FROM rubricatic WHERE cognome = ? "
				+ "AND nome = ? "
				+ "AND telefono = ? "
				+ "AND id_user = ?";
		
		ResultSet contactSelected = null;
		
		stmt = cnn.prepareStatement(query);
		stmt.setString(1, cognome);
		stmt.setString(2, nome);
		stmt.setString(3, telefono);
		stmt.setInt(4, user);
			
		contactSelected = stmt.executeQuery();
		contactSelected.next();
			
		idSelected = String.valueOf(contactSelected.getInt("id"));
			
		contactSelected.close();
		stmt.close();
		
		return idSelected;
	}
	
	/**
	 * Insert a new contact in the phone book
	 * @param info
	 * @return true if method throws no exceptions
	 * @throws SQLException, NullPointerException 
	 */
	public boolean insertRecord(Contatto info) throws SQLException, NullPointerException {
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO rubricatic (cognome, nome, indirizzo, citta, telefono, email, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String cognome, nome, indirizzo, citta, telefono, email;
		
		cognome = info.getCognome();
		nome = info.getNome();
		indirizzo = info.getIndirizzo();
		citta = info.getCitta();
		telefono = info.getTelefono();
		email = info.getEmail();
			
		stmt = cnn.prepareStatement(query);
			
		stmt.setString(1, cognome);
		stmt.setString(2, nome);
		stmt.setString(3, indirizzo);
		stmt.setString(4, citta);
		stmt.setString(5, telefono);
		stmt.setString(6, email);
		stmt.setInt(7, user);
			
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
	 * Update a contact in the phone book
	 * @param info
	 * @return true if method throws no exceptions
	 * @throws SQLException 
	 */
	public boolean updateRecord(Contatto info) throws SQLException {
		
		PreparedStatement stmt = null;
		String query = "UPDATE rubricatic SET cognome = ?, nome = ?, "
				+ "indirizzo = ?, citta = ?, telefono = ?, email = ? WHERE id = ?";
		
		stmt = cnn.prepareStatement(query);
			
		stmt.setString(1, info.getCognome());
		stmt.setString(2, info.getNome());
		stmt.setString(3, info.getIndirizzo());
		stmt.setString(4, info.getCitta());
		stmt.setString(5, info.getTelefono());
		stmt.setString(6, info.getEmail());
		stmt.setInt(7, info.getId());			
			
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
	 * Search a contact from own id
	 * @param idToSearch
	 * @return an object of Contatto type
	 * @throws SQLException, NullPointerException 
	 */
	public Contatto searchContact(int idToSearch) throws SQLException, NullPointerException {
		
		Contatto contatto = null;
		
		PreparedStatement stmt = null;
		ResultSet contact = null;
		String query = "SELECT * FROM rubricatic WHERE id = ? AND id_user = ?";
		String cognome, nome, indirizzo, citta, telefono, email;
		int id;
		
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, idToSearch);
		stmt.setInt(2, user);
			
		contact = stmt.executeQuery();

		contact.next();
	
		id = contact.getInt("id");
		cognome = contact.getString("cognome");
		nome = contact.getString("nome");
		indirizzo = contact.getString("indirizzo");
		citta = contact.getString("citta");
		telefono = contact.getString("telefono");
		email = contact.getString("email");

		contatto = new Contatto(id, cognome, nome, indirizzo, citta, telefono, email);

		contact.close();
		stmt.close();
			
		return contatto;
	}
	
	/**
	 * Remove a contact in the phone book
	 * @param id
	 * @return true if method throws no exceptions
	 * @throws SQLException 
	 */
	public boolean deleteRecord(int id) throws SQLException {
		
		PreparedStatement stmt = null;
		String query = "DELETE FROM rubricatic WHERE id = ?";
		
		stmt = cnn.prepareStatement(query);
		stmt.setInt(1, id);			
			
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
	 * Close database connection
	 */
	public void close() throws SQLException {
		
		if (this.cnn != null) {
			this.cnn.close();
		}
	}
}
