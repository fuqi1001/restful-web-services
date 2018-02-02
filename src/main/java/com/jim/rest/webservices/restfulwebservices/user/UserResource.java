package com.jim.rest.webservices.restfulwebservices.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService userDAOService;

    //GET ALL
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDAOService.findAll();
    }


    //GET {id} User
    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user =  userDAOService.findOne(id);
        if(user == null)
            throw new UserNotFoundException("no id-" + id);

        // HATEOAS
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo =
            linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    //POST new User
    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userDAOService.save(user);

        //CREATED
        //  /users/{newId}

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userDAOService.deleteById(id);
        if(user == null)
            throw new UserNotFoundException("id-"+id);
    }
}
