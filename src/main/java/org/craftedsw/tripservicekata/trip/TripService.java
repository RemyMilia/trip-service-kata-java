package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.Collections;
import java.util.List;

public class TripService {

    protected List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (getLoggedUser() == null) {
            throw new UserNotLoggedInException();
        }
        return user.isFriendWith(getLoggedUser()) ? tripsByUser(user) : noTrips();

    }

    private List noTrips() {
        return Collections.EMPTY_LIST;
    }

    protected List<Trip> tripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
