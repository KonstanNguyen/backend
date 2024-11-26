package com.systems.backend.service;

import com.systems.backend.model.DocUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocUserService {
    List<DocUser> getAllDocUsers();
    DocUser getDocUserById(Long docUserId);
    DocUser createDocUser(DocUser docUser);
    DocUser updateDocUser(Long docUserId, DocUser docUser);
    void deleteDocUser(Long docUserId);
}
