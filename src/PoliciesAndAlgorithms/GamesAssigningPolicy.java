package PoliciesAndAlgorithms;


import com.sun.xml.internal.bind.v2.TODO;

public abstract class GamesAssigningPolicy {
    String policy;
    public void setPolicy(String newGamePolicy) {
        this.policy = newGamePolicy;
    }

    public abstract int getPolicyPoints();
    public void executePolicy() {
        //TODO take the teams from DB from the season and league, and create games for those teams
    }

    public String getPolicy(){
        return policy;
    }

}
