package Users;

public class representativeFootballAssociation extends Fan {

    public representativeFootballAssociation(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }

    //region Methods
    public void setNewLeague(){} // useCase #9.1

    public void setNewSeason(){} // useCase #9.2

    public void nominateReferee(){} // useCase #9.3

    public void removeReferee(){} // useCase #9.3

    public void assignReferees(){} // useCase #9.4

    public void setScoreTablePolicy(){ // useCase #9.5

        //TODO: NOTE - as Dana instructed us, we will define 2 policies and let the user choose one.
        // Therefore, please use the family-class of "ScoreTablePolicy" (PoliciesAndAlgorithms package)
        // I hope we could implement it as strategy design pattern. contact __Amit/Alon_ who is in charge on
        // those classes.

    }

    public void setGamesAssigningPolicy(){ // useCase #9.6

        //TODO: NOTE - as Dana instructed us, we will define 2 policies and let the user choose one.
        // Therefore, please use the family-class of "GamesAssigningPolicy" (PoliciesAndAlgorithms package)
        // I hope we could implement it as strategy design pattern. contact ___ who is in charge on
        // those classes.
        // Here, we will use those heuristic algorithms Dana talked about.
    }

    public void activateGamesAssigning(){} // useCase #9.7

    public void setGroupBudgetRules(){// useCase #9.8

        //TODO: Leave it for now. we will be smarter soon :)

    }

    public void setGroupBudgetActions(){// useCase #9.9

        //TODO: Leave it for now. we will be smarter soon :)

    }
    //endregion

}
