package com.plagiarism.demo.controller;

import com.plagiarism.demo.model.PlagiarismRequest;
import com.plagiarism.demo.model.PlagiarismResponse;
import com.plagiarism.demo.service.PlagiarismService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plagiarism")
@CrossOrigin(origins = "http://localhost:5173")
public class PlagiarismController {

    private final PlagiarismService service;

    public PlagiarismController(PlagiarismService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "Java Plagiarism Checker Backend is Running";
    }

    @PostMapping("/check")
    public ResponseEntity<PlagiarismResponse> checkPlagiarism(
            @RequestBody PlagiarismRequest request) {

        if (request.getSubmittedText() == null ||
            request.getReferenceText() == null) {

            return ResponseEntity.badRequest().build();
        }

        if (request.getSubmittedText().trim().isEmpty() ||
            request.getReferenceText().trim().isEmpty()) {

            return ResponseEntity.badRequest().build();
        }

        PlagiarismResponse result =
                service.analyze(
                        request.getSubmittedText(),
                        request.getReferenceText()
                );

        return ResponseEntity.ok(result);
    }
}