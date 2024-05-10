package com.blulogxi.taskfrist.controller.books;

import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;
import com.blulogxi.taskfrist.services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// هنا رابط موحد من اجل جميع الطلبات ك git,post,put,delete,search,pagination
@RequestMapping(path = "/api/v1/book")
public class BookController {
   private final BooksServices booksServices;

   @Autowired
   public BookController(BooksServices booksServices) {
       this.booksServices = booksServices;
   }

// git all data with Books
   @GetMapping("")
   public ResponseEntity<List<BookDto>> getBooks() {
      return this.booksServices.findAll();
   }

   // git data Books by Pagination
   @GetMapping("/p/{page}")
   public ResponseEntity<List<BookDto>> getBooks(@PathVariable int page,
                                                 @RequestParam(defaultValue = "10") int size) {
      return this.booksServices.findAllpage(page, size);
   }

   // git data Book by id
   @GetMapping("/{bookid}")
   public ResponseEntity<BookDto> getBookById(@PathVariable Long bookid) {
      return this.booksServices.findById(bookid);
   }
// Create New Book
   @PostMapping()
   public ResponseEntity<BookDto> createBook(@RequestBody AddBookDto addBookDto) {
      return this.booksServices.create(addBookDto);
   }

// ApData book with Id book
   @PutMapping("/{bookid}")
   public ResponseEntity<BookDto> upDataBook(@PathVariable Long bookid, @RequestBody AddBookDto addBookDto) {
      return this.booksServices.update(bookid, addBookDto);
   }
   // delete Book with by Id
   @DeleteMapping("/{bookid}")
   public ResponseEntity<String> deleteBook(@PathVariable Long bookid) {
      return this.booksServices.remove(bookid);
   }

// search in the Books with Author and title book
   //http://localhost:8080/api/v1/book/search?query=best
   @GetMapping("/search")
   public ResponseEntity<List<BookDto>> searchBooks(@RequestParam String query) {
      List<BookDto> bookDtos = booksServices.searchBooks(query);
      return ResponseEntity.ok(bookDtos);
   }
}
