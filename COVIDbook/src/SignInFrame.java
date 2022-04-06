import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;

public class SignInFrame extends JFrame {
	private JTextField nameField,emailField;
	private JButton enterButton,infectionsButton,newButton,saveButton;
	private ArrayList<User> uList = new ArrayList<>();
	private ArrayList<Group> gList = new ArrayList<>();
	private JPanel panel;
	
	public SignInFrame (ArrayList<User> userList,ArrayList<Group> groupList) {
		this.uList = userList;
		this.gList = groupList;
		
		newButton = new JButton("New User");
		saveButton = new JButton("Save PamakBook");
		nameField = new JTextField(7);
		nameField.setText("User Name");
		nameField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                nameField.setText("");
            }
        });  
		emailField = new JTextField(7);
		emailField.setText("User Email");
		emailField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	emailField.setText("");
            }
        });   
		enterButton = new JButton("Enter User Page");
		infectionsButton = new JButton("Show Potential Infections");
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		
		panel.add(newButton);
		panel.add(nameField);
		panel.add(emailField);
		panel.add(enterButton);
		panel.add(infectionsButton);
		panel.add(saveButton);
		
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		enterButton.addActionListener(listener);
		infectionsButton.addActionListener(listener);
		newButton.addActionListener(listener);
		saveButton.addActionListener(listener);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Sign in");
		this.setSize(400,150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent anevent) {
			boolean flag=false;
			User theUser = null;
			String name = nameField.getText();
			System.out.print("");
			for(User a : uList) {
				if(a.getName().equals(name)) {
					flag=true;
					theUser = a;
				}
			}
			
			if(anevent.getSource().equals(enterButton)) {
				if(!flag) {
					JOptionPane.showMessageDialog(null, "User "+name+" Not Found");
				} else {
					new UserPage(theUser,uList,gList);
					dispose();
				}
			} else if(anevent.getSource().equals(infectionsButton)){
				if(!flag) {
					JOptionPane.showMessageDialog(null, "User "+name+" Not Found");
				}else {
					new VirusFrame(theUser,uList,gList);
					dispose();
				}
			} else if(anevent.getSource().equals(newButton)) {
					if(!flag)
						new User(nameField.getText(),emailField.getText(),uList);
					else
						JOptionPane.showMessageDialog(null, "User "+name+" already exists");
			} else {
				try {
					FileOutputStream fileOut = new FileOutputStream("users.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(uList);
					out.close();
					fileOut.close();		
				}
				catch(IOException i) {
					i.printStackTrace();
				}
				
				try {
					FileOutputStream fileOut = new FileOutputStream("groups.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(gList);
					out.close();
					fileOut.close();		
				}
				catch(IOException i) {
					i.printStackTrace();
				}
			}
		}
	}
}
