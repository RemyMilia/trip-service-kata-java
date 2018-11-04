package io.wesquad.tripservicekata.trip;

import io.wesquad.tripservicekata.exception.UserNotLoggedInException;
import io.wesquad.tripservicekata.user.User;
import io.wesquad.tripservicekata.user.UserSession;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        validateUser(loggedUser);
        if (user.isFriendWith(loggedUser)) {
            return findTripsByUser(user);
        }
        return emptyList();
    }

    private void validateUser(User loggedUser) {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> findTripsByUser(User user) {
        return tripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
