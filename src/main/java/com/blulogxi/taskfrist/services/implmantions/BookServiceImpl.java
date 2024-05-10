package com.blulogxi.taskfrist.services.implmantions;

import com.blulogxi.taskfrist.entities.books.Book;
import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;
import com.blulogxi.taskfrist.entities.mappears.BookMapparImpl;
import com.blulogxi.taskfrist.entities.mappears.BookMapper;
import com.blulogxi.taskfrist.reposeitories.books.BookRepositories;
import com.blulogxi.taskfrist.services.BooksServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BooksServices {

    private final BookRepositories bookRepositories;
    private final BookMapper mapper=new BookMapparImpl();

    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;

    }
// git all Books in the table books
    @Override
    public ResponseEntity<List<BookDto>> findAll() {
        List<Book> books=this.bookRepositories.findAll();
        List<BookDto> bookDtos =books.stream().map(mapper::bookToBookDto).collect(Collectors.toList());
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }
// function from git Books use pagination
    @Override
    public ResponseEntity<List<BookDto>> findAllpage(int page,int size) {
        Page<Book> bookPage = this.bookRepositories.findAll(PageRequest.of(page, size));
        List<BookDto> bookDtos = bookPage.getContent().stream()
                .map(mapper::bookToBookDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookDtos,HttpStatus.OK);
    }

// git book with by id
    @Override
    public ResponseEntity<BookDto> findById(Long id) {
        Optional<Book> course=this.bookRepositories.findById(id);
        if(course.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "books not found");
        }
        BookDto bookDto =mapper.bookToBookDto(course.get());
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    // create new book
    @Override
    public ResponseEntity<BookDto> create(AddBookDto addBookDto) {
        final Book book = mapper.addBookDtoToBook(addBookDto);
        final Book createdBook = this.bookRepositories.save(book);
        BookDto bookDto = mapper.bookToBookDto(createdBook);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }
// upData book with by id book
    @Override
    public ResponseEntity<BookDto> update(Long id, AddBookDto newBook) {

        Optional<Book> book = this.bookRepositories.findById(id);
        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        Book existingBook = book.get();
        existingBook.setTitle(newBook.getTitle());
        existingBook.setDescription(newBook.getDescription());
        existingBook.setPrice(newBook.getPrice());
        existingBook.setAuthor(newBook.getAuthor());
        existingBook.setPublicationYear(newBook.getPublicationYear());
        Book updatedBook = this.bookRepositories.save(existingBook);
        BookDto courseDto = mapper.bookToBookDto(updatedBook);
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }
        // delete book by id
        @Override
        public ResponseEntity<String> remove(Long id) {

            boolean exist = this.bookRepositories.existsById(id);
            if(!exist) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
            }
            this.bookRepositories.deleteById(id);
            return new ResponseEntity<>("Book has been removed successfully",HttpStatus.NO_CONTENT);
        }
// search for a book by Author Or Title book ,Kay Word
        @Override
        public List<BookDto> searchBooks(String query) {
            List<Book> books = bookRepositories.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
            List<BookDto> bookDtos = books.stream().map(mapper::bookToBookDto).collect(Collectors.toList());
            return bookDtos;
        }


}
