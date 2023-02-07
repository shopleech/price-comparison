package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.dal.mapper.UserDALMapper;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

   private UserRepository userRepository;

   private UserDALMapper userDALMapper;

   @Override
   public void addUser(UserDALDTO user) {
      //userRepository.add(userDALMapper.mapToEntity(user));
   }

   @Override
   public UserDALDTO getUserById(Integer id) {
      return userDALMapper.mapToDto(userRepository.getReferenceById(id));
   }

   @Override
   public List<UserDALDTO> getAllUsers() {
      return userDALMapper.mapToDto(userRepository.findAll());
   }
}
