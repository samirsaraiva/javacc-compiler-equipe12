package IterationFive.RegAlloc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Graph.Node;
import Graph.NodeList;
import Temp.Temp;

public class RegisterAllocation {
    public Stack<Node<Temp>> stack;
    public InteferenceGraph interferenceGraph;
    public ArrayList<Temp> registers;
    //usados na fase de select
    public HashMap<Temp, ArrayList<Node<Temp>>> succ;
    public HashMap<Temp, ArrayList<Temp>> coalesce;
    public HashMap<Temp, Temp> colors;
    public ArrayList<Node<Temp>> spilledNodes;
   
    
    public RegisterAllocation(ArrayList<Temp> registers, InteferenceGraph interInteferenceGraph) {
        stack = new Stack<Node<Temp>>();
        this.registers = registers;
        this.interferenceGraph = interInteferenceGraph;
        coalesce = new HashMap<Temp, ArrayList<Temp>>();
        succ = new HashMap<Temp, ArrayList<Node<Temp>>>();
        // grava a lista de adjacencia do grafo
        for (NodeList<Node<Temp>> li = interInteferenceGraph.nodes(); li!=null; li=li.tail) {
        	ArrayList<Node<Temp>> succs = new ArrayList<Node<Temp>>();
            for (NodeList<Node<Temp>> suc = li.head.succ(); suc!=null; suc=suc.tail) {
                succs.add(suc.head);
            }
            succ.put(li.head.value,succs);
        }
        
        colors = new HashMap<Temp, Temp>();
        spilledNodes = new ArrayList<Node<Temp>>();
    }
    
    public void simplify(){
    	
        //lista de nos do grafo de interferencia
        if(interferenceGraph.nodes()!=null && interferenceGraph.nodes().size()==1){
                stack.add(interferenceGraph.nodes().head);
                interferenceGraph.removeNode(interferenceGraph.nodes().head);
                return;
        }
        for (NodeList<Node<Temp>> li = interferenceGraph.nodes(); li!=null; li=li.tail) {
                //no de grau menor que o numero de registradores
                 if(!isSignificative(li.head) && !isMoveRelated(li.head)){
	                 //coloca o no na pilha
                	 System.out.println("SIMPLICA: "+li.head.value);
	                 stack.add(li.head);
	                 //remove o no do grafo de interferencia
	                 interferenceGraph.removeNode(li.head);
	                 for (NodeList<Node<Temp>> liNodes = interferenceGraph.nodes(); liNodes!=null; liNodes=liNodes.tail){
	                    interferenceGraph.moveRelated[li.head.mykey][liNodes.head.mykey] = 0;
	                    interferenceGraph.moveRelated[liNodes.head.mykey][li.head.mykey] = 0;
	                 }
	                 break;
                 }
        }
        
    }
    
    public int degree(Node<Temp> node){
        if(node.succs!=null) return node.succs.size();
        else return 0;
    }
    
    public boolean isMoveRelated(Node<Temp> node){
        if(node.succs==null) return false;
        for (NodeList<Node<Temp>> li = node.succ(); li!=null; li=li.tail) {
            if(interferenceGraph.moveRelated[li.head.mykey][node.mykey] == 1) return true;
        }
        return false;
    }
    
    public void spill(){
        //primeiro no da lista de nos do grafo sofre spill
    	for(NodeList<Node<Temp>> l = interferenceGraph.nodes(); l!=null; l=l.tail){
    		if(!isMoveRelated(l.head)){
    			stack.add(l.head);
    	        System.out.println("TRANSBORDAMENTO POTENCIAL: "+l.head.value);
    	        interferenceGraph.removeNode(l.head);
    	        return;
    		}
    	}
       
        
    }
    
    public void coalesce(){
        //verifica se existe algum no move related
        Node<Temp> node;
        for (NodeList<Node<Temp>> li = interferenceGraph.nodes(); li!=null; li=li.tail){
            //no e move related e pode ser aglutinado pelo criterio de briggs 
            if(checksNodesMoveRelated(li.head) && (node=briggs(li.head))!=null){
                //nos aglutinados viram nao move related
            	System.out.println("AGLUTINA: "+li.head.value+" COM "+node.value);
                interferenceGraph.moveRelated[li.head.mykey][node.mykey] = 0;
                interferenceGraph.moveRelated[node.mykey][li.head.mykey] = 0;
                
                //aglutina os dois nos (li.head com node)
                for (NodeList<Node<Temp>> liSuc = node.succ(); liSuc!=null; liSuc=liSuc.tail) {
                    if((!li.head.succ().contains(liSuc.head)|| 
                    	interferenceGraph.moveRelated[li.head.mykey][liSuc.head.mykey]==1)
                    	&& li.head!=liSuc.head){
                        interferenceGraph.addEdge(li.head, liSuc.head);
                        interferenceGraph.addEdge(liSuc.head,li.head);
                        succ.get(li.head.value).add(liSuc.head);
                        succ.get(liSuc.head.value).add(li.head);
                        interferenceGraph.moveRelated[li.head.mykey][liSuc.head.mykey] = 0;
                        interferenceGraph.moveRelated[liSuc.head.mykey][li.head.mykey] = 0;
                        
                    }
                    
                }
                for (NodeList<Node<Temp>> liNodes = interferenceGraph.nodes(); liNodes!=null; liNodes=liNodes.tail){
                        interferenceGraph.moveRelated[li.head.mykey][node.mykey] = 0;
                        interferenceGraph.moveRelated[node.mykey][li.head.mykey] = 0;
                }
                ArrayList<Temp> c;
                if((c=coalesce.get(li.head.value))==null){
                	c = new ArrayList<Temp>();
                	coalesce.put(li.head.value, c);
                }
                c.add(node.value);
                interferenceGraph.removeNode(node);     
                li = interferenceGraph.nodes();
            	}
            }
          
    }
    
    //retorna o no que pode ser aglutinado com "a" favorecendo o criterio de briggs
    public Node<Temp> briggs(Node<Temp> a){
    	
        for (NodeList<Node<Temp>> liSuc = a.succ(); liSuc!=null; liSuc=liSuc.tail) {
                if(interferenceGraph.moveRelated[a.mykey][liSuc.head.mykey] == 1){
                        if(canCoalescedNodes(a,liSuc.head)) return liSuc.head;
                }
        }
        return null;
    }
    
    //verifica se os nos podem ser aglutinados pelo criterio de briggs
    private boolean canCoalescedNodes(Node<Temp> a, Node<Temp> b) {
    	HashMap<Temp, ArrayList<Temp>> newAdj = new HashMap<Temp, ArrayList<Temp>>();
    	ArrayList<Temp> adj;
    	//contador de vizinhos de grau significativos de a e de b
        int count = 0;
        
        for (NodeList<Node<Temp>> liSuc = a.succ(); liSuc!=null; liSuc=liSuc.tail) {
        	if(interferenceGraph.moveRelated[a.mykey][liSuc.head.mykey]==0){
        		adj = new ArrayList<Temp>();
        		for (NodeList<Node<Temp>> liadj = liSuc.head.succ(); liadj!=null; liadj=liadj.tail) {
        			if(!liadj.head.value.equals(b.value) && 
        					interferenceGraph.moveRelated[liadj.head.mykey][liSuc.head.mykey]==0 )adj.add(liadj.head.value);
        		}
        		if(adj.size()>=registers.size())count++;
        		newAdj.put(liSuc.head.value,adj);
        	}
        }
        for (NodeList<Node<Temp>> liSuc = b.succ(); liSuc!=null; liSuc=liSuc.tail) {
             //caso o sucessor de b nao seja sucessor de a, seja de grau significativo, o contador e incrementado
        	if(interferenceGraph.moveRelated[b.mykey][liSuc.head.mykey]==0 ){
        		adj = new ArrayList<Temp>();
        		for (NodeList<Node<Temp>> liadj = liSuc.head.succ(); liadj!=null; liadj=liadj.tail) {
        			if(!liadj.head.value.equals(a.value) && 
        			interferenceGraph.moveRelated[liadj.head.mykey][liSuc.head.mykey]==0 ){
        				adj.add(liadj.head.value);
        			}
        		}
        		if(newAdj.get(liSuc.head.value)==null){
        			if(adj.size()>=registers.size())count++;
        			
        			newAdj.put(liSuc.head.value,adj);
        		}
        	}
        }
        if(count<registers.size()) return true;
        return false;
    }

    //verifica se um no e significativo
    private boolean isSignificative(Node<Temp> a) {
		int count = 0;
		//percorre os sucessores do no, incrementando o contador.
		//No caso onde e encontrado um sucessor que nao e move related, o contador e incrementado.
		for (NodeList<Node<Temp>> liSuc = a.succ(); liSuc!=null; liSuc=liSuc.tail) {
			if(interferenceGraph.moveRelated[a.mykey][liSuc.head.mykey]==0) count++;
		}
		if(count>=registers.size()) return true;
		return false;
    }

	//retorna um no que era move related de grau nao significativo, e agora e nao move related 
	public Node<Temp> freeze(){
		for (NodeList<Node<Temp>> li = interferenceGraph.nodes(); li!=null; li=li.tail){
			if(!isSignificative(li.head) && checksNodesMoveRelated(li.head)){
                 for (NodeList<Node<Temp>> liSuc = li.head.succ(); liSuc!=null; liSuc=liSuc.tail){
                     if(interferenceGraph.moveRelated[li.head.mykey][liSuc.head.mykey]==1){
	                     interferenceGraph.moveRelated[li.head.mykey][liSuc.head.mykey]=0;
	                     interferenceGraph.moveRelated[liSuc.head.mykey][li.head.mykey]=0;
	                     li.head.succs = li.head.succ().remove(liSuc.head);
	                     liSuc.head.succs = liSuc.head.succ().remove(li.head);
                     }
                 }
                System.out.println("FREEZE: "+li.head.value);
                 return li.head;
             }
        }
     	return null;
	}

    //verifica se existe algum no do grafo com grau significativo
    public boolean checksNodesSpill(){
        for (NodeList<Node<Temp>> li = interferenceGraph.nodes(); li!=null; li=li.tail){
                if(degree(li.head)< registers.size()) return false;
        }
        return true;
    }
    
    //verifica se existe algum no do grafo que e move related
    public boolean checksNodesMoveRelated(){
         for (NodeList<Node<Temp>> li = interferenceGraph.nodes(); li!=null; li=li.tail){
                         for (NodeList<Node<Temp>> liSuc = li.head.succ(); liSuc!=null; liSuc=liSuc.tail) {
                        if(interferenceGraph.moveRelated[li.head.mykey][liSuc.head.mykey]==1) return true;
                         }
         }
         return false;
    }
    
    public boolean checksNodesMoveRelated(Node<Temp> node){
          for (NodeList<Node<Temp>> liSuc = node.succ(); liSuc!=null; liSuc=liSuc.tail) {
                 if(interferenceGraph.moveRelated[node.mykey][liSuc.head.mykey]==1) return true;
          }
         return false;
   }
    
    //retira os nos da pilha e atribui registradores a eles
    public void select(){
        Node<Temp> n;
        while(!stack.isEmpty()){
        	Node<Temp> node = stack.pop();
        	//inclui um no no grafo que estava na pilha, recosntruindo o grafo
	        node = interferenceGraph.newNode(node.value);
	        ArrayList<Node<Temp>> s = succ.get(node.value);
	        if(s!=null){
	        	for (Node<Temp> suc: s) {
					//busca o no adjacente se ele esta no grafo
					n = returnNode(suc.value);
					if(suc!=null &&  n!=null){
						interferenceGraph.addEdge(n, node);
						interferenceGraph.addEdge(node, n);
                    }
                }
	        }
	       colorGraph(node);
        }
        if(!spilledNodes.isEmpty()){
        	System.out.println("TRANSBORDAMENTOS REAIS: "+spilledNodes);
        	startOver();
        }
            
    }

    private Node<Temp> returnNode(Temp value) {
    	for (NodeList<Node<Temp>> li = interferenceGraph.nodes(); li!=null; li=li.tail) {
    		if(li.head.value.equals(value)) return li.head;
    	}
    	return null;
	}

	//atribui registradores ao no
    public void colorGraph(Node<Temp> node) {
        ArrayList<Temp> c = new ArrayList<Temp>();
        c.addAll(registers);
        for (NodeList<Node<Temp>> li = node.succ(); li!=null; li=li.tail) {
            //remove da lista de cores, a cor atribuida ao sucessor do no
        	c.remove(colors.get(li.head.value));
        }
        for (Temp temp : c) {
            if(temp!=null) {
                color(node.value, temp);
                return;
            }
        }
        spilledNodes.add(node);
    }
    
    public void color(Temp node, Temp color){
    	colors.put(node, color);
    	ArrayList<Temp> a = coalesce.get(node);
    	if(a!=null)
			for (Temp temp : a) {
				color(temp, color);
			}
    }
    

    public void registersAlloc(){
    	Node<Temp> node;
    	while(hasNodeInsignificative())simplify();
    	
    	if(interferenceGraph.mynodes==null){
    		select();
    	}else{
    		if(hasMoveRelated()){
    			coalesce();
    			if(hasNodeInsignificative())registersAlloc();
    			else{
    				node = freeze();
    				if(node==null){
    					spill();
    				}
    				registersAlloc();    				
    			}
    		}else{
    			spill();
				registersAlloc();
    		}
    	}
    	
    }
    
    public boolean hasMoveRelated(){  
    	for (NodeList<Node<Temp>> lj = interferenceGraph.nodes(); lj!=null; lj=lj.tail) {
    		if(isMoveRelated(lj.head)) return true;
    	}
		return false;
    }
    
    public boolean hasNodeInsignificative(){    	
    	for (NodeList<Node<Temp>> lj = interferenceGraph.nodes(); lj!=null; lj=lj.tail) {
    		if(!isSignificative(lj.head) && !isMoveRelated(lj.head)) return true;
    	}
		return false;
    }
    
    public void startOver(){
         Rebuild.rebuild(this);
         System.out.println();
 		 System.out.println("****Novo Grafo de Interferencia****");
 		 interferenceGraph.show();
 		 System.out.println();
         registersAlloc();
    }
    
}
