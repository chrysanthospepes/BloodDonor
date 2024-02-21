package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.entity.Notification;
import ds.course.group.fiftyone.blooddonor.service.CitizenService;
import ds.course.group.fiftyone.blooddonor.service.NotificationService;
import ds.course.group.fiftyone.blooddonor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;

    // Method to get all notifications for a user
    @Secured({"ROLE_USER"})
    @GetMapping("/get/{userId}")
    public List<Notification> getNotifications(@PathVariable("userId") Long userId) {
        return notificationService.getNotifications(userId);
    }

    // Method that creates a new notification for each user with a specific blood type
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("send-notification")
    public void sendNotification(@RequestBody String message) {
        if (message.isEmpty())
            message = "We need your help! Please donate blood!";
        notificationService.sendNotifications(message);
    }
}

