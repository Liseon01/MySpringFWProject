package mylab.notification.di.annot.config;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.NotificationService;
import mylab.notification.di.annot.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void manager_주입_NotNull() {
        assertNotNull(notificationManager);
    }

    @Test
    void 이메일_서비스_검증() {
        NotificationService emailSvc = notificationManager.getEmailService();
        assertNotNull(emailSvc);
        assertTrue(emailSvc instanceof EmailNotificationService);

        EmailNotificationService email = (EmailNotificationService) emailSvc;
        assertEquals("smtp.gmail.com", email.getSmtpServer());
        assertEquals(587, email.getPort());
    }

    @Test
    void SMS_서비스_검증() {
        NotificationService smsSvc = notificationManager.getSmsService();
        assertNotNull(smsSvc);
        assertTrue(smsSvc instanceof SmsNotificationService);

        SmsNotificationService sms = (SmsNotificationService) smsSvc;
        assertEquals("SKT", sms.getProvider());
    }

    @Test
    void 매니저_메서드_실행() {
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}
