package com.systems.backend.controller;

import com.systems.backend.model.DocUser;
import com.systems.backend.service.DocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocUserController {
    @Autowired
    private DocUserService docUserService;

    @GetMapping
    public ResponseEntity<List<DocUser>> getAllDocUsers() {
        List<DocUser> docUsers = docUserService.getAllDocUsers();
        return ResponseEntity.ok(docUsers);
    }

    @PostMapping
    public ResponseEntity<DocUser> createDocUser(@RequestBody DocUser docUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(docUserService.createDocUser(docUser));
    }

    @GetMapping("{docUserId}")
    public ResponseEntity<DocUser> getDocUser(@PathVariable(name = "docUserId") Long docUserId) {
        return ResponseEntity.ok(docUserService.getDocUserById(docUserId));
    }

    @RequestMapping(value = "{docUserId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<DocUser> updateDocUser(@PathVariable(name = "docUserId") Long docUserId, @RequestBody DocUser docUser) {
        return ResponseEntity.ok(docUserService.updateDocUser(docUserId, docUser));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{docUserId}/delete")
    public void deleteDocUser(@PathVariable(name = "docUserId") Long accountId) {
        docUserService.deleteDocUser(accountId);
    }
}
