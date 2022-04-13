package com.example.demo.repository;

import com.example.demo.domain.AuthorUuid;
import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {

}
