package com.ggi.service;

import com.ggi.domain.model.Client;
import com.ggi.domain.repository.ClientRepository;
import com.ggi.domain.service.ClientService;
import com.ggi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {
    private static final String DEFAULT_USERNAME = "john.doe@gmail.com";
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found wit Id" + id));
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client clientRequest) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator", "Id", id));

        client.setAccountSettings(clientRequest.getAccountSettings())
                .setId(clientRequest.getId())
                .setPassword(clientRequest.getPassword())
                .setLoginStatus(clientRequest.getLoginStatus())
                .setUserName(clientRequest.getUserName())
                .setEmail(clientRequest.getEmail())
                .setRegisterDate(clientRequest.getRegisterDate());

        return clientRepository.save(client);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "Id", id));

        clientRepository.delete(client);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Implement Repository-based User Store
        String defaultPassword = passwordEncoder.encode("password");
        if (DEFAULT_USERNAME.equals(username)) {
            return new User(DEFAULT_USERNAME, defaultPassword, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException("User not found with username " + username);
    }
}
