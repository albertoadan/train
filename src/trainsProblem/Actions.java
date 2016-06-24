package trainsProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Actions {
	private List<Action> actionsList;
	private ActionFactory actionFactory;
        private Map<String, Class<?> > actionsMap;
	
	public void read(String str) throws IllegalArgumentException {
		if (!str.isEmpty()) {
			String words[] = str.split("\\s");
			
			String name = words[0];
			Action action = actionFactory.createAction(name);                        
			
			if (action == null) {
                            throw new IllegalArgumentException( name);				
			}			
			action.setParameters(words);			
			actionsList.add(action);
		}
	}
        
    
	public Actions(InputStream stream) throws IOException {
		this();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String str;
		while((str = reader.readLine()) != null) {
			read(str);
		}
	}
	
	public Actions() {            
		actionFactory = new ActionFactory();
		actionsList   = new ArrayList<Action>();
	}
	
	
	public String execute(Graph graph) {
		StringBuilder s = new StringBuilder();

		for (Action action : actionsList) {
			s.append(action + " => " + action.execute(graph) + "\n");
		}
		
		return s.toString();
	}
}
