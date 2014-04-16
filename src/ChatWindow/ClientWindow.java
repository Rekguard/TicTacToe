package ChatWindow;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class ClientWindow extends JFrame{
	
	private Client client;
	
	private JPanel contentPane;
	private JTextField txtMessage;
	private JTextArea textHistory;
	private DefaultCaret textHistoryCaret;
	
	public ClientWindow(String name, String ipAddress, int port){
		
		client = new Client(name,ipAddress,port);
		

		boolean connect = client.openConnection(ipAddress);

		if (!connect) {
			System.err.println("Connection Failed!");
			toConsole("Connection Failed!", true);
		}

		setUpFrame();
		toConsole("IP Adress: " + ipAddress, false);
		toConsole("Port: " + port, false);
		String connection = "/c/" + client.getName();
		client.send(connection.getBytes());
	}
	
	private void setUpFrame() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Chatroom Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		this.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/* Grid Bag Layout */

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 20, 750, 20, 10 };
		gbl_contentPane.rowHeights = new int[] { 10, 530, 50, 10 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		displayTextHistoryField();
		sendTextField();
		sendTextButton();

		textHistoryCaret = (DefaultCaret) textHistory.getCaret();
		textHistoryCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		toConsole("Successfully connected on: " + new Date().toString(), false);
		toConsole("Welcome user: " + client.getName(), false);

	}

	/*
	 * displayTextHistoryField() This method displays all messages the users
	 * send to each other.
	 */
	private void displayTextHistoryField() {
		textHistory = new JTextArea();
		textHistory.setEditable(false);

		JScrollPane scrollHistWindow = new JScrollPane(textHistory);
		GridBagConstraints gbc_scrollHistConst = new GridBagConstraints();
		gbc_scrollHistConst.insets = new Insets(0, 10, 10, 0);
		gbc_scrollHistConst.fill = GridBagConstraints.BOTH;
		gbc_scrollHistConst.gridx = 0;
		gbc_scrollHistConst.gridy = 0;
		gbc_scrollHistConst.gridwidth = 3;
		gbc_scrollHistConst.gridheight = 2;
		contentPane.add(scrollHistWindow, gbc_scrollHistConst);
	}

	/*
	 * sendTextField() This method is where the user enters the message they
	 * want to send to the chat.
	 */
	private void sendTextField() {
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage(txtMessage.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 10, 5, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		txtMessage.requestFocusInWindow();
	}

	private void sendTextButton() {
		JButton btnSend = new JButton("Send");
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 5, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;

		/* Action Listener */
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(txtMessage.getText());
			}
		});

		contentPane.add(btnSend, gbc_btnSend);
	}
	
	/*
	 * sendMessage() Method
	 */
	private void sendMessage(String message) {
		String userChatText = txtMessage.getText();
		Date dateTime = new Date();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT,
				Locale.getDefault());
		message = /*df.format(dateTime)  +*/"/m/" + client.getName() + ": " + message;
		
		if (userChatText.equals("") == false) {
			toConsole(userChatText, true);
			client.send(message.getBytes());
			txtMessage.setText("");
		}
	}

	/*
	 * toConsole(String Message, Boolean showTime) Method to prepare a message
	 * to be sent to the history window.
	 * 
	 * String message, this parameter takes the message from the chat bar.
	 * Boolean showTime, this parameter can be set to true to display the time
	 * or not.
	 */

	public void toConsole(String message, Boolean showTime) {

		Date dateTime = new Date();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT,
				Locale.getDefault());

		if (showTime) {
			textHistory.append(df.format(dateTime) + " | " + client.getName() + ": ");
		}
		textHistory.append(message);
		textHistory.append("\n\r");
		textHistory.setCaretPosition(textHistory.getDocument().getLength());
	}

}
