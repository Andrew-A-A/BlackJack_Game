package blackjack;
import java.util.Random;
import java.util.Scanner;

public  class Game {
    Player[] player = new Player[4];
   Random random=new Random();
   int ranChoice=random.nextInt(52);
    int MaxScore = -1;
    Card[] CardDeck = new Card[52];
    public void SetCardDeck() {

        while(CardDeck[51]==null) {
            for (int j=0;j<13;j++){
                int x=j+1;
                if (x>10) x=10;
                CardDeck[j]=new Card(0,j,x);
            }    for (int j=0;j<13;j++){
                int x=j+1;
                if (x>10) x=10;
                CardDeck[j+13]=new Card(1,j,x);
            }    for (int j=0;j<13;j++){
                int x=j+1;
                if (x>10) x=10;
                CardDeck[j+26]=new Card(2,j,x);
            }    for (int j=0;j<13;j++){
                int x=j+1;
                if (x>10) x=10;
                CardDeck[j+39]=new Card(3,j,x);
            }
        }



    }
    public Card DrawCard(){
       int x=ranChoice;
       while (CardDeck[x]==null){
           int ranChoice=random.nextInt(52);
           x=ranChoice;
       }
       Card c=new Card(CardDeck[x]);
       CardDeck[x]=null;
       return c;
    }
    public void setPlayers(){
        for (int i=0;i<4;i++){
            String n;
            player[i]= new Player(0,false,false);
            Scanner input= new Scanner(System.in);
            n=input.next();
            player[i].Name=n;
            player[i].Cards[0]=DrawCard();
            player[i].Cards[1]=DrawCard();
            player[i].score+=player[i].Cards[1].value+player[i].Cards[0].value;
        }
}
    public void ScoreUpdate() {
        for (int i = 0; i < 3; i++) {
            int x=0;
            for (int j = 0; j < player[i].Cards.length; j++) {
               if(player[i].Cards[j]!=null)
                x+= player[i].Cards[j].value;

            }
            player[i].score=x;
            if (x>MaxScore&&x<21) MaxScore=x;

            if (x>21) player[i].lost=true;
            if(x==21) player[i].blackjack=true;
        }
    }
}