package database;

/**
 *
 * @author Perlt
 */
public class ScoreEntity implements Comparable<ScoreEntity> {
    
    private int id;
    private String username;
    private int score;

    public ScoreEntity(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoreEntity t) {
        return t.score - this.score; 
    }

    @Override
    public String toString() {
        return "Name: " + username + " Score: " + score;
    }

    
}
