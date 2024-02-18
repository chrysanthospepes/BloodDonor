package ds.course.group.fiftyone.blooddonor.service;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.entity.Notification;
import ds.course.group.fiftyone.blooddonor.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CitizenService citizenService;

    public void sendNotifications(String message) {
        List<Citizen> citizens = citizenService.getDonors();

        // Create a new notification for each user
        for (Citizen citizen : citizens) {
            // get the user id from the citizen
            Long userId = citizen.getUserId();

            Notification notification = new Notification(userId, message);
            notificationRepository.save(notification);
        }
    }

    // new method that returns all notifications for a user
    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findAllByUserId(userId);
    }
}
