package rubrica.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel used to insert a new contact into Database
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class NewContact extends JPanel {
	
	private JButton btnInserisci, btnReset;
	private JTextField txtCognome, txtNome, txtIndirizzo, txtCitta, txtTelefono, txtEmail;
	private JLabel lblCognome, lblNome, lblIndirizzo, lblCitta, lblTelefono, lblEmail;
	
	private JPanel panelForm, panelButton;
	private JPanel[] panelTxT;
	
	/**
	 * Constructor of class
	 */
	public NewContact() {
		
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
		this.btnInserisci = new JButton("Inserisci Contatto");
		this.btnReset = new JButton("Reset Campi");
		
		this.lblCognome = new JLabel("Cognome: * ", JLabel.RIGHT);
		this.txtCognome = new JTextField(15);
		
		this.lblNome = new JLabel("Nome: * ", JLabel.RIGHT);
		this.txtNome = new JTextField(15);
		
		this.lblIndirizzo = new JLabel("Indirizzo: ", JLabel.RIGHT);
		this.txtIndirizzo = new JTextField(25);
		
		this.lblCitta = new JLabel("Citta: ", JLabel.RIGHT);
		this.txtCitta = new JTextField(15);
		
		this.lblTelefono = new JLabel("Telefono: * ", JLabel.RIGHT);
		this.txtTelefono = new JTextField(15);
		
		this.lblEmail = new JLabel("Email: ", JLabel.RIGHT);
		this.txtEmail = new JTextField(25);
	}
	
	/**
	 * Setting up all the panels of the main panel
	 */
	private void createInsidePanels() {
		this.panelForm = new JPanel(new GridLayout(4, 1, 5, 5));
		this.panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
		this.panelTxT = new JPanel[3];
		
		for (int i=0; i<3; i++) {
			this.panelTxT[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
		}
		
		this.panelTxT[0].add(lblCognome);
		this.panelTxT[0].add(txtCognome);
		this.panelTxT[0].add(new JLabel("    "));
		this.panelTxT[0].add(lblNome);
		this.panelTxT[0].add(txtNome);
		
		this.panelTxT[1].add(lblIndirizzo);
		this.panelTxT[1].add(txtIndirizzo);
		this.panelTxT[1].add(new JLabel("  "));
		this.panelTxT[1].add(lblCitta);
		this.panelTxT[1].add(txtCitta);
		
		this.panelTxT[2].add(lblTelefono);
		this.panelTxT[2].add(txtTelefono);
		this.panelTxT[2].add(new JLabel("   "));
		this.panelTxT[2].add(lblEmail);
		this.panelTxT[2].add(txtEmail);

		this.panelForm.add(new JLabel(""));
		this.panelForm.add(panelTxT[0]);
		this.panelForm.add(panelTxT[1]);
		this.panelForm.add(panelTxT[2]);

		this.panelForm.setBorder(BorderFactory.createTitledBorder("Inserimento nuovo contatto"));
		
		this.panelButton.add(btnInserisci);
		this.panelButton.add(btnReset);
	}
	
	/**
	 * Final setting of main panel
	 */
	private void setMainPanel() {
		this.setLayout(new BorderLayout(5, 5));		
		
		this.add(new JLabel(""), BorderLayout.PAGE_START);
		this.add(panelForm, BorderLayout.CENTER);
		this.add(panelButton, BorderLayout.PAGE_END);
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
	
	/* Start getter and setter methods */
	public JButton getBtnInserisci() {
		return btnInserisci;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JTextField getTxtCognome() {
		return txtCognome;
	}

	public void setTxtCognome(String txtCognome) {
		this.txtCognome.setText(txtCognome);
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(String txtNome) {
		this.txtNome.setText(txtNome);
	}

	public JTextField getTxtIndirizzo() {
		return txtIndirizzo;
	}

	public void setTxtIndirizzo(String txtIndirizzo) {
		this.txtIndirizzo.setText(txtIndirizzo);
	}

	public JTextField getTxtCitta() {
		return txtCitta;
	}

	public void setTxtCitta(String txtCitta) {
		this.txtCitta.setText(txtCitta);
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono.setText(txtTelefono);
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail.setText(txtEmail);
	}
	/* End getter and setter methods */
}
