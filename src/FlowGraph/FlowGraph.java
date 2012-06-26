package FlowGraph;
import util.List;
import Temp.Temp;
import Graph.Graph;
import Graph.NodeList;
import Graph.Node;

/**
 * A control flow graph is a directed graph in which each edge
 * indicates a possible flow of control.  Also, each node in
 * the graph defines a set of temporaries; each node uses a set of
 * temporaries; and each node is, or is not, a <strong>move</strong>
 * instruction.
 * @param <E>
 *
 * @see AssemFlowGraph
 */

public abstract class FlowGraph<E> extends Graph<E> {
 /**
  * The set of temporaries defined by this instruction or block 
  */
	public abstract List<Temp> def(Node<E> node);

 /**
  * The set of temporaries used by this instruction or block 
  */
	public abstract List<Temp> use(Node<E> node);

 /**
  * True if this node represents a <strong>move</strong> instruction,
  * i.e. one that can be deleted if def=use. 
  */
	public abstract boolean isMove(Node<E> node);

 /**
  * Print a human-readable dump for debugging.
  */
     public void show(java.io.PrintStream out) {
	for (NodeList<Node<E>> p=nodes(); p!=null; p=p.tail) {
	  Node<E> n = p.head;
	  out.print(n.toString());
	  out.print(": ");
	  for(List<Temp> q=def(n); q!=null; q=q.tail) {
	     out.print(q.head.toString());
	     out.print(" ");
	  }
	  out.print(isMove(n) ? "<= " : "<- ");
	  for(List<Temp> q=use(n); q!=null; q=q.tail) {
	     out.print(q.head.toString());
	     out.print(" ");
	  }
	  out.print("; goto ");
	  for(NodeList<Node<E>> q=n.succ(); q!=null; q=q.tail) {
	     out.print(q.head.toString());
	     out.print(" ");
	  }
	  out.println();
	}
     }

}