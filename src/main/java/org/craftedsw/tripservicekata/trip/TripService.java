package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

public class TripService {

    protected List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
        return user.isFriendWith(loggedInUser) ? tripsByUser(user) : noTrips();

    }

    private List noTrips() {
        return Collections.EMPTY_LIST;
    }

    protected List<Trip> tripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
