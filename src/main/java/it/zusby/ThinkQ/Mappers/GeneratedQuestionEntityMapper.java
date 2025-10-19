package it.zusby.ThinkQ.Mappers;

import it.zusby.ThinkQ.Types.Entity.GeneratedQuestionEntity;
import it.zusby.ThinkQ.Types.Model.GeneratedQuestionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneratedQuestionEntityMapper {
    @Mapping(target = "documentId", source = "document.id")
    GeneratedQuestionModel toModel(GeneratedQuestionEntity entity);

    @Mapping(target = "document", ignore = true) // lo settiamo a mano dopo
    GeneratedQuestionEntity toEntity(GeneratedQuestionModel model);
}
