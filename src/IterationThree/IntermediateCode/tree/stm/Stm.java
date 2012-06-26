package IterationThree.IntermediateCode.tree.stm;

import IterationThree.IntermediateCode.tree.ExpList;
import IterationFour.jouette.Codegen;

abstract public class Stm {
	abstract public ExpList kids();
	abstract public Stm build(ExpList kids);
}

