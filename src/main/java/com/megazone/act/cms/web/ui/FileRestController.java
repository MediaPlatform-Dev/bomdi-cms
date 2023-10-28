package com.megazone.act.cms.web.ui;

import com.megazone.act.cms.application.dto.FileService;
import com.megazone.act.cms.application.dto.response.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
public class FileRestController {

    private final FileService fileService;

    @GetMapping("/files")
    public ResponseEntity<Resource> file(@RequestParam String name) throws IOException {
        FileResponse response = fileService.download(name);
        Resource resource = response.resource();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + response.originalName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.parseMediaType(response.type()).toString())
                .contentLength(resource.contentLength())
                .body(response.resource());
    }
}
