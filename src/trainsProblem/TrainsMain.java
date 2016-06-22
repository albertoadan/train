package trainsProblem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class TrainsMain 
{

	
	private static FileInputStream getFileInputStream(String name) {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(name);
		} catch (FileNotFoundException e) {
    		System.err.println(name + ": File not found");
    		System.exit(1);
    	}
		return stream;
	}
	
    public static void main( String[] args )    {    	

                FileInputStream graphFile = getFileInputStream ("graph-input.txt");
                FileInputStream commandsFile = getFileInputStream ("commands.txt");
		
		
    	try {
        	
                Graph graph = new Graph(graphFile);
    	    Actions actions = new Actions(commandsFile);
    	    
    	    System.out.println(actions.execute(graph));
    	} catch (IOException e) {
    		System.err.println("FATAL: " + e.getMessage());
    		System.exit(1);
    	}
    }
    
    
}
