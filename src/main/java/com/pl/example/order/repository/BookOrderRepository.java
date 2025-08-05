package com.pl.example.order.repository;

import com.pl.example.order.dto.BookOrderReportItem;
import com.pl.example.order.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    @Query("SELECT new com.pl.example.order.dto.BookOrderReportItem(bor.isbn, bor.title, SUM(bor.quantity)) " +
            "FROM BookOrderReport bor " +
            "WHERE bor.orderDate >= :fromDate " +
            "AND bor.orderDate <= :toDate " +
            "GROUP BY bor.isbn, bor.title")
    List<BookOrderReportItem> getBookOrdersByDate(LocalDate fromDate, LocalDate toDate);
}
