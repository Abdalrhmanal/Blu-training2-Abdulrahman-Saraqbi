package com.blulogxi.taskfrist.entities.books;

import com.blulogxi.taskfrist.entities.BaseEntities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

// هذا class من اجل انشاء جدول بقاعدة البيانات

@Entity
@Table(name = "books") //تحديد اسم الجدول بقاعدة البيانات
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntities {

    @NotNull
    @NotBlank(message = "Title may not blank")// رسالة في حال عدم وجود بيانات
    private String title;

    @NotNull // يجب ان تكون القيمة اجبارية
    @NotBlank(message = "description may not blank")
    private String description;

    @NotNull(message = "Prise not Null")
    @DecimalMin("1.0") // اقل قيمة 1
    private BigDecimal price;

    @NotNull()
    @NotBlank(message = "Author may not be blank")
    private String author;

    @NotNull(message = "Publication year cannot be null")
    private LocalDate publicationYear;
}
