import com.snaptest.testing.drivers.AndroidDriverInstance;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

/**
 *  Base methods for driver managing.
 */

public class BaseTest {

    public static AndroidDriver driver = null;

    @BeforeClass
    public void setUpDriver(){
        driver = getDriver();
    }

    @AfterTest
    public void tearDownDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public AndroidDriver getDriver(){
        if (driver == null) {
            driver = AndroidDriverInstance.getInstance();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return driver;
    }
}