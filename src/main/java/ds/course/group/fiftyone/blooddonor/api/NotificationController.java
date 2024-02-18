package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.entity.Notification;
import ds.course.group.fiftyone.blooddonor.service.CitizenService;
import ds.course.group.fiftyone.blooddonor.service.NotificationService;
import ds.course.group.fiftyone.blooddonor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // new method to get all notifications for a user
    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable("userId") Long userId) {
        return notificationService.getNotifications(userId);
    }

    // new method that creates a new notification for each user with a specific blood type
    @PostMapping("")
    public void sendNotification() {
        notificationService.sendNotifications("We need your help! Please donate blood!");
    }
}
