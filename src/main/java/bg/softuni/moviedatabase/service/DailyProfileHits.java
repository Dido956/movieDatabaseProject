package bg.softuni.moviedatabase.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface DailyProfileHits {

    @Scheduled(cron = "0 0 0 * * *")
    void updateDailyProfileHits();
}
