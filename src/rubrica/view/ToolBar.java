package rubrica.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Implementation of application tool bar
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class ToolBar extends JPanel {
	
	private JButton btnElenco, btnNuovo, btnModifica, btnElimina;
	private Color btnBackDefault, btnBackSelected;
	
	/**
	 * Constructor of class
	 */
	public ToolBar() {
		
		createToolBar();		
	}
	
	/**
	 * Setting up and construction of panels and components 
	 */
	private void createToolBar() {
		
		initComponents();
		setToolBar();
	}
	
	/**
	 * Setting up JComponent's panel
	 */
	private void initComponents() {
		
		this.btnElenco = new JButton("Elenco");
		this.btnElenco.setFocusable(false);
		
		this.btnNuovo = new JButton("Nuovo");
		this.btnNuovo.setFocusable(false);
		
		this.btnModifica = new JButton("Modifica");
		this.btnModifica.setFocusable(false);
		
		this.btnElimina = new JButton("Elimina");
		this.btnElimina.setFocusable(false);
	}
	
	/**
	 * Final setting of tool bar
	 */
	private void setToolBar() {

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));		
		
		this.add(this.btnElenco);		
		this.add(this.btnNuovo);		
		this.add(this.btnModifica);		
		this.add(this.btnElimina);
		
		this.btnBackDefault = this.btnElenco.getBackground();
		this.btnBackSelected = new Color(100, 255, 255); 
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	}
	
	/**
	 * Reset JButton background color to default
	 */
	public void resetBtnBack() {
		this.btnElenco.setBackground(btnBackDefault);
		this.btnNuovo.setBackground(btnBackDefault);
		this.btnModifica.setBackground(btnBackDefault);
		this.btnElimina.setBackground(btnBackDefault);
	}
	
	/**
	 * Change background color of selected JButton
	 * @param btnSelected
	 */
	public void setSelected(JButton btnSelected) {
		btnSelected.setBackground(btnBackSelected);
	}
	
	/* Start getter methods */
	public JButton getBtnElenco() {
		return btnElenco;
	}

	public JButton getBtnNuovo() {
		return btnNuovo;
	}

	public JButton getBtnModifica() {
		return btnModifica;
	}

	public JButton getBtnElimina() {
		return btnElimina;
	}
	/* End getter methods */
}
