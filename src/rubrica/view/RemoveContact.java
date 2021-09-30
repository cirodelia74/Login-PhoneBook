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
 * Panel used to delete a contact from Database
 * @author cirod
 *
 */
@SuppressWarnings("serial")
public class RemoveContact extends JPanel {
	
	private JButton btnCercaID, btnElimina, btnReset;
	private JTextField txtCercaID, txtCognome, txtNome, txtIndirizzo, txtCitta, txtTelefono, txtEmail;
	private JLabel lblCercaID, lblCognome, lblNome, lblIndirizzo, lblCitta, lblTelefono, lblEmail;
	
	private JPanel panelForm, panelButton;
	private JPanel[] panelTxT;
	
	/**
	 * Constructor of class
	 */
	public RemoveContact() {
		
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
		this.btnCercaID = new JButton("Cerca Contatto");
		
		this.btnElimina = new JButton("Elimina Contatto");
		this.btnElimina.setEnabled(false);
		
		this.btnReset = new JButton("Reset Campi");
		this.btnReset.setEnabled(false);
		
		this.lblCercaID = new JLabel("ID da cercare ");
		this.txtCercaID = new JTextField(3);
		
		this.lblCognome = new JLabel("Cognome ", JLabel.RIGHT);
		this.txtCognome = new JTextField(15);
		this.txtCognome.setEditable(false);
		
		this.lblNome = new JLabel("Nome ", JLabel.RIGHT);
		this.txtNome = new JTextField(15);
		this.txtNome.setEditable(false);
		
		this.lblIndirizzo = new JLabel("Indirizzo ", JLabel.RIGHT);
		this.txtIndirizzo = new JTextField(25);
		this.txtIndirizzo.setEditable(false);
		
		this.lblCitta = new JLabel("Citta ", JLabel.RIGHT);
		this.txtCitta = new JTextField(15);
		this.txtCitta.setEditable(false);
		
		this.lblTelefono = new JLabel("Telefono ", JLabel.RIGHT);
		this.txtTelefono = new JTextField(15);
		this.txtTelefono.setEditable(false);
		
		this.lblEmail = new JLabel("Email ", JLabel.RIGHT);
		this.txtEmail = new JTextField(25);
		this.txtEmail.setEditable(false);
	}
	
	/**
	 * Setting up all the panels of the main panel
	 */
	private void createInsidePanels() {
		this.panelForm = new JPanel(new GridLayout(4, 1, 5, 5));
		this.panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
		this.panelTxT = new JPanel[4];
		
		for (int i=0; i<3; i++) {
			this.panelTxT[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
		}
		
		this.panelTxT[3] = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
		
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
		
		this.panelTxT[3].add(lblCercaID);
		this.panelTxT[3].add(txtCercaID);
		this.panelTxT[3].add(new JLabel("   "));
		this.panelTxT[3].add(btnCercaID);

		this.panelForm.add(panelTxT[3]);
		this.panelForm.add(panelTxT[0]);
		this.panelForm.add(panelTxT[1]);
		this.panelForm.add(panelTxT[2]);

		this.panelForm.setBorder(BorderFactory.createTitledBorder("Elimina contatto"));
		
		this.panelButton.add(btnElimina);
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
		this.txtCognome.setEditable(false);
		this.txtNome.setText("");
		this.txtNome.setEditable(false);
		this.txtIndirizzo.setText("");
		this.txtIndirizzo.setEditable(false);
		this.txtCitta.setText("");
		this.txtCitta.setEditable(false);
		this.txtTelefono.setText("");
		this.txtTelefono.setEditable(false);
		this.txtEmail.setText("");
		this.txtEmail.setEditable(false);
		this.txtCercaID.setText("");		
		
		this.btnElimina.setEnabled(false);
		this.btnReset.setEnabled(false);		
	}
	
	/* Start getter and setter methods */
	public JButton getBtnCercaID() {
		return btnCercaID;
	}

	public JButton getBtnElimina() {
		return btnElimina;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public void setTxtCognome(String txtCognome) {
		this.txtCognome.setText(txtCognome);
	}

	public void setTxtNome(String txtNome) {
		this.txtNome.setText(txtNome);
	}

	public void setTxtIndirizzo(String txtIndirizzo) {
		this.txtIndirizzo.setText(txtIndirizzo);
	}

	public void setTxtCitta(String txtCitta) {
		this.txtCitta.setText(txtCitta);
	}

	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono.setText(txtTelefono);
	}
	
	public void setTxtEmail(String txtEmail) {
		this.txtEmail.setText(txtEmail);
	}


	public JTextField getTxtCognome() {
		return txtCognome;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtIndirizzo() {
		return txtIndirizzo;
	}
	
	public JTextField getTxtCitta() {
		return txtCitta;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtCercaID(String txtCercaID) {
		this.txtCercaID.setText(txtCercaID);
	}

	public String getTxtCercaID() {
		return txtCercaID.getText();
	}
	/* End getter and setter methods */
}
