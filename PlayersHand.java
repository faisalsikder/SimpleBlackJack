
import java.util.Vector;

public class PlayersHand {

   private Vector hand;   
   
   public PlayersHand() {
          
      hand = new Vector();
   }
   
   public void clear() {
        
      hand.removeAllElements();
   }
   
   public void addCard(GameCards c) {
        
      if (c != null)
         hand.addElement(c);
   }
   
   public void removeCard(GameCards c) {
        
      hand.removeElement(c);
   }
   
   public void removeCard(int position) {
        
      if (position >= 0 && position < hand.size())
         hand.removeElementAt(position);
   }
   
   public int getCardCount() {
         // Return the number of cards in the hand.
      return hand.size();
   }
   
   public GameCards getCard(int position) {
         
      if (position >= 0 && position < hand.size())
         return (GameCards)hand.elementAt(position);
      else
         return null;
   }
   
   public void sortBySuit() {
       
      Vector newHand = new Vector();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         GameCards c = (GameCards)hand.elementAt(0);  // Minumal card.
         for (int i = 1; i < hand.size(); i++) {
            GameCards c1 = (GameCards)hand.elementAt(i);
            if ( c1.getSuit() < c.getSuit() ||
                    (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
                pos = i;
                c = c1;
            }
         }
         hand.removeElementAt(pos);
         newHand.addElement(c);
      }
      hand = newHand;
   }
   
   public void sortByValue() {
        
      Vector newHand = new Vector();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         GameCards c = (GameCards)hand.elementAt(0);  // Minumal card.
         for (int i = 1; i < hand.size(); i++) {
            GameCards c1 = (GameCards)hand.elementAt(i);
            if ( c1.getValue() < c.getValue() ||
                    (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                pos = i;
                c = c1;
            }
         }
         hand.removeElementAt(pos);
         newHand.addElement(c);
      }
      hand = newHand;
   }
   
}
