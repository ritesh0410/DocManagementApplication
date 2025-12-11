package com.IndraSoftech.DocManagementApplication.controller;

import com.IndraSoftech.DocManagementApplication.dto.DocumentDto;
import com.IndraSoftech.DocManagementApplication.service.DocumentService;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service)
    {
        this.service=service;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentDto> upload(@RequestParam("file") MultipartFile file) throws IOException
    {
        DocumentDto dto=service.store(file);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PathResource> download(@PathVariable Long id) {
        Path p = service.getFilePath(id);
        PathResource resource = new PathResource(p);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + p.getFileName().toString() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
