package com.postcode.service;

import com.postcode.dao.PostcodeDao;
import com.postcode.domain.PostCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostcodeService {

    @Autowired
    PostcodeDao postcodeDao;

    public PostCode getCity(PostCode postCode){
        return postcodeDao.getCity(postCode);
    }

    public PostCode getPostCode(PostCode postCode) {
        return postcodeDao.getPostcode(postCode);
    }

    public PostCode saveData(PostCode postCode) {
        return postcodeDao.saveData(postCode);
    }

    public PostCode deleteData(PostCode postcode) {
        return postcodeDao.deleteData(postcode);
    }

    public PostCode updateData(PostCode postCode) {
        return postcodeDao.updateData(postCode);
    }
}
