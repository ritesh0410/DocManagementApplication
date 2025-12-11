package com.IndraSoftech.DocManagementApplication.dto;

import java.time.Instant;

public record DocumentDto(Long id, String filename, Long filesize, Instant createdAt) {
}
