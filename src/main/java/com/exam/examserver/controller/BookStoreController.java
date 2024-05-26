package com.exam.examserver.controller;


import com.exam.examserver.model.bookStore.Books;
import com.exam.examserver.repo.bookstorerepo.BookRepository;
import com.exam.examserver.service.bookSoreService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BookStoreController {

    @Autowired
    private BookService service;

    @Autowired
    private BookRepository repository;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestParam("pdfBook") MultipartFile pdfBook,
                                        @RequestParam("bookPicture")MultipartFile bookPicture,
                                        @RequestParam String description,
                                        @RequestParam String title,
                                        @RequestParam String author) throws IOException {
        // Check if files are empty
        if (bookPicture.isEmpty() || pdfBook.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Files are empty");
        }
        // Check if the files are of the correct type
        if (!"application/pdf".equals(pdfBook.getContentType())) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Only PDF files are allowed for book");
        }

        Books newBook = service.createBook(pdfBook, bookPicture, description,title,author);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Books>> findAllBooks(){
        return ResponseEntity.ok(service.getAllBook());
    }
    @GetMapping("/book/{keyword}")
    public ResponseEntity<?> searchByName(@PathVariable("keyword") String keyword){
        return ResponseEntity.ok(service.searchByName(keyword));
    }
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        service.deleteBook(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id){
        Books book = repository.findById(id).orElse(null);
        if(book != null){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(book.getContentType()));
            headers.setContentDisposition(ContentDisposition.attachment().filename(book.getTitle()).build());
            ByteArrayResource resource = new ByteArrayResource(book.getPdfBook());
            return ResponseEntity.ok().headers(headers).body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/bookById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getBookById(id));
    }

}
