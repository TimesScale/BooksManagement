// BorrowRepository.java
package repository;

import model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByReaderId(Long readerId);
    List<Borrow> findByBookId(Long bookId);
    Optional<Borrow> findByReaderIdAndBookIdAndReturnDateIsNull(Long readerId, Long bookId);
}
