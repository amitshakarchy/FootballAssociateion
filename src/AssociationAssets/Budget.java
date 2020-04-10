package AssociationAssets;

import java.util.List;

public class Budget {

Team team;
Season season;
List<Integer> incomes;
List<Integer> outcomes;
double salary;


    public Budget(List<Integer> incomes, List<Integer> outcomes, double salary) {
        this.incomes = incomes;
        this.outcomes = outcomes;
        this.salary = salary;
    }


    public List<Integer> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Integer> incomes) {
        this.incomes = incomes;
    }

    public List<Integer> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Integer> outcomes) {
        this.outcomes = outcomes;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
