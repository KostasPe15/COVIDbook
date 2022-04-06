import javax.swing.JOptionPane;

public class ClosedGroup extends Group {
	//Kleisth omada me kloironomikothta apo thn Groups
	
	public ClosedGroup(String aname, String adescription) {
		super(aname, adescription);
	}

	//Prosthhkh melous me eidiko kritirio gia kleisth omada
	public void addMember(User aUser) {
		if(members.isEmpty())
			super.addMember(aUser);
		else {
			for(User f : aUser.getFriends()) {
				if(members.contains(f)) 
					super.addMember(aUser);
			}
		}
		if(!isMember(aUser)) {
			System.out.println("FAILED: "+ aUser.getName() + " cannot be enrolled in group "+ name);
			JOptionPane.showMessageDialog(null, "FAILED: "+ aUser.getName() + " cannot be enrolled in group "+ name);
		}
	}
}

