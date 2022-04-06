import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class User implements Serializable {
	private String name;
	private String email;
	private ArrayList<User> friends;
	private ArrayList<Group> groups;
	private ArrayList<Post> posts;
	
	//Kataskeuasths kai elegxos gia swsto email
	public User(String aname,String anemail,ArrayList<User> userList) {
		if((anemail.endsWith("@uom.edu.gr"))&&((anemail.startsWith("iis"))||(anemail.startsWith("ics"))||(anemail.startsWith("dai")))&&
				(Character.isDigit(anemail.charAt(5)))&&(!Character.isDigit(anemail.charAt(8)))) {
			name=aname;
			email=anemail;
			friends = new ArrayList<>();
			groups = new ArrayList<>();
			posts = new ArrayList<>();
			userList.add(this);
		} else {
			System.out.println("User "+aname+" has not been created. Email format is not acceptable.");
			JOptionPane.showMessageDialog(null, "User "+aname+" has not been created. Email format is not acceptable.");
		}
	}
	
	//Dhmiourgia listas proteinwmenwn filwn
	public ArrayList<User> suggestedFriends(){
		ArrayList<User> sFriends = new ArrayList<>();
		for(User s : this.friends) {
			for(User f : s.friends) {
				if(!this.isFriend(f) && !sFriends.contains(f) && !f.equals(this)){
					sFriends.add(f);
				}
			}
		}
		return sFriends;
	}
	
	//Metatroph ths listas proteinwmenwn filwn se String
	public String suggestedFriendsString() {
		ArrayList<User> sFriends = suggestedFriends();
		String str = "";
		for (User s : sFriends){
		    str += s.getName() + "\n";
		}
		return str;
	}
	
	//Dhmiourgia listas me ta posts
	public ArrayList<Post> postsList(){
		ArrayList<Post> pList = new ArrayList<>();
		for(Post p : this.posts)
			pList.add(p);
		for(User f : this.friends) {
			for(Post k : f.posts)
				pList.add(k);
		}
		Collections.sort(pList);
		return pList;
	}
	
	//Metatroph ths listas posts se String
	public String postsString() {
		String str = "";
		ArrayList<Post> pList = postsList();
		for(int i = pList.size()-1;i>=0;i--) {
			 str += pList.get(i).getCreator().getName()+ " " + pList.get(i).getTimestamp()+ "\n" + pList.get(i).getText()+"\n";
		}
		return str;
	}
	
	//Prosthikh neou post
	public void addPost(Post aPost) {
		posts.add(aPost);
	}
	
	//Elegxos an enas user einai filos
	public boolean isFriend(User aUser) {
		if(!(aUser.getName().equals(name) && aUser.getEmail().equals(email))) {
			for (int i=0; i<friends.size(); i++) {
				if(aUser.getName().equals(friends.get(i).getName()))
					return true;
			}
		}
		return false;
	}
	
	//Prosthhkh filou
	public void addFriend(User aUser) {
		if(aUser.getName().equals(name) && aUser.getEmail().equals(email)) {
			System.out.println("This user is the same");
			JOptionPane.showMessageDialog(null, "This user is the same");
		}else if(isFriend(aUser)) {
			System.out.println("This user is already a friend");
			JOptionPane.showMessageDialog(null, "This user is already a friend");

		}else {
			friends.add(aUser);
			aUser.getFriends().add(this);
			System.out.println(name + " and " + aUser.getName() + " are now friends!");
		}
	}
	
	//Dhmiourgia listas koinwn filwn
	public ArrayList<User> mutualFriends(User aUser){
		ArrayList<User> mf = new ArrayList<User>();
		for(int i = 0;i<aUser.getFriends().size();i++) {
			if(friends.contains(aUser.getFriends().get(i))) {
				mf.add(aUser.getFriends().get(i));
			}
		}
		return mf;
	}
	
	//Ektypwsh koinwn filwn
	public void printMutualFriends(User aUser) {
		System.out.println("**************************************");
		System.out.println("Common friends of "+ name + " and " + aUser.getName());
		System.out.println("**************************************");
		for(int i=0;i<mutualFriends(aUser).size();i++)
			System.out.println(i+1 + ": " + mutualFriends(aUser).get(i).getName());
		System.out.println("--------------------------------------");
	}
	
	//Ektypwsh filwn
	public void printFriends() {
		System.out.println("************************");
		System.out.println("Friends of "+name);
		System.out.println("************************");
		for (int i=0;i<friends.size();i++) {
			System.out.println(i+1 + ": " + friends.get(i).getName());
		}
		System.out.println("------------------------");
	}
	
	//Ektypwsh group pou einai melos
	public void printGroups() {
		System.out.println("**************************************");
		System.out.println("Groups that "+name+" has been enrolled in");
		System.out.println("**************************************");
		for (int i=0;i<friends.size();i++) {
			System.out.println(i+1 + ": " + groups.get(i).getName());
		}
		System.out.println("--------------------------------------");
	}
	
	//Dhmiourgia listas twn users pou prepei na kanoun test
	public ArrayList<User> covid(){
		ArrayList<User> cov = new ArrayList<User>();
		for (User f : friends ) {
			cov.add(f);
			for (User f2 : f.getFriends() ) {
				if((!cov.contains(f2))&&(f2!=this))
					cov.add(f2);
			}
		}
		return cov;
	}
	
	//Ektypwsh twn users pou prepei na kanoun test
		public void covidTest() {
			System.out.println("*******************************");
			System.out.println(name+" has been infected. The following users have to be tested");
			for (int i=0;i< this.covid().size();i++) {
				System.out.println(i+1 + ": " + this.covid().get(i).getName());
			}
			System.out.println("-----------------------------");
		}
	
	//Metatroph thw listas twn users pou prepei na kanoun test se String
	public String covidTestString() {
		String str = "***********************************************************************\n";
		str += name+" has been infected. The following users have to be tested\n";
		str += "***********************************************************************\n";
		for (int i=0;i< this.covid().size();i++) {
			if(!str.contains(this.covid().get(i).getName()))
				str += this.covid().get(i).getName() + "\n";
		}
		return str;
	}

	//Getters
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}
	
	
}
