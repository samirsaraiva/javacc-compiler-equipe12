package IterationFive.RegAlloc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import util.List;
import FlowGraph.AssemFlowGraph;
import Graph.Node;
import Graph.NodeList;
import Temp.Temp;
import assem.Instr;
import assem.MOVE;
import assem.OPER;

public class Rebuild {
	public static void rebuild(RegisterAllocation r){
		AssemFlowGraph fgraph = r.interferenceGraph.fGraph;
		Stack<Instr> trace;
		List<Instr> rebuildedT=null;
		List<List<Instr>> rebuildedP = null;
		OPER load;
		OPER store;
		Instr instr;
		Temp reg = null;
		Node<Temp> n= r.spilledNodes.get(0);
				
		for(List<List<Instr>> t = fgraph.instrs; t!=null; t=t.tail){
			trace = new Stack<Instr>();
			
			for (List<Instr> i = t.head; i!=null ; i=i.tail) {
				reg = null;
				// verifica se n eh usado na instrucao
				for(List<Temp> use=i.head.use; use!=null; use = use.tail){
					if(n.value.equals(use.head)){
						reg = new Temp();
						load = new OPER("LOAD "+reg+" <- M[aloc"+n+" + 0]", new List<Temp>(reg,null), null, null, new Integer(0));
						// substitui o uso do noh por reg
						instr = replaceUSE(i.head, reg, n.value);
						//store = new OPER("STORE M[aloc"+n+"+ 0] <- "+reg, null, new List<Temp>(reg, null), null, new Integer(0));
						trace.push(load);
						trace.push(instr);
						//trace.push(store);
						
					}
					
				}
				// se n nao eh usado
				if(reg == null)
				// verifica se n eh definido na instrucao
				for(List<Temp> def=i.head.def; def!=null; def = def.tail){
					if(n.value.equals(def.head)){
						reg = new Temp();
						instr = replaceDEF(i.head, reg, n.value);
						store = new OPER("STORE M[aloc"+n+"+ 0] <-"+reg, null, new List<Temp>(reg,null), null, new Integer(0));
						trace.push(instr);
						trace.push(store);
					}
				}
				if(reg == null)trace.push(i.head);
					
			}	
			rebuildedP=null;
			while(!trace.isEmpty()){
				rebuildedT = new List<Instr>(trace.pop(),rebuildedT);
			}
			if(rebuildedT!=null)rebuildedP = new List<List<Instr>>(rebuildedT,rebuildedP);
			
			
			
		}
		System.out.println();
		System.out.println("**** CÓDIGO RECONSTRUÍDO APÓS TRANSBORDAMENTO ****");
		for(List<List<Instr>> t = rebuildedP; t!=null; t=t.tail){
			for (List<Instr> i = t.head; i!=null ; i=i.tail) {
				System.out.println(i.head.assem);
			}
		}
		System.out.println();
		AssemFlowGraph g = new AssemFlowGraph(rebuildedP);		
		g.computeLiveness();
		r.interferenceGraph = new InteferenceGraph(g);
		r.coalesce = new HashMap<Temp, ArrayList<Temp>>();
		r.colors = new HashMap<Temp, Temp>();
		r.stack = new Stack<Node<Temp>>();
		r.succ = new HashMap<Temp, ArrayList<Node<Temp>>>();
        // grava a lista de adjacencia do grafo
        for (NodeList<Node<Temp>> li = r.interferenceGraph.nodes(); li!=null; li=li.tail) {
        	ArrayList<Node<Temp>> succs = new ArrayList<Node<Temp>>();
            for (NodeList<Node<Temp>> suc = li.head.succ(); suc!=null; suc=suc.tail) {
                succs.add(suc.head);
            }
            r.succ.put(li.head.value,succs);
        }
	        
		
		r.colors = new HashMap<Temp, Temp>();
		r.spilledNodes = new ArrayList<Node<Temp>>();
	}
	
	private static Instr replaceDEF(Instr i, Temp newReg, Temp oldReg) {
		Instr ret = null;
		String assem = i.assem.replaceAll(oldReg.toString(), newReg.toString());
		if(i instanceof OPER)ret = new OPER(assem, new List<Temp>(newReg, null), i.use, i.jumps, i.cons);
		else ret = new MOVE(assem, newReg, i.use.head);
		
		return ret;
	}

	public static Instr replaceUSE(Instr i, Temp newReg, Temp oldReg){
		Stack<Temp> use = new Stack<Temp>();
		List<Temp> useRet= null;
		Instr ret = null;
		for(List<Temp> t=i.use; t!=null; t=t.tail){
			if(t.head.equals(oldReg)){
				use.push(newReg);
			}else use.push(t.head);
		}
		
		while(!use.isEmpty())useRet = new List<Temp>(use.pop(),useRet);
		String assem = i.assem.replaceAll(oldReg.toString(), newReg.toString());
		if(i instanceof OPER)ret = new OPER(assem, i.def, useRet, i.jumps, i.cons);
		else ret = new MOVE(assem, i.def.head, useRet.head);
		
		return ret;
	}
}
