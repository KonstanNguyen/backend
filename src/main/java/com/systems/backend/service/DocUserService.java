package com.systems.backend.service;

import com.systems.backend.model.DocUser;
import com.systems.backend.requests.CreateDocUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocUserService {
    List<DocUser> getAllDocUsers();
    DocUser getDocUserById(Long docUserId);
    DocUser createDocUser(CreateDocUserRequest createDocUserRequest);
    DocUser updateDocUser(Long docUserId, DocUser docUser);
    void deleteDocUser(Long docUserId);
}
