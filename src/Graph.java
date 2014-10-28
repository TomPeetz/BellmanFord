import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Graph {
	private String name;
	private HashMap<Graph, Integer> routes; // Gerichteten Kanten zu anderen Knoten
	private Graph prev; // Vorgängerknoten
	private Integer distance; // Entfernung zum Startknoten
	
	public Graph(String name) {
		this.name=name;
		routes = new HashMap<Graph, Integer>();
		prev = null;
		distance = null;
	}
	
	public String getName() {
		return name;
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
	
	public Integer getRoute(Graph g) {
		Integer distance = null;
		if(g != null && routes.containsKey(g)) {
			distance = routes.get(g);
		}
		return distance;
	}
	
	public boolean hasPrev() {
		return prev == null;
	}
	
	public Graph getPrev() {
		return prev;
	}
	
	/*
	 * TODO Methode schreiben
	 * @return true, wenn erfolgreich geändert.
	 * @return false, bei Fehler (übergebener Graph hat keine Kante zu dem aktuellen)
	 */
	public boolean setPrev(Graph g) {
		if (g == null) {
			prev = g;
			setDistance(false);
		} else if (g.getRoute(this) == null) {
			return false;
		} else {
			prev = g;
			setDistance(false);
		}
		return true;
	}
	
	public void setDistance(boolean isStart) {
		if (isStart) {
			distance = 0;
		} else {
			if(prev == null) {
				distance = null;
			} else {
				distance = prev.getDistance() + prev.getRoute(this);
			}
		}
	}
	
	public Integer getDistance() {
		return distance;
	}
	
	public HashMap<Graph, Integer> getAllRoutes() {
		return (HashMap<Graph, Integer>) routes;
	}
}
