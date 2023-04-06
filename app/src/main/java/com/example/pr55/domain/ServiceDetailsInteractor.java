package com.example.pr55.domain;

import com.example.pr55.data.ServiceItem;
import com.example.pr55.data.ServiceRepository;

import java.util.List;

public class ServiceDetailsInteractor {
    private ServiceRepository repository;
    private ServiceDetailsMapper mapper;

    public ServiceDetailsInteractor(ServiceRepository repository, ServiceDetailsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ServiceDetailsModel getServiceDetails(int autoServiceId) {
        List<ServiceItem> servicesList = repository.getServices();
        ServiceItem serviceItem = servicesList.get(autoServiceId);
        return mapper.mapAutoServiceToDetailsModel(serviceItem);
    }
}
