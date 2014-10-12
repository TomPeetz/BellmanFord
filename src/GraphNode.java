
/**
 * <p>Materialien zu den zentralen
 * Abiturpruefungen im Fach Informatik ab 2012 in 
 * Nordrhein-Westfalen.</p>
 * <p>Klasse GraphNode</p>
 * <p>Ein ungerichteter Graph besteht aus einer Menge 
 * von Knoten und einer Menge von Kanten. Die Kanten 
 * verbinden jeweils zwei Knoten und koennen ein Gewicht haben.
 * Objekte der Klasse GraphNode sind Knoten eines Graphen. 
 * Ein Knoten hat einen Namen und kann markiert werden.</p>
 * 
 * <p>NW-Arbeitsgruppe: Materialentwicklung zum Zentralabitur 
 * im Fach Informatik</p>
 * 
 * @version 2010-10-22
 */
public class GraphNode {
    private String name;   
    private boolean marked;

    /**
     *Ein Knoten mit dem Namen pName wird erzeugt. 
     *Der Knoten ist nicht markiert. 
     *@param pName Bezeichnung des Knotens
     */
    public GraphNode(String pName) { 
        name=pName;          
        marked=false;
    }

    /**
     * Der Knoten wird markiert. Falls er 
     * nicht markiert ist, sonst bleibt er unveraendert.
     */  
    public void mark() {
        marked=true;
    }
    
    /**
     * Die Markierung des Knotens wird entfernt, falls er markiert ist, 
     * sonst bleibt er unveraendert.
     */
    public void unmark() {
        marked=false;
    }
    
    /**
     * Die Anfrage liefert den Wert true, wenn der Knoten markiert ist, 
     * sonst liefert sie den Wert false.
     * @return true falls markiert, sonst false
     */  
    public boolean isMarked() {
        return marked;

    }
    
    /**
     * Die Anfrage liefert den Namen des Knotens.
     * @return Bezeichnung des Knotens
     */  
    public String getName()   {
        return name;
    }
}
