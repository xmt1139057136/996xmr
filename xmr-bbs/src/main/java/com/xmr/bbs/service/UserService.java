package com.xmr.bbs.service;

import com.xmr.bbs.dto.NewUserDTO;
import com.xmr.bbs.dto.ResultTypeDTO;
import com.xmr.bbs.modal.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void save(User user);

    User findUserByToken(String token);

    void SaveOrUpdate(User user);

    List<NewUserDTO> findNewsUsers(Integer top);

    List<User> getFollowList( User user);

    List<User> getFansList(User user);

    ResultTypeDTO signIn(Integer id);

    boolean isSigined(Integer id);
}
