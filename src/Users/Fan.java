package Users;

public class Fan extends AUser {

    public void subscribePersonalPage() {//useCase 3.2

        //TODO: you should discuss with __, who is in charge on notification and observers management
        // in order to determine how to implement it.

    }

    public void subscribeGamesNotifications() {//useCase 3.3

        //TODO: you should discuss with __, who is in charge on notification and observers management
        // in order to determine how to implement it.

    }

    public void submitComplain() {//useCase 3.4

        // the way I see it, this method should receive a complain text (from the service layer)
        // and send it as an email to a system manage (you can get it's email from the FootballSystem itself).

    }

    public Object getSearchHistory() { //useCase 3.5

        // return search history in order to display it on the screen.

        return null;
    }



}
