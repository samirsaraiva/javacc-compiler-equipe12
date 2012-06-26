package IterationTwo.syntaxtree;

import java.util.ArrayList;

public class FormalList {
   private ArrayList<Formal> list;

   public FormalList() {
      list = new ArrayList<Formal>();
   }

   public void addElement(Formal n) {
      list.add(0, n);
   }

   public Formal elementAt(int i)  { 
      return list.get(i); 
   }

   public int size() { 
      return list.size(); 
   }
}

