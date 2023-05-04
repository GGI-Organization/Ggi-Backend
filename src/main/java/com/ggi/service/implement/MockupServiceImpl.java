package com.ggi.service.implement;

import com.ggi.model.ComponentUI;
import com.ggi.model.EComponent;
import com.ggi.model.EStatus;
import com.ggi.model.Mockup;
import com.ggi.payload.request.MockupDetailReq;
import com.ggi.payload.request.MockupReq;
import com.ggi.repository.ComponentUIRepository;
import com.ggi.repository.MockupRepository;
import com.ggi.service.interfaces.MockupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MockupServiceImpl implements MockupService {

    @Autowired
    private MockupRepository mockupRepository;

    @Autowired
    private ComponentUIRepository componentUIRepository;

    @Override
    public Page<Mockup> getAll(Pageable pageable, Long userId) {
        var result = mockupRepository.findAll(pageable).stream().filter(mockup -> Objects.equals(mockup.getUserId(), userId)).toList();
        return new PageImpl<Mockup>(result);
    }

    @Override
    public Optional<Mockup> getById(Long id) {
        return mockupRepository.findById(id);
    }

    @Override
    public ArrayList<Mockup> create(MockupReq mockupReq) {
        ArrayList<Mockup> mockupsAdded = new ArrayList<>();
        for (var mockup : mockupReq.getMockups()) {
            var newComponents = mockup.getComponents().stream().map(component -> new ComponentUI(EComponent.valueOf(component.getName()), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight())).toList();
            componentUIRepository.saveAll(newComponents);
            Set<ComponentUI> targetSet = new HashSet<ComponentUI>(newComponents);
            var newMockup = new Mockup(mockupReq.getUserId(),EStatus.ACTIVO,mockup.getName(), mockup.getPath(), targetSet);
            mockupRepository.save(newMockup);
            mockupsAdded.add(newMockup);
        }
        return mockupsAdded;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        mockupRepository.deleteById(id);
        return null;
    }
}
