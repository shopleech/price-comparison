package com.shopleech.publicapi.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 16.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRefreshDTO {
    private String token;
    private String refreshToken;
}
