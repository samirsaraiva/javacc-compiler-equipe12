package IterationThree.IntermediateCode.tree;

import IterationThree.IntermediateCode.tree.exp.Exp;
import util.List;

public class ExpList extends List<Exp> {
	
	public Exp head;
	public ExpList tail;
	  
	public ExpList(Exp h, ExpList t) {
		super(h, t);
	}
  //public ExpList(Exp h, ExpList t) {head=h; tail=t;}
}



