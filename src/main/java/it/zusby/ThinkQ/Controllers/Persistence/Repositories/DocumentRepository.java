package it.zusby.ThinkQ.Controllers.Persistence.Repositories;

import it.zusby.ThinkQ.Auth2.UserEntity;
import it.zusby.ThinkQ.Types.Entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {
}
