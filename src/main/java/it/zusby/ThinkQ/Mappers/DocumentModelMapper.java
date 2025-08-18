package it.zusby.ThinkQ.Mappers;

import it.zusby.ThinkQ.Types.Dto.DocumentCreateDTO;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentModelMapper {
    DocumentModel fromCreateDTO(DocumentCreateDTO dto);
}
