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


    @Override
    public String viewMyPersonalPage() {
        return "Welcome to my page! "+
                "My Name is: '" + super.getMyFisrtName() + '\'' +
                " " + super.getMyLastName() + '\'' +
                "My Training is " + getTraining() +
                ". My role is " + super.getRole() +
                ", My Feed : " + super.getMyFeed() ;
    }
}
