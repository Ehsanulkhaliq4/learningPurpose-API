package com.exam.examserver.repo.bookstorerepo;

import com.exam.examserver.model.bookStore.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books,Long> {


    List<Books> findByTitleContaining(String title);

    Optional<Books> findByTitle(String title);
}
