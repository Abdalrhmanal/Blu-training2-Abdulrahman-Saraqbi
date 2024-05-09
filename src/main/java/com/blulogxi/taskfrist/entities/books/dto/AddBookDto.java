package com.blulogxi.taskfrist.entities.books.dto;

import com.blulogxi.taskfrist.entities.books.Book;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

// في هذا الملف ايضا نحدد البيانات التي يستطيع المستخدم ادخالها عند اضافة كورس
// فهو يحدد البيانات التي سوف يحملها من العميل او المستخدم الى الخادم

public class AddBookDto {
    private final Book book;
    @NotNull(message = "Price is required")
    private BigDecimal price;

    public AddBookDto(Book book) {
        this.book = book;
    }

    public AddBookDto(String title, String description, BigDecimal price, String author, LocalDate publicationYear) {
        this.book = new Book(title, description, price, author, publicationYear);
        this.price = price;
    }

    public String getTitle() {
        return this.book.getTitle();
    }

    public String getDescription() {
        return this.book.getDescription();
    }

    public BigDecimal getPrice() {
        if (price == null) {
            BigDecimal price = this.book.getPrice();
            if (price == null) {
                return null;
            }
            return price.setScale(3, RoundingMode.HALF_UP);
        }
        return price.setScale(3, RoundingMode.HALF_UP);
    }

    public String getAuthor(){
        return this.book.getAuthor();
    }

    public LocalDate getPublicationYear(){
        return this.book.getPublicationYear();
    }
}