package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity  implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String appUser;

    private LocalDateTime createdAt;
}
