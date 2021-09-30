package rubrica.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rubrica.model.Contatto;
import rubrica.model.DbRubrica;
import rubrica.view.ViewContact;

/**
 * Listener implementation to manage JTable rows
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class ContactsJTable implements ActionListener {
	
	private DbRubrica rubrica;
	private Vector<String> columnNames;
	private JTablePopupMenu jTablePopupMenu;
	private ViewContact viewContact;
	
	// TO DO....implementare azioni per il menu
	
	/**
	 * Constructor of class to associate the database connection,
	 * create JTable view and create popup menu
	 * @param rubrica
	 */
	public ContactsJTable(DbRubrica rubrica) {
		this.rubrica = rubrica;
		
		// JTable column names
		columnNames = new Vector<String>();
		columnNames.add("Cognome");
		columnNames.add("Nome");
		columnNames.add("Indirizzo");
		columnNames.add("Citta");
		columnNames.add("Telefono");
		columnNames.add("Email");
		
		jTablePopupMenu = new JTablePopupMenu();
		
		viewContact = new ViewContact();	
	}
	
	/**
	 * Create the panel with all contacts of logged account 
	 * @return
	 * @throws SQLException
	 */
	public JPanel getAllContactTable() throws SQLException {
		
		JPanel panelAllContactJPanel = new JPanel(new BorderLayout());
		
		// Create data array
		Vector< Vector<String> > data = new Vector<Vector<String>>();
		
		ArrayList<Contatto> allContacts = new ArrayList<>();
		
		allContacts = this.rubrica.getAllRecords();
			for (Contatto contact : allContacts) {
				data.add(contact.contattoToVector());
			}
			
			// Create JTable
			JTable tblContacts = new JTable(data, columnNames) {
				
		        public boolean isCellEditable(int row, int column) {                
		                return false;               
		        }
		        
	            //Implement JTable cell tool tips.           
	            public String getToolTipText(MouseEvent e) {
	                String tip = null;
	                java.awt.Point p = e.getPoint();
	                int rowIndex = rowAtPoint(p);
	
	                try {
	                	String cognome = getValueAt(rowIndex, 0).toString();
	                	String nome = getValueAt(rowIndex, 1).toString();
	                	String telefono = getValueAt(rowIndex, 4).toString();
	                    tip = "Contact ID : " +rubrica.getId(cognome, nome, telefono);
	                } 
	                catch (ArrayIndexOutOfBoundsException ex) {
	                    //catch Array Index Out Of Bounds Exception if JTable is empty
	                }
	                catch (NullPointerException ex) {
	                    //catch null pointer exception if mouse is over an empty line
	                }
	                catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(null, "Unable to search contact. Try later please", "Search Error", JOptionPane.ERROR_MESSAGE);
	                }	
	                return tip;
	               }	            
	        };
        
	        MyMouseListener myMouseListener = new MyMouseListener(tblContacts);
	        tblContacts.addMouseListener(myMouseListener);
			
			// Enable JTable scrolling 
			JScrollPane scrollTable = new JScrollPane(tblContacts);
			tblContacts.setFillsViewportHeight(true);
			tblContacts.setComponentPopupMenu(jTablePopupMenu);
			
			// Fill the panel with JTable
			panelAllContactJPanel.add(tblContacts.getTableHeader(), BorderLayout.PAGE_START);
			panelAllContactJPanel.add(scrollTable, BorderLayout.CENTER);
			
		return panelAllContactJPanel;		
	}
	
	/**
	 * Create the panel with all contacts starting with selected letter of logged account 
	 * @return
	 * @throws SQLException
	 */
	public JPanel getCharContactTable(char c) throws SQLException {
		
		JPanel panelAllContactJPanel = new JPanel(new BorderLayout());
		
		// Create data array
		Vector< Vector<String> > data = new Vector<Vector<String>>();
		
		ArrayList<Contatto> allContact = new ArrayList<>();
		
		allContact = this.rubrica.getListaRecords(c);
		for (Contatto contact : allContact) {
			data.add(contact.contattoToVector());
		}
		
		// Create JTable
		JTable tblContacts = new JTable(data, columnNames){
			
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
            //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);

                try {
                	String cognome = getValueAt(rowIndex, 0).toString();
                	String nome = getValueAt(rowIndex, 1).toString();
                	String telefono = getValueAt(rowIndex, 4).toString();
                    tip = "Contact ID : " +rubrica.getId(cognome, nome, telefono);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    //catch Array Index Out Of Bounds Exception if JTable is empty
                }
                catch (NullPointerException ex) {
                    //catch null pointer exception if mouse is over an empty line
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to search contact. Try later please", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
                return tip;
            }
        };
        
        MyMouseListener myMouseListener = new MyMouseListener(tblContacts);
        tblContacts.addMouseListener(myMouseListener);
        
		// Enable JTable scrolling 
		JScrollPane scrollTable = new JScrollPane(tblContacts);
		tblContacts.setFillsViewportHeight(true);
		tblContacts.setComponentPopupMenu(jTablePopupMenu);
		
		// Fill the panel with JTable		
		panelAllContactJPanel.add(tblContacts.getTableHeader(), BorderLayout.PAGE_START);
		panelAllContactJPanel.add(scrollTable, BorderLayout.CENTER);
		
		return panelAllContactJPanel;		
	}
	
	/**
	 * Action performed implementation
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO IMPLEMENTS POPUP MENU
		
	}
	
	/**
	 * Inner class that implements MouseListener for JTable
	 * @author cirod
	 *
	 */
	class MyMouseListener implements MouseListener {
		
		private JTable table;
	
		public MyMouseListener(JTable table) {
			this.table = table;
		}

		@Override
		public void mouseClicked(MouseEvent e) {}
		
		/**
		 * Show contact selected details
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			 java.awt.Point p = e.getPoint();
             int rowIndex = table.rowAtPoint(p);
             viewContact.resetFields();
             if (e.getButton() == MouseEvent.BUTTON1) {
	             try {
		             String id = rubrica.getId(table.getValueAt(rowIndex, 0).toString(),
		            		 				   table.getValueAt(rowIndex, 1).toString(), 
		            		 				   table.getValueAt(rowIndex, 4).toString());
		             implementView(rowIndex);
		             
		             JOptionPane.showMessageDialog(null, viewContact, "Contact details ID: " +id, JOptionPane.INFORMATION_MESSAGE);
	             }
	             catch (ArrayIndexOutOfBoundsException ex) {
	                 //catch Array Index Out Of Bounds Exception if JTable is empty
	             }
	             catch (SQLException ex) {
	                 JOptionPane.showMessageDialog(null, "Unable to search contact. Try later please", "Search Error", JOptionPane.ERROR_MESSAGE);
	             }
             }             
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
		/**
		 * Fill fields form before show contact details
		 * @param rowIndex
		 */
		private void implementView(int rowIndex) {
			
			viewContact.setTxtCognome(table.getValueAt(rowIndex, 0).toString());
			viewContact.setTxtNome(table.getValueAt(rowIndex, 1).toString());
			viewContact.setTxtIndirizzo(table.getValueAt(rowIndex, 2).toString());
			viewContact.setTxtCitta(table.getValueAt(rowIndex, 3).toString());
			viewContact.setTxtTelefono(table.getValueAt(rowIndex, 4).toString());
			viewContact.setTxtEmail(table.getValueAt(rowIndex, 5).toString());
		}		
	}	
}
