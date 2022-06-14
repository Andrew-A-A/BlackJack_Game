package blackjack;

public class Player {
  String Name;
  int score;
  Card[] Cards =new Card[11];
  boolean blackjack,lost;

  public Player( int score, boolean blackjack, boolean lost) {

    this.score = score;
    this.blackjack = blackjack;
    this.lost = lost;
  }
}
