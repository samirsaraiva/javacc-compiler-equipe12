package IterationTwo.syntaxtree;

import java.util.ArrayList;

public class ClassDeclList {
   private ArrayList<ClassDecl> list;

   public ClassDeclList() {
      list = new ArrayList<ClassDecl>();
   }

   public void addElement(ClassDecl n) {
      list.add(0, n);
   }

   public ClassDecl elementAt(int i)  { 
      return list.get(i); 
   }

   public int size() { 
      return list.size(); 
   }
}

