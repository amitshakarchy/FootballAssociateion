package AssociationAssets;

public class Score {

int goalsHost;
int goalsGuest;


    public Score(){
        this.goalsHost = 0;
        this.goalsGuest = 0;
    }

    public Score(int goalsHost, int goalsGuest) {
        this.goalsHost = goalsHost;
        this.goalsGuest = goalsGuest;
    }

    public int getGoalsHost() {
        return goalsHost;
    }

    public void setGoalsHost(int goalsHost) {
        this.goalsHost = goalsHost;
    }

    public int getGoalsGuest() {
        return goalsGuest;
    }

    public void setGoalsGuest(int goalsGuest) {
        this.goalsGuest = goalsGuest;
    }
}
