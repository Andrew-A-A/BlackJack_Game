package blackjack;


import java.util.Scanner;

public class BlackJack {
  static Game game=new Game();
   public static void main(String[] args) {
       GUI gui=new GUI();
      game.SetCardDeck();
      game.setPlayers();
       gui.runGUI( game.CardDeck, game.player[0].Cards, game.player[1].Cards, game.player[2].Cards, game.player[3].Cards);
       Scanner scan=new Scanner(System.in);
      for(int i=0;i<3;i++) {
          boolean stand = false;
          int x = 2;
          while (!stand) {
              String Order;
              System.out.println(game.player[i].Name + ": " + "{Hit/Stand}");
              Order = scan.next();
              if (Order.equals("Hit") || Order.equals("hit") || Order.equals("HIT")) {
                  game.player[i].Cards[x] = game.DrawCard();
                  game.ScoreUpdate();
                  gui.updatePlayerHand(game.player[i].Cards[x], i);
                  x++;
                  if (game.player[i].lost){stand=true;
                      System.out.println(game.player[i].Name+" BUSTED");}
                  else if(game.player[i].blackjack){System.out.println(game.player[i].Name+" BLACKJACK"); break;}
              } else {
                  stand = true;
              }

          }
          if(game.player[i].blackjack)break;
      }
      if(game.player[3].score>game.MaxScore&& game.player[3].score<=21){
          System.out.println("Dealer Blackjack");
      }
         else if(game.player[3].score<game.MaxScore){
              while(game.player[3].score<21){
                  int x=2;
                  game.player[3].Cards[x]= game.DrawCard();
                  gui.updateDealerHand(game.player[3].Cards[x],game.CardDeck);
                  if (game.player[3].score> game.MaxScore&& game.player[3].score<=21) {System.out.println("Dealer Blackjack"); break;}
                  else if(game.player[3].score>21) { break;}
                  x++;
                  DealerScoreUpdate();
              }

          }
         CheckScore();
      }

   static public void DealerScoreUpdate(){
       int score=0;
       for (int i = 0; i<game.player[3].Cards.length; i++){
           if (game.player[3].Cards[i]!=null)
           score+=game.player[3].Cards[i].value;
       }
       game.player[3].score=score;
   }
   static void CheckScore(){
       boolean push=false;
       if(game.player[0].score<=21 &&game.player[2].score<=21 &&game.player[1].score<=21) {
           if (game.player[0].score == game.MaxScore && game.player[2].score == game.MaxScore) push = true;
           if (game.player[0].score == game.MaxScore && game.player[1].score == game.MaxScore) push = true;
           if (game.player[1].score == game.MaxScore && game.player[2].score == game.MaxScore) push = true;
           if (push) System.out.println("PUSH");
           else {
           for (int i = 0; i < 3; i++) {
               if (game.player[0].blackjack)break;
               if (game.player[i].score == game.MaxScore ) {
                   System.out.println(game.player[i].Name + " Win");
               }
           }
           }
       }
   }
}
