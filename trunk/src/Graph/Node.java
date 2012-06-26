package Graph;

public class Node<E> {

	public E value;
    public Graph<E> mygraph;
    //private Node<E>(){}
    public int mykey;
    public Node(Graph<E> g) {
		mygraph=g; 
		mykey= g.nodecount++;
		NodeList<Node<E>> p = new NodeList<Node<E>>(this, null);
		if (g.mylast==null)
		   g.mynodes=g.mylast=p;
		else g.mylast = g.mylast.tail = p;
    }
    
    public Node(Graph<E> g, E v) {
    	value = v;
		mygraph=g; 
		mykey=g.nodecount++;
		NodeList<Node<E>> p = new NodeList<Node<E>>(this, null);
		if (g.mylast==null)
		   g.mynodes=g.mylast=p;
		else {
			g.mylast.tail = p;
			g.mylast = p;
		}
    }

    public NodeList<Node<E>> succs;
    public NodeList<Node<E>> preds;
    public NodeList<Node<E>> succ() {return succs;}
    public NodeList<Node<E>> pred() {return preds;}
      NodeList<Node<E>> cat(NodeList<Node<E>> a, NodeList<Node<E>> b) {
          if (a==null) return b;
	  else return new NodeList<Node<E>>(a.head, cat(a.tail,b));
    }
    public NodeList<Node<E>> adj() {return cat(succ(), pred());}

    int len(NodeList<Node<E>> l) {
	int i=0;
	for(NodeList<Node<E>> p=l; p!=null; p=p.tail) i++;
	return i;
    }

    public int inDegree() {return len(pred());}
    public int outDegree() {return len(succ());}
    public int degree() {return inDegree()+outDegree();} 

    public boolean goesTo(Node<E> n) {
	return Graph.inList(n, succ());
    }

    public boolean comesFrom(Node<E> n) {
	return Graph.inList(n, pred());
    }

    public boolean adj(Node<E> n) {
	return goesTo(n) || comesFrom(n);
    }

    public String toString() {
    	//return String.valueOf(mykey);
    	return value.toString();
    }
    
    public boolean equals(Node<E> node){
    	if(value==node.value) return true;
    	return false;
    }
    
}