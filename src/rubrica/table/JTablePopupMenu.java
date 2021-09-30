package rubrica.table;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Popup menu for JTable row
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class JTablePopupMenu extends JPopupMenu {
	
	private JMenuItem modifyItem, deleteItem;
	
	/**
	 * Constructor of class - Implements menu items
	 */
	public JTablePopupMenu() {
		
		modifyItem = new JMenuItem("Modifica Contatto");
		deleteItem = new JMenuItem("Elimina Conatto");
		
		this.add(modifyItem);
		this.add(deleteItem);		
	}
	
	/* Start getter methods */
	public JMenuItem getModifyItem() {
		return modifyItem;
	}

	public JMenuItem getDeleteItem() {
		return deleteItem;
	}
	/* End getter methods */
}
