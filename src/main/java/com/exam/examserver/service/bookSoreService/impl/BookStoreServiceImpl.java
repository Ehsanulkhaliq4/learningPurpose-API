package com.exam.examserver.service.bookSoreService.impl;

import com.exam.examserver.helper.DuplicateBookException;
import com.exam.examserver.model.bookStore.Books;
import com.exam.examserver.repo.bookstorerepo.BookRepository;
import com.exam.examserver.service.bookSoreService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;


    public Books createBook(MultipartFile pdfBook, MultipartFile bookPicture, String description, String title, String author) throws IOException {
        Optional<Books> byTitle = repository.findByTitle(title);
        if (byTitle.isPresent()) {
            throw new DuplicateBookException("This Book is Already Present !!!");
        }
        Books book = new Books();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPostedDate(new Date());
        book.setDescription(description);
        book.setPdfBook(pdfBook.getBytes());
        book.setBookPicture(bookPicture.getBytes());
        book.setContentType(pdfBook.getContentType());
        return repository.save(book);
    }

    public List<Books> getAllBook(){
        return repository.findAll();
    }

    public List<Books> searchByName(String title){
        return repository.findByTitleContaining(title);
    }

    public void deleteBook(Long id){
        repository.deleteById(id);
    }

    public Optional<Books> getBookById(Long id){
        return repository.findById(id);
    }
}
