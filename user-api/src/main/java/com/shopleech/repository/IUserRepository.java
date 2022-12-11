package com.shopleech.repository;

import com.shopleech.domain.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableScan
@Repository
public interface IUserRepository extends CrudRepository<User, UUID> {

}
