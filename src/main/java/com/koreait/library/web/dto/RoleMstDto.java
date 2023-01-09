package com.koreait.library.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleMstDto {
    private String roleId;

    private String roleName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
