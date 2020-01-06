package readingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readingList.utils.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {

    Reader findByusername(String username);
}
