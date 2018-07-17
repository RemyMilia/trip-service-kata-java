package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

public class TripService {


    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    protected List<Trip> getTripsByUser(User friend, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
        return friend.isFriendWith(loggedInUser) ? tripsByUser(friend) : noTrips();

    }

    private List noTrips() {
        return Collections.EMPTY_LIST;
    }

    private List<Trip> tripsByUser(User user) {
        return tripDAO.tripsBy(user);
    }

}
