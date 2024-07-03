package com.pl.example.books.data;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    @Query("SELECT new com.pl.example.books.data.BookOrderReportItem(bor.isbn, bor.title, SUM(bor.quantity)) " +
            "FROM BookOrderReport bor " +
            "WHERE bor.orderDate >= :fromDate " +
              "AND bor.orderDate < :toDate " +
            "GROUP BY bor.isbn, bor.title")
    List<BookOrderReportItem> getBookOrdersByDate(LocalDate fromDate, LocalDate toDate);
}
