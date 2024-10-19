package EnergyManagementSystemProject;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    MultipleExceptionHandlerTest.class,
    RethrowExceptionHandlerTest.class,  
    LogFileReaderTest.class,
    ChainedExceptionHandlerTest.class 
})
public class EnergyManagementTestSuite {
    // The class remains empty. It only serves as a holder for the annotations.
}
