package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Showtime;
import bg.tuvarna.springboot.Cinema.repositories.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> findAll() {
        List<Showtime> showtimes = (List<Showtime>) showtimeRepository.findAll();

        if (showtimes.size() > 0) {
            return showtimes;
        } else {
            return new ArrayList<Showtime>();
        }
    }

    public Showtime findById(long id) {
        Optional<Showtime> showtimeOptional = showtimeRepository.findById(id);

        if (showtimeOptional.isPresent()) {
            return showtimeOptional.get();
        } else {
            return null;
        }
    }

    public Showtime createOrUpdateShowtime(Showtime showtime) {

        Optional<Showtime> showtimeOptional = showtimeRepository.findById(showtime.getId());

        if (showtimeOptional.isPresent()) {
            Showtime newEntity = showtimeOptional.get();

            newEntity.setEndTime(showtime.getEndTime());
            newEntity.setMovie(showtime.getMovie());
            newEntity.setType(showtime.getType());
            newEntity.setStartTime(showtime.getStartTime());
            newEntity.setHall(showtime.getHall());

            newEntity = showtimeRepository.save(newEntity);

            return newEntity;
        } else {

            showtime = showtimeRepository.save(showtime);

            return showtime;
        }
    }

    public void deleteById(Long id) {
        Optional<Showtime> showtime = showtimeRepository.findById(id);

        if (showtime.isPresent()) {
            showtimeRepository.deleteById(id);
        }
    }

    public Showtime checkShowtime(Showtime showtime) {

        return showtimeRepository.checkShowtime(showtime.getStartTime(), showtime.getEndTime(), showtime.getHall());
    }

    public List<Showtime> findAllByDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);

        return showtimeRepository.findAllByDate(calendar.getTime(), c.getTime());
    }
}
