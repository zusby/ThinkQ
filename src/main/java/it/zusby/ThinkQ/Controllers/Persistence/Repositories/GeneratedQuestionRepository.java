package it.zusby.ThinkQ.Controllers.Persistence.Repositories;

import it.zusby.ThinkQ.Auth2.UserEntity;
import it.zusby.ThinkQ.Types.Dto.GeneratedQuestionDTO;
import it.zusby.ThinkQ.Types.Entity.DocumentEntity;
import it.zusby.ThinkQ.Types.Entity.GeneratedQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GeneratedQuestionRepository extends JpaRepository<GeneratedQuestionEntity, UUID> {

    @Query("select distinct g from GeneratedQuestionEntity g where g.document.id = ?1")
    List<GeneratedQuestionDTO> findDistinctByDocument_Id(UUID document_id);

}
