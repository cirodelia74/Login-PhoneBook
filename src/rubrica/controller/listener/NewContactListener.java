package rubrica.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import rubrica.model.Contatto;
import rubrica.model.DbRubrica;
import rubrica.view.NewContact;

/**
 * Listener implementation for inserting a new contact in the phone book 
 * @author cirod
 *
 */
public class NewContactListener implements ActionListener {
	
	private String NEW_CONTACT = "nuovo";
	private String NEW_RESET = "reset nuovo";
	private DbRubrica rubrica;
	private NewContact panel;
	
	/**
	 * Constructor of class to associate the connection and the new contact panel 
	 * @param rubrica
	 * @param panel
	 */
	public NewContactListener(DbRubrica rubrica, NewContact panel) {
		this.rubrica = rubrica;
		this.panel = panel;
	}
	
	/**
	 * Action performed implementation 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(NEW_CONTACT)) {			
			addNewContact();
			return;
		}

		if (e.getActionCommand().equals(NEW_RESET)) {
			panel.resetFields();
			return;
		}
	}
	
	/**
	 * Check the required fields and try to add a new contact in the database
	 */
	private void addNewContact() {
		
		if (verificaCampiObbligatori()) {
			String cognome = this.panel.getTxtCognome().getText();
			String nome = this.panel.getTxtNome().getText();
			String indirizzo = this.panel.getTxtIndirizzo().getText();
			String citta = this.panel.getTxtCitta().getText();
			String telefono = this.panel.getTxtTelefono().getText();
			String email = this.panel.getTxtEmail().getText();
			
			Contatto newContact = new Contatto(cognome, nome, indirizzo, citta, telefono, email);
			
			try {
				if (rubrica.insertRecord(newContact)) {
					JOptionPane.showMessageDialog(null, "New contact has been successfully saved.", "INSERT OK", JOptionPane.INFORMATION_MESSAGE);
					this.panel.resetFields();
				}
				else {
					JOptionPane.showMessageDialog(null, "Data entry failed", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
				}
				return;
			} 
			catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Data entry failed. Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Data entry failed. Try later please", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		else {
			JOptionPane.showMessageDialog(null, "You must enter the required fields", "Required Fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Check the required fields are filled
	 * @return
	 */
	private boolean verificaCampiObbligatori() {
		return !this.panel.getTxtCognome().getText().equals("") && !this.panel.getTxtNome().getText().equals("") && !this.panel.getTxtTelefono().getText().equals("");
	}
}
