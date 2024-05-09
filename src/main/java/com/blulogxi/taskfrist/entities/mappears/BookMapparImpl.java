package com.blulogxi.taskfrist.entities.mappears;

import com.blulogxi.taskfrist.entities.books.Book;
import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;

public class BookMapparImpl implements BookMapper {
    @Override
    public Book addBookDtoToBook(AddBookDto addBookDto) {

        // عند الاضافة Post يتم اضافة هذه الخصائص ومن ثم اضافة باقي الخصائص مثل ال Id  ومن ثم يتم الاضافة الى قاعدة البيانات

        Book book = new Book();
        book.setTitle(addBookDto.getTitle());
        book.setDescription(addBookDto.getDescription());
        book.setPrice(addBookDto.getPrice());
        book.setAuthor(addBookDto.getAuthor());
        book.setPublicationYear(addBookDto.getPublicationYear());
        return book;
    }

    @Override
    public BookDto bookToBookDto(Book book) {
        return new BookDto(book);

    }
}
