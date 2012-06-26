package IterationTwo.syntaxtree;

import java.util.ArrayList;

public class ExpList {
   private ArrayList<Exp> list;

   public ExpList() {
      list = new ArrayList<Exp>();
   }

   public void addElement(Exp n) {
      list.add(0, n);
   }

   public Exp elementAt(int i)  { 
      return list.get(i); 
   }

   public int size() { 
      return list.size(); 
   }
}

