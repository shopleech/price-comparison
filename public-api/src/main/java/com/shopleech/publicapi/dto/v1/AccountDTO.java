package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

   private Integer id;
   private Status status;
}
