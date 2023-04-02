package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.AccountBLLDTO;
import com.shopleech.publicapi.bll.mapper.AccountBLLMapper;
import com.shopleech.publicapi.dal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    protected AccountRepository accountRepository;

    protected AccountBLLMapper accountMapper;

    public AccountBLLDTO createAccount(AccountBLLDTO data) {
        return accountMapper.mapToDto(accountRepository.addAccount(accountMapper.mapToEntity(data)));
    }

    public AccountBLLDTO get(int id) {
        return accountMapper.mapToDto(accountRepository.getAccountById(id));
    }

    public List<AccountBLLDTO> getAll() {
        return accountMapper.mapToDto(accountRepository.getAllAccounts());
    }
}
