package com.blulogxi.taskfrist.config;
import com.blulogxi.taskfrist.entities.books.Book;
import com.blulogxi.taskfrist.entities.books.dto.AddBookDto;
import com.blulogxi.taskfrist.entities.mappears.BookMapparImpl;
import com.blulogxi.taskfrist.entities.mappears.BookMapper;
import com.blulogxi.taskfrist.reposeitories.books.BookRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.IntStream;
@Configuration
public class AppConfig {

    @Bean
    public BookMapper bookMapper() {
        return new BookMapparImpl();
    }
    // هذا الملف من اجل ملئ الجدول بقاعدة البيانات ببيانات عشوائية ل 10 ريكوردات
    // ويعتبر ايضا ملف اعدادات المشروع نستطيع اعداد به كل ما نريده في المشروع
    @Bean
    CommandLineRunner initDatabase(BookRepositories bookRepositories, BookMapper bookMapper) {
        return args -> {
            IntStream.rangeClosed(1,10).forEach(i -> {
                AddBookDto addBookDto =
                        new AddBookDto("Book #" + i,
                                "Learn Courses in the book #" +i + " in the best way.",
                                new BigDecimal(String.valueOf(i*100)),
                                "Abdulrahman #"+i,
                                LocalDate.of(2022, 1, i)
                                );
                Book book = bookMapper.addBookDtoToBook(addBookDto);
                bookRepositories.save(book);
            });
        };
    }
}
