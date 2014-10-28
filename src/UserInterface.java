
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class UserInterface {
	private Scanner scanner;
	
	public UserInterface() {	
		scanner = new Scanner(System.in);
	}
	
	
	public void run() {		
		print("\n\n\nEin neuer Graph wird angelegt...\n");
		
		HashMap<String, Graph> lNodes = new HashMap<String, Graph>();
		
		print("Wie viele Kanten soll er haben ? : ");
		Integer lEdgeCount = scanner.nextInt();		
		print("Legen Sie die Kanten nach folgendem Schema an... \n\n");	
		
		print("Kante X: [Name Knoten1] [Name Knoten2] [Gewicht Kante]\n\n");
		
		String lNodeAName, lNodeBName;
		Integer lEdgeWeight;
		Graph lNodeA, lNodeB;
		
		for (int i = 1; i <= lEdgeCount; i++) {
			print("Kante "+i+": ");
			
			lNodeAName = scanner.next();
			lNodeBName = scanner.next();
			lEdgeWeight = scanner.nextInt();
			
			if (!lNodes.containsKey(lNodeAName)) {
				lNodes.put(lNodeAName, new Graph(lNodeAName));
			}			
			if (!lNodes.containsKey(lNodeBName)) {
				lNodes.put(lNodeBName, new Graph(lNodeBName));
			}
			
			lNodeA = lNodes.get(lNodeAName);
			lNodeB = lNodes.get(lNodeBName);
			
			lNodeA.addRoute(lNodeB, lEdgeWeight);
		}
		
		// -> TODO: Was wenn Graph nicht zusammenhängend ?
		
		print("\nFolgende Knoten wurden angelegt:");
		for (String lItem: lNodes.keySet()) {
			print("\n"+lItem);
		}	
		print("\n\n");
		
		Graph lStartNode = null;
		while (lStartNode == null) { // Solange fragen, bis einer der vorgegebenen Knoten ausgwählt wird
			print("Ernennen Sie einen DIESER Knoten zum Startknoten: ");			
			lStartNode = lNodes.get(scanner.next());
			scanner.nextLine();
		}
		
		scanner.close();
		
		print("\n\n");		
		boolean lHasNegativeCircle = BellmanFord.bellmanFord(lNodes, lStartNode);
		
		if (lHasNegativeCircle) {
			print("Der Graph hat keine kürzesten Wege, da er einen negativen Kreis enthält.");
		} else {			
			Graph lNode;		
			String lPath;
			for (Entry<String, Graph> lItem: lNodes.entrySet()) {
				lNode =  lItem.getValue();
				
				print("Distanz zu: "+ lItem.getKey() +" = "+ lNode.getDistance() + "\n");
				
				lPath = lNode.getName();
				lNode = lNode.getPrev();				
				while (lNode != null) {
					lPath = lNode.getName() +" -> "+ lPath;					
					lNode = lNode.getPrev();
				}				
				print("Strecke: "+ lPath +"\n\n");
				
			}
		}		
	}	
	
	private static void print(String pString) {
		System.out.print(pString);
	}
	
	public static void main(String[] args) {
		print("Dieses Programm wendet den Bellman-Ford-Algorithmus auf einen selbst angelegten Graphen an.");
		
		//while (true) {
			try {
				new UserInterface().run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
	}
	
}
