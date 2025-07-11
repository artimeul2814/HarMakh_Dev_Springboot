package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @GetMapping
//    public ResponseEntity<List<String>> listFiles(@RequestParam(defaultValue = "") String path) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName(); // This is the logged-in user's username
        public ResponseEntity<List<String>> listFiles(@AuthenticationPrincipal Jwt jwt,
                                                  @RequestParam(defaultValue = "") String path) {
        String username = jwt.getClaimAsString("preferred_username");

        String basePath = "/home/" + username; // Dynamic path

        File dir = new File(basePath + File.separator + path);
        if (!dir.exists() || !dir.isDirectory()) {
            return ResponseEntity.badRequest().build();
        }
        String[] contents = dir.list();
        assert contents != null;
        return ResponseEntity.ok(Arrays.asList(contents));
    }
}