package com.blulogxi.taskfrist.services;

import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksServices {
    // git All data Courses
    ResponseEntity<List<BookDto>> findAll();

    ResponseEntity<List<BookDto>> findAllpage(int page, int size);

    // git oun Course data
    ResponseEntity<BookDto> findById(Long id);
    // create New Course
    ResponseEntity<BookDto> create(AddBookDto addBookDto);
    // Updata data Course
    ResponseEntity<BookDto> update(Long id, AddBookDto addBookDto);
    // delete Course
    ResponseEntity<String> remove(Long id);

    List<BookDto> searchBooks(String query);
}
