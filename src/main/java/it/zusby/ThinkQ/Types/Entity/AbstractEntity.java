package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AbstractEntity  implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @EqualsAndHashCode.Include
    private UUID id;

    private String appUser;

    private LocalDateTime createdAt;
}
