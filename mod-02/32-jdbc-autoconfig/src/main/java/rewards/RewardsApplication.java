package rewards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO-11 (Optional) : Disable 'DataSource' auto-configuration
// - Note that you are using your own 'DataSource' bean now
//   instead of auto-configured one
// - Use 'exclude' attribute of '@SpringBootApplication'
//   excluding 'DataSourceAutoConfiguration' class
// - Run this application and observe a failure
// - Import 'RewardsConfig' class
// - Run this application again and observe a successful execution

// TODO-12 (Optional) : Look in application.properties for the next step.

// TODO-13 (Optional) : Follow the instruction in the lab document.
//           The section titled "Build and Run using Command Line tools".

@SpringBootApplication
public class RewardsApplication {
    static final String SQL = "SELECT count(*) FROM T_ACCOUNT";

    final Logger logger
            = LoggerFactory.getLogger(RewardsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RewardsApplication.class, args);
    }

    // TODO-05 : Implement a command line runner that will query count from
    //           T_ACCOUNT table and log the count to the console
    // - Use the SQL query and logger provided above.
    // - Use the JdbcTemplate bean that Spring Boot auto-configured for you
    // - Run this application and verify "Hello, there are 21 accounts" log message
    //   gets displayed in the console

    // TODO-07 (Optional): Enable full debugging in order to observe how Spring Boot
    //           performs its auto-configuration logic
    // - Follow TO-DO-07 in application.properties, then come back here.
    // - Run the application
    // - In the console output, find "CONDITIONS EVALUATION REPORT".
    //   It represents the auto-configuration logic used by Spring Boot.
    // - Search for "JdbcTemplateAutoConfiguration matched:" and
    //   "DataSourceAutoConfiguration matched:". Note that each @Conditional*
    //   represents a single conditional statement in the "JdbcTemplateAutoConfiguration"
    //   and "DataSourceAutoConfiguration" classes.

}
