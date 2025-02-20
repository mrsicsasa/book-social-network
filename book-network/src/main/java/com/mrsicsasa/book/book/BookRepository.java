package com.mrsicsasa.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    Integer id(Integer id);

    @Query("""
        SELECT  book
        From Book book
        WHERE book.archived = false 
        AND  book.shareable = true 
        AND book.owner != :userId
""")
    Page<Book> findAllDisplayableBooks(Pageable pageable, Integer userId);
}
