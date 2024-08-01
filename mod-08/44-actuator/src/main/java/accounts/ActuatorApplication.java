package accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

import config.AppConfig;

/**
 * TODO-06: Publish build information
 * - Add an appropriate plugin to pom.xml (for Maven) or BuildInfo task to
 *   build.gradle (for Gradle)
 * - Rebuild the application preferably at the command line
 *   ./mvnw -pl 00-rewards-common -pl 01-rewards-db -pl 44-actuator clean install (for Maven)
 *   ./gradlew 44-actuator:clean 44-actuator:build (for Gradle)
 * - Verify the presence of the build-info.properties file as following
 *   ./target/classes/META-INF/build-info.properties (for Maven)
 *   ./build/resources/main/META-INF/build-info.properties (for Gradle)
 * - Restart the application and access "info" endpoint and verify the build
 *   info gets displayed
 *
 * TODO-07 (Optional): Add additional properties to the info endpoint

 *
 * ------------------------------------------------
 *
 * TODO-14: Look for TO-DO-14 in application.properties
 *
 * ------------------------------------------------
 *
 * TODO-17: Verify the behavior of custom health indicator
 * - Let the application to restart (via devtools)
 * - Access the health indicator - it should be DOWN as there are no restaurants.
 *
 * TODO-18: Verify the behavior of custom health indicator with change
 * - Modify the `spring.sql.init.data-locations` property in the application.properties
 *   to use `data-with-restaurants.sql`
 * - Let the application to restart (via devtools)
 * - Access the health indicator - it should be UP this time
 *
 * ------------------------------------------------
 *
 * TODO-20: Look for "TO-DO-20: Organize health indicators into groups"
 *          in the application.properties
 *
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
