package rubrica.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import rubrica.controller.listener.NewContactListener;
import rubrica.controller.listener.RemoveContactListener;
import rubrica.controller.listener.TabbedListener;
import rubrica.controller.listener.UpdateContactListener;

import rubrica.model.DbRubrica;

import rubrica.view.AllContact;
import rubrica.view.NewContact;
import rubrica.view.RemoveContact;
import rubrica.view.UpdateContact;
import rubrica.view.ToolBar;
import rubrica.table.ContactsJTable;

/**
 * Main class that manages all the application components 
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class RubricaGui extends JFrame implements WindowListener {
	
	// costanti per le azioni dell'applicazione
	private String PANEL_ALL = "rubrica";
	private String PANEL_NEW = "inserisci";
	private String PANEL_UP = "aggiorna";
	private String PANEL_DEL = "cancella";
	private String NEW_CONTACT = "nuovo";
	private String NEW_RESET = "reset nuovo";
	private String UP_SEARCH = "up_cerca";
	private String UP_UPDATE = "up_modifica";
	private String UP_RESET = "up_reset";
	private String DEL_SEARCH = "del_cerca";
	private String DEL_REMOVE = "del_cancella";
	private String DEL_RESET = "del_reset";	
	
	// business-logic class
	private DbRubrica rubrica;
	
	// application panels
	private JPanel panelMain;
	private ToolBar toolBar;
	private AllContact allContact;
	private NewContact newContact;
	private UpdateContact updateContact;
	private RemoveContact removeContact;
	
	// application listeners
	private MyFunctionListener myFunctionListener;
	private NewContactListener newContactListener;	
	private UpdateContactListener updateContactListener;
	private RemoveContactListener removeContactListener;
	private TabbedListener tabbedListener;	
	
	// Main panel layout
	private CardLayout cardMain;
	
	/**
	 * Constructor of class to setting up and construction all panels and components
	 * and associate the database connection 
	 * @param conn
	 * @param user
	 */
	public RubricaGui(Connection conn, int user) {
		
		super("Rubrica Telefonica - TIC");
		
		try {		
			this.rubrica = new DbRubrica(conn, user);
			
			initApplicationPanels();			
			initApplicationListeners();			
			setApplicationPanels();			
			manageFunctionButtons();			
			managePhoneBookView();	
			manageNewContact();			
			manageUpdateContact();			
			manageRemoveContact();			
			finalSettings();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to contact database", "Database Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	/**
	 * Initialize the application panels
	 */
	private void initApplicationPanels() {
		this.toolBar = new ToolBar();
		this.allContact = new AllContact(this.rubrica);
		this.newContact = new NewContact();
		this.updateContact = new UpdateContact();
		this.removeContact = new RemoveContact();
	}
	
	/**
	 * Initialize the application listeners
	 */
	private void initApplicationListeners() {
		myFunctionListener = new MyFunctionListener();
		newContactListener = new NewContactListener(rubrica, newContact);
		updateContactListener = new UpdateContactListener(rubrica, updateContact);
		removeContactListener = new RemoveContactListener(rubrica, removeContact);
		tabbedListener = new TabbedListener(rubrica, allContact);
	}
	
	/**
	 * Setting up the application panels
	 */
	private void setApplicationPanels() {
		// Card layout for the main panel
		panelMain = new JPanel();
		cardMain = new CardLayout();
		panelMain.setLayout(cardMain);
		
		panelMain.add(allContact, PANEL_ALL);
		panelMain.add(newContact, PANEL_NEW);
		panelMain.add(updateContact, PANEL_UP);
		panelMain.add(removeContact, PANEL_DEL);
		
		this.add(toolBar, BorderLayout.PAGE_START);
		this.add(panelMain, BorderLayout.CENTER);
	}
	
	/**
	 * Setting up the main application functions
	 */
	private void manageFunctionButtons() {		
		this.toolBar.getBtnElenco().setActionCommand(PANEL_ALL);
		this.toolBar.getBtnElenco().addActionListener(myFunctionListener);
		
		this.toolBar.getBtnNuovo().setActionCommand(PANEL_NEW);
		this.toolBar.getBtnNuovo().addActionListener(myFunctionListener);
		
		this.toolBar.getBtnModifica().setActionCommand(PANEL_UP);
		this.toolBar.getBtnModifica().addActionListener(myFunctionListener);
		
		this.toolBar.getBtnElimina().setActionCommand(PANEL_DEL);
		this.toolBar.getBtnElimina().addActionListener(myFunctionListener);
	}
	
	/**
	 * Manage phone book views
	 */
	private void managePhoneBookView() {
		this.allContact.getTbpContatti().addChangeListener(tabbedListener);
	}
	
	/**
	 * Manage a new contact insert
	 */
	private void manageNewContact() {
		this.newContact.getBtnInserisci().setActionCommand(NEW_CONTACT);
		this.newContact.getBtnInserisci().addActionListener(newContactListener);
		this.newContact.getBtnReset().setActionCommand(NEW_RESET);
		this.newContact.getBtnReset().addActionListener(newContactListener);
		
	}
	
	/**
	 * Manage a contact update
	 */
	private void manageUpdateContact() {
		this.updateContact.getBtnCercaID().setActionCommand(UP_SEARCH);
		this.updateContact.getBtnCercaID().addActionListener(updateContactListener);
		this.updateContact.getBtnAggiorna().setActionCommand(UP_UPDATE);
		this.updateContact.getBtnAggiorna().addActionListener(updateContactListener);
		this.updateContact.getBtnReset().setActionCommand(UP_RESET);
		this.updateContact.getBtnReset().addActionListener(updateContactListener);
	}
	
	/**
	 * Manage a contact delete
	 */
	private void manageRemoveContact() {
		this.removeContact.getBtnCercaID().setActionCommand(DEL_SEARCH);
		this.removeContact.getBtnCercaID().addActionListener(removeContactListener);
		this.removeContact.getBtnElimina().setActionCommand(DEL_REMOVE);
		this.removeContact.getBtnElimina().addActionListener(removeContactListener);
		this.removeContact.getBtnReset().setActionCommand(DEL_RESET);
		this.removeContact.getBtnReset().addActionListener(removeContactListener);
	}
	
	/**
	 * Final setting of application
	 */
	private void finalSettings() {
		toolBar.setSelected(toolBar.getBtnElenco());
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {}
	
	/**
	 * Manage the application closure
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			rubrica.close();
		} catch (SQLException exc) {
			JOptionPane.showMessageDialog(null, "Database disconnection failed", "Connection Error", JOptionPane.ERROR_MESSAGE);
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
	
	/**
	 * Inner class to implements ActionListener for the main application functions
	 */
	class MyFunctionListener implements ActionListener {
		
		/**
		 * Action performed implementation 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {			
			
			// show selected panels
			cardMain.show(panelMain, e.getActionCommand());
			
			if (e.getActionCommand().equals(PANEL_NEW)) {
				setNewContactPanel();
				return;				
			}
			
			if (e.getActionCommand().equals(PANEL_UP)) {
				setUpdateContactPanel();
				return;						
			}
			
			if (e.getActionCommand().equals(PANEL_DEL)) {
				setRemoveContactPanel();
				return;				
			}
			
			if (e.getActionCommand().equals(PANEL_ALL)) {
				setAllContactPanel();
				return;								
			}
		}
		
		/**
		 * Setting up panel to insert a new contact
		 */
		private void setNewContactPanel() {
			newContact.resetFields();
			toolBar.resetBtnBack();
			toolBar.setSelected(toolBar.getBtnNuovo());
		}
		
		/**
		 * Setting up panel to search and update a contact
		 */
		private void setUpdateContactPanel() {
			updateContact.resetFields();
			toolBar.resetBtnBack();	
			toolBar.setSelected(toolBar.getBtnModifica());
		}
		
		/**
		 * Setting up panel to search and remove a contact
		 */
		private void setRemoveContactPanel() {
			removeContact.resetFields();
			toolBar.resetBtnBack();
			toolBar.setSelected(toolBar.getBtnElimina());
		}
		
		/**
		 * Setting up panel to view the phone book
		 */
		private void setAllContactPanel() {
			try {
				toolBar.resetBtnBack();
				toolBar.setSelected(toolBar.getBtnElenco());
	        	ContactsJTable cTable = new ContactsJTable(rubrica);
	        	JTabbedPane jtAux = allContact.getTbpContatti();
	        	jtAux.setComponentAt(0, cTable.getAllContactTable());
	        	jtAux.setSelectedIndex(0);				
			}
			catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Unable to contact database", "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
