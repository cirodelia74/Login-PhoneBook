package rubrica.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import rubrica.model.Contatto;
import rubrica.model.DbRubrica;
import rubrica.view.UpdateContact;

/**
 * Listener implementation for updating a contact in the phone book 
 * @author cirod
 *
 */
public class UpdateContactListener implements ActionListener {
	
	private String UP_SEARCH = "up_cerca";
	private String UP_UPDATE = "up_modifica";
	private String UP_RESET = "up_reset";
	private DbRubrica rubrica;
	private UpdateContact panel;
	
	/**
	 * Constructor of class to associate the connection and the update contact panel 
	 * @param rubrica
	 * @param panel
	 */
	public UpdateContactListener(DbRubrica rubrica, UpdateContact panel) {
		this. rubrica = rubrica;
		this.panel = panel;
	}
	
	/**
	 * Action performed implementation 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(UP_SEARCH)) {			
			searchContact();
			return;			
		}
		
		if (e.getActionCommand().equals(UP_UPDATE)) {
			updateContact();
			return;			
		}
		
		if (e.getActionCommand().equals(UP_RESET)) {
			panel.resetFields();
			return;
		}
	}
	
	/**
	 * Search a contact with id
	 */
	private void searchContact() {
		if (!panel.getTxtCercaID().equals("")) {
			try {
			Contatto contact = rubrica.searchContact(Integer.valueOf(panel.getTxtCercaID()));
			
				fillPanel(contact);				
				makeFieldsEditable();
				
				panel.getBtnAggiorna().setEnabled(true);
				panel.getBtnReset().setEnabled(true);
			} 
			catch (NullPointerException exc) {
				JOptionPane.showMessageDialog(null, "Contact ID not present in the phone book", "Search Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (SQLException exc) {
				JOptionPane.showMessageDialog(null, "Contact ID not present in the phone book", "Search Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		else {
			JOptionPane.showMessageDialog(null, "No ID entered", "ID Error", JOptionPane.ERROR_MESSAGE);
			panel.resetFields();
		}
	}
	
	/**
	 * Update selected contact  
	 */
	private void updateContact() {
		String datiUpdate = setStringaInfo();
		
		if (verificaCampiObbligatori()) {
			int okUpdate = JOptionPane.showConfirmDialog(null, datiUpdate, "Update Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (okUpdate == JOptionPane.YES_OPTION) {
				
				try {
					Contatto contact = new Contatto(Integer.valueOf(panel.getTxtCercaID()), 
													panel.getTxtCognome().getText(), 
													panel.getTxtNome().getText(), 
													panel.getTxtIndirizzo().getText(), 
													panel.getTxtCitta().getText(), 
													panel.getTxtTelefono().getText(), 
													panel.getTxtEmail().getText());
					if (rubrica.updateRecord(contact)) {
						JOptionPane.showMessageDialog(null, "Contact update successful ", "Update ok", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
					JOptionPane.showMessageDialog(null, "Contact update failed", "Update Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (SQLException exc) {
					JOptionPane.showMessageDialog(null, "Unable to update contact. Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You must enter the required fields", "Required Fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Fill JtextFields with a contact info
	 * @param contact
	 */
	private void fillPanel(Contatto contact) {
		panel.setTxtCognome(contact.getCognome());
		panel.setTxtNome(contact.getNome());
		panel.setTxtIndirizzo(contact.getIndirizzo());
		panel.setTxtCitta(contact.getCitta());
		panel.setTxtTelefono(contact.getTelefono());
		panel.setTxtEmail(contact.getEmail());
	}
	
	/**
	 * Enable data modification 
	 */
	private void makeFieldsEditable() {
		panel.getTxtCognome().setEditable(true);
		panel.getTxtNome().setEditable(true);
		panel.getTxtIndirizzo().setEditable(true);
		panel.getTxtCitta().setEditable(true);
		panel.getTxtTelefono().setEditable(true);
		panel.getTxtEmail().setEditable(true);
	}
	
	/**
	 * Check the required fields are filled
	 * @return
	 */
	private boolean verificaCampiObbligatori() {
		return !this.panel.getTxtCognome().getText().equals("") && !this.panel.getTxtNome().getText().equals("") && !this.panel.getTxtTelefono().getText().equals("");
	}
	
	/**
	 * Set string information for update contact authorization
	 * @return
	 */
	private String setStringaInfo() {
		return "Contatto da aggiornare:"
				+ "\nCognome: " +panel.getTxtCognome().getText()
				+ "\nNome: " +panel.getTxtNome().getText()
				+ "\nIndirizzo: " +panel.getTxtIndirizzo().getText()
				+ "\nCitta': " +panel.getTxtCitta().getText()
				+ "\nTelefono: " +panel.getTxtTelefono().getText()
				+ "\nEmail: " +panel.getTxtEmail().getText()
				+"\nVuoi proseguire con l'aggiornamento?";
	}
}
