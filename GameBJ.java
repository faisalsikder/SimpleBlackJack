// Program: BlackJack
// Programmer: Faisal Sikder
// Date: Nov 2014
// Purpose: I was trying to learn blackjack.
//          Then I tought what if i make one
//          Some of the code is not mine
//          I got some code from net 
//          But forgot the source location
//          Extremly sorry for not remembering 
//          some part of the code source url


import java.util.Scanner;

 
    
    public class GameBJ {
       static Scanner  input = new  Scanner(System.in);
       static String name = "User";
       public static void main(String[] args) {
       
          int money;          // Amount of money the user has.
          int bet=-1;            // Amount user bets on a game.
          boolean userWins=false;   // Did the user win the game?
          
          
          System.out.println("Welcome to the game of blackjack.");
          System.out.println();
          
          money = 0;  // User starts with 0.
          System.out.print("Please enter your name? ");
          name = input.nextLine();
          while(money<=0){
        	  try{
        		  System.out.print("How much money you want to start with? ");
        		  money = Integer.parseInt(input.nextLine());
        		  if(money<=0){
        			  System.out.println("Invalid Amount ! Try Again!");
        			  money = 0;
        		  }
        	  }catch(Exception ex){
        		  System.out.println("Invalid Amount !");
        	  }
      		}
          
         money = playWholeGame(money,bet,userWins);
          System.out.println();
          System.out.println(name+" is levaing with $" + money + '.');
       
       } // end main()
       
       static int playWholeGame(int money,int bet, boolean userWins){
    	   while (true) {
         	  
               System.out.println(name+ " has " + money + " dollars.");
               do {
                  System.out.println("How many dollars do you want to bet?  (Enter 0 to end.)");
                  System.out.print(name+" bet amount: ");
                  bet = input.nextInt();
                  if (bet < 0 || bet > money)
                      System.out.println("Your answer must be between 0 and " + money + '.');
               } while (bet < 0 || bet > money);
               if (bet == 0)
                  break;
               userWins = playSingleGame();
               if (userWins)
                  money = money + bet;
               else
                  money = money - bet;
               System.out.println();
               if (money == 0) {
                  System.out.println(name+ " is out of money! Please start a new game with new money!");
                  break;
               }
           }
    	   return money;
       }
       static boolean playSingleGame() {
            
    
          UserDeck userDeck;       // A deck of cards.  A new deck for each game.
          GameBJHand dealerHand;   // The dealer's hand.
          GameBJHand userHand;     // The user's hand.
          
          userDeck = new UserDeck();
          dealerHand = new GameBJHand();
          userHand = new GameBJHand();
    
          /*  Shuffle the deck, then deal two cards to each player. */
          
          userDeck.shuffle();
          dealerHand.addCard( userDeck.dealCard() );
          dealerHand.addCard( userDeck.dealCard() );
          userHand.addCard( userDeck.dealCard() );
          userHand.addCard( userDeck.dealCard() );
          
          System.out.println();
          System.out.println();
     
          
          if (dealerHand.getBlackjackValue() == 21) {
               System.out.println("Dealer has the " + dealerHand.getCard(0)
                                       + " and the " + dealerHand.getCard(1) + ".");
               System.out.println(name+" has the " + userHand.getCard(0)
                                         + " and the " + userHand.getCard(1) + ".");
               System.out.println();
               System.out.println("Dealer has Blackjack.  Dealer wins.");
               return false;
          }
          
          if (userHand.getBlackjackValue() == 21) {
               System.out.println("Dealer has the " + dealerHand.getCard(0)
                                       + " and the " + dealerHand.getCard(1) + ".");
               System.out.println(name+ " has the " + userHand.getCard(0)
                                         + " and the " + userHand.getCard(1) + ".");
               System.out.println();
               System.out.println(name+ " has Blackjack.  You win.");
               return true;
          }
          
       
          
          while (true) {
              
               /* Display user's cards, and let user decide to Hit or Stand. */
    
               System.out.println();
               System.out.println();
               System.out.println(name+ " cards are:");
               for ( int i = 0; i < userHand.getCardCount(); i++ )
                  System.out.println("    " + userHand.getCard(i));
               System.out.println(name+ " total is " + userHand.getBlackjackValue());
               System.out.println();
               System.out.println("Dealer is showing the " + dealerHand.getCard(0));
               System.out.println();
               System.out.print("Hit (H) or Stand (S)? ");
               char userAction;  // User's response, 'H' or 'S'.
               do {
                  userAction = Character.toUpperCase( input.next().charAt(0));
                  if (userAction != 'H' && userAction != 'S')
                     System.out.print("Please respond H or S:  ");
               } while (userAction != 'H' && userAction != 'S');
    
             
    
               if ( userAction == 'S' ) {
                       // Loop ends; user is done taking cards.
                   break;
               }
               else {  // userAction is 'H'.  Give the user a card.  
                       // If the user goes over 21, the user loses.
                   GameCards newCard = userDeck.dealCard();
                   userHand.addCard(newCard);
                   System.out.println();
                   System.out.println(name+" hits.");
                   System.out.println("Your card is the " + newCard);
                   System.out.println("Your total is now " + userHand.getBlackjackValue());
                   if (userHand.getBlackjackValue() > 21) {
                       System.out.println();
                       System.out.println(name+" busted by going over 21.  You lose.");
                       System.out.println("Dealer's other card was the " 
                                                          + dealerHand.getCard(1));
                       return false;  
                   }
               }
               
          } // end while loop
           
          return winlossdecission(userDeck,dealerHand,userHand);
    
       }  // end playBlackjack()
    
       
       static boolean winlossdecission(UserDeck userDeck,GameBJHand dealerHand,GameBJHand userHand){
    	   System.out.println();
           System.out.println(name+ " stands.");
           System.out.println("Dealer's cards are");
           System.out.println("    " + dealerHand.getCard(0));
           System.out.println("    " + dealerHand.getCard(1));
           while (dealerHand.getBlackjackValue() <= 16) {
              GameCards newCard = userDeck.dealCard();
              System.out.println("Dealer hits and gets the " + newCard);
              dealerHand.addCard(newCard);
              if (dealerHand.getBlackjackValue() > 21) {
                 System.out.println();
                 System.out.println("Dealer went over 21.  You win.");
                 return true;
              }
           }
           System.out.println("Dealer's total is " + dealerHand.getBlackjackValue());
           
          
           
           System.out.println();
    	   if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
               System.out.println("Dealer wins on a tie.  You lose.");
               return false;
            }
            else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
               System.out.println("Dealer wins, " + dealerHand.getBlackjackValue() 
                                + " points to " + userHand.getBlackjackValue() + ".");
               return false;
            }
            else {
               System.out.println("You win, " + userHand.getBlackjackValue() 
                                + " points to " + dealerHand.getBlackjackValue() + ".");
               return true;
            }
       }
    } // end class Blackjack
