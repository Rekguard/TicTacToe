package ChatWindow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtName;
	private JLabel lblIpAddress;
	private JTextField txtIpAddress;
	private JTextField txtPort;
	private JLabel lblPort;
	
	
	private String cName, cIPAddress;
	private int cPort;

	/**
	 * Create the frame.
	 */
	public Login() {

		setUpFrame();

		nameField();
		ipAddressField();
		portField();
		loginButton();
	}

	private void setUpFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		setResizable(false);
		setTitle("Chatroom Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 380);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	}

	private void nameField(){
			/* Label */
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(130, 24, 34, 20);
		contentPane.add(lblName);
			
			/* Textfield */
		txtName = new JTextField();
		txtName.setBounds(69, 45, 156, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
	}
	
	private void ipAddressField(){
			/* Label */
		lblIpAddress = new JLabel("Ip Address:");
		lblIpAddress.setBounds(114, 95, 65, 14);
		contentPane.add(lblIpAddress);

		/* Textfield */
		txtIpAddress = new JTextField();
		txtIpAddress.setBounds(69, 115, 147, 20);
		contentPane.add(txtIpAddress);
		txtIpAddress.setColumns(10);
	}
	
	private void portField(){
			/* Label */
		lblPort = new JLabel("Port");
		lblPort.setBounds(134, 178, 26, 14);
		contentPane.add(lblPort);
		
			/* Textfield */
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(69, 194, 147, 20);
		contentPane.add(txtPort);
		
	}
	
	private void loginButton(){
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Actions performed when login button is clicked */
				cName = txtName.getText();
				cIPAddress = txtIpAddress.getText();
				cPort = Integer.parseInt(txtPort.getText());
				login(cName,cIPAddress,cPort);
			}
		});
		btnLogin.setBounds(102, 248, 89, 23);
		contentPane.add(btnLogin);
	}
	
	/*
	 * Login
	 */
	
	private void login(String name, String ipAddress, int port){
		dispose();
		new ClientWindow(name, ipAddress, port);
		
	}
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
	
}
