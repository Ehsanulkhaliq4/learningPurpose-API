package com.exam.examserver.service.bookSoreService;

import com.exam.examserver.model.bookStore.Books;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Books createBook(MultipartFile pdfBook, MultipartFile bookPicture, String description, String name, String author) throws IOException;

    List<Books> getAllBook();

    List<Books> searchByName(String name);
    void deleteBook(Long id);

    Optional<Books> getBookById(Long id);
}
