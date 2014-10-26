import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Graph {
	private String name;
	private Map<Graph, Integer> routes;
	public Graph(String name) {
		this.name=name;
		routes = new HashMap<Graph, Integer>();
	}
	
	public void addRoute(Graph g, Integer i) {
		if (g != null && !routes.containsKey(g)) {
			routes.put(g, i);
		}
	}
	
	public Integer removeRoute(Graph g) {
		Integer removed = null;
		if (g!= null && routes.containsKey(g)) {
			removed = routes.remove(g);
		}
		return removed;
	}
	
	public Integer getDistance(Graph g) {
		Integer distance = null;
		if(g != null && routes.containsKey(g)) {
			distance = routes.get(g);
		}
		return distance;
	}
}
