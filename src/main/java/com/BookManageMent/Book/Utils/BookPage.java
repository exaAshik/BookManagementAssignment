package com.BookManageMent.Book.Utils;

import com.BookManageMent.Book.DTOs.BookDto;
import com.BookManageMent.Book.Entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPage {

    private List<BookDto>content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElement;
    private Integer totalPages;
    private boolean isLastPage;
}
