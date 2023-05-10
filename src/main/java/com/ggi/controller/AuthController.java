package com.ggi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ggi.model.ERole;
import com.ggi.model.Role;
import com.ggi.model.User;
import com.ggi.repository.RoleRepository;
import com.ggi.repository.UserRepository;
import com.ggi.payload.request.LoginReq;
import com.ggi.payload.request.SignupReq;
import com.ggi.payload.response.DefaultRes;
import com.ggi.payload.response.LoginRes;
import com.ggi.security.jwt.JwtUtils;
import com.ggi.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginReq loginRequest) {
        var res = new DefaultRes<LoginRes>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            res = new DefaultRes<LoginRes>("", false);
            var user = userRepository.findById(userDetails.getId());
            var loginRes = new LoginRes(userDetails.getId(), jwt, user.get().getFullname(), userDetails.getEmail(), roles);
            res.setResult(loginRes);

            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            res = new DefaultRes<LoginRes>(e.getMessage(), true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupReq signUpRequest) {
        var res = new DefaultRes();
        try {
            //var findUser = userRepository.existsByUsername(signUpRequest.getUsername());
            //if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            //    res = new DefaultRes("Error: Username is already taken!", true);
            //    return ResponseEntity
            //            .badRequest()
            //            .body(res);
            //}

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                res = new DefaultRes("Error: Email is already in use!", true);
                return ResponseEntity
                        .badRequest()
                        .body(res);
            }

            // Create new user's account
            User user = new User(signUpRequest.getFullname(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        case "mod":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);

                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }

            user.setRoles(roles);
            userRepository.save(user);
            res = new DefaultRes("Usuario registrado correctamente!", false);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res = new DefaultRes(e.getMessage(), true);
            return ResponseEntity.badRequest().body(res);
        }
    }
}
