package io.wesquad.tripservicekata.trip;

import io.wesquad.tripservicekata.exception.CollaboratorCallException;
import io.wesquad.tripservicekata.user.User;

import java.util.List;

public class TripDAO {

    public List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException(
                "TripDAO should not be invoked on an unit test.");
    }

}