package com.postcode.controller;
import com.postcode.domain.PostCode;
import com.postcode.exception.NotFoundException;
import com.postcode.service.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PostcodeController {
    @Autowired
    PostcodeService postcodeService;

    @GetMapping(value="/postcode/{pincode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCode>cityName(@PathVariable("pincode") String postcode) {
        PostCode postCode = this.postcodeService.getCity(new PostCode(postcode,""));
        if(postCode==null)
            throw new NotFoundException("POSTCODE = "+postcode);
        return new ResponseEntity<PostCode>(postCode, HttpStatus.OK);
    }

    @GetMapping(value="/city/{cityName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCode> postcodeCode(@PathVariable("cityName") String cityName) {
        PostCode postCode= postcodeService.getPostCode(new PostCode("",cityName));
        if(postCode==null)
            throw new NotFoundException("CITY NAME = "+cityName);
        return new ResponseEntity<PostCode>(postCode, HttpStatus.OK);
    }

    @PostMapping(value="/postcode",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCity(@RequestBody PostCode postCode){
        postcodeService.saveData(postCode);
        URI location = ServletUriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/cityName/")
                .path("/{id}")
                .buildAndExpand(postCode.getPostCode())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value="/postcode",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCode> deleteCity(@RequestBody PostCode postCode) {
        PostCode response = postcodeService.deleteData(postCode);
        return new ResponseEntity<PostCode>(postCode, HttpStatus.OK);
    }

    @PutMapping(value="/postcode",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCode> updateData(@RequestBody PostCode postCode){
        PostCode response  = postcodeService.updateData(postCode);
        return new ResponseEntity<PostCode>(postCode, HttpStatus.OK);
    }

}
