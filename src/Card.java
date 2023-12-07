import java.util.Arrays;
import java.util.Collections;

public class Card implements Comparable<Card>{
    public String hand;
    public Integer bid;
    public Integer rank;
    Card(String hand, String bid){
        this.bid = Integer.parseInt(bid);
        this.hand = hand;
        this.rank = parseCard(this);
    }

    public static int parseCard(Card card) {
        Integer[] cardCount = new Integer[14];
        Arrays.fill(cardCount,0);
        for(int i = 0; i < card.hand.length(); ++i) {
            Character c = card.hand.charAt(i);
            if(Character.isDigit(c)){
                cardCount[c - '2']++;
            } else if (c == 'A') {
                cardCount[8]++;
            }else if (c == 'K') {
                cardCount[9]++;
            }else if (c == 'Q') {
                cardCount[10]++;
            }else if (c == 'J') {
                cardCount[11]++;
            }else if (c == 'T') {
                cardCount[12]++;
            }
        }
        return getRank(cardCount);
    }
    public static int getRank(Integer[] cardCount) {
        Arrays.sort(cardCount, Collections.reverseOrder());
        if(cardCount[0] == 5){
            return 7;
        }else if (cardCount[0] == 4){
            return 6;
        } else if (cardCount[0] == 3 && cardCount[1] == 2) {
            return 5;
        } else if (cardCount[0] == 3 && cardCount[1] == 1) {
            return 4;
        } else if (cardCount[0]==2 && cardCount[1] == 2) {
            return 3;
        } else if (cardCount[0] == 2){
            return 2;
        } else if (cardCount[0] == 1) {
            return 1;
        }
        return 0;
    }
    private int parseCardToInt(Character c) {
        if(Character.isDigit(c)){
            return c - '2';
        }else if (c == 'A') {
            return 12;
        }else if (c == 'K') {
            return 11;
        }else if (c == 'Q') {
            return 10;
        }else if (c == 'J') {
            return 9;
        }else if (c == 'T') {
            return 8;
        }
        return -1;
    }
    @Override
    public int compareTo(Card other) {
        int comp = Integer.compare(this.rank, other.rank);
        if(comp != 0){
            return comp;
        }
        else {
            for (int i =0; i < 5; ++i) {
                int thisCard = parseCardToInt(this.hand.charAt(i));
                int otherCard = parseCardToInt(other.hand.charAt(i));
                int cmp = Integer.compare(thisCard, otherCard);
                if(cmp != 0) return cmp;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Card = {hand= " + this.hand + ", Bid= " + this.bid + ", Rank = " + this.rank + " }";
    }
}
