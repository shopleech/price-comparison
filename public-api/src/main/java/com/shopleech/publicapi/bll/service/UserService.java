package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.MyUserDetails;
import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.bll.mapper.UserBLLMapper;
import com.shopleech.publicapi.bll.util.JwtTokenUtil;
import com.shopleech.publicapi.dal.mapper.UserDALMapper;
import com.shopleech.publicapi.dal.repository.AccountRepository;
import com.shopleech.publicapi.dal.repository.CustomerAccountRepository;
import com.shopleech.publicapi.dal.repository.CustomerRepository;
import com.shopleech.publicapi.dal.repository.UserRepository;
import com.shopleech.publicapi.domain.Account;
import com.shopleech.publicapi.domain.Customer;
import com.shopleech.publicapi.domain.CustomerAccount;
import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.dto.v1.UserRegisterDTO;
import com.shopleech.publicapi.dto.v1.UserTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Service
public class UserService implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepository roleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    private UserBLLMapper userBLLMapper;

    @Autowired
    private UserDALMapper userDALMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserBLLDTO getUser(String email) {
        return null;
    }

    @Override
    public UserBLLDTO getUserByUserId(String userId) {
        return null;
    }

    @Override
    public UserBLLDTO updateUser(UserBLLDTO userBLLDTO) {

        return userBLLMapper.mapToDto(userDALMapper.mapToDto(
                userRepository.save(userDALMapper.mapToEntity(
                        userBLLMapper.mapToEntity(userBLLDTO)))
        ));
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<UserBLLDTO> getUsers(int page, int limit) {
        return null;
    }

    public UserTokenDTO register(UserRegisterDTO request) throws Exception {

        if (!request.isConsent()) {
            throw new Exception("consent is missing");
        }

        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            throw new CredentialException("email and password is a must");
        }

        if (userRepository.isUsernameExist(request.getEmail()) > 0) {
            throw new Exception("user exists");
        }


//        if (loadUserByUsername(request.getEmail()).isEnabled()) {
//            throw new Exception("user is enabled");
//        }

        var user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setEnabled(true);

        var customer = new Customer();
        customer.setFirstName(user.getFirstname());
        customer.setLastName(user.getLastname());
        customer.setEmail(user.getEmail());
        customer.setValidFrom(Timestamp.from(Instant.now()));
        customer.setCreatedAt(Timestamp.from(Instant.now()));
        customer.setUpdatedAt(Timestamp.from(Instant.now()));
        var customerAdded = customerRepository.save(customer);

        user.setCustomer(customerAdded);
        user.setValidFrom(Timestamp.from(Instant.now()));
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        var userAdded = userRepository.save(user);

        var account = new Account();
        account.setValidFrom(Timestamp.from(Instant.now()));
        account.setCreatedAt(Timestamp.from(Instant.now()));
        account.setUpdatedAt(Timestamp.from(Instant.now()));
        var accountAdded = accountRepository.save(account);

        var customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customerAdded);
        customerAccount.setAccount(accountAdded);
        customerAccount.setValidFrom(Timestamp.from(Instant.now()));
        customerAccount.setCreatedAt(Timestamp.from(Instant.now()));
        customerAccount.setUpdatedAt(Timestamp.from(Instant.now()));
        var customerAccountAdded = customerAccountRepository.save(customerAccount);
        logger.info(customerAccountAdded.toString());

        var userDetails = loadUserByUsername(userAdded.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new UserTokenDTO(token, userAdded.getEmail());
    }

    @Override
    public User readUser(Long id) {
        return null;
    }

    @Override
    public List<UserBLLDTO> findUsers() {
        return userBLLMapper.mapToDto(userRepository.getAllUsers());
    }

    @Override
    public User post(User user) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        logger.info("loadUserByUsername: " + username);

        return new MyUserDetails(userRepository.getUserByUsername(username));
    }

    public static UserDetails currentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof UserDetails ? (UserDetails) principal : null;
        }
        return null;
    }
}
