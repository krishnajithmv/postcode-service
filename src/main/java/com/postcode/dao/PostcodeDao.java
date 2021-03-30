package com.postcode.dao;

import com.postcode.domain.PostCode;
import com.postcode.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

@Repository
public class PostcodeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
//    EntityManager entityManager = factory.createEntityManager();
//    EntityTransaction transaction = entityManager.getTransaction();

    public PostCode getCity(PostCode postCode) {
        String cityName = null;
        try {
//            transaction.begin();
            cityName = jdbcTemplate.queryForObject("select CityName from pinToCity where PinCode='" + postCode.getPostCode() + "'", String.class);
//            transaction.commit();
        }
        catch (Exception e){
            throw new NotFoundException("POSTCODE = "+postCode.getPostCode());
        }
        return new PostCode(postCode.getPostCode(),cityName);
    }

    public PostCode getPostcode(PostCode postCode) {
        String postcode = null;
        try {
//            transaction.begin();
            postcode = jdbcTemplate.queryForObject("select PinCode from pinToCity where cityName='" + postCode.getCityName() + "'", String.class);
//            transaction.commit();
        }
        catch (Exception e){
            throw new NotFoundException("CITYNAME = "+postCode.getCityName());
        }
        return new PostCode(postcode,postCode.getCityName());
    }

    public PostCode saveData(PostCode postCode) {
        try {
//            transaction.begin();
            jdbcTemplate.update(
                    "INSERT INTO pinToCity (pincode, cityname) VALUES(?,?)",
                    postCode.getPostCode(),
                    postCode.getCityName());
//            transaction.commit();
        }
        catch (Exception e){
            throw new NotFoundException("SQL Error");
        }
        return postCode;
    }

    public PostCode deleteData(PostCode postcode) {
        try{
//            transaction.begin();
            jdbcTemplate.update("DELETE FROM pinToCity where PINCODE=?", postcode.getPostCode());
//            transaction.commit();
        }
        catch (Exception e){
            throw new NotFoundException("PINCODE NOT FOUND");
        }
        return postcode;
    }

    public PostCode updateData(PostCode postCode) {
        try {
//            transaction.begin();
            jdbcTemplate.update("update pinToCity set PINCODE=?,CITYNAME=? where PINCODE=?",
                    postCode.getPostCode(),
                    postCode.getCityName(),
                    postCode.getPostCode());
//            transaction.commit();
        }
        catch (Exception e){
            throw new NotFoundException("PINCODE NOT FOUND");
        }
        return postCode;
    }
}