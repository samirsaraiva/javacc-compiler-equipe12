package IterationThree.IntermediateCodeGeneration.translate;

import IterationThree.IntermediateCode.tree.stm.Stm;
import frame.Frame;


public class FragImpl extends Frag{
	public Frame frame;
	public Stm body;

	public FragImpl(Frame currentFrame, Stm body) {
		frame = currentFrame;
		this.body = body;
	}

	
}
