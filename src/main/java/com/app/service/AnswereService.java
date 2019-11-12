package com.app.service;

import com.app.Exception.MyException;
import com.app.dto.AnswereDto;
import com.app.model.Answer;
import com.app.modelMapper.MyModelMapper;
import com.app.repository.AnswereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswereService {

    private final AnswereRepository answereRepository;

    public AnswereDto add(AnswereDto answereDto) {
        if (answereDto == null) {
            throw new MyException("Answer shouldn't be null");
        }
        Answer answer = MyModelMapper.fromAnswereDtpToAnswere(answereDto);
        Answer answer1 = answereRepository.save(answer);
        return MyModelMapper.fromAnswereToAnswereDto(answer1);
    }

    public AnswereDto findById(Long id) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        return answereRepository
                .findById(id)
                .map(MyModelMapper::fromAnswereToAnswereDto)
                .orElseThrow(() -> new MyException("There is no answere with this id"));
    }

    public List<AnswereDto> findAll() {
        return answereRepository.findAll()
                .stream()
                .map(MyModelMapper::fromAnswereToAnswereDto)
                .collect(Collectors.toList());
    }

    public AnswereDto deleteAnswere(Long id) {
        if (id == null) {
            throw new MyException("id shouldnt be null");
        }
        AnswereDto answereDto = answereRepository.findById(id)
                .map(MyModelMapper::fromAnswereToAnswereDto)
                .orElseThrow(() -> new MyException("there is no answere with this id"));
        answereRepository.deleteById(id);
        return answereDto;
    }

    public AnswereDto updateAnswere(Long id, AnswereDto answereDto) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        if (answereDto == null) {
            throw new MyException("answere with update data shouldn't be null");
        }


        Answer answerFromDb = answereRepository.findById(id)
                .orElseThrow(() -> new MyException("There is no answere with this id"));

        answerFromDb.setContent(answereDto.getContent() == null ? answerFromDb.getContent() : answereDto.getContent());
        answerFromDb.setPoints(answereDto.getPoints() == null ? answerFromDb.getPoints() : answereDto.getPoints());
        answerFromDb.setQuestion(answereDto.getQuestion() == null ? answerFromDb.getQuestion() : answereDto.getQuestion());
        return MyModelMapper.fromAnswereToAnswereDto(answereRepository.save(answerFromDb));
    }
}
