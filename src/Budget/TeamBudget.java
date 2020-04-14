package Budget;
import AssociationAssets.Season;
import AssociationAssets.Team;
import javafx.util.Pair;
import java.util.*;


public class TeamBudget extends Observable {
    Team team;
    Season season;
    HashMap<String, Pair<Double,String>> incomes; // String- income name, pair: double-cost, String-description
    HashMap<String, Pair<Double,String>> outcomes;
    double threshHold;
    /**
     * Constructor
     * @param team
     * @param season
     * @param threshHold - The threshold that defines whether the team has exceeded from its budget
     */
    public TeamBudget(Team team, Season season,double threshHold) {
        this.team = team;
        this.season = season;
        this.threshHold = threshHold;
        this.incomes = new HashMap<>();
        this.outcomes= new HashMap<>();
    }

    public void checkTeamBudgetExceedRule(){
        if((1+threshHold)*totalIncomes() < totalOutcomes()){
           setChanged();
           notifyAll();
        }
    }

    /**
     * @return total team's outcomes
     */
    public double totalOutcomes(){
        Double sum=0.0;
        for (Map.Entry<String, Pair<Double, String>> entry : outcomes.entrySet()){
            sum+=entry.getValue().getKey();
        }
        return sum;
    }

    /**
     * @return total team's incomes
     */
    public double totalIncomes(){
        Double sum=0.0;
        for (Map.Entry<String, Pair<Double, String>> entry : incomes.entrySet()){
            sum+=entry.getValue().getKey();
        }
        return sum;
    }

    /**
     * add outcome to outcome's team
     * @param outcomeName
     * @param outcomeValue
     * @param description
     */
    public  void addOutcome (String outcomeName,Double outcomeValue, String description){
        outcomes.put(outcomeName, new Pair<>(outcomeValue,description));
        checkTeamBudgetExceedRule();
    }

    /**
     * add income to income's team
     * @param incomeName
     * @param incomeName
     * @param description
     */
    public  void addIncome (String incomeName,Double incomeValue, String description ){
        incomes.put(incomeName, new Pair<>(incomeValue,description));
        checkTeamBudgetExceedRule();
    }

    //region Getters: getThreshHold, getTeam, getIncomes, getOutcomes
    public double getThreshHold() {
        return threshHold;
    }
    public Team getTeam() {
        return team;
    }
    public HashMap<String, Pair<Double, String>> getIncomes() {
        return incomes;
    }

    public HashMap<String, Pair<Double, String>> getOutcomes() {
        return outcomes;
    }

    //endregion

    //region Setter: setThreshHold
    public void setThreshHold(double threshHold) {
        this.threshHold = threshHold;
        checkTeamBudgetExceedRule();
    }

    //endregion

}
