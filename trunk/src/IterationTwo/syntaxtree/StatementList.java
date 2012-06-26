package IterationTwo.syntaxtree;

import java.util.ArrayList;

public class StatementList {
   private ArrayList<Statement> list;

   public StatementList() {
      list = new ArrayList<Statement>();
   }

   public void addElement(Statement n) {
      list.add(0, n);
   }

   public Statement elementAt(int i)  { 
      return list.get(i); 
   }

   public int size() { 
      return list.size(); 
   }
}

