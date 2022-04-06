import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<User> userList = new ArrayList<>();
		ArrayList<Group> groupList = new ArrayList<>();
		
/*		//Dhmiourgia Users
		User u1 = new User("Makis", "iis1998@uom.edu.gr",userList);
		User u2 = new User("Petros", "ics1924@uom.edu.gr",userList);
		User u3 = new User("Maria", "iis2012@uom.edu.gr",userList);
		User u4 = new User("Gianna", "iis19133@uom.edu.gr",userList);
		User u5 = new User("Nikos", "dai1758@uom.edu.gr",userList);
		User u6 = new User("Babis", "ics19104@uom.edu.gr",userList);
		User u7 = new User("Stella", "dai1827@uom.edu.gr",userList);
	//	User u8 = new User("Eleni", "ics2086@gmail.com",userList);
		
		//Dhmiourgia Groups
		Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");
		
		groupList.add(g1);
		groupList.add(g2);
		
		//Filies
		u1.addFriend(u2);
		u1.addFriend(u5);
		u5.addFriend(u6);
		u3.addFriend(u4);
		u3.addFriend(u2);
		u4.addFriend(u6);
		u5.addFriend(u3);
		u1.addFriend(u6);
		u5.addFriend(u2);
		u7.addFriend(u1);
		
		//Ektypwsh koinwn filwn
		u5.printMutualFriends(u4);
		u1.printMutualFriends(u5);
		
		//Ektypwsh filwn
		u1.printFriends();
		u3.printFriends();
		
		//Prosthhkh melwn sta groups
		g1.addMember(u4);
		g1.addMember(u3);
		g1.addMember(u2);
		g2.addMember(u4);
//		g2.addMember(u5);
		g2.addMember(u6);
		g2.addMember(u5);      
		
		//Ektypwsh group sta opoia einai melos
		u4.printGroups();
		
		//Ektypwsh melwn group
		g1.printMembers();
		g2.printMembers();
		
		//Ektypwsh twn users pou prepei na kanoun test covid
		u4.covidTest();
		*/
		
		//Anakthsh apothhkeumenhs katastashs
		try {
			FileInputStream userIn = new FileInputStream("users.ser");
			ObjectInputStream in = new ObjectInputStream(userIn);
			userList = (ArrayList<User>)in.readObject();
			userIn.close();
			in.close();
		}
		catch(IOException exc1)
		{
			exc1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream groupIn = new FileInputStream("groups.ser");
			ObjectInputStream in = new ObjectInputStream(groupIn);
			groupList = (ArrayList<Group>)in.readObject();
			groupIn.close();
			in.close();
		}
		catch(IOException exc1)
		{
			exc1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		new SignInFrame(userList,groupList);
	}

}
