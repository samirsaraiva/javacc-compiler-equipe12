package FlowGraph;

import java.util.ArrayList;
import java.util.HashMap;
import assem.Instr;
import assem.LABEL;
import assem.MOVE;
import util.List;
import Graph.Node;
import Graph.NodeList;
import Temp.Label;
import Temp.Temp;

public class AssemFlowGraph extends FlowGraph<Instr>{

	public HashMap<Node<Instr>, ArrayList<Temp>> liveIn;
	public HashMap<Node<Instr>, ArrayList<Temp>> liveOut;
	public List<List<Instr>> instrs;
	
	public AssemFlowGraph(List<List<Instr>> blocks) {
		super();
		instrs = blocks;
		liveIn = new HashMap<Node<Instr>, ArrayList<Temp>>();
		liveOut = new HashMap<Node<Instr>, ArrayList<Temp>>();
		
		// para cada bloco basico
		for (List<List<Instr>> lb = blocks; lb!=null; lb=lb.tail){
			
			// inicia com primeira instrucao do bloco basico
			Node<Instr> from;
			if((from=contains(lb.head.head))==null)from = newNode(lb.head.head);
			Node<Instr> to;
			
			for (List<Instr> l = lb.head.tail; l!=null; l=l.tail) {
				// insere no com instrucao no grafo, caso nao exista
				if((to=contains(l.head))==null){
					to = newNode(l.head);
				}
							
				addEdge(from, to);
								
				// jumps ou cjumps
				if(to.value.jumps!=null){
					addEdgeJumps(to, to.value.jumps.head, blocks);
				}
				from = to;
			}
		}
	}
	
	public void computeLiveness(){
		ArrayList<Temp> inLine;
		ArrayList<Temp> outLine;
		HashMap<Node<Instr>, ArrayList<Temp>> liveInLine = new HashMap<Node<Instr>, ArrayList<Temp>>();
		HashMap<Node<Instr>, ArrayList<Temp>> liveOutLine = new HashMap<Node<Instr>, ArrayList<Temp>>();
		NodeList<Node<Instr>> nl;
		
		for(nl = mynodes; nl!=null; nl = nl.tail){
			inLine = new ArrayList<Temp>();
			outLine = new ArrayList<Temp>();
			liveInLine.put(nl.head, inLine);
			liveOutLine.put(nl.head, outLine);
			liveIn.put(nl.head, inLine);
			liveOut.put(nl.head, outLine);
		}
		
		do{
		
			for(nl = mynodes; nl!=null; nl = nl.tail){
				
				//remove todos os elementos de in'
				liveInLine.remove(nl.head);
				//insere os elementos de in em in'
				liveInLine.put(nl.head, liveIn.get(nl.head));				
				
				
				//remove todos os elementos de out'
				liveOutLine.remove(nl.head);
				//insere os elementos de out em out'
				liveOutLine.put(nl.head, liveOut.get(nl.head));
				
				
				//remove os elementos de in
				liveIn.remove(nl.head);
				
				//in[n] = use[n] U (out[n]-def[n])
				buildLiveIn(nl.head);
				
				//remove os elementos de out
				liveOut.remove(nl.head);
				
				//out[n]= Us in succ[n] in[s]
				buildLiveOut(nl.head);
		
			}
		}while(!compareInOut(liveInLine, liveOutLine));
	}
	
	
	private void buildLiveOut(Node<Instr> node) {
		//succ[n]
		liveOut.put(node, new ArrayList<Temp>());
		for (NodeList<Node<Instr>> suc = node.succ(); suc!=null; suc = suc.tail) {
			//para cada s de succ[n], out[n] = in[s]
			for (int i = liveIn.get(suc.head).size()-1; i >=0; i--) {
				liveOut.get(node).add(liveIn.get(suc.head).get(i));
			}
		}
	}

	//in[n] = use[n] U (out[n]-def[n])
	private void buildLiveIn(Node<Instr> node) {
		
		liveIn.put(node, new ArrayList<Temp>());
		for(List<Temp> listTemp = use(node); listTemp!=null; listTemp = listTemp.tail){
			liveIn.get(node).add(listTemp.head);
		}
		for(int i= liveOut.get(node).size()-1;i>=0;i--){
			Temp t = liveOut.get(node).get(i);
			if(!isDef(t, node)&& !liveIn.get(node).contains(t)) liveIn.get(node).add(t);
		}
	}

	//verifica se um temp de entrada que esta em out[node] pertence a def[node]
	private boolean isDef(Temp temp, Node<Instr> node) {
		for (List<Temp> def = def(node); def!=null; def = def.tail) {
			if(def.head.equals(temp)) return true;
		}
		return false;
	}

	//verifica se in'[n]=in[n] e out'[n]=out[n] para todo n
	private boolean compareInOut(HashMap<Node<Instr>, ArrayList<Temp>> liveInLine,
			HashMap<Node<Instr>, ArrayList<Temp>> liveOutLine) {
		
		for(NodeList<Node<Instr>> nl = mynodes; nl!=null; nl = nl.tail){
			for (int i = 0; i < liveIn.get(nl.head).size(); i++) {
				if(!liveInLine.get(nl.head).contains(liveIn.get(nl.head).get(i))) return false;
			}
			for (int i = 0; i < liveOut.get(nl.head).size(); i++) {
				if(!liveOutLine.get(nl.head).contains(liveOut.get(nl.head).get(i))) return false;
			}
			for (int i = 0; i < liveInLine.get(nl.head).size(); i++) {
				if(!liveIn.get(nl.head).contains(liveInLine.get(nl.head).get(i))) return false;
			}
			for (int i = 0; i < liveOutLine.get(nl.head).size(); i++) {
				if(!liveOut.get(nl.head).contains(liveOutLine.get(nl.head).get(i))) return false;
			}
		}
		return true;
	}

	private void addEdgeJumps(Node<Instr> from, Label label, List<List<Instr>> blocks){
		Node<Instr> n = null;
		
		// verifica se aresta ja existe
		for(NodeList<Node<Instr>> nl = from.adj(); nl!=null; nl=nl.tail){
			if(nl.head.value instanceof LABEL && 
					((LABEL)nl.head.value).label.toString().equals(label.toString())){
				return;
			}
		}
		
		// verifica se o no referente a label ja existe no grafo e a tribui a n caso exista
		for(NodeList<Node<Instr>> nl = mynodes; nl!=null; nl=nl.tail){
			if(nl.head.value instanceof LABEL && 
					((LABEL)nl.head.value).label.toString().equals(label.toString())){
				n = nl.head;
			}
		}
		
		// caso o no nao exista, cria o no
		if(n==null){
			for (List<List<Instr>> lb = blocks; lb!=null; lb=lb.tail){
				for (List<Instr> lt = lb.head; lt!=null; lt=lt.tail) {
					if(lt.head instanceof LABEL && 
							((LABEL)lt.head).label.toString().equals(label.toString())){
						System.out.println("achou");
						n = newNode(lt.head);
						addEdge(from, n);
						return;
					}
				}
			}
		}
		addEdge(from, n);	
	}
	
	@Override
	public List<Temp> def(Node<Instr> node) {
		return node.value.def;
	}

	@Override
	public boolean isMove(Node<Instr> node) {
		return (node.value instanceof MOVE); 
	}

	@Override
	public List<Temp> use(Node<Instr> node) {
		return node.value.use;
	}
	
	public void showIN_OUT(){
		for(NodeList<Node<Instr>> nl = mynodes; nl!= null; nl = nl.tail){
			System.out.println("No: "+nl.head.value.assem);
			
			System.out.println("LiveIn:");
			for (Temp t : liveIn.get(nl.head)) {
				System.out.print(t+" ");
			}
			System.out.println();
			System.out.println("LiveOut:");
			for (Temp t : liveOut.get(nl.head)) {
				System.out.print(t+" ");
			}
			System.out.println();
			System.out.println();
		}
	}

}
