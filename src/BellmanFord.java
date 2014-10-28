import java.util.HashMap;
import java.util.Map.Entry;


public class BellmanFord {
	public static boolean bellmanFord(HashMap<String, Graph> lNodes, Graph startNode) {
		
		for (Entry<String, Graph> node : lNodes.entrySet()) {
			node.getValue().setPrev(null);
		}
		startNode.setDistance(true);
		Graph aNode;
		
		
		for (int i = 0; i < lNodes.size()-1; i++) {
			for (Entry<String, Graph> node : lNodes.entrySet()) {
				aNode = node.getValue();
				for (Entry<Graph, Integer> way : aNode.getAllRoutes().entrySet()) {
					if (aNode.getDistance() == null) break;
					if (way.getKey().getDistance() == null) {
						way.getKey().setPrev(aNode);
					} else if (aNode.getDistance() + aNode.getRoute(way.getKey()) < way.getKey().getDistance()) {
						way.getKey().setPrev(aNode);
					}
				}
			}
		}
		
		boolean negativeCircle = false;
		for (Entry<String, Graph> node : lNodes.entrySet()) {
			aNode = node.getValue();
			for (Entry<Graph, Integer> way : aNode.getAllRoutes().entrySet()) {
				if (aNode.getDistance() == null) break;
				if (way.getKey().getDistance() == null) {
					way.getKey().setPrev(aNode);
				} else if (aNode.getDistance() + aNode.getRoute(way.getKey()) < way.getKey().getDistance()) {
					negativeCircle = true;
					break;
				}
			}
			if (negativeCircle == true) {
				break;
			}
		}
		
		return negativeCircle;
	}
}
