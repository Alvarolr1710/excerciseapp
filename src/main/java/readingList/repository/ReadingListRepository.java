package readingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readingList.utils.Book;
import readingList.utils.Reader;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(Reader reader);
}
