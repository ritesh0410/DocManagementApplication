package com.IndraSoftech.DocManagementApplication.repository;

import com.IndraSoftech.DocManagementApplication.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity,Long> {
}
