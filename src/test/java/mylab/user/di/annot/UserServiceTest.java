package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("DI 주입 및 기능 동작 검증")
    void di_and_behavior() {
        assertNotNull(userService);

        assertNotNull(userService.getUserRepository());
        assertEquals("MySQL", userService.getUserRepository().getDbType());

        assertNotNull(userService.getSecurityService());
        assertTrue(userService.registerUser("user1", "홍길동", "1234"));
    }
}
