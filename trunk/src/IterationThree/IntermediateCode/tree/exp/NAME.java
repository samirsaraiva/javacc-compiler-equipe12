package IterationThree.IntermediateCode.tree.exp;
import IterationThree.IntermediateCode.tree.ExpList;
import Temp.Label;
public class NAME extends Exp {
  public Label label;
  public NAME(Label l) {label=l;}
  public ExpList kids() {return null;}
  public Exp build(ExpList kids) {return this;}
}

