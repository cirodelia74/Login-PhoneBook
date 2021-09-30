package rubrica.controller.listener;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rubrica.model.DbRubrica;
import rubrica.view.AllContact;
import rubrica.table.ContactsJTable;

/**
 * Implementation of the listener associated with the JTabbedPane 
 * @author cirod
 *
 */
public class TabbedListener implements ChangeListener {
	
	private DbRubrica rubrica;
	private AllContact panel;
	
	/**
	 * Constructor of class to associate the connection and the all contact panel
	 * @param rubrica
	 * @param panel
	 */
	public TabbedListener(DbRubrica rubrica, AllContact panel) {
		this.rubrica = rubrica;
		this.panel = panel;
	}
	
	/**
	 * Refresh contact view for tab selected
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
        int indexTab = sourceTabbedPane.getSelectedIndex();
        ContactsJTable cTable = new ContactsJTable(rubrica);
        
        try {
	        if (indexTab == 0) {
	        	sourceTabbedPane.setComponentAt(indexTab, cTable.getAllContactTable());
	        }
	        else {
	        	String charContact = this.panel.getTabIndex()[indexTab - 1];
	        	sourceTabbedPane.setComponentAt(indexTab, cTable.getCharContactTable(charContact.charAt(0)));
	        }
        }
        catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Unable to search contacts. Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
        }		
	}
}
