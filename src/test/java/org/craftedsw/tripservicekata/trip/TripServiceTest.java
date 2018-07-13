package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_if_current_user_is_not_logged_in() {
        TripServiceChild tripService = new TripServiceChild();
        tripService.getTripsByUser(null);
    }

    private class TripServiceChild extends TripService {

        @Override
        protected User getLoggedUser() {
            return null;
        }

    }

}
