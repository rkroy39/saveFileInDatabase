package com.saveFileInDatabase.controller;

import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.saveFileInDatabase.Entities.Document_table;
import com.saveFileInDatabase.Repo.DocumentRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileHandlingController {
	
	 @Autowired
	    private DocumentRepo documentRepo;
	 @Autowired
	    private Document_table document_table;
	 
@RequestMapping("/")	
public String firstFirst() {
	return "Test";
	
}
@PostMapping(value="/submit")	
public String secondFirst( String doc_name, int id, MultipartFile file) throws IOException {
	System.out.println("Inside second");
//	doc.setId(1);
//	doc.setDoc_name("Test");
	document_table.setDoc_name(doc_name);
	document_table.setId(id);
	document_table.setDocument(file.getBytes());
	documentRepo.save(document_table);
	return "thanks";
	
}

@GetMapping("/retrieveDoc")
public void retrieveDoc(@RequestParam("id") String id, HttpServletResponse  response,  HttpServletRequest req) throws IOException {
    try {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/storefile", "postgres", "postgres");
        String sql = "SELECT document FROM document_table WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, Integer.valueOf("2"));
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
             InputStream inputStream = rs.getBinaryStream("document");
             response.setContentType("application/pdf");
             response.setHeader("Content-Disposition", "inline; filename=example.pdf");
             OutputStream outputStream = response.getOutputStream();
             byte[] buffer = new byte[1024];
             int bytesRead;
             while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, bytesRead);
             }

             inputStream.close();
             outputStream.close();
          
        } else {
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}
