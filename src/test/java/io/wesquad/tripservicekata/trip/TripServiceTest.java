package io.wesquad.tripservicekata.trip;


import io.wesquad.tripservicekata.exception.UserNotLoggedInException;
import io.wesquad.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    public static final Trip TO_RIO = new Trip();
    private static final User NOT_CONNECTED = null;
    private static final User UNKNOWN_USER = null;
    private TestableTripService tripService;
    private User loggedInUser = new User();
    private User bob;
    private TripDAO tripDao;

    @Before
    public void setUp() {
        tripDao = mock(TripDAO.class);
        tripService = new TestableTripService(tripDao);
        bob = new User();
    }

    @Test(expected = UserNotLoggedInException.class)
    public void should_return_a_user_not_logged_exception_when_user_is_not_logged_in() {
        loggedInUser = NOT_CONNECTED;
        tripService.getTripsByUser(UNKNOWN_USER);
    }

    @Test
    public void should_return_an_empty_list_if_user_has_no_friends() {
        assertThat(tripService.getTripsByUser(bob)).isEmpty();
    }

    @Test
    public void should_return_an_empty_list_if_user_is_not_friend_with_logged_in_user() {
        User peter = new User();
        bob.addFriend(peter);
        assertThat(tripService.getTripsByUser(bob)).isEmpty();
    }

    @Test
    public void should_return_a_list_of_trips_if_user_is_friend_with_logged_in_user() {
        bob.addFriend(loggedInUser);
        bob.addTrip(TO_RIO);
        when(tripDao.findTripsByUser(bob)).thenReturn(bob.trips());
        assertThat(tripService.getTripsByUser(bob)).isNotEmpty();
    }

    private class TestableTripService extends TripService {
        public TestableTripService(TripDAO tripDAO) {
            super(tripDAO);
        }

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

    }
}
