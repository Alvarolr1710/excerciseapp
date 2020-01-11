package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readinglist.utils.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {

    Reader findByusername(String username);
}
