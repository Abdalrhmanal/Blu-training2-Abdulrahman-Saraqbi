package com.blulogxi.taskfrist.controller.books;

import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;
import com.blulogxi.taskfrist.services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// هنا رابط موحد من اجل جميع الطلبات ك git,post,put,delete
@RequestMapping(path = "/api/v1/book")
public class BookController {
   private final BooksServices booksServices;

   @Autowired
   public BookController(BooksServices booksServices) {
       this.booksServices = booksServices;
   }
// git all data with Courses
   @GetMapping("")
   public ResponseEntity<List<BookDto>> getBooks() {
      return this.booksServices.findAll();
   }
   // git data Course by id
   @GetMapping("/{bookid}")
   public ResponseEntity<BookDto> getBookById(@PathVariable Long bookid) {
      return this.booksServices.findById(bookid);
   }

   @PostMapping()
   public ResponseEntity<BookDto> createBook(@RequestBody AddBookDto addBookDto) {
      return this.booksServices.create(addBookDto);
   }


   @PutMapping("/{bookid}")
   public ResponseEntity<BookDto> upDataBook(@PathVariable Long bookid, @RequestBody AddBookDto addBookDto) {
      return this.booksServices.update(bookid, addBookDto);
   }

   @DeleteMapping("/{bookid}")
   public ResponseEntity<String> deleteBook(@PathVariable Long bookid) {
      return this.booksServices.remove(bookid);
   }

}
