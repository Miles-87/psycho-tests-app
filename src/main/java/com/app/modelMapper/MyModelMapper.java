package com.app.modelMapper;

import com.app.dto.*;
import com.app.model.*;

public interface MyModelMapper {

    static AnswereDto fromAnswereToAnswereDto(Answer answer) {
        return AnswereDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .points(answer.getPoints())
                .question(answer.getQuestion())
                .build();
    }

    static Answer fromAnswereDtpToAnswere(AnswereDto answereDto) {
        return Answer.builder()
                .id(answereDto.getId())
                .content(answereDto.getContent())
                .points(answereDto.getPoints())
                .question(answereDto.getQuestion())
                .build();
    }

    static CategoryDto fromCategoryToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    static Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    static QuestionDto fromQuestionToQuestionDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .name(question.getName())
                .build();
    }

    static Question fromQuestionDtoToQuestion(QuestionDto questionDto) {
        return Question.builder()
                .id(questionDto.getId())
                .name(questionDto.getName())
                .build();
    }

    static QuizDto fromQuizToQuizDto(Quiz quiz) {
        return QuizDto.builder()
                .id(quiz.getId())
                .date(quiz.getDate())
                .build();
    }

    static Quiz fromQuizDtoToQuiz(QuizDto quizDto) {
        return Quiz.builder()
                .id(quizDto.getId())
                .date(quizDto.getDate())
                .build();
    }


    static UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .education(user.getEducation())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
    }

    static User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .age(userDto.getAge())
                .education(userDto.getEducation())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .build();
    }
}
