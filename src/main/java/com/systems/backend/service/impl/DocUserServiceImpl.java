package com.systems.backend.service.impl;

import com.systems.backend.model.DocUser;
import com.systems.backend.repository.DocUserRepository;
import com.systems.backend.service.DocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocUserServiceImpl implements DocUserService {
    @Autowired
    private DocUserRepository docUserRepository;

    @Override
    public List<DocUser> getAllDocUsers() {
        return docUserRepository.findAll();
    }

    @Override
    public DocUser getDocUserById(Long docUserId) {
        Optional<DocUser> docUser = docUserRepository.findById(docUserId);
        return docUser.orElse(null);
    }

    @Override
    public DocUser createDocUser(DocUser docUser) {
        DocUser checkDocUser = getDocUserById(docUser.getId());
        if (checkDocUser == null) {
            throw new RuntimeException("User not found!");
        }
        return docUserRepository.save(docUser);
    }

    @Override
    public DocUser updateDocUser(Long docUserId, DocUser docUser) {
        DocUser updateDocUser = getDocUserById(docUserId);
        if (updateDocUser == null) {
            throw new RuntimeException("User not found!");
        }

        updateDocUser.setDateOfBirth(docUser.getDateOfBirth());
        updateDocUser.setName(docUser.getName());
        updateDocUser.setEmail(docUser.getEmail());
        updateDocUser.setAddress(docUser.getAddress());
        updateDocUser.setPhone(docUser.getPhone());
        updateDocUser.setGender(docUser.getGender());
        updateDocUser.setAddress(docUser.getAddress());

        return docUserRepository.save(docUser);
    }

    @Override
    public void deleteDocUser(Long docUserId) {
        DocUser role = getDocUserById(docUserId);
        if (role == null) {
            throw new RuntimeException("Role is not found!");
        }
        docUserRepository.delete(role);
    }
}
