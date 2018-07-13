package io.wesquad.tripservicekata.trip;

import java.util.List;

import io.wesquad.tripservicekata.exception.CollaboratorCallException;
import io.wesquad.tripservicekata.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}