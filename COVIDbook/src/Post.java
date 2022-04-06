import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post implements Serializable, Comparable<Post>{
	private Date timestamp;
	private String text;
	private User creator;
	
	//Kataskeuasths
	public Post(Date timestamp, String text, User creator) {
		this.timestamp = timestamp;
		this.text = text;
		this.creator = creator;
	}
	
	//Epistrofh Date se String
	public String getTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");  
        String strDate = dateFormat.format(this.timestamp);  
		return strDate;
	}
	
	//Getters
	public String getText() {
		return text;
	}
	public User getCreator() {
		return creator;
	}

	@Override
	public int compareTo(Post arg0) {
		return this.timestamp.compareTo(arg0.timestamp);
	}

	
}
