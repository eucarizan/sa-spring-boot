package accounts.web;

import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import rewards.internal.restaurant.RestaurantRepository;

@Component
public class RestaurantHealthCheck implements HealthIndicator {

    private final RestaurantRepository restaurantRepository;

    public RestaurantHealthCheck(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Health health() {
        Long restaurantCount = restaurantRepository.getRestaurantCount();
        if (restaurantCount > 0) {
            return Health.up()
                    .build();
        } else {
            return Health.down().build();
        }
    }

}


/**
 * TODO-25 (Optional): Experiment with HealthIndicator above
 * - Change "spring.sql.init.data-locations" property in the
 *   "application.properties" file back to use "data-no-restaurants.sql"
 * - Include the restaurant count as extra detail when DOWN state.
 * - Instead of returning DOWN when there are no restaurants,
 *   define and use a custom status called NO_RESTAURANTS.
 * - When there are no restaurants in the DB, what top-level status
 *   is returned for the "application" health group?
 * - Set "management.endpoint.health.group.application.status.order" property
 *   in the "application.properties" file so that NO_RESTAURANTS
 *   gets displayed as top-level status for the "application" health group.
 * - Restart the application and verify the result.
 */
