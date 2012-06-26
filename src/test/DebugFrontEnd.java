package test;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import frame.Frame;

import IterationThree.IntermediateCodeGeneration.translate.IRTranslateVisitor;
import JouetteFrame.FrameImpl;
import IterationTwo.syntaxtree.Program;
import IterationOne.parser.*;
import IterationTwo.SymbolTable.SymbolTable;
import IterationTwo.TypeChecking.CheckVisitor;
import IterationTwo.TypeChecking.TypeCheckVisitor;

public class DebugFrontEnd {
	
	/**
	 * Teste da fase de An�lise Sint�tica e Sem�ntica, at� a constru��o
	 * da Tabela de S�mbolos e Checagem de tipos. 
	 */
	public static void main(String args []) throws ParseException, IOException
    {

       System.out.print("Enter an archive path :");
       BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
       String filename = buf.readLine();

       System.out.println("Reading from file " + filename);
       try {
    	   Parser parser = new Parser(new java.io.FileInputStream(filename));    
    	   try {
    		   // An�lise Sint�tica e Sem�ntica
        	   Program p = Parser.program(); 
        	   // Verifica��o de Tipos
        	   CheckVisitor v = new TypeCheckVisitor();
        	   //constr�i tabela de simbolos e faz checagem de tipos
        	   SymbolTable table = v.visit(p);
        	   Frame f = new FrameImpl();
        	   
        	   // Tradu��o de C�digo Interm�di�rio
               // Obs: aqui seria a traducao para codigo jouette, erro pois nao iniciamos o acess das variaveis
        	   //
        	   IRTranslateVisitor irv = new IRTranslateVisitor(table, f);
        	   // gera��o de c�digo intermedi�rio 
        	   // irv.visit(p);
        	   // BackEnd
        	   // gera��o de �rvore can�nica
        	   // ArrayList<StmList> stms = new ArrayList<StmList>();
        	   // for (FragImpl frag : irv.frags) {
        	   // 	stms.add(Canon.linearize(frag.body));
        	   // }
        	   //
              
        	   
           } catch (IterationOne.parser.ParseException e) {
    			e.printStackTrace();
           }
       } catch (java.io.FileNotFoundException e){
    	   System.out.println("File " + filename + " not found.");
    	   return;
       }
       
       
       
    }
  
}
