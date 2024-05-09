package com.blulogxi.taskfrist.entities.books.dto;

import com.blulogxi.taskfrist.entities.books.Book;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@ToString
// لماذا استخدمت نمط التصميم DTO ؟؟
//  لتجنب إرسال البيانات الكاملة من طبقة إلى أخرى وللحد من الاعتماد على هيكل البيانات الداخلي.
// (يعني بحدد البيانات الي لازم ارسلها للعرض هون)
public class BookDto {
    private final Book book;

    public BookDto(Book book) {
        this.book = book;
    }

    public String title() {
        return book.getTitle();
    }

    public String description() {
        return book.getDescription();
    }

    public BigDecimal price() {
        BigDecimal price = book.getPrice();
        return price.setScale(2, RoundingMode.HALF_UP);
    }
    public String author(){
        return book.getAuthor();
    }

    public LocalDate publicationYear(){
        return book.getPublicationYear();
    }
}