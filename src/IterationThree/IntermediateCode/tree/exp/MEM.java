package IterationThree.IntermediateCode.tree.exp;

import IterationThree.IntermediateCode.tree.ExpList;

public class MEM extends Exp {
  public Exp exp;
  public MEM(Exp e) {exp=e;}
  public ExpList kids() {return new ExpList(exp,null);}
  public Exp build(ExpList kids) {
    return new MEM(kids.head);
  }
}

