package ohtu;

public class Player implements Comparable<Player> {

    private String name;
    private String team;
    private String nationality;
    private String birthdate;
    private int goals;
    private int assists;
    private int penalties;

    public Player(String name, String team, String nationality, String birthdate, int goals, int assists, int penalties) {
        this.name = name;
        this.team = team;
        this.nationality = nationality;
        this.birthdate = birthdate;
        this.goals = goals;
        this.assists = assists;
        this.penalties = penalties;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int points() {
        return this.goals + this.assists;
    }

    @Override
    public String toString() {
        String string = String.format("%1$" + 40 + "s", " ");
        StringBuilder sb = new StringBuilder(string);

        sb.insert(0, name);
        sb.insert(20, team);
        sb.insert(25, goals);
        sb.insert(28, "+");
        sb.insert(30, assists);
        sb.insert(33, "=");
        sb.insert(35, points());

        return sb.toString();
    }

    @Override
    public int compareTo(Player t) {
        return t.points() - this.points();
    }

}
