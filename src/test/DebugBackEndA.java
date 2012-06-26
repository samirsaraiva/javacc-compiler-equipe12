package test;

import java.util.ArrayList;

import util.List;
import FlowGraph.AssemFlowGraph;
import IterationThree.IntermediateCode.tree.exp.BINOP;
import IterationThree.IntermediateCode.tree.exp.CONST;
import IterationThree.IntermediateCode.tree.exp.TEMP;
import IterationThree.IntermediateCode.tree.stm.CJUMP;
import IterationThree.IntermediateCode.tree.stm.LABEL;
import IterationThree.IntermediateCode.tree.stm.MOVE;
import IterationThree.IntermediateCode.tree.stm.Stm;
import IterationFour.jouette.Codegen;
import IterationFive.RegAlloc.InteferenceGraph;
import IterationFive.RegAlloc.RegisterAllocation;
import JouetteFrame.FrameImpl;
import Temp.Label;
import Temp.Temp;
import assem.Instr;

public class DebugBackEndA {
	/**
	 * Classe para teste de:
	 * - Geração de código a partir do código intermediário
	 * - Análise de Longevidade
	 * - Alocação de registradores 
	 * 
	 * O caso de teste nos permite abordar todas as possíveis etapas
	 * da alocação de registradoreas: simplify, spill, coalesce, freeze e starover
	 */
	public static void main(String[] args) {
		ArrayList<Temp> temps = new ArrayList<Temp>();
        temps.add(new Temp());
        temps.add(new Temp());
        temps.add(new Temp());

		Codegen codegen = new Codegen(new FrameImpl());
				List<Stm> instrs;
		//instrucoes da ir para serem traduzidas na codegen
		// tc := r3;
		Temp tc = new Temp();
		Temp r3 = new Temp();
		
		Stm i0 = new MOVE(new TEMP(tc), new TEMP(r3));
	
		// ta := r1;
		Temp ta = new Temp();
		Temp r1 = new Temp();
		
		Stm i1 = new MOVE(new TEMP(ta), new TEMP(r1));
		
		// tb := r2;
		Temp tb = new Temp();
		Temp r2 = new Temp();
		
		Stm i2 = new MOVE(new TEMP(tb), new TEMP(r2));
		
		// td := 0;
		Temp td = new Temp();
				
		Stm i3 = new MOVE(new TEMP(td), new CONST(0));
		
		// te := ta;
		Temp te = new Temp();
				
		Stm i4 = new MOVE(new TEMP(te), new TEMP(ta));
		
		// loop
		Label loop = new Label("loop");
		
		Stm i5 = new LABEL(loop);
		
		// td := td + tb;
		Stm i6 = new MOVE(new TEMP(td), new BINOP(BINOP.PLUS, new TEMP(td), new TEMP(tb)));
				
		// te := te - 1;
		Stm i7 = new MOVE(new TEMP(te), new BINOP(BINOP.MINUS, new TEMP(te), new CONST(1)));
		
		// if 0 < te goto loop 
		
		Label lfalse = new Label("false");
		Stm i8 = new CJUMP(CJUMP.LT, new CONST(0), new TEMP(te), loop, lfalse );
		
		// else goto false
		
		// false
		Stm i9 = new LABEL(lfalse);
		
		// r1 := td;
		Stm i10 = new MOVE(new TEMP(r1), new TEMP(td));
		
		// r3 := tc;
		Stm i11 = new MOVE(new TEMP(r3), new TEMP(tc));
		
		// done
		Label done = new Label("done");
		Stm i12 = new LABEL(done);
		
		instrs = new List<Stm>(i0, new List<Stm>(i1, new List<Stm>(i2, new List<Stm>(i3, new List<Stm>(i4,
				 new List<Stm>(i5, new List<Stm>(i6, new List<Stm>(i7, new List<Stm>(i8, new List<Stm>(i9, 
				 new List<Stm>(i10, new List<Stm>(i11, new List<Stm>(i12, null)))))))))))));
		
		// gerar instrucoes da maquina jouette a partir da lista de instrucoes
			
		try {
			ArrayList<Instr> i = codegen.codegen(instrs);
			
			List<Instr> l = null;
			System.out.println("***Geração de Código***");
			
			for (int j = i.size()-1; j >=0 ; j--) {
				l = new List<Instr>(i.get(j),l);
				
			}
			
			for (int j = 0; j < i.size() ; j++) {
				System.out.println(i.get(j).assem);
				
			}
			
			System.out.println();
			
		// criar grafo de controle de fluxo de dados
			
		AssemFlowGraph g = new AssemFlowGraph(new List<List<Instr>>(l, null));
		System.out.println();
		g.computeLiveness();
		System.out.println("***Grafo de controle de fluxo***");
		System.out.println();
		g.showIN_OUT();		
		System.out.println();
		InteferenceGraph ig = new InteferenceGraph(g);
		System.out.println();
		System.out.println("****Grafo de Interferencia****");
		ig.show();
		System.out.println();
		System.out.println("****Alocação de Registradores****");
		RegisterAllocation rg = new RegisterAllocation(temps,ig);
        rg.registersAlloc();
        System.out.println("Coloração:");
        System.out.println(rg.colors);
        
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
