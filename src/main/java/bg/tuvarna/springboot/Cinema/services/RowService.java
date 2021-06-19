package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Row;
import bg.tuvarna.springboot.Cinema.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RowService {

    @Autowired
    private RowRepository rowRepository;

    public List<Row> findAll() {
        List<Row> rows = (List<Row>) rowRepository.findAll();

        if (rows.size() > 0) {
            return rows;
        } else {
            return new ArrayList<Row>();
        }
    }


    public List<Row> getHallRows(long id) {
        return rowRepository.getHallRows(id);
    }

    public Row findById(long id) {
        Optional<Row> row = rowRepository.findById(id);

        if (row.isPresent()) {
            return row.get();
        } else {
            return null;
        }
    }

    public Row createOrUpdateRow(Row row) {

        Optional<Row> rowOptional = rowRepository.findById(row.getId());

        if (rowOptional.isPresent()) {
            Row newEntity = rowOptional.get();

            newEntity.setHall(row.getHall());
            newEntity.setRowNumber(row.getRowNumber());

            newEntity = rowRepository.save(newEntity);

            return newEntity;
        } else {
            rowRepository.save(row);

            return row;
        }
    }

    public void deleteById(Long id) {
        Optional<Row> row = rowRepository.findById(id);

        if (row.isPresent()) {
            rowRepository.deleteById(id);
        }
    }
}
