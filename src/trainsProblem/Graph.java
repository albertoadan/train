package trainsProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    
    Map<String, Node> nodeMap = new TreeMap<String, Node>();
    
    public Node[] getNodes() {
    	return nodeMap.values().toArray(new Node[0]);
    }
    
   
    public Node getNode(String name) {
    	Node node = nodeMap.get(name);
    	if (node == null) {
    		node = new Node(name);
    		nodeMap.put(name, node);
    	}
    	return node;
    }
    
	
	public void read(String str) {
		for (String s : str.split("[\\s,]")) {
			if (!s.isEmpty()) {
				// An edge spec must be at least 3 characters.. i.e. 
				// AB3
				if (s.length() < 3) {
					throw new IllegalArgumentException("Bad graph specification: " + s);
				}
				String sourceNodeName = s.substring(0,1);
				String targetNodeName = s.substring(1,2);
				
				int distance;
				
				try {
					distance = Integer.parseInt(s.substring(2));
				} catch(NumberFormatException e) {
					throw new IllegalArgumentException("Bad graph specification: " + s +
							": " + s.substring(2) + " is not a valid integer");
				}
				Node source = getNode(sourceNodeName);
				Node target = getNode(targetNodeName);
				
				source.addEdge(new Edge(target, distance));
			}
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, Node> entry : nodeMap.entrySet()) {
			s.append(entry.getKey() + ":\n");
			for (Edge edge : entry.getValue().neighbours) {
				s.append("  -> " + edge + "\n");
			}
		}
		return s.toString();
	}
	
	
	public Graph(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			read(str);
		}
	}
	
	public Graph(String str) {
		read(str);
	}
}
