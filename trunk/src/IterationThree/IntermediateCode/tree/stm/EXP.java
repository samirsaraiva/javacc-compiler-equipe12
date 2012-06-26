package IterationThree.IntermediateCode.tree.stm;

import IterationThree.IntermediateCode.tree.ExpList;
import IterationThree.IntermediateCode.tree.exp.Exp;
import IterationFour.jouette.Codegen;

public class EXP extends Stm {
  public Exp exp; 
  public EXP(Exp e) {exp=e;}
  public ExpList kids() {return new ExpList(exp,null);}
  public Stm build(ExpList kids) {
    return new EXP(kids.head);
  }

}

