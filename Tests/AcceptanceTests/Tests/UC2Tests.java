package AcceptanceTests.Tests;
import AcceptanceTests.SystemOperations.ISystemOperationsBridge;
import AcceptanceTests.SystemOperations.SystemOperationProxy;
import org.junit.Before;

public class UC2Tests {
    ISystemOperationsBridge systemOperations;

    @Before
    public void Setup(){
        systemOperations = new SystemOperationProxy();
    }
}
