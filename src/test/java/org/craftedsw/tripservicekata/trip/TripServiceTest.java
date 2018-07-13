package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class TripServiceTest {

    private static final User NO_USER = null;
    private static final User UNKNOWN_USER = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_if_current_user_is_not_logged_in() {
        TripServiceChild tripService = new TripServiceChild(NO_USER);
        tripService.getTripsByUser(NO_USER);
    }

    @Test
    public void should_return_no_trips_for_an_unknown_user() {
        TripServiceChild tripService = new TripServiceChild(UNKNOWN_USER);
        List<Trip> trips = tripService.getTripsByUser(UNKNOWN_USER);
        Assertions.assertThat(trips).isEqualTo(Collections.EMPTY_LIST);
    }

    private class TripServiceChild extends TripService {

        private User loggedUser;

        protected TripServiceChild(User user) {
            this.loggedUser = user;
        }

        @Override
        protected User getLoggedUser() {
            return this.loggedUser;
        }

    }

}
