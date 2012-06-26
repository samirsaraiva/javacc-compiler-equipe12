package IterationFour.Canon;

import IterationThree.IntermediateCode.tree.stm.*;
import Temp.Label;

public class BasicBlocks {
  public StmListList blocks;
  public Label done;

  private StmListList lastBlock;
  private StmList lastStm;

  private void addStm(Stm s) {
	lastStm = lastStm.tail = new IterationThree.IntermediateCode.tree.stm.StmList(s,null);
  }

  private void doStms(IterationThree.IntermediateCode.tree.stm.StmList l) {
      if (l==null) 
	doStms(new IterationThree.IntermediateCode.tree.stm.StmList(new IterationThree.IntermediateCode.tree.stm.JUMP(done), null));
      else if (l.head instanceof JUMP 
	      || l.head instanceof CJUMP) {
	addStm(l.head);
	mkBlocks(l.tail);
      } 
      else if (l.head instanceof LABEL)
           doStms(new StmList(new JUMP(((LABEL)l.head).label), 
	  			   l));
      else {
	addStm(l.head);
	doStms(l.tail);
      }
  }

  void mkBlocks(StmList l) {
     if (l==null) return;
     else if (l.head instanceof LABEL) {
	lastStm = new StmList(l.head,null);
        if (lastBlock==null)
  	   lastBlock= blocks= new StmListList(lastStm,null);
        else
  	   lastBlock = lastBlock.tail = new StmListList(lastStm,null);
	doStms(l.tail);
     }
     else mkBlocks(new StmList(new LABEL(new Label()), l));
  }
   

  public BasicBlocks(StmList stms) {
    done = new Label();
    mkBlocks(stms);
  }
}
