package it.zusby.ThinkQ.Types.Model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class AbstractModel implements Serializable {
    private UUID id;
    private String appUser;
    private LocalDateTime createdAt;
}
