package IterationThree.IntermediateCode.tree.stm;

import IterationThree.IntermediateCode.tree.ExpList;
import IterationThree.IntermediateCode.tree.exp.Exp;
import IterationThree.IntermediateCode.tree.exp.MEM;
import IterationFour.jouette.Codegen;

public class MOVE extends Stm {
  public Exp dst, src;
  public MOVE(Exp d, Exp s) {dst=d; src=s;}
  public ExpList kids() {
        if (dst instanceof MEM)
	   return new ExpList(((MEM)dst).exp, new ExpList(src,null));
	else return new ExpList(src,null);
  }
  public Stm build(ExpList kids) {
        if (dst instanceof MEM)
	   return new MOVE(new MEM(kids.head), kids.tail.head);
	else return new MOVE(dst, kids.head);
  }
	
}

