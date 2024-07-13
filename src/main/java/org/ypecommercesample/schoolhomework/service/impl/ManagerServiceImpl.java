package org.ypecommercesample.schoolhomework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import org.ypecommercesample.schoolhomework.entity.Manager;
import org.ypecommercesample.schoolhomework.entity.School;
import org.ypecommercesample.schoolhomework.mapper.ManagerMapper;
import org.ypecommercesample.schoolhomework.repository.ManagerRepository;
import org.ypecommercesample.schoolhomework.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerMapper managerMapper;


    @Transactional
    @Override
    public ManagerDto createManager(ManagerDto managerDto) {
        // School school = schoolService.findSchoolById(managerDto.getSchoolUuid());
        // managerDto.setSchoolUuid(school.getId());
        return managerMapper.entityToDto(managerRepository.save(managerMapper.dtoToEntity(managerDto)));
    }

    @Override
    public ManagerDto getManagerById(UUID id) {
        return managerMapper.entityToDto(Objects.requireNonNull(managerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<ManagerDto> getAllManagers() {
        return managerRepository.findAll().stream().map(managerMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ManagerDto updateManager(UUID id, ManagerDto managerDto) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        manager.setFullName(managerDto.getFullName());
        manager.setAge(managerDto.getAge());
        manager.setTckn(managerDto.getTckn());
        manager.setAuthority(managerDto.getAuthority());
        manager = managerRepository.save(manager);
        return managerMapper.entityToDto(manager);
    }

    @Override
    public void deleteManager(UUID id) {
        managerRepository.deleteById(id);
    }

    @Override
    public ManagerDto findManagerById(UUID id) {
        return managerMapper.entityToDto(managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found")));
    }

    public List<ManagerDto> getManagerDtoList(School school) {
        List<ManagerDto> managerDtoList = new ArrayList<>();
        for (Manager manager : school.getManagerList()) {
            ManagerDto managerDto = new ManagerDto();
            managerDto.setId(manager.getId());
            managerDto.setFullName(manager.getFullName());
            managerDto.setAge(manager.getAge());
            managerDto.setAuthority(manager.getAuthority());
            managerDto.setTckn(manager.getTckn());
            if (manager.getSchool() != null && manager.getSchool().getId() != null && !manager.getSchool().getId().equals(school.getId())) {
                managerDto.setSchoolId(school.getId());
            }
            managerDtoList.add(managerDto);
        }
        return managerDtoList;
    }
}