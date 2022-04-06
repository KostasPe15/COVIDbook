import java.util.ArrayList;

public class Graph {
	private int size;
	private ArrayList<ArrayList<Integer>> graphArray;
	
	//Kataskeuasths grafou
	public Graph (ArrayList<User> list) {
		size = list.size();
		graphArray = new ArrayList<>(size);
		for (int i = 0; i < size; i++) 
            graphArray.add(new ArrayList<Integer>());
		for (int i = 0; i < size; i++) {
			for (int y = 0; y < size; y++) {
				if(list.get(i).isFriend(list.get(y)))
					graphArray.get(i).add(y);
			}
		}
	}
	
	//Ypologismos diametrou
	public void graphDiam() {
		int max = -1;
		int count=1;
		for(int i=0; i < size; i++) {
			for(int y=0; y < size; y++) {
				count = 1;
				if(graphDist(i,y)>=0) 
					count = graphDist(i,y);
				else {
					count =2;
					for(int k=0;k<graphArray.get(i).size();k++) {
						if(graphDist(graphArray.get(i).get(k),y)>=0) {
							count =+ graphDist(k,y);
							break;
						}
						count++;
						if(count>max)
							max=count;
					}
				}
				if(count>max)
					max=count;
			}
		}
		System.out.println("H diametros einai: "+max);
	}
	
	//Ypologismos apostashs metaxy 2 kombwn
	public int graphDist(int a,int b) {
		if(a==b)
			return 0;
		else if (graphArray.get(a).contains(b))
			return 1;
		else {
			for(int i=0;i<graphArray.get(a).size();i++) {
				if(graphArray.get(graphArray.get(a).get(i)).contains(b)) 
					return 2;
			}
			return -1;
		}
	}
	
	//Ektypwsh grafou
	public void printGraph() {
		for (int i = 0; i < size; i++) {
			System.out.print(i+": [");
			for (int y = 0; y < graphArray.get(i).size(); y++) {
				System.out.print(graphArray.get(i).get(y));
			}
			System.out.println("]");
		}
	}
}
