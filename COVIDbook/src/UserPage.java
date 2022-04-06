import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class UserPage extends JFrame {
	private JTextField nameField,emailField,addField;
	private TextArea postField,recentPostsField,suggestedFriendsField;
	private JLabel postLabel,suggestedFriendsLabel;
	private JButton backButton,postButton,addButton,groupButton;
	private JPanel panel1,panel2,panel3,panel4,panel5,panel6;
	private User theUser;
	private ArrayList<User> uList = new ArrayList<>();
	private ArrayList<Group> gList = new ArrayList<>();
	private JList<String> list;
	
	public UserPage (User aUser,ArrayList<User> userList,ArrayList<Group> groupList) {
		this.uList = userList;
		this.gList = groupList;
		theUser = aUser;
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new FlowLayout());
		panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,30));
		panel6 = new JPanel(new FlowLayout());
		
		panel2.setPreferredSize(null);
		nameField = new JTextField(10);
		nameField.setText(aUser.getName());
		nameField.setEditable(false);
		emailField = new JTextField(17);
		emailField.setText(aUser.getEmail());
		emailField.setEditable(false);
		backButton = new JButton("Back to Login Screen");
		
		postField = new TextArea(10,30);
		postButton = new JButton("Post");
		
		postLabel = new JLabel("Recent Posts by Friends:");
		recentPostsField = new TextArea(10,30);
		recentPostsField.setText(theUser.postsString());
		recentPostsField.setEditable(false);
		
		suggestedFriendsLabel = new JLabel("Suggested Friends:");
		suggestedFriendsField = new TextArea(5,10);
		suggestedFriendsField.setText(theUser.suggestedFriendsString());
		suggestedFriendsField.setEditable(false);
		
		addField = new JTextField(8);
		addField.setText("Enter Name");
		addField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                addField.setText("");
            }
        }); 
		addButton = new JButton("Add to friends");
		
		groupButton = new JButton("Enroll");
		list = new JList<String>();
		DefaultListModel<String> model = new DefaultListModel<String>();		
		for(Group g : gList) {
			
			model.addElement(g.getName());
		}
		list.setModel(model);
				

		panel1.add(nameField);
		panel1.add(emailField);
		panel1.add(backButton);
		
		panel2.add(postField);
		panel2.add(postButton);
		
		panel3.add(postLabel);
		panel3.add(recentPostsField);
	
		panel4.add(suggestedFriendsLabel);
		panel4.add(suggestedFriendsField);
		
		panel5.add(addField);
		panel5.add(addButton);
		
		panel6.add(list);
		panel6.add(groupButton);
		
		contentPane.add(panel1);
		contentPane.add(panel2);
		contentPane.add(panel3);
		contentPane.add(panel4);
		contentPane.add(panel5);
		contentPane.add(panel6);
		
		ButtonListener listener = new ButtonListener();
		backButton.addActionListener(listener);
		postButton.addActionListener(listener);
		addButton.addActionListener(listener);
		groupButton.addActionListener(listener);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("User Page");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent anevent) {
			if(anevent.getSource().equals(backButton)) {
				dispose();
				new SignInFrame(uList,gList);
			}
			else if(anevent.getSource().equals(postButton)){
				Date date=new java.util.Date();  
				String text = postField.getText();
				Post thePost = new Post(date,text,theUser);
				theUser.addPost(thePost);
				recentPostsField.setText(theUser.postsString());
			} 
			else if(anevent.getSource().equals(addButton)) {
				boolean flag = false;
				for(User a : uList) {
					if(a.getName().equals(addField.getText())) {
						theUser.addFriend(a);
						flag = true;
						Graph gr = new Graph(uList);
						gr.printGraph();
						gr.graphDiam();
					}
				}
				if (!flag)
					JOptionPane.showMessageDialog(null, "User "+addField.getText()+" does not exist");
			}
			else if(anevent.getSource().equals(groupButton)) {
				String selectedGroupName = list.getSelectedValue();
				
				Group selectedGroup = null;
				for(Group gr: gList) {
					if(gr.getName().equals(selectedGroupName))
						selectedGroup = gr;
				}
				selectedGroup.addMember(theUser);
			}
		}
	}
}
