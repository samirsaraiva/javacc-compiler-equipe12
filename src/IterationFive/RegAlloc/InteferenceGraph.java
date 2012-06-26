package IterationFive.RegAlloc;

import util.List;
import assem.Instr;
import assem.MOVE;
import FlowGraph.AssemFlowGraph;
import Graph.Graph;
import Graph.Node;
import Graph.NodeList;
import Temp.Temp;

public class InteferenceGraph extends Graph<Temp>{
	
	public int[][] moveRelated;
	public AssemFlowGraph fGraph;
	
	public InteferenceGraph(AssemFlowGraph fGraph) {
		this.fGraph = fGraph;
		Node<Temp> nodeA;
		Node<Temp> nodeB;
		for (NodeList<Node<Instr>> n = fGraph.nodes(); n != null; n=n.tail) {
			
			// percorre todos os temporarios (usados e definidos) para incluir
			// um noh no grafo de interferencia
			
			for(List<Temp> l = fGraph.use(n.head); l != null; l=l.tail){
				if((contains(l.head))==null){
					newNode(l.head);
				}
			}
			for(List<Temp> l = fGraph.def(n.head); l != null; l=l.tail){
				if((contains(l.head))==null){
					newNode(l.head);
				}
			}	
		}	
		
		moveRelated = new int[nodes().size()][nodes().size()];
		
		

		// incluindo arestas
		for (NodeList<Node<Instr>> n = fGraph.nodes(); n != null; n=n.tail) {
			// para cada instrucao move 	
			if(n.head.value instanceof MOVE){
				// seja "a" o temp definido no noh move
				for(List<Temp> a = fGraph.def(n.head); a != null; a=a.tail){
					nodeA = contains(a.head);
					nodeB = contains(fGraph.use(n.head).head); 
					addEdge(nodeA, nodeB);
					addEdge(nodeB, nodeA);
					// marca que par de arestas eh move
					moveRelated[nodeB.mykey][nodeA.mykey]=1;
					moveRelated[nodeA.mykey][nodeB.mykey]=1;
					// adionar (a, b), onde b pertence a liveOut e "b"!="c"
					for (Temp  b : fGraph.liveOut.get(n.head)) {
						if(b!=a.head){
							nodeB = contains(b);
							addEdge(nodeA, nodeB);
							addEdge(nodeB, nodeA);
							// marca que par de arestas eh nao move
							moveRelated[nodeB.mykey][nodeA.mykey]=0;
							moveRelated[nodeA.mykey][nodeB.mykey]=0;
						}
					}	
				}
			}
			
			// em qualquer noh nao move 
			else {
				// para a variavel "a" definida no noh
				for(List<Temp> a = fGraph.def(n.head); a != null; a=a.tail){
					// adionar (a, b), onde b pertence a liveOut	
					nodeA = contains(a.head);
					for (Temp  b : fGraph.liveOut.get(n.head)) {	
						if(b!=a.head){
							nodeB = contains(b);
							addEdge(nodeA, nodeB);
							addEdge(nodeB, nodeA);
							// marca que par de arestas eh nao move
							moveRelated[nodeB.mykey][nodeA.mykey]=0;
							moveRelated[nodeA.mykey][nodeB.mykey]=0;
						}
					}
				}
			} 
			

		}
			
	}
	 public void show() {
	 		for (NodeList<Node<Temp>> p=nodes(); p!=null; p=p.tail) {
	 		  Node<Temp> n = p.head;
	 		  System.out.print(n.value.toString());
	 		  System.out.print(": ");
	 		  for(NodeList<Node<Temp>> q=(NodeList<Node<Temp>>) n.succ(); q!=null; q=q.tail) {
	 		     if(moveRelated[n.mykey][q.head.mykey]==1)System.out.print(q.head.value+"(is move) ");
	 		     else System.out.print(q.head.value+"(is adj) ");
	 		     System.out.print(" ");
	 		  }
	 		 System.out.println();
	 		}
	      }
	
}
