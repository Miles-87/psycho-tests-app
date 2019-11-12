package com.app.dto;

import com.app.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswereDto {

    private Long id;
    private String content;
    private Question question;
    private Integer points;
}
