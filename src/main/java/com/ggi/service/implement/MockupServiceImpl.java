package com.ggi.service.implement;

import com.ggi.model.*;
import com.ggi.payload.request.MockupDetailReq;
import com.ggi.payload.request.MockupReq;
import com.ggi.payload.response.PredictionMockupRes;
import com.ggi.repository.ComponentUIRepository;
import com.ggi.repository.MockupGroupRepository;
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

    @Autowired
    private MockupGroupRepository mockupGroupRepository;

    @Override
    public Page<MockupGroup> getAll(Pageable pageable, Long userId) {
        var result = mockupGroupRepository.findAll(pageable).stream().filter(mockup -> Objects.equals(mockup.getUserId(), userId)).toList();
        return new PageImpl<MockupGroup>(result);
    }

    @Override
    public Optional<Mockup> getById(Long id) {
        return mockupRepository.findById(id);
    }

    @Override
    public boolean create(String nameFolder, Long userId, PredictionMockupRes predictionMockupRes) {
        try {
            int countMockup = 1;
            var mockupsAdd = new ArrayList<Mockup>();
            for (var predictionMockup : predictionMockupRes.getMockups()) {
                var componentsAdd = new ArrayList<ComponentUI>();
                for (var component : predictionMockup.getComponents()) {
                    ComponentUI componentUI = new ComponentUI(EComponent.valueOf(component.getType()), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight());
                    componentsAdd.add(componentUI);
                }
                //componentUIRepository.saveAll(componentsAdd);
                Mockup mockup = new Mockup(nameFolder + "/mockup_" + countMockup + ".png", componentsAdd);
                mockupsAdd.add(mockup);
            }
            MockupGroup mockupGroup = new MockupGroup(userId, "", nameFolder, EStatus.EN_PROCESO, mockupsAdd);
            mockupGroupRepository.save(mockupGroup);
            return true;
        } catch (Exception e) {
            System.out.println("error save mockup info " + e.getMessage());
            return false;
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        mockupRepository.deleteById(id);
        return null;
    }
}
