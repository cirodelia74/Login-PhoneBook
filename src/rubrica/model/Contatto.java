package rubrica.model;

import java.util.Vector;

/**
 * Class that clones the database table.
 * @author cirod
 *
 */
public class Contatto {
	
	private int id;
	private String cognome;
	private String nome;
	private String indirizzo;
	private String citta;
	private String telefono;
	private String email;
	
	/**
	 * Constructor without parameters
	 */
	public Contatto() {
		
		this.id = 0;
		this.nome = "";
		this.cognome = "";
		this.indirizzo = "";
		this.citta = "";
		this.telefono = "";
		this.email = "";
	}
	
	/**
	 * Constructor with all the parameters
	 */
	public Contatto(int id, String cognome, String nome, String indirizzo, String citta, String telefono,
			String email) {

		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.telefono = telefono;
		this.email = email;
	}
	
	/**
	 * Constructor with all the parameters except id
	 */
	public Contatto(String cognome, String nome, String indirizzo, String citta, String telefono,
				String email) {
	
		this.id = 0;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.telefono = telefono;
		this.email = email;
	}
	
	/**
	 * Constructor with an object of the same class as parameter
	 */
	public Contatto(Contatto contatto) {
	
		this.id = contatto.getId();
		this.nome = contatto.getNome();
		this.cognome = contatto.getCognome();
		this.indirizzo = contatto.getIndirizzo();
		this.citta = contatto.getCitta();
		this.telefono = contatto.getTelefono();
		this.email = contatto.getEmail();
	}
	
	/**
	 * Override oh hashCode method to compare two contacts 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}
	
	/**
	 * Override oh equals method to compare two contacts 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contatto other = (Contatto) obj;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
	/**
	 * Convert contact in to object array
	 * @return
	 */
	public Object[] contattoToArray() {
		
		Object[] contatto = {
				this.cognome,
				this.nome,
				this.indirizzo,
				this.citta,
				this.telefono,
				this.email
		};
		return contatto;
	}
	
	/**
	 * Convert contact in to string vector
	 * @return
	 */
	public Vector<String> contattoToVector() {
		
		Vector<String> contatto = new Vector<>();
		contatto.add(this.cognome);
		contatto.add(this.nome);
		contatto.add(this.indirizzo);
		contatto.add(this.citta);
		contatto.add(this.telefono);
		contatto.add(this.email);
		
		return contatto;
	}
	
	/* Start getter and Setter methods */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/* End getter and Setter methods */
	
	/**
	 * toString method customization 
	 */
	@Override
	public String toString() {
		return "Contatto Id=" + id + "\nNome=" + nome + "\nCognome=" + cognome 
				+ "\nIndirizzo=" + indirizzo + "\nCitta=" + citta 
				+ "\nTelefono=" + telefono + "\nEmail=" + email +"\n\n";
	}
}
