package it.zusby.ThinkQ.Controllers.Persistence.Repositories;

import it.zusby.ThinkQ.Types.Dto.GeneratedQuestionDTO;
import it.zusby.ThinkQ.Types.Entity.GeneratedQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface GeneratedQuestionRepository extends JpaRepository<GeneratedQuestionEntity, UUID> {

    @Query("select distinct g from GeneratedQuestionEntity g where g.document.id = ?1")
    List<GeneratedQuestionDTO> findDistinctByDocument_Id(UUID document_id);

    @Transactional
    @Modifying
    @Query("update GeneratedQuestionEntity g set g.correctAnswer = ?1 where g.id = ?2")
    void updateCorrectAnswerById(@NonNull String correctAnswer, @NonNull UUID id);

}
