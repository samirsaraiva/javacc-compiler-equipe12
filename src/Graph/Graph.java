package Graph;


public class Graph<E> {

  int nodecount=0;
  public NodeList<Node<E>> mynodes;
  NodeList<Node<E>> mylast;
  
  
  public NodeList<Node<E>> nodes() { return mynodes;} 

  public Node<E> newNode() {
	return new Node<E>(this);
  }
  
  public Node<E> newNode(E v) {
	return new Node<E>(this, v);
  }

  void check(Node<E> n) {
      if (n.mygraph != this)
	throw new Error("Graph.addEdge using nodes from the wrong graph");
  }
  
  
  static boolean inList(Node<?> a, NodeList<?> l) {
       for(NodeList<?> p=l; p!=null; p=p.tail)
             if (p.head==a) return true;
       return false;
  }

  public void addEdge(Node<E> from, Node<E> to) {
      check(from); check(to);
      if (from.goesTo(to)) return;
      to.preds= (NodeList<Node<E>>) new NodeList<Node<E>>(from, (NodeList<Node<E>>) to.preds);
      from.succs=(NodeList<Node<E>>) new NodeList<Node<E>>(to, (NodeList<Node<E>>) from.succs);
  }

  NodeList<Node<E>> delete(Node<E> a, NodeList<Node<E>> l) {
	if (l==null) throw new Error("Graph.rmEdge: edge nonexistent");
        else if (a==l.head) return l.tail;
	else return new NodeList<Node<E>>(l.head, delete(a, l.tail));
  }

  public void rmEdge(Node<E> from, Node<E> to) {
	  to.preds=delete(from,to.preds);
      from.succs=delete(to,from.succs);
  }
  
  public Node<E> contains(E v){
	  for (NodeList<Node<E>> li = mynodes; li!=null; li=li.tail) {
		  if(li.head.value.equals(v))return li.head;
	  }
	  
	  return null;
  }
  
  public void removeNode(Node<E> node){
	  //remove o no da lista de nos do grafo
	  mynodes = mynodes.remove(node);
	  mylast=null;
	  for (NodeList<Node<E>> li = mynodes; li!=null; li=li.tail) {
		mylast = li; 
		//Caso o no removido esteja na lista de sucessores dos outros nos, ele sera removido dessa lista.
		if(li.head.succs!=null&&li.head.succs.contains(node))li.head.succs = li.head.succ().remove(node);
		//Caso o no removido esteja na lista de predecessores dos outros nos, ele sera removido dessa lista.
		if(li.head.preds!=null&&li.head.preds.contains(node))li.head.preds = li.head.pred().remove(node);
	  }
  }

 /**
  * Print a human-readable dump for debugging.
  */
     public void show(java.io.PrintStream out) {
		for (NodeList<Node<E>> p=nodes(); p!=null; p=p.tail) {
		  Node<E> n = p.head;
		  out.print(n.value.toString());
		  out.print(": ");
		 /* for(NodeList<Node<E>> q=(NodeList<Node<E>>) n.succ(); q!=null; q=q.tail) {
		     out.print(q.head.value.toString());
		     out.print(" ");
		  }*/
		  out.println();
		}
     }
     public void show() {
 		for (NodeList<Node<E>> p=nodes(); p!=null; p=p.tail) {
 		  Node<E> n = p.head;
 		  System.out.print(n.value.toString());
 		  System.out.print(": ");
 		  for(NodeList<Node<E>> q=(NodeList<Node<E>>) n.succ(); q!=null; q=q.tail) {
 		     System.out.print(q.head.value.toString());
 		     System.out.print(" ");
 		  }
 		 System.out.println();
 		}
      }

}