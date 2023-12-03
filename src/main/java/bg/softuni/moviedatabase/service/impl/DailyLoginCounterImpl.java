package bg.softuni.moviedatabase.service.impl;

import bg.softuni.moviedatabase.model.entity.UserEntity;
import bg.softuni.moviedatabase.service.DailyProfileHits;
import bg.softuni.moviedatabase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyLoginCounterImpl implements DailyProfileHits {


    private final UserService userService;

    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void updateDailyProfileHits() {

        List<UserEntity> allUsers = userService.getAllUsers();

        for (UserEntity user : allUsers) {
            logProfileVisitsToFile(user);
        }

    }

    private void logProfileVisitsToFile(UserEntity user) {
        String filePath = "src/main/resources/profile_visits.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.format("Profile Visits:\nID:%d - Name:%s - Visits: %d\n----------",
                    user.getId(), user.getUsername(), user.getProfileHits()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

