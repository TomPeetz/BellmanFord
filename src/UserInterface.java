
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
	private Scanner input;
	
	public UserInterface() {	
		input = new Scanner(System.in);
	}
	
	
	private void print(String pString) {
		System.out.print(pString);
	}
	
	public void run() {
		print("(Einführungstext)\n\n\n");		
		
		HashMap<String, Graph> lNodes = new HashMap<String, Graph>();
		
		
		print("Wie viele Kanten sollen angelegt werden ? : ");		
		Integer lEdgeCount = input.nextInt();
		print("Legen Sie Kanten mit folgender Syntax an... \n\n");	
		
		print("Kante X: [Name Knoten1] [Name Knoten2] [Gewicht Kante]\n\n");
		
		String lNodeAName, lNodeBName;
		Integer lEdgeWeight;
		Graph lNodeA, lNodeB;
		
		for (int i = 1; i <= lEdgeCount; i++) {
			print("Kante "+i+": ");
			
			lNodeAName = input.next();
			lNodeBName = input.next();
			lEdgeWeight = input.nextInt();
			
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
		while (lStartNode == null) {
			print("Ernennen Sie einen DIESER Knoten zum Startknoten: ");			
			lStartNode = lNodes.get(input.next());
			input.nextLine();
		}
		
		input.close();
		
		// -> TODO: irgendwas mit 'lStartNode' machen
	}	
	
	
	public static void main(String[] args) {
		new UserInterface().run();
	}
	
}
