package hh.harjoitustyo.ajanhallintadashboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Controller
@RequestMapping("/notes")
public class NoteController {
  @PostMapping("/download")
  public ResponseEntity<InputStreamResource> downloadMarkdown(@RequestParam("content") String markdownContent) {
    byte[] data = markdownContent.getBytes(StandardCharsets.UTF_8);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

    InputStreamResource resource = new InputStreamResource(inputStream);

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"note.md\"")
      .contentType(MediaType.parseMediaType("text/markdown"))
      .body(resource);
  }
}
