package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {

    private static final User UNUSED_USER = null;
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_PARIS = new Trip();

    private User loggedInUser;

    private TestableTripService tripService;


    @Before
    public void setUp() {
        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() {
        loggedInUser = GUEST;
        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void should_not_return_any_trips_when_users_user_are_not_friends() {
        loggedInUser = REGISTERED_USER;

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_PARIS);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertThat(friendTrips).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void explore() {

    }

    private class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

    }

}
