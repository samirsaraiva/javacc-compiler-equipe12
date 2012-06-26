package IterationThree.IntermediateCode.tree.stm;

import IterationThree.IntermediateCode.tree.ExpList;
import IterationThree.IntermediateCode.tree.exp.Exp;
import IterationThree.IntermediateCode.tree.exp.NAME;
import IterationFour.jouette.Codegen;
import Temp.Label;
import Temp.LabelList;


public class JUMP extends Stm {
  public Exp exp;
  public LabelList targets;
  public JUMP(Exp e, LabelList t) {exp=e; targets=t;}
  public JUMP(Label target) {
      this(new NAME(target), new LabelList(target,null));
  }
  public ExpList kids() {return new ExpList(exp,null);}
  public Stm build(ExpList kids) {
    return new JUMP(kids.head,targets);
  }
}

