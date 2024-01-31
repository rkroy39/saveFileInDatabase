package com.saveFileInDatabase.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saveFileInDatabase.Entities.Document_table;

@Repository
public interface DocumentRepo extends JpaRepository<Document_table, Integer> {

}
