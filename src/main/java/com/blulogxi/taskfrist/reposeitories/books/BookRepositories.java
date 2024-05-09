package com.blulogxi.taskfrist.reposeitories.books;

import com.blulogxi.taskfrist.entities.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// عند ما استخدمت JPA فان JpaRepository الافتراضية و الخاصة بها تقوم باستخدام Hibernate

public interface BookRepositories extends JpaRepository<Book, Long> {


}
