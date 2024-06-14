import org.hibernate.query.named.NamedObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numbers")
public class AverageController {

    @Autowired
    private NamedObjectRepository numberRepository;

    @GetMapping("/{numberid}")
    public ResponseEntity<Double> calculateAverage(@PathVariable List<Double> numberid) {
        if (numberid.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        double sum = numberid.stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / numberid.size();

        return ResponseEntity.ok(average);
    }

    @PostMapping
    public ResponseEntity<Number> addNumber(@RequestBody Number number) {
        Number savedNumber = numberRepository.save(number);
        return new ResponseEntity<>(savedNumber, HttpStatus.CREATED);
    }
}
