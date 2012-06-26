/* Generated By:JavaCC: Do not edit this line. Parser.java */
package IterationOne.parser;
import IterationTwo.syntaxtree.*;

public class Parser implements ParserConstants {

  static final public int one_line() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CLASS:
      program();
    {if (true) return 0;}
      break;
    default:
      jj_la1[0] = jj_gen;
    {if (true) return 1;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Program program() throws ParseException {
 MainClass mc; ClassDeclList cdl; Program p;
    mc = main_class();
    cdl = class_decl_();
    jj_consume_token(0);
  p = new Program(mc,cdl);{if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  static final public MainClass main_class() throws ParseException {
 MainClass mc; Token i1; Token i2; Statement st;
    jj_consume_token(CLASS);
    i1 = jj_consume_token(ID);
    jj_consume_token(LCURLYBRACE);
    jj_consume_token(PUBLIC);
    jj_consume_token(STATIC);
    jj_consume_token(VOID);
    jj_consume_token(MAIN);
    jj_consume_token(LBRACE);
    jj_consume_token(STRING);
    jj_consume_token(ARRAY_MODIFIER);
    i2 = jj_consume_token(ID);
    jj_consume_token(RBRACE);
    jj_consume_token(LCURLYBRACE);
    st = statement();
    jj_consume_token(RCURLYBRACE);
    jj_consume_token(RCURLYBRACE);
                                 mc = new MainClass(new Identifier(i1.toString()), new Identifier(i2.toString()), st); {if (true) return mc;}
    throw new Error("Missing return statement in function");
  }

  static final public Type type() throws ParseException {
 Token s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN_TYPE:
      jj_consume_token(BOOLEAN_TYPE);
                                                   {if (true) return new BooleanType();}
      break;
    case ID:
      s = jj_consume_token(ID);
              {if (true) return new IdentifierType(s.toString());}
      break;
    case INT_ARRAY:
      jj_consume_token(INT_ARRAY);
                  {if (true) return new IntArrayType();}
      break;
    case INT:
      jj_consume_token(INT);
            {if (true) return new IntegerType();}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ClassDeclList class_decl_() throws ParseException {
 ClassDecl cd; ClassDeclList cdl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CLASS:
      cd = class_decl();
      cdl = class_decl_();
  cdl.addElement(cd);{if (true) return cdl;}
      break;
    default:
      jj_la1[2] = jj_gen;
  {if (true) return new ClassDeclList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public ClassDecl class_decl() throws ParseException {
 Token i; ClassDecl cd;
    jj_consume_token(CLASS);
    i = jj_consume_token(ID);
    cd = class_id(new Identifier(i.toString()));
   {if (true) return cd;}
    throw new Error("Missing return statement in function");
  }

  static final public ClassDecl class_id(Identifier id) throws ParseException {
 Token i;VarDeclList vdl; MethodDeclList mdl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LCURLYBRACE:
      jj_consume_token(LCURLYBRACE);
      vdl = var_decl_();
      mdl = method_decl_();
      jj_consume_token(RCURLYBRACE);
   {if (true) return new ClassDeclSimple(id,vdl,mdl);}
      break;
    case EXTENDS:
      jj_consume_token(EXTENDS);
      i = jj_consume_token(ID);
      jj_consume_token(LCURLYBRACE);
      vdl = var_decl_();
      mdl = method_decl_();
      jj_consume_token(RCURLYBRACE);
                                                                                   {if (true) return new ClassDeclExtends(id,new Identifier(i.toString()),vdl,mdl);}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public VarDecl var_decl() throws ParseException {
 Type t; Token i;
    t = type();
    i = jj_consume_token(ID);
    jj_consume_token(SEMI_COLON);
   {if (true) return new VarDecl(t,new Identifier(i.toString()));}
    throw new Error("Missing return statement in function");
  }

  static final public VarDeclList var_decl_() throws ParseException {
 VarDecl vd; VarDeclList vdl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN_TYPE:
    case INT_ARRAY:
    case INT:
    case ID:
      vd = var_decl();
      vdl = var_decl_();
                                                 vdl.addElement(vd);{if (true) return vdl;}
      break;
    default:
      jj_la1[4] = jj_gen;
           {if (true) return new VarDeclList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public MethodDecl method_decl() throws ParseException {
 VarDeclList vdl= new VarDeclList();Token i;
StatementList sl;Type t; FormalList fl;Exp exp;
    jj_consume_token(PUBLIC);
    t = type();
    i = jj_consume_token(ID);
    jj_consume_token(LBRACE);
    fl = formal_list();
    jj_consume_token(RBRACE);
    jj_consume_token(LCURLYBRACE);
    sl = var_statement_(vdl);
    jj_consume_token(RETURN);
    exp = exp();
    jj_consume_token(SEMI_COLON);
    jj_consume_token(RCURLYBRACE);
         {if (true) return new MethodDecl(t, new Identifier(i.toString()), fl,vdl,sl,exp);}
    throw new Error("Missing return statement in function");
  }

  static final public StatementList var_statement_(VarDeclList vdl) throws ParseException {
 Type t;StatementList sl;Statement s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN_TYPE:
    case INT_ARRAY:
    case INT:
    case ID:
      t = type();
      sl = a(t,vdl);
   {if (true) return sl;}
      break;
    case IF:
    case WHILE:
    case PRINT:
    case LCURLYBRACE:
      s = statement_two();
      sl = statement_();
                                        sl.addElement(s);{if (true) return sl;}
      break;
    default:
      jj_la1[5] = jj_gen;
   {if (true) return new StatementList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public StatementList a(Type type,VarDeclList vdl) throws ParseException {
 Token i; Exp exp; Exp exp2; VarDecl vd;StatementList sl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      i = jj_consume_token(ID);
      jj_consume_token(SEMI_COLON);
                           vd = new VarDecl(type, new Identifier(i.toString()));
      sl = var_statement_(vdl);
    vdl.addElement(vd);{if (true) return sl;}
      break;
    case EQUALS:
      jj_consume_token(EQUALS);
      exp = exp();
      jj_consume_token(SEMI_COLON);
      sl = statement_();
   sl.addElement(new Assign( new Identifier(((IdentifierType)type).s),exp));{if (true) return sl;}
      break;
    case LSQUAREBRACKET:
      jj_consume_token(LSQUAREBRACKET);
      exp = exp();
      jj_consume_token(RSQUAREBRACKET);
      jj_consume_token(EQUALS);
      exp2 = exp();
      jj_consume_token(SEMI_COLON);
      sl = statement_();
   sl.addElement(new ArrayAssign(new Identifier(((IdentifierType)type).s), exp, exp2));{if (true) return sl;}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public MethodDeclList method_decl_() throws ParseException {
 MethodDecl md; MethodDeclList mdl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PUBLIC:
      md = method_decl();
      mdl = method_decl_();
          mdl.addElement(md); {if (true) return mdl;}
      break;
    default:
      jj_la1[7] = jj_gen;
                {if (true) return new MethodDeclList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public FormalList formal_list() throws ParseException {
 FormalList fl; Token i; Type t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN_TYPE:
    case INT_ARRAY:
    case INT:
    case ID:
      t = type();
      i = jj_consume_token(ID);
      fl = formal_rest_();
  fl.addElement(new Formal(t, new Identifier(i.toString())));{if (true) return fl;}
      break;
    default:
      jj_la1[8] = jj_gen;
  {if (true) return new FormalList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public FormalList formal_rest_() throws ParseException {
 FormalList fl; Formal f;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      f = formal_rest();
      fl = formal_rest_();
          fl.addElement(f);{if (true) return fl;}
      break;
    default:
      jj_la1[9] = jj_gen;
                {if (true) return new FormalList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Formal formal_rest() throws ParseException {
 Type t; Token i;
    jj_consume_token(COMMA);
    t = type();
    i = jj_consume_token(ID);
   {if (true) return new Formal(t, new Identifier(i.toString()));}
    throw new Error("Missing return statement in function");
  }

  static final public Statement statement() throws ParseException {
 Statement s; Statement s2; StatementList sl; Exp exp;Token i;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LCURLYBRACE:
      jj_consume_token(LCURLYBRACE);
      sl = statement_();
      jj_consume_token(RCURLYBRACE);
   {if (true) return new Block(sl);}
      break;
    case IF:
      jj_consume_token(IF);
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
      s = statement();
      jj_consume_token(ELSE);
      s2 = statement();
   {if (true) return new If(exp, s, s2);}
      break;
    case WHILE:
      jj_consume_token(WHILE);
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
      s = statement();
   {if (true) return new While(exp, s);}
      break;
    case PRINT:
      jj_consume_token(PRINT);
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
      jj_consume_token(SEMI_COLON);
   {if (true) return new Print(exp);}
      break;
    case ID:
      i = jj_consume_token(ID);
      s = statement_factor(new Identifier(i.toString()));
    {if (true) return s;}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public StatementList statement_() throws ParseException {
 Statement s; StatementList sl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
    case WHILE:
    case PRINT:
    case LCURLYBRACE:
    case ID:
      s = statement();
      sl = statement_();
   sl.addElement(s); {if (true) return sl;}
      break;
    default:
      jj_la1[11] = jj_gen;
        {if (true) return new StatementList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Statement statement_factor(Identifier id) throws ParseException {
 Exp exp; Exp exp2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUALS:
      jj_consume_token(EQUALS);
      exp = exp();
      jj_consume_token(SEMI_COLON);
  {if (true) return new Assign(id, exp);}
      break;
    case LSQUAREBRACKET:
      jj_consume_token(LSQUAREBRACKET);
      exp = exp();
      jj_consume_token(RSQUAREBRACKET);
      jj_consume_token(EQUALS);
      exp2 = exp();
      jj_consume_token(SEMI_COLON);
  {if (true) return new ArrayAssign(id, exp, exp2);}
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Statement statement_two() throws ParseException {
 Statement s; Statement s2; StatementList sl; Exp exp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LCURLYBRACE:
      jj_consume_token(LCURLYBRACE);
      sl = statement_();
      jj_consume_token(RCURLYBRACE);
   {if (true) return new Block(sl);}
      break;
    case IF:
      jj_consume_token(IF);
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
      s = statement();
      jj_consume_token(ELSE);
      s2 = statement();
   {if (true) return new If(exp, s, s2);}
      break;
    case WHILE:
      jj_consume_token(WHILE);
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
      s = statement();
   {if (true) return new While(exp, s);}
      break;
    case PRINT:
      jj_consume_token(PRINT);
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
      jj_consume_token(SEMI_COLON);
   {if (true) return new Print(exp);}
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp exp() throws ParseException {
 Exp exp; Exp exp2;
    exp = final_exp();
    exp2 = exp_derivation(exp);
  {if (true) return exp2;}
    throw new Error("Missing return statement in function");
  }

  static final public Exp final_exp() throws ParseException {
 Token s; Exp exp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      s = jj_consume_token(INTEGER_LITERAL);
                           {if (true) return new IntegerLiteral(Integer.parseInt(s.toString()));}
      break;
    case BOOLEAN:
      s = jj_consume_token(BOOLEAN);
                   if (s.toString().equals("true")){if (true) return new True();} {if (true) return new False();}
      break;
    case ID:
      s = jj_consume_token(ID);
               {if (true) return new IdentifierExp(s.toString());}
      break;
    case THIS:
      jj_consume_token(THIS);
             {if (true) return new This();}
      break;
    case NEW:
      jj_consume_token(NEW);
      exp = final_exp_factor();
                                 {if (true) return exp;}
      break;
    case NEGATION:
      jj_consume_token(NEGATION);
      exp = exp();
                         {if (true) return new Not(exp);}
      break;
    case LBRACE:
      jj_consume_token(LBRACE);
      exp = exp();
      jj_consume_token(RBRACE);
                                {if (true) return exp;}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp final_exp_factor() throws ParseException {
 Exp exp; Token i;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
      jj_consume_token(LSQUAREBRACKET);
      exp = exp();
      jj_consume_token(RSQUAREBRACKET);
                                                               {if (true) return new NewArray(exp);}
      break;
    case ID:
      i = jj_consume_token(ID);
      jj_consume_token(LBRACE);
      jj_consume_token(RBRACE);
                            {if (true) return new NewObject(new Identifier(i.toString()));}
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp exp_derivation(Exp exp) throws ParseException {
 Exp exp2;Token op;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case POINT:
      jj_consume_token(POINT);
      exp2 = exp_derivation_factor(exp);
                                               {if (true) return exp2;}
      break;
    case OPERATOR:
      op = jj_consume_token(OPERATOR);
      exp2 = exp();
  if(op.toString().equals("&&")){if (true) return new And(exp, exp2);}
  if(op.toString().equals("<")){if (true) return new LessThan(exp, exp2);}
  if(op.toString().equals("+")) {if (true) return new Plus(exp, exp2);}
  if(op.toString().equals("-")) {if (true) return new Minus(exp, exp2);}
  {if (true) return new Times(exp, exp2);}
      break;
    case LSQUAREBRACKET:
      jj_consume_token(LSQUAREBRACKET);
      exp2 = exp();
      jj_consume_token(RSQUAREBRACKET);
                                                      {if (true) return new ArrayLookup(exp, exp2);}
      break;
    default:
      jj_la1[16] = jj_gen;
   {if (true) return exp;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp exp_derivation_factor(Exp exp) throws ParseException {
 ExpList el; Token i;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      i = jj_consume_token(ID);
      jj_consume_token(LBRACE);
      el = exp_list();
      jj_consume_token(RBRACE);
         {if (true) return new Call(exp,new Identifier(i.toString()),el);}
      break;
    case LENGTH:
      jj_consume_token(LENGTH);
 {if (true) return new ArrayLength(exp);}
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ExpList exp_list() throws ParseException {
 Exp exp; ExpList expl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case INTEGER_LITERAL:
    case THIS:
    case NEW:
    case NEGATION:
    case LBRACE:
    case ID:
      exp = exp();
      expl = exp_rest_();
  expl.addElement(exp);{if (true) return expl;}
      break;
    default:
      jj_la1[18] = jj_gen;
  {if (true) return new ExpList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public ExpList exp_rest_() throws ParseException {
 Exp exp; ExpList expl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      exp = exp_rest();
      expl = exp_rest_();
  expl.addElement(exp);{if (true) return expl;}
      break;
    default:
      jj_la1[19] = jj_gen;
  {if (true) return new ExpList();}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp exp_rest() throws ParseException {
 Exp exp;
    jj_consume_token(COMMA);
    exp = exp();
 {if (true) return exp;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[20];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x800,0x700000,0x800,0x40000,0x700000,0x6f00000,0x20000000,0x1000,0x700000,0x0,0x6800000,0x6800000,0x20000000,0x6800000,0x0,0x400000,0x88000000,0x40000000,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x1000,0x0,0x400,0x1000,0x1400,0x1100,0x0,0x1000,0x20,0x1400,0x1400,0x100,0x400,0x105f,0x1000,0x100,0x1000,0x105f,0x20,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[46];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 20; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 46; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}