// DO NOT TOUCH
public class Player {
 
    private String name;
    private int totalScore = 0;
 
    Player(String name) {
        this.name = name;
    }
 
    String getName() {
        return name;
    }
 
    void changeScore(int roundScore) {
        totalScore += roundScore;
    }
 
    int getTotalScore() {
        return totalScore;
    }
 
}