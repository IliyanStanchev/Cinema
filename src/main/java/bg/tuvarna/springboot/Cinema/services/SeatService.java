package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Seat;
import bg.tuvarna.springboot.Cinema.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> findAll() {
        List<Seat> seats = (List<Seat>) seatRepository.findAll();

        if (seats.size() > 0) {
            return seats;
        } else {
            return new ArrayList<Seat>();
        }
    }

    public Seat findById(long id) {
        Optional<Seat> seat = seatRepository.findById(id);

        if (seat.isPresent()) {
            return seat.get();
        } else {
            return null;
        }
    }

    public Seat createOrUpdateSeat(Seat seat) {

        Optional<Seat> seatOptional = seatRepository.findById(seat.getId());

        if (seatOptional.isPresent()) {
            Seat newEntity = seatOptional.get();

            newEntity.setSeatNumber(seat.getSeatNumber());
            newEntity.setRow(seat.getRow());

            newEntity = seatRepository.save(newEntity);

            return newEntity;
        } else {
            seatRepository.save(seat);

            return seat;
        }
    }

    public void deleteById(Long id) {
        Optional<Seat> seat = seatRepository.findById(id);

        if (seat.isPresent()) {
            seatRepository.deleteById(id);
        }
    }

    public List<Seat> getSeatsByHall(long id) {
        return seatRepository.getSeatsByHall(id);
    }
}
