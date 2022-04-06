import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Group implements Serializable {
	protected String name;
	protected String description;
	protected ArrayList<User> members;
	
	//Kataskeuasths
	public Group(String aname,String adescription) {
		name=aname;
		description=adescription;
		members = new ArrayList<User>();
	}
	
	//Elegxos an einai melos
	public boolean isMember(User aUser) {
		if(members.contains(aUser))
			return true;
		return false;
	}
	
	//Prosthhkh melous
	public void addMember(User aUser) {
		if(!isMember(aUser)) {
			members.add(aUser);
			aUser.getGroups().add(this);
			System.out.println(aUser.getName() + " has been successfully enrolled in group " + name);
		} else {
			System.out.println("User is already a member");
			JOptionPane.showMessageDialog(null, "User is already a member");
		}
	}
	
	//Ektypwsh melwn
	public void printMembers() {
		System.out.println("*******************************");
		System.out.println("Members of group "+name);
		System.out.println("*******************************");
		for (int i=0;i<members.size();i++) {
			System.out.println(i+1 + ": " + members.get(i).getName());
		}
		System.out.println("-------------------------------");
	}
	
	//Getters
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<User> getMembers() {
		return members;
	}	
}
