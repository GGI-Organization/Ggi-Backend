package com.ggi.service;

import com.ggi.domain.model.Administrator;
import com.ggi.domain.repository.AdministratorRepository;
import com.ggi.domain.service.AdministratorService;
import com.ggi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Page<Administrator> getAll(Pageable pageable) {
        return administratorRepository.findAll(pageable);
    }

    @Override
    public Administrator getById(Long id) {
        return administratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Administrator not found wit Id" + id));
    }

    @Override
    public Administrator create(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator update(Long id, Administrator administratorRequest) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator", "Id", id));

        administrator.setAuthorityLevel(administratorRequest.getAuthorityLevel())
                .setShareRegister(administratorRequest.getShareRegister())
                .setPassword(administratorRequest.getPassword())
                .setLoginStatus(administratorRequest.getLoginStatus())
                .setUserName(administratorRequest.getUserName())
                .setEmail(administratorRequest.getEmail())
                .setRegisterDate(administratorRequest.getRegisterDate());

        return administratorRepository.save(administrator);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator", "Id", id));

        administratorRepository.delete(administrator);
        return ResponseEntity.ok().build();
    }
}
