// ReaderController.java
package controller;

import model.Reader;
import repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        Reader savedReader = readerRepository.save(reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReader);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable Long id, @RequestBody Reader readerDetails) {
        return readerRepository.findById(id)
                .map(reader -> {
                    reader.setCardNumber(readerDetails.getCardNumber());
                    reader.setName(readerDetails.getName());
                    reader.setGender(readerDetails.getGender());
                    reader.setPhone(readerDetails.getPhone());
                    reader.setEmail(readerDetails.getEmail());
                    reader.setCategory(readerDetails.getCategory());
                    Reader updatedReader = readerRepository.save(reader);
                    return ResponseEntity.ok(updatedReader);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReader(@PathVariable Long id) {
        return readerRepository.findById(id)
                .map(reader -> {
                    readerRepository.delete(reader);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
