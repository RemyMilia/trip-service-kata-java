package org.craftedsw.tripservicekata.user;

import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.trip.UserBuilder;
import org.junit.Test;

public class UserTest {


    private static final User PETER = new User();
    private static final User CLEMENT = new User();

    @Test
    public void should_inform_when_users_are_not_friends() {
        User user = UserBuilder
                .aUser()
                .withFriends(PETER)
                .build();

        Assertions.assertThat(user.isFriendWith(CLEMENT)).isFalse();
    }

    @Test
    public void should_inform_when_users_are_friends() {
        User user = UserBuilder
                .aUser()
                .withFriends(PETER)
                .build();

        Assertions.assertThat(user.isFriendWith(PETER)).isTrue();
    }
}
