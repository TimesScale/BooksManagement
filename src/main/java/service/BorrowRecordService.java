package service;// ReaderService.java
import model.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BorrowRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowRecordService {
    @Autowired
    private BorrowRepository borrowRecordRepository;

    public Borrow saveBorrowRecord(Borrow borrowRecord) {
        return borrowRecordRepository.save(borrowRecord);
    }

    public List<Borrow> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public Optional<Borrow> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id);
    }

    public List<Borrow> getBorrowRecordsByReaderId(Long readerId) {
        return borrowRecordRepository.findByReaderId(readerId);
    }

    public List<Borrow> getBorrowRecordsByBookId(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId);
    }

    public void deleteBorrowRecord(Long id) {
        borrowRecordRepository.deleteById(id);
    }
}