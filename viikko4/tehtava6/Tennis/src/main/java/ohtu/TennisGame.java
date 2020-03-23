package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    
    private String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";
        
        if (m_score1 >= 4 || m_score2 >= 4) {
            return solveStanding(m_score1, m_score2);
        }
        
        score = scoreToString(m_score1) + "-";
        
        if (m_score1 == m_score2) {
            score += "All";
        } else {
            score += scoreToString(m_score2);
        }
        
        return score;
    }

    private String solveStanding(int points1, int points2) {
        int result = points1 - points2;
        String standing = "Deuce";

        if (result <= -2) {
            standing = "Win for player2";
        } else if (result >= 2) {
            standing = "Win for player1";
        }

        switch (result) {
            case -1:
                standing = "Advantage player2";
                break;
            case 1:
                standing = "Advantage player1";
                break;
            default:
                break;
        }

        return standing;
    }

    private String scoreToString(int points) {
        return scores[points];
    }

}
