package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {
    private String path;
    private String fileName;
    private InputStream inputStream;
}
