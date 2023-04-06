package com.example.pr55.domain;

import com.example.pr55.data.ServiceItem;

public class ServiceDetailsMapper {
    public ServiceDetailsModel mapAutoServiceToDetailsModel(ServiceItem serviceItem) {
        ServiceDetailsModel detailsModel = new ServiceDetailsModel();
        detailsModel.setName(serviceItem.getName());
        detailsModel.setDescription(serviceItem.getDescription());
        return detailsModel;
    }
}
