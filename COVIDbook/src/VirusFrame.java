import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class VirusFrame extends JFrame{
	private JPanel panel;
	private TextArea virusField;
	private JButton btn;
	private ArrayList<User> uList = new ArrayList<>();
	private ArrayList<Group> gList = new ArrayList<>();
	
	public VirusFrame(User aUser,ArrayList<User> userList,ArrayList<Group> groupList) {
		this.uList = userList;
		this.gList = groupList;
		User theUser = aUser;
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		virusField = new TextArea(15,50);
		virusField.setText(theUser.covidTestString());
		btn = new JButton("Back to Login Screen");
		
		panel.add(virusField,BorderLayout.NORTH);
		panel.add(btn,BorderLayout.CENTER);
		
		ButtonListener listener = new ButtonListener();
		btn.addActionListener(listener);
		
		this.setContentPane(panel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Possible Virus Transmission");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent anevent) {
			new SignInFrame(uList,gList);
			dispose();
		}
	}
}
