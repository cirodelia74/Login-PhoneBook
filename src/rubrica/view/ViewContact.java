package rubrica.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel used to show a contact
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class ViewContact extends JPanel {
	
	private JTextField txtCognome, txtNome, txtIndirizzo, txtCitta, txtTelefono, txtEmail;
	private JLabel lblCognome, lblNome, lblIndirizzo, lblCitta, lblTelefono, lblEmail;
	private JPanel panelLbl, panelTxt;
	
	/**
	 * Constructor of class
	 */
	public ViewContact() {
		
		createPanel();		
	}
	
	/**
	 * Setting up and construction of panels and components 
	 */
	private void createPanel() {
		
		initComponents();
		createInsidePanels();
		setMainPanel();
	}
	
	/**
	 * Setting up JComponent's panel
	 */
	private void initComponents() {
		this.lblCognome = new JLabel("Cognome: ", JLabel.RIGHT);
		this.txtCognome = new JTextField();
		this.txtCognome.setEditable(false);
		
		this.lblNome = new JLabel("Nome: ", JLabel.RIGHT);
		this.txtNome = new JTextField();
		this.txtNome.setEditable(false);
		
		this.lblIndirizzo = new JLabel("Indirizzo ", JLabel.RIGHT);
		this.txtIndirizzo = new JTextField();
		this.txtIndirizzo.setEditable(false);
		
		this.lblCitta = new JLabel("Citta: ", JLabel.RIGHT);
		this.txtCitta = new JTextField();
		this.txtCitta.setEditable(false);
		
		this.lblTelefono = new JLabel("Telefono: ", JLabel.RIGHT);
		this.txtTelefono = new JTextField();
		this.txtTelefono.setEditable(false);
		
		this.lblEmail = new JLabel("Email: ", JLabel.RIGHT);
		this.txtEmail = new JTextField();
		this.txtEmail.setEditable(false);
	}
	
	/**
	 * Setting up all the panels of the main panel
	 */
	private void createInsidePanels() {
		
		this.panelLbl = new JPanel(new GridLayout(6, 1, 5, 5));
		this.panelLbl.add(lblCognome);
		this.panelLbl.add(lblNome);
		this.panelLbl.add(lblIndirizzo);
		this.panelLbl.add(lblCitta);
		this.panelLbl.add(lblTelefono);
		this.panelLbl.add(lblEmail);
		
		this.panelTxt = new JPanel(new GridLayout(6, 1, 5, 5));		
		this.panelTxt.add(txtCognome);
		this.panelTxt.add(txtNome);		
		this.panelTxt.add(txtIndirizzo);	
		this.panelTxt.add(txtCitta);		
		this.panelTxt.add(txtTelefono);		
		this.panelTxt.add(txtEmail);
	}
	
	/**
	 * Final setting of main panel
	 */
	private void setMainPanel() {
		this.setLayout(new BorderLayout(5, 5));		
		
		this.add(panelLbl, BorderLayout.LINE_START);
		this.add(panelTxt, BorderLayout.CENTER);

		this.setBorder(BorderFactory.createTitledBorder("Dettagli contatto"));		
	}
	
	/**
	 * Reset contents of fields
	 */
	public void resetFields() {
		
		this.txtCognome.setText("");
		this.txtNome.setText("");
		this.txtIndirizzo.setText("");
		this.txtCitta.setText("");
		this.txtTelefono.setText("");
		this.txtEmail.setText("");
	}
	
	/* Start setter methods */
	public void setTxtCognome(String txtCognome) {
		this.txtCognome.setText(txtCognome);;
	}

	public void setTxtNome(String txtNome) {
		this.txtNome.setText(txtNome);;
	}

	public void setTxtIndirizzo(String txtIndirizzo) {
		this.txtIndirizzo.setText(txtIndirizzo);;
	}

	public void setTxtCitta(String txtCitta) {
		this.txtCitta.setText(txtCitta);
	}

	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono.setText(txtTelefono);;
	}
	
	public void setTxtEmail(String txtEmail) {
		this.txtEmail.setText(txtEmail);;
	}
	/* End setter methods */
}
