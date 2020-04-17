package PoliciesAndAlgorithms;


public abstract class GamesAssigningPolicy {
    String policy;
    public void setPolicy(String newGamePolicy) {
        this.policy = newGamePolicy;
    }

    public void executePolicy() {
    }

    public String getPolicy(){
        return policy;
    }

}
