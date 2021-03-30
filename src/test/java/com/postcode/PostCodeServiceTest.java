package com.postcode;
import com.postcode.domain.PostCode;
import com.postcode.service.PostcodeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostCodeServiceTest {

    @Autowired
    PostcodeService postcodeService;

    @Test
    void addCity(){
        PostCode input= new PostCode("1000","Thikkodi");
        PostCode output = postcodeService.saveData(input);
        Assertions.assertEquals(input.getCityName(),output.getCityName());
        Assertions.assertEquals(input.getPostCode(),output.getPostCode());
    }

    @Test
    void getCity(){
        PostCode input= new PostCode("100","");
        PostCode expected= new PostCode("100","Calicut");
        PostCode TestInput = postcodeService.getCity(input);
        Assertions.assertEquals(TestInput.getCityName(),expected.getCityName());
    }

    @Test
    void getPostcode(){
        PostCode input= new PostCode("","Calicut");
        PostCode expected= new PostCode("100","Calicut");
        PostCode TestInput = postcodeService.getPostCode(input);
        Assertions.assertEquals(TestInput.getPostCode(),expected.getPostCode());
    }

    @Test
    void updatePostcode(){
        PostCode input = new PostCode("100","Kochi");
        postcodeService.updateData(input);
        PostCode output = postcodeService.getCity(input);
        Assertions.assertEquals(input.getCityName(),output.getCityName());
    }

    @Test
    void deletePostcode(){
        PostCode input = new PostCode("100","Calicut");
        PostCode output = postcodeService.deleteData(input);
        Assertions.assertEquals(input.getCityName(),output.getCityName());
        Assertions.assertEquals(input.getPostCode(),output.getPostCode());
    }
}
