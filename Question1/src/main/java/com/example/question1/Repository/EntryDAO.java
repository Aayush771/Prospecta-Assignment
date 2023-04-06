package com.example.question1.Repository;


import com.example.question1.Entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryDAO extends JpaRepository<Entry,String> {
}
