package com.shopleech.publicapi.bll.model.service;

import com.shopleech.publicapi.dto.v1.AccountDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public interface IAccountService {
   List<AccountDTO> getAll();
}
