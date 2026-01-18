package com.minidrive.minidrive.model;

import java.time.LocalDateTime;

public class FileInfo {

    private String name;
    private String size;   // 👈 human readable
    private String type;
    private LocalDateTime uploadedAt;

    public FileInfo(String name, String size, String type, LocalDateTime uploadedAt) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.uploadedAt = uploadedAt;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
