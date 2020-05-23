package AcceptanceTests.Tests;

import AcceptanceTests.DataObjects.TeamDetails;
import AcceptanceTests.SystemOperations.ISystemOperationsBridge;
import AcceptanceTests.SystemOperations.RealSystemOperations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UC5Tests {
    ISystemOperationsBridge systemOperations;
    TeamDetails registeredTeam;
    @Before
    public void Setup(){
        systemOperations = new RealSystemOperations();
        registeredTeam = systemOperations.getNewRegisteredTeamForTest();
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFirstNameOfTeamManager_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditFirstNameOfTeamManager_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditLastNameOfTeamManager_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditLastNameOfTeamManager_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFirstNameOfPlayer_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditFirstNameOfPlayer_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditLastNameOfPlayer_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditLastNameOfPlayer_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditBDayOfPlayer_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditBDayOfPlayer_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditRoleOfPlayer_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditRoleOfPlayerWithWrongFormatOfRole_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditRoleOfPlayer_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFieldName_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFieldNameWithTakenName_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditFieldName_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFieldCityName_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditFieldCityName_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFieldCapacity_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditFieldCapacity_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFieldCapacityWithNegativeValue_FailureScenario(){
    }
    
    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditFirstNameOfCoach_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditFirstNameOfCoach_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditLastNameOfCoach_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditLastNameOfCoach_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditRoleOfCoach_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditRoleOfCoachWithWrongFormatOfRole_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditRoleOfCoach_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditTrainingOfCoach_SuccessScenario(){
    }

    @Test
    public void EditTeamAssetsTest_TeamOwnerTryToEditTrainingOfCoachWithWrongFormatOfTraining_FailureScenario(){
    }

    @Test
    public void EditTeamAssetsTest_UnPrivilegeUserTryToEditTrainingOfCoach_FailureScenario(){
    }

}