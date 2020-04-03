package Users;

public class CoachPageEditor extends APageEditor {
    private ETraining training;
    public CoachPageEditor(String myFirstName, String myLastName, Enum role, ETraining training) {
        super(myFirstName, myLastName, role);
        this.training=training;
    }

    public ETraining getTraining() {
        return training;
    }

    public void setTraining(ETraining training) {
        this.training = training;
    }
}
