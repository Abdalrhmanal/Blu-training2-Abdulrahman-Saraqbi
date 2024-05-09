package com.blulogxi.taskfrist.services.implmantions;

import com.blulogxi.taskfrist.entities.books.Book;
import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;
import com.blulogxi.taskfrist.entities.mappears.BookMapparImpl;
import com.blulogxi.taskfrist.entities.mappears.BookMapper;
import com.blulogxi.taskfrist.reposeitories.books.BookRepositories;
import com.blulogxi.taskfrist.services.BooksServices;
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
// هذه الدالة تقوم بجلب جميع بيانات الكورسات في جدول الكورسات
    @Override
    public ResponseEntity<List<BookDto>> findAll() {
        List<Book> books=this.bookRepositories.findAll();
        List<BookDto> bookDtos =books.stream().map(mapper::bookToBookDto).collect(Collectors.toList());
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }
// من شان نجيب بيانات كورس محدد findById هنا اعددنا دالة
    @Override
    public ResponseEntity<BookDto> findById(Long id) {
        Optional<Book> course=this.bookRepositories.findById(id);
        if(course.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "books not found");
        }
        BookDto bookDto =mapper.bookToBookDto(course.get());
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }
    // هذه الدوال لسع ما اشتغلتها
        // هذه الدالة من اجل الاضافة
    @Override
    public ResponseEntity<BookDto> create(AddBookDto addBookDto) {
        final Book book = mapper.addBookDtoToBook(addBookDto);
        final Book createdBook = this.bookRepositories.save(book);
        BookDto bookDto = mapper.bookToBookDto(createdBook);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

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
        // هذه الدالة من اجل الحذف
        @Override
        public ResponseEntity<String> remove(Long id) {

            boolean exist = this.bookRepositories.existsById(id);
            if(!exist) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
            }
            this.bookRepositories.deleteById(id);
            return new ResponseEntity<>("Book has been removed successfully",HttpStatus.NO_CONTENT);
        }


}
