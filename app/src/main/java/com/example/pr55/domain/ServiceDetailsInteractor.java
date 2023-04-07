package com.example.pr55.domain;

import com.example.pr55.data.repository.ServiceRepository;

public class ServiceDetailsInteractor  {
    private ServiceRepository repository;
    private ServiceDetailsMapper mapper;

    public ServiceDetailsInteractor(ServiceRepository repository, ServiceDetailsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
/*
    public ServiceDetailsViewModel getServiceDetails(int autoServiceId) {
        LiveData<List<ServiceItem>> servicesList = repository.getServices();
        ServiceItem serviceItem = servicesList.getValue().get(autoServiceId);
        return mapper.mapAutoServiceToDetailsModel(serviceItem);
    }

 */
}
