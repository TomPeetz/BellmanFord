
/**
 * <p>Materialien zu den zentralen
 * Abiturpruefungen im Fach Informatik ab 2012 in 
 * Nordrhein-Westfalen.</p>
 * <p>Klasse Graph</p>
 * <p>Objekte der Klasse Graph sind ungerichtete, gewichtete Graphen. 
 * Der Graph besteht aus Knoten, die Objekte der Klasse GraphNode sind, 
 * und Kanten, die Knoten miteinander verbinden. 
 * Die Knoten werden ueber ihren Namen eindeutig identifiziert. </p>
 * 
 * <p>NW-Arbeitsgruppe: Materialentwicklung zum Zentralabitur 
 * im Fach Informatik</p>
 * 
 * @version 2010-10-20
 */
public class Graph {
    private List nodeList; 
    private List edgeList;
    
    private class Edge {
        private GraphNode nodeA, nodeB;
        double weight;
        
        public Edge(GraphNode pNodeA, GraphNode pNodeB, double pWeight) {
            nodeA = pNodeA;
            nodeB = pNodeB;
            weight = pWeight;
        }
        
        public GraphNode getNodeA() {
            return nodeA;
        }
        
        public GraphNode getNodeB() {
            return nodeB;
        }
        
        public double getWeight() {
            return weight;
        }

        public void setWeight(double pWeight) {
            weight = pWeight;
        }
    }
    
    /**
     * Ein neuer Graph wird erzeugt. 
     * Er enthaelt noch keine Knoten.
     */
    public Graph() { 
        nodeList = new List ();
        edgeList = new List ();
    }
    
    /**
     * Die Anfrage liefert true, wenn der Graph keine Knoten
     * enthaelt, andernfalls liefert die Anfrage false.
     * @return true, falls leer, sonst false
     */
    public boolean isEmpty() {
        return nodeList.isEmpty(); 
    }

    /**
     * Der Knoten pNode wird dem Graphen hinzugefuegt. 
     * Falls bereits ein Knoten mit gleichem Namen im 
     * Graphen existiert, wird dieser Knoten nicht eingefuegt. 
     * Falls pNode null ist, veraendert sich der Graph nicht. 
     * @param pNode neuer Knoten
     */
    public void addNode(GraphNode pNode) {
        if (pNode != null && !this.hasNode(pNode.getName()))
            nodeList.append(pNode);
    }  

    private boolean hasNode(GraphNode pNode) {
        nodeList.toFirst();
        boolean lGefunden = false;
        while (nodeList.hasAccess() && !lGefunden) {
            if (pNode == nodeList.getObject()) {
                lGefunden = true;
            }
            nodeList.next();
        }
        return lGefunden;
    }
    
    /**
     * Die Anfrage liefert true, wenn ein Knoten mit dem Namen 
     * pName im Graphen existiert. 
     * Sonst wird false zurueck gegeben.
     * @param pName Knoten
     * @return true, falls es Knoten gibt, sonst false
     */
    public boolean hasNode(String pName) {
        return getNode(pName) != null;
    }

    /**
     * Die Anfrage liefert den Knoten mit dem Namen pName zurueck. 
     * Falls es keinen Knoten mit dem Namen im Graphen gibt, 
     * wird null zurueck gegeben.
     * @param pName Knotenbezeichnung
     * @return der gefundene Knoten oder null
     */    
    public GraphNode getNode(String pName) {
        GraphNode lNode0 = null;
        nodeList.toFirst();
        boolean lStop = false;
        while (nodeList.hasAccess() && !lStop) {
            GraphNode lNode = (GraphNode)nodeList.getObject();
            if (lNode.getName().equals(pName)) {
                lNode0 = lNode;
                lStop = true;
            }
            nodeList.next(); 
        }
        return lNode0;
    }

    /**
     * Falls pNode ein Knoten des Graphen ist, so werden er und alle 
     * mit ihm verbundenen Kanten aus dem Graphen entfernt. 
     * Sonst wird der Graph nicht veraendert.
     * @param pNode Knoten
     */   
    public void removeNode(GraphNode pNode) {
        if (pNode != null) {
            nodeList.toFirst();
            boolean lStop = false;
            while (nodeList.hasAccess() && !lStop) {
                GraphNode lNode = (GraphNode)nodeList.getObject();
                if (lNode == pNode) {
                    nodeList.remove();
                    lStop = true;
                }
                nodeList.next(); 
            }
            if (lStop) {
                edgeList.toFirst();
                while (edgeList.hasAccess()) {
                    Edge e = (Edge)edgeList.getObject();
                    GraphNode a = e.getNodeA();
                    GraphNode b = e.getNodeB();
                    if (pNode == a || pNode == b) {
                        edgeList.remove();
                    }
                    edgeList.next();
                }
            }
        }
    }

    private Edge getEdge(GraphNode pNode1, GraphNode pNode2) {
        Edge result = null;
        edgeList.toFirst();
        while(edgeList.hasAccess() && result == null) {
            Edge e = (Edge)edgeList.getObject();
            GraphNode a = e.getNodeA();
            GraphNode b = e.getNodeB();
            if ( (pNode1 == a && pNode2 == b) || (pNode1 == b && pNode2 == a) ) {
                result = e;
            }
            edgeList.next();
        }
        return result;                     
    }
    
    /**
     * Falls eine Kante zwischen pNode1 und pNode2 noch nicht existiert, 
     * werden die Knoten pNode1 und pNode2 durch eine Kante verbunden, 
     * die das Gewicht pWeight hat. pNode1 ist also Nachbarknoten 
     * von pNode2 und umgekehrt. Falls eine Kante zwischen pNode1 und pNode2 
     * bereits existiert, erhaelt sie das Gewicht pWeight. 
     * Falls einer der Knoten pNode1 oder pNode2 im Graphen nicht existiert oder null ist, 
     * veraendert sich der Graph nicht.
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @param pWeight Kantengewicht
     */
    public void addEdge(GraphNode pNode1, GraphNode pNode2, double pWeight) {        
        if (pNode1 != null && pNode2 != null && hasNode(pNode1) && hasNode(pNode2)) {
            Edge lEdge = getEdge(pNode1, pNode2);
            if (lEdge != null) {
                lEdge.setWeight(pWeight);
            } else {
                lEdge = new Edge(pNode1, pNode2, pWeight);
                edgeList.append(lEdge);
            }
        }  
    }

    /**
     * Die Anfrage liefert true, falls eine Kante zwischen pNode1 und
     * pNode2 existiert, sonst liefert die Anfrage false.
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @return true, falls Kante existiert, sonst false
     */    
    public boolean hasEdge(GraphNode pNode1, GraphNode pNode2) {
        if (pNode1 != null && pNode2 != null) {
            Edge result = getEdge(pNode1, pNode2);
            return result != null;
        } else {
            return false;
        }
    }

    /**
     * Die Anfrage liefert das Gewicht der Kante zwischen pNode1 und pNode2. 
     * Falls die Kante nicht existiert, wird Double.NaN (not a number) 
     * zurueck gegeben.
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @return Kantengewicht
     */    
    public double getEdgeWeight(GraphNode pNode1, GraphNode pNode2) { 
        Edge result = getEdge(pNode1, pNode2);
        if (result != null) {
            return result.getWeight();
        } else {
            return Double.NaN;
        }
    }    

    /**
     * Falls pNode1 und pNode2 nicht null sind und eine Kante zwischen 
     * pNode1 und pNode2 existiert, wird die Kante geloescht. Sonst 
     * bleibt der Graph unveraendert.
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     */
    public void removeEdge(GraphNode pNode1, GraphNode pNode2) { 
        if (pNode1!=null && pNode2!=null) {
            edgeList.toFirst();
            boolean lFound = false;
            while(edgeList.hasAccess() && !lFound) {
                Edge e = (Edge)edgeList.getObject();
                GraphNode a = e.getNodeA();
                GraphNode b = e.getNodeB();
                if ( (pNode1 == a && pNode2 == b) || (pNode1 == b && pNode2 == a) ) {
                    edgeList.remove();
                    lFound = true;
                }
                edgeList.next();
            }
        }
    }

    /**
     * Alle Knoten des Graphen werden als unmarkiert gekennzeichnet.
     */
    public void resetMarks() {
        if (!nodeList.isEmpty()) {
            nodeList.toFirst();
            while (nodeList.hasAccess()) {
                ((GraphNode) nodeList.getObject()).unmark();
                nodeList.next();          
            }
        }  
    }    

    /**
     * Die Anfrage liefert den Wert true, wenn alle Knoten des Graphen 
     * markiert sind, sonst liefert sie den Wert false.
     * @return true, falss alle Knoten markiert, sonst false
     */    
    public boolean allNodesMarked() {
        if (!nodeList.isEmpty()) {
            nodeList.toFirst();
            boolean lAllMarked=true;
            while (nodeList.hasAccess() && lAllMarked) {
                if (!((GraphNode)nodeList.getObject()).isMarked())
                    lAllMarked=false;
                nodeList.next();          
            }  
            return lAllMarked;
        }

        else return true;
    }
 
    /**
     * Die Anfrage liefert eine Liste, die alle Knoten des Graphen enthaelt.
     * @return Knotenliste
     */       
    public List getNodes() { // liefert Knoten als Kopie der Knotenliste
        List lList=new List();
        nodeList.toFirst();
        while (nodeList.hasAccess()) {
            GraphNode g=(GraphNode) nodeList.getObject();
            lList.append(g);
            nodeList.next();
        }
        return lList;
    }

    /**
     * Die Anfrage liefert eine Liste, die alle Nachbarknoten des 
     * Knotens pNode enthaelt.
     * @param pNode Knoten
     * @return Liste mit allen Nachbarknoten
     */    
    public List getNeighbours(GraphNode pNode) {
        List result = new List();
        edgeList.toFirst();
        while(edgeList.hasAccess()) {
            Edge e = (Edge)edgeList.getObject();
            GraphNode a = e.getNodeA();
            GraphNode b = e.getNodeB();
            if (pNode == a) {
                result.append(b);
            } else if (pNode == b) {
                result.append(a);
            }
            edgeList.next();
        }
        return result;
    }
}