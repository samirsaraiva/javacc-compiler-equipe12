/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package IterationOne.parser;
import IterationTwo.syntaxtree.*;

/** Token Manager. */
public class ParserTokenManager implements ParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x8471df800L) != 0L)
         {
            jjmatchedKind = 44;
            return 18;
         }
         if ((active0 & 0xc00000L) != 0L)
         {
            jjmatchedKind = 44;
            return 3;
         }
         if ((active0 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 44;
            return 10;
         }
         return -1;
      case 1:
         if ((active0 & 0x800000L) != 0L)
            return 18;
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 1;
            return 0;
         }
         if ((active0 & 0xc471df800L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 1;
            return 18;
         }
         return -1;
      case 2:
         if ((active0 & 0x400000L) != 0L)
            return 2;
         if ((active0 & 0x800000000L) != 0L)
            return 18;
         if ((active0 & 0x4471df800L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 2;
            return 18;
         }
         return -1;
      case 3:
         if ((active0 & 0x461d3800L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 3;
            return 18;
         }
         if ((active0 & 0x40100c000L) != 0L)
            return 18;
         return -1;
      case 4:
         if ((active0 & 0x2000800L) != 0L)
            return 18;
         if ((active0 & 0x441d3000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 4;
            return 18;
         }
         return -1;
      case 5:
         if ((active0 & 0x40093000L) != 0L)
            return 18;
         if ((active0 & 0x4140000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 5;
            return 18;
         }
         return -1;
      case 6:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         if ((active0 & 0x140000L) != 0L)
            return 18;
         return -1;
      case 7:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 8:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 9:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 10:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 11:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 12:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 13:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 14:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 15:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 16:
         if ((active0 & 0x4000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjStopAtPos(0, 36);
      case 40:
         return jjStopAtPos(0, 38);
      case 41:
         return jjStopAtPos(0, 39);
      case 44:
         return jjStopAtPos(0, 37);
      case 46:
         return jjStopAtPos(0, 31);
      case 47:
         return jjMoveStringLiteralDfa1_0(0x60L);
      case 59:
         return jjStopAtPos(0, 28);
      case 61:
         return jjStopAtPos(0, 29);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x4010000L);
      case 91:
         jjmatchedKind = 40;
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 93:
         return jjStopAtPos(0, 41);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x1040000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0xc00000L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 123:
         return jjStopAtPos(0, 42);
      case 125:
         return jjStopAtPos(0, 43);
      default :
         return jjMoveNfa_0(4, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x20L) != 0L)
            return jjStopAtPos(1, 5);
         break;
      case 47:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 93:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x840080000L);
      case 102:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(1, 23, 18);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x402000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000800L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x104000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x12000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x2800L);
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x40200c000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x5000000L);
      case 116:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(2, 22, 2);
         return jjMoveStringLiteralDfa3_0(active0, 0xc0000L);
      case 119:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(2, 35, 18);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(3, 14, 18);
         break;
      case 101:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(3, 24, 18);
         return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x2101000L);
      case 110:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(3, 15, 18);
         break;
      case 115:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(3, 34, 18);
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x4002000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(4, 25, 18);
         return jjMoveStringLiteralDfa5_0(active0, 0x4100000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x3000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x50000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000L);
      case 115:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(4, 11, 18);
         break;
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x100000L);
      case 99:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(5, 12, 18);
         else if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(5, 13, 18);
         break;
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000L);
      case 103:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(5, 16, 18);
         break;
      case 104:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(5, 30, 18);
         break;
      case 109:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000L);
      case 110:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(5, 19, 18);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 46:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000000L);
      case 110:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(6, 20, 18);
         break;
      case 115:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(6, 18, 18);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa8_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 117:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 116:
         return jjMoveStringLiteralDfa10_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
static private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 46:
         return jjMoveStringLiteralDfa11_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
static private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 112:
         return jjMoveStringLiteralDfa12_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
static private int jjMoveStringLiteralDfa12_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 114:
         return jjMoveStringLiteralDfa13_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(11, active0);
}
static private int jjMoveStringLiteralDfa13_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(11, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(12, active0);
      return 13;
   }
   switch(curChar)
   {
      case 105:
         return jjMoveStringLiteralDfa14_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(12, active0);
}
static private int jjMoveStringLiteralDfa14_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(12, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(13, active0);
      return 14;
   }
   switch(curChar)
   {
      case 110:
         return jjMoveStringLiteralDfa15_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(13, active0);
}
static private int jjMoveStringLiteralDfa15_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(13, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(14, active0);
      return 15;
   }
   switch(curChar)
   {
      case 116:
         return jjMoveStringLiteralDfa16_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(14, active0);
}
static private int jjMoveStringLiteralDfa16_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(14, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(15, active0);
      return 16;
   }
   switch(curChar)
   {
      case 108:
         return jjMoveStringLiteralDfa17_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(15, active0);
}
static private int jjMoveStringLiteralDfa17_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(15, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(16, active0);
      return 17;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x4000000L) != 0L)
            return jjStopAtPos(17, 26);
         break;
      default :
         break;
   }
   return jjStartNfa_0(16, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 19;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 18:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(18);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 33)
                        kind = 33;
                     jjCheckNAdd(16);
                  }
                  else if ((0x10002c0000000000L & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                  }
                  else if (curChar == 38)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(18);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(18);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(18);
                  break;
               case 5:
                  if (curChar == 38 && kind > 27)
                     kind = 27;
                  break;
               case 6:
                  if (curChar == 38)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((0x10002c0000000000L & l) != 0L)
                     kind = 27;
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(16);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 44)
                        kind = 44;
                     jjCheckNAdd(18);
                  }
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 4:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 44)
                        kind = 44;
                     jjCheckNAdd(18);
                  }
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 14;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 10;
                  else if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 3:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 44)
                        kind = 44;
                     jjCheckNAdd(18);
                  }
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 2:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 44)
                        kind = 44;
                     jjCheckNAdd(18);
                  }
                  else if (curChar == 91)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 10:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 44)
                        kind = 44;
                     jjCheckNAdd(18);
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 1:
                  if (curChar == 93)
                     kind = 21;
                  break;
               case 8:
                  if (curChar == 101 && kind > 32)
                     kind = 32;
                  break;
               case 9:
                  if (curChar == 117)
                     jjCheckNAdd(8);
                  break;
               case 11:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 12:
                  if (curChar == 115)
                     jjCheckNAdd(8);
                  break;
               case 13:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 17:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(18);
                  break;
               case 18:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(18);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 19 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static private int jjMoveStringLiteralDfa0_2()
{
   return jjMoveNfa_2(0, 0);
}
static private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 1;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0L)
                     kind = 9;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 1 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x80L);
      default :
         return 1;
   }
}
static private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, 
"\143\154\141\163\163", "\160\165\142\154\151\143", "\163\164\141\164\151\143", "\166\157\151\144", 
"\155\141\151\156", "\123\164\162\151\156\147", "\133\135", "\145\170\164\145\156\144\163", 
"\162\145\164\165\162\156", "\142\157\157\154\145\141\156", null, "\151\156\164", "\151\146", 
"\145\154\163\145", "\167\150\151\154\145", 
"\123\171\163\164\145\155\56\157\165\164\56\160\162\151\156\164\154\156", null, "\73", "\75", "\154\145\156\147\164\150", "\56", null, null, 
"\164\150\151\163", "\156\145\167", "\41", "\54", "\50", "\51", "\133", "\135", "\173", "\175", 
null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "multilinecomment",
   "singlelinecomment",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 1, 2, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1ffffffff801L, 
};
static final long[] jjtoSkip = {
   0x7feL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[19];
static private final int[] jjstateSet = new int[38];
static protected char curChar;
/** Constructor. */
public ParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 19; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 8)
       {
          jjmatchedKind = 8;
       }
       break;
     case 2:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       if (jjmatchedPos == 0 && jjmatchedKind > 10)
       {
          jjmatchedKind = 10;
       }
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}