package com.ggi.controller;

import com.ggi.domain.service.ClientService;
import com.ggi.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersController {

    @Qualifier("clientServiceImpl")
    @Autowired
    private ClientService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<UserResource> getAll(Pageable pageable) {
        return userDetailsService.getAll(pageable).stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
    }
    private UserResource convertToResource(User user) {
        return modelMapper.map(user, UserResource.class);
    }

}
