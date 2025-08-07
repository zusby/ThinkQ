package it.zusby.ThinkQ.Types.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractCreateDTO implements Serializable {

    private String appUser;
}
