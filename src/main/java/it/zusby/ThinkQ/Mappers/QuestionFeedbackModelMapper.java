package it.zusby.ThinkQ.Mappers;

import it.zusby.ThinkQ.Types.Dto.QuestionFeedbackDTO;
import it.zusby.ThinkQ.Types.Entity.QuestionFeedbackEntity;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface QuestionFeedbackModelMapper {
    QuestionFeedbackDTO toDTO(QuestionFeedbackModel model);
    QuestionFeedbackModel toModel(QuestionFeedbackDTO entity);
}
