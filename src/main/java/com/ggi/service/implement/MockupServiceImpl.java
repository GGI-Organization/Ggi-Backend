package com.ggi.service.implement;

import com.ggi.model.*;
import com.ggi.payload.request.MockupDetailReq;
import com.ggi.payload.request.MockupReq;
import com.ggi.payload.response.PredictionMockupRes;
import com.ggi.payload.response.TasksRes;
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
    public Page<MockupGroup> getAll(Pageable pageable, String name, Long userId) {
        List<MockupGroup> result;
        if (name == null || name.isEmpty()){
            result = mockupGroupRepository.findAll(pageable).stream().filter(mockup -> Objects.equals(mockup.getUserId(), userId) && mockup.getStatus() == EStatus.ACTIVO).toList();

        }else{
            result = mockupGroupRepository.findAll(pageable).stream().filter(mockup -> Objects.equals(mockup.getUserId(), userId) && mockup.getStatus() == EStatus.ACTIVO && mockup.getName().trim().toLowerCase().contains(name.trim().toLowerCase())).toList();
        }
        return new PageImpl<MockupGroup>(result);
    }

    @Override
    public Optional<Mockup> getById(Long id) {
        return mockupRepository.findById(id);
    }

    @Override
    public boolean create(String nameFolder, Long userId, PredictionMockupRes predictionMockupRes, TasksRes taskRes) {
        try {
            // Save mockup group
            MockupGroup mockupGroup = new MockupGroup(userId, "", nameFolder, EStatus.EN_PROCESO);
            mockupGroupRepository.save(mockupGroup);
            int indexTask = 0;
            var tasks = taskRes.getTasks();
            int countMockup = 1;
            for (var predictionMockup : predictionMockupRes.getMockups()) {
                // Save mockups
                var task = tasks.get(indexTask).getTask();
                Mockup mockup = new Mockup(nameFolder + "/mockup_" + countMockup + ".png", task, mockupGroup);
                mockupRepository.save(mockup);
                var componentsAdd = new ArrayList<ComponentUI>();
                for (var component : predictionMockup.getComponents()) {
                    ComponentUI componentUI = new ComponentUI(EComponent.valueOf(component.getType()), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight(), mockup);
                    componentsAdd.add(componentUI);
                }
                componentUIRepository.saveAll(componentsAdd);
                countMockup++;
                indexTask++;
            }
            return true;
        } catch (Exception e) {
            System.out.println("error save mockup info " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(String name, String path, Long userId) {
        var mockupGroup = mockupGroupRepository.findByUserIdAndPath(userId, path);
        if (mockupGroup.isEmpty()) return false;
        mockupGroup.get().setName(name);
        mockupGroup.get().setStatus(EStatus.ACTIVO);
        mockupGroupRepository.save(mockupGroup.get());
        return true;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        mockupRepository.deleteById(id);
        return null;
    }
}
