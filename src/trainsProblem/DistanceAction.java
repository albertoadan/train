package trainsProblem;

public class DistanceAction extends Action {
	public String execute(Graph graph) {
		String[] parameters = getParameters();
		
		String nodesSpec = parameters[1];
		Node[] nodes = getNodes(graph, nodesSpec);
				
		int distance = 0;		
		
		Node lastNode = nodes[0];		
		
		for (int i = 1; i < nodes.length; ++i) {
			Node node = nodes[i];
			boolean routeFound = false;
			
			for (Edge neighbour: lastNode.getNeighbours()) {
				if (neighbour.getDestination().isEqual(node)) {					
					distance += neighbour.getDistance();
					routeFound = true;
					break;
				}
			}
			if (!routeFound) {				
				distance = Integer.MAX_VALUE;
				break;
			}
			lastNode = node;
		}
		
		if (distance == Integer.MAX_VALUE) {
                        return "NO SUCH ROUTE";			
		}
		return String.valueOf(distance);
	}
}
