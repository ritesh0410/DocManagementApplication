package com.IndraSoftech.DocManagementApplication.service;

import com.IndraSoftech.DocManagementApplication.dto.DocumentDto;
import com.IndraSoftech.DocManagementApplication.exception.NotFoundException;
import com.IndraSoftech.DocManagementApplication.model.DocumentEntity;
import com.IndraSoftech.DocManagementApplication.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final Path uploadDir;
    private final DocumentRepository repository;

    public DocumentService(@Value("${app.upload.dir}") String uploadDir,DocumentRepository repository) throws IOException
    {
        this.uploadDir= Paths.get(uploadDir).toAbsolutePath().normalize();
        this.repository=repository;
        Files.createDirectories(this.uploadDir);
    }


    public DocumentDto store(MultipartFile file) throws  IOException
    {
        // validation :PDF only
        if (!"application/pdf".equalsIgnoreCase(file.getContentType()))
        {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        String original=Path.of(file.getOriginalFilename()).getFileName().toString();
        String filename= System.currentTimeMillis()+ "_"+ original;  // simple unique name
        Path target =uploadDir.resolve(filename);

        Files.copy(file.getInputStream(),target, StandardCopyOption.REPLACE_EXISTING);

        DocumentEntity e=new DocumentEntity();
        e.setFilename(original);
        e.setFilepath(target.toString());
        e.setFilesize(file.getSize());
        e.setCreatedAt(Instant.now());

        DocumentEntity saved = repository.save(e);
        return  new DocumentDto(saved.getId(), saved.getFilename(), saved.getFilesize(), saved.getCreatedAt());
    }

    public List<DocumentDto> listAll()
    {
        return repository.findAll().stream()
                .map(d-> new DocumentDto(d.getId(), d.getFilename(), d.getFilesize(), d.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Path getFilePath(Long id)
    {
        DocumentEntity e= repository.findById(id).orElseThrow(()->new NotFoundException("Document not found"));
        Path p= Paths.get(e.getFilepath());

        if (!Files.exists(p)) throw new NotFoundException("File missing on disk");

        return  p;
    }

    public void delete(Long id) throws IOException
    {
        DocumentEntity e = repository.findById(id).orElseThrow(() -> new NotFoundException("Document not found"));
        Path p = Paths.get(e.getFilepath());
        try{
                Files.deleteIfExists(p);
        }catch (IOException ex){
                ex.printStackTrace();
        }
        repository.deleteById(id);
    }
}
