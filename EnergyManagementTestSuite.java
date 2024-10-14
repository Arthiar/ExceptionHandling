package EnergyManagementSystemProject;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// This annotation tells JUnit to run as a suite and include multiple test classes
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MultipleExceptionHandlerTest.class, // Add the test classes here
    RethrowExceptionHandlerTest.class   // You can add more classes as needed
})
public class EnergyManagementTestSuite {
    // The class remains empty. It only serves as a holder for the annotations.
}
