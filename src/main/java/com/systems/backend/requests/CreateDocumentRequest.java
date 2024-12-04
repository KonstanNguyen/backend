package com.systems.backend.requests;

import com.systems.backend.model.DocUser;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocumentRequest {
    @NotBlank(message = "Thumbnail is required!")
    private String thumbnail;
    @NotBlank(message = "Title is required!")
    private String title;
    @NotBlank(message = "Author is required!")
    private DocUser author;
    @NotBlank(message = "Content is required!")
    private String content;
}