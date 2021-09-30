package rubrica.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import rubrica.model.Contatto;
import rubrica.model.DbRubrica;
import rubrica.view.RemoveContact;

/**
 * Listener implementation for deleting a contact in the phone book 
 * @author cirod
 *
 */
public class RemoveContactListener implements ActionListener {
	
	private String DEL_SEARCH = "del_cerca";
	private String DEL_REMOVE = "del_cancella";
	private String DEL_RESET = "del_reset";
	private DbRubrica rubrica;
	private RemoveContact panel;
	
	/**
	 * Constructor of class to associate the connection and the remove contact panel 
	 * @param rubrica
	 * @param panel
	 */
	public RemoveContactListener(DbRubrica rubrica, RemoveContact panel) {
		this.rubrica = rubrica;
		this.panel = panel;
	}
	
	/**
	 * Action performed implementation 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(DEL_SEARCH)) {			
			searchContact();
			return;			
		}
		
		if (e.getActionCommand().equals(DEL_REMOVE)) {
			deleteContact();
			return;			
		}
		
		if (e.getActionCommand().equals(DEL_RESET)) {
			panel.resetFields();
			return;
		}
	}
	
	/**
	 * Search a contact with id
	 */
	private void searchContact() {		
		try {			
			if (!panel.getTxtCercaID().equals("")) {				
				Contatto contact = rubrica.searchContact(Integer.valueOf(panel.getTxtCercaID()));
				
					fillPanel(contact);						
					panel.getBtnElimina().setEnabled(true);
					panel.getBtnReset().setEnabled(true);						
			}
			else {
				JOptionPane.showMessageDialog(null, "No ID entered", "ID Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (NullPointerException exc) {
			JOptionPane.showMessageDialog(null, "Contact ID not present in the phone book", "Search Error", JOptionPane.ERROR_MESSAGE);
			panel.resetFields();
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Contact ID not present in the phone book", "Search Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Delete selected contact  
	 */
	private void deleteContact() {
		String datiUpdate = setStringaInfo();

		int okUpdate = JOptionPane.showConfirmDialog(null, datiUpdate, "CONFERMA CANCELLAZIONE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (okUpdate == JOptionPane.YES_OPTION) {
			
			try {

				if (rubrica.deleteRecord(Integer.valueOf(panel.getTxtCercaID()))) {
					JOptionPane.showMessageDialog(null, "Contact remove successful", "Delete OK", JOptionPane.INFORMATION_MESSAGE);
					panel.resetFields();
				}
				else {
				JOptionPane.showMessageDialog(null, "Contact remove failed", "Delete Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (SQLException exc) {
				JOptionPane.showMessageDialog(null, "Unable to delete contact. Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
			}			
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
	 * Set string information for update contact authorization
	 * @return
	 */
	private String setStringaInfo() {
		return "Contatto da eliminare:"
				+ "\nCognome: " +panel.getTxtCognome().getText()
				+ "\nNome: " +panel.getTxtNome().getText()
				+ "\nIndirizzo: " +panel.getTxtIndirizzo().getText()
				+ "\nCitta': " +panel.getTxtCitta().getText()
				+ "\nTelefono: " +panel.getTxtTelefono().getText()
				+ "\nEmail: " +panel.getTxtEmail().getText()
				+"\nVuoi proseguire con la cancellazione?";
	}
}
