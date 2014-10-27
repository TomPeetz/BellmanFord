import java.util.HashMap;
import java.util.Map.Entry;


public class BellmanFord {
	public static boolean bellmanFord(HashMap<String, Graph> lNodes, Graph startNode) {
		startNode.setDistance(true);
		Graph aNode;
		for (int i = 0; i < lNodes.size(); i++) {
			for (Entry<String, Graph> node : lNodes.entrySet()) {
				aNode = node.getValue();
				for (Entry<Graph, Integer> way : aNode.getAllRoutes().entrySet()) {
					if (aNode.getDistance() + aNode.getRoute(way.getKey()) < way.getKey().getDistance()) {
						way.getKey().setPrev(aNode);
					}
				}
			}
		}
		
		boolean negativeCircle = false;
		for (Entry<String, Graph> node : lNodes.entrySet()) {
			aNode = node.getValue();
			for (Entry<Graph, Integer> way : aNode.getAllRoutes().entrySet()) {
				if (aNode.getDistance() + aNode.getRoute(way.getKey()) < way.getKey().getDistance()) {
					negativeCircle = true;
					break;
				}
			}
			if (negativeCircle = true) {
				break;
			}
		}
		
		return negativeCircle;
	}
}
