package it.zusby.ThinkQ.Mappers;

import it.zusby.ThinkQ.Types.Entity.DocumentEntity;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentEntityMapper {
    DocumentEntity fromModelToEntity(DocumentModel model);
    DocumentModel fromEntityToModel(DocumentEntity entity);
}
