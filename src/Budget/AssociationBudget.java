package Budget;

import java.util.HashMap;
import Users.AUser;

public class AssociationBudget {
    double registrationFee; //all teams have the same registration Fee
    double TutuIntakes;
    HashMap<AUser, Double> Salaries;

    /**
     * Constructor
     */
    public AssociationBudget()
    {
        Salaries=  new HashMap<AUser, Double>();
    }

    /**
     * add salary for user
     * @param user
     * @param salary
     */
    public void addSalary(AUser user,Double salary)
    {
        Salaries.put(user,salary);
    }

    //region Setters: setRegistrationFee , setTotoIntakes
    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setTutuIntakes(double totoIntakes) {
        TutuIntakes = totoIntakes;
    }
    //endregion

    //region Getters: getRegistrationFee, getTutuIntakes
    public double getRegistrationFee() {
        return registrationFee;
    }

    public double getTutuIntakes() {
        return TutuIntakes;
    }
    //endregion

}



