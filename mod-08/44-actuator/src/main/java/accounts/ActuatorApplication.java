package accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

import config.AppConfig;

/**
 * TODO-14: Look for TO-DO-14 in application.properties
 * <p>
 * ------------------------------------------------
 * <p>
 * TODO-17: Verify the behavior of custom health indicator
 * - Let the application to restart (via devtools)
 * - Access the health indicator - it should be DOWN as there are no restaurants.
 * <p>
 * TODO-18: Verify the behavior of custom health indicator with change
 * - Modify the `spring.sql.init.data-locations` property in the application.properties
 *   to use `data-with-restaurants.sql`
 * - Let the application to restart (via devtools)
 * - Access the health indicator - it should be UP this time
 * <p>
 * ------------------------------------------------
 * <p>
 * TODO-20: Look for "TO-DO-20: Organize health indicators into groups"
 *          in the application.properties
 */
@SpringBootApplication
@Import(AppConfig.class)
@EntityScan("rewards.internal")
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

}

/*
 * TODO-27 (Optional): Access Actuator endpoints using JMX
 * (If you are short on time, skip this step.)
 * - Add "spring.jmx.enabled=true" to the "application.properties"
 * - Restart the application
 * - In a terminal window, run "jconsole" (from <JDK-directory>/bin)
 * - Select "accounts.ActuatorApplication" under "Local Process"
 *   then click "Connect"
 * - Click "insecure connection" if prompted
 * - Select the MBeans tab, find the "org.springframework.boot"
 *   folder, then open the "Endpoint" sub-folder
 * - Note that all the actuator endpoints ARE exposed for JMX
 * - Expand Health->Operations->health
 * - Click "health" button on the top right pane
 * - Observe the health data gets displayed
 */
