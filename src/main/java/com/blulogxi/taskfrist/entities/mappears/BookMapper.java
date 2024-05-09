package com.blulogxi.taskfrist.entities.mappears;

import com.blulogxi.taskfrist.entities.books.Book;
import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.books.dto.BookDto;

public interface BookMapper {
    Book addBookDtoToBook(AddBookDto addBookDto);

    BookDto bookToBookDto(Book book);
}
