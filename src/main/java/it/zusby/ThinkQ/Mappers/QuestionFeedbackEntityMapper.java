package it.zusby.ThinkQ.Mappers;

import it.zusby.ThinkQ.Types.Entity.QuestionFeedbackEntity;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionFeedbackEntityMapper {
    QuestionFeedbackEntity toEntity(QuestionFeedbackModel model);
    QuestionFeedbackModel toModel(QuestionFeedbackEntity entity);
}
