package com.learn.sammidev.demo.controller;

import com.learn.sammidev.demo.entity.Student;
import com.learn.sammidev.demo.repository.StudentRepo;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.http.HttpStatus;
import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class StudentControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentRepo studentRepo;

    @Before
    public void setup() {
        RestAssured.port = this.port;
        studentRepo.delete(new Student(10));

        Student student = new Student();
        student.setId(10);
        student.setName("sammidev");
        student.setMail("sammidev@gmail.com");
        studentRepo.save(student);
    }

    @Test
    public void getSuccess() {
        given()
                .when()
                .get("/10")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getFailed() throws Exception {
        given()
                .when()
                .get("/7")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
