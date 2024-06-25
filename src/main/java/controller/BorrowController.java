// BorrowController.java
package controller;

import model.Borrow;
import repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Borrow> borrowBook(@RequestBody Borrow borrow) {
        borrow.setBorrowDate(new Date());
        borrow.setDueDate(new Date(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000)); // 2 weeks from now
        Borrow savedBorrow = borrowRepository.save(borrow);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrow);
    }

    @PutMapping("/return")
    public ResponseEntity<Borrow> returnBook(@RequestBody Borrow returnDetails) {
        Optional<Borrow> borrowOptional = borrowRepository.findByReaderIdAndBookIdAndReturnDateIsNull(returnDetails.getReaderId(), returnDetails.getBookId());
        if (borrowOptional.isPresent()) {
            Borrow borrow = borrowOptional.get();
            borrow.setReturnDate(new Date());
            borrow.setStatus("Returned");
            Borrow updatedBorrow = borrowRepository.save(borrow);
            return ResponseEntity.ok(updatedBorrow);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
