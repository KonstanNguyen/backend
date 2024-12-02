package com.systems.backend.controller;

import com.systems.backend.model.DocUser;
import com.systems.backend.requests.CreateDocUserRequest;
import com.systems.backend.service.DocUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/doc-users")
public class DocUserController {
    @Autowired
    private DocUserService docUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DocUser> getAllDocUsers() {
        return docUserService.getAllDocUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DocUser createDocUser(@RequestBody CreateDocUserRequest createDocUserRequest) {
        return docUserService.createDocUser(createDocUserRequest);
    }

    @GetMapping("{docUserId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DocUser getDocUser(@PathVariable(name = "docUserId") Long docUserId) {
        return docUserService.getDocUserById(docUserId);
    }

    @RequestMapping(value = "{docUserId}/update", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DocUser updateDocUser(@PathVariable(name = "docUserId") Long docUserId, @RequestBody DocUser docUser) {
        return docUserService.updateDocUser(docUserId, docUser);
    }

    @DeleteMapping("{docUserId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocUser(@PathVariable(name = "docUserId") Long accountId) {
        docUserService.deleteDocUser(accountId);
    }
}
