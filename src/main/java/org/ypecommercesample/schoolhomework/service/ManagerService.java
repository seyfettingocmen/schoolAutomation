package org.ypecommercesample.schoolhomework.service;

import org.ypecommercesample.schoolhomework.dto.ManagerDto;
import java.util.List;
import java.util.UUID;

public interface ManagerService {
    ManagerDto createManager(ManagerDto managerDto);
    ManagerDto getManagerById(UUID id);
    List<ManagerDto> getAllManagers();
    ManagerDto updateManager(UUID id, ManagerDto managerDto);
    void deleteManager(UUID id);
    ManagerDto findManagerBySchoolId(UUID id);
}