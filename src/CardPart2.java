import java.util.Arrays;
import java.util.Collections;

public class CardPart2 implements Comparable<CardPart2>{
    public String hand;
    public Integer bid;
    public Integer rank;
    public Integer wildCards;
    CardPart2(String hand, String bid){
        this.bid = Integer.parseInt(bid);
        this.hand = hand;
        this.wildCards= 0;
        this.rank = parseCard(this);
    }

    public int parseCard(CardPart2 card) {
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
                this.wildCards++;
            }else if (c == 'T') {
                cardCount[12]++;
            }
        }
        return getRank(cardCount, card);
    }
    public static int getRank(Integer[] cardCount, CardPart2 card) {
        Arrays.sort(cardCount, Collections.reverseOrder());
        System.out.println(Arrays.toString(cardCount));
        if((cardCount[0] + card.wildCards) == 5){
            return 7;
        }else if ((cardCount[0] + card.wildCards) == 4){
            return 6;
        } else if (((cardCount[1] + card.wildCards) == 3 && cardCount[0] == 2)
                || (cardCount[0]+ card.wildCards ==3 && cardCount[1] == 2)) {
            return 5;
        } else if ((cardCount[0] + card.wildCards) == 3 && cardCount[1] == 1) {
            return 4;
        } else if (((cardCount[0] + card.wildCards)==2 && cardCount[1] == 2)
                || (cardCount[1] + card.wildCards == 2 && cardCount[0] == 2)) {
            return 3;
        } else if ((cardCount[0] + card.wildCards) == 2){
            return 2;
        } else if ((cardCount[0] + card.wildCards) == 1) {
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
            return -1;
        }else if (c == 'T') {
            return 8;
        }
        return -1;
    }
    @Override
    public int compareTo(CardPart2 other) {
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
