package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;

public class TripServiceTest {

    private static final User UNUSED_USER = null;
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_PARIS = new Trip();
    private static final Trip TO_LONDON = new Trip();

    private TestableTripService tripService;

    @Before
    public void setUp() {
        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() {
        tripService.getTripsByUser(UNUSED_USER, GUEST);
    }

    @Test
    public void should_not_return_no_trips_when_users_user_are_not_friends() {
        User friend = aUser()
                .withFriends(ANOTHER_USER)
                .withTrips(TO_PARIS)
                .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERED_USER);
        assertThat(friendTrips).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void should_return_friends_trips_when_users_are_friends() {

        User friend = aUser()
                .withFriends(REGISTERED_USER)
                .withTrips(TO_PARIS, TO_LONDON)
                .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERED_USER);
        assertThat(friendTrips.size()).isEqualTo(2);
    }

    private class TestableTripService extends TripService {

        @Override
        protected List<Trip> tripsByUser(User user) {
            return user.trips();
        }
    }
}
