package Graph;

import java.util.ArrayList;

public class NodeList<Node> {
  public Node head;
  public NodeList<Node> tail;
  public NodeList(Node h, NodeList<Node> t) {head=h; tail=t;}
  public int size()
  {
      if ( tail == null && head!=null)
          return 1;
      else if(head==null) return 0;
      return 1 + tail.size();
  }
  
  public NodeList<Node> remove(Node n){
	  ArrayList<Node> s = new ArrayList<Node>();
	  int i = 0;
	  for (NodeList<Node> li = this; li!=null; li=li.tail) {
		  if(li.head != n){
			  s.add(i,li.head);
			  i++;
		  }
	  }
	 return reconstruct(s,null);
  }
  
  public boolean contains(Node n){
	  for (NodeList<Node> li = this; li!=null; li=li.tail) {
		  if(li.head.equals(n)) return true;
	  }
	  return false;
  }
  
  public NodeList<Node> reconstruct(ArrayList<Node> s, NodeList<Node> l){
	  	if(!s.isEmpty()){
	  		l = new NodeList<Node>(s.remove(0),l);
	  		l.tail = reconstruct(s,l.tail);
	  	}	
		
		return l;
		
	}
  
  
  
}


