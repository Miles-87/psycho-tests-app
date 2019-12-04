package com.app.service;

import com.app.Exception.MyException;
import com.app.dto.QuestionDto;
import com.app.model.Question;
import com.app.modelMapper.MyModelMapper;
import com.app.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionDto add(QuestionDto questionDto){
        if(questionDto == null){
            throw new MyException("Question shouldn't be null");
        }
        Question question = MyModelMapper.fromQuestionDtoToQuestion(questionDto);
        Question question1 = questionRepository.save(question);
        return MyModelMapper.fromQuestionToQuestionDto(question1);
    }

    public QuestionDto findById(Long id){
        if(id == null){
            throw new MyException("id shouldn't be null");
        }
        return questionRepository.findById(id)
                .map(MyModelMapper::fromQuestionToQuestionDto)
                .orElseThrow(() ->new MyException("There is no question with this id"));
    }

    public List<QuestionDto> findAll(){
        return questionRepository.findAll()
                .stream()
                .map(MyModelMapper::fromQuestionToQuestionDto)
                .collect(Collectors.toList());
    }

    public QuestionDto deleteQuestion(Long id){
        if(id == null){
            throw new MyException("id shouldn't be null");
        }
        QuestionDto questionDto = questionRepository.findById(id)
                .map(MyModelMapper::fromQuestionToQuestionDto)
                .orElseThrow(() ->new MyException("there is no question with this id"));
        questionRepository.deleteById(id);
        return questionDto;
    }

    public QuestionDto updateCategory(Long id, QuestionDto questionDto){
        if(id == null){
            throw new MyException("id shouldn't be null");
        }
        if (questionDto == null) {
            throw new MyException("question with update data shouldn't be null");
        }
        Question questionFromDb = questionRepository.findById(id)
                .orElseThrow(()->new MyException("there is no question with this id"));
    }

}
