package rubrica.view;

import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import rubrica.model.DbRubrica;
import rubrica.table.ContactsJTable;

/**
 * Construction of the contact tables divided by last name's initial
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class AllContact extends JPanel {
	
	private ContactsJTable tblModel;
	private JPanel all;
	private JPanel[] panelChar;
	private String[] tabIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z"};
	private JTabbedPane tbpContatti;
	
	/**
	 * Constructor of class to put JTables in JTabbedPane
	 * @param rubrica
	 */
	public AllContact(DbRubrica rubrica) {
		
		this.setLayout(new GridLayout(1, 1));
		
		// Set JTable Model
		this.tblModel = new ContactsJTable(rubrica);
		
		// make JTabbedPane panels
		this.panelChar = new JPanel[tabIndex.length];
		
		makeJTablesConntacts();
	}
	
	/**
	 * Set up contact tables and insert them into the JTabbedPane panels 
	 */
	private void makeJTablesConntacts() {
		try {
			this.all = tblModel.getAllContactTable();
			for (int i=0; i<tabIndex.length; i++) {
				this.panelChar[i] = tblModel.getCharContactTable(tabIndex[i].charAt(0));
			}
	
			// JTabbedPane for the phone book
			this.tbpContatti = new JTabbedPane();
			this.tbpContatti.setTabPlacement(JTabbedPane.RIGHT);
			
			// insert JTable whit contacts in the JTabbedPane tabs
			this.tbpContatti.addTab("*", this.all);
			for (int i=0; i<tabIndex.length; i++) {
				this.tbpContatti.addTab(tabIndex[i], panelChar[i]);
			}
					
			this.add(this.tbpContatti);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to search contacts. Try later please", "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* Start getter and setter methods */
	public JPanel[] getPanelChar() {
		return panelChar;
	}

	public void setTblModel(ContactsJTable tblModel) {
		this.tblModel = tblModel;
	}

	public JPanel getAll() {
		return all;
	}

	public void setAll(JPanel all) {
		this.all = all;
	}

	public JTabbedPane getTbpContatti() {
		return tbpContatti;
	}

	public void setTbpContatti(JTabbedPane tbpContatti) {
		this.tbpContatti = tbpContatti;
	}

	public ContactsJTable getTblModel() {
		return tblModel;
	}

	public String[] getTabIndex() {
		return tabIndex;
	}
	/* End getter and setter methods */
}