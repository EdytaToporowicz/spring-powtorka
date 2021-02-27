package pl.sdacademy.wiosnademo.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private Map<String, List<String>> details;
}
