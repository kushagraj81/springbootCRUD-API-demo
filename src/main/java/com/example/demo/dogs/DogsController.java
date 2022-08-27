package com.example.demo.dogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DogsController {
    @Autowired
    DogsRepo obj;
    @GetMapping("/dogs")
    public ResponseEntity<List<Object>> getAllDogs(@RequestParam(required = false) String title) {
        System.out.println("In the get all request");
        try {
            List<Object> dogs = new ArrayList<>();


                if(title!=null)
                {
                    obj.findByname(title).forEach(dogs::add);
                }
                else
                {
                    obj.findAll().forEach(dogs::add);
                }

            if (dogs.isEmpty()) {
                String s = "no object in database";
                ErrorMsg eobj= new ErrorMsg(s);
                dogs.add(eobj);
                //if we send no content status then body wont be send anyhow and it is not needed also
                //coz front end developer can do the job with status code , he does not needs body
                return new ResponseEntity<>(dogs,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dogs, HttpStatus.OK);
        } catch (Exception e) {
            List<Object> dogs = new ArrayList<>();
            String s = "there is some internal error";
            ErrorMsg eobj= new ErrorMsg(s);
            dogs.add(eobj);
            return new ResponseEntity<>(dogs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/dogs/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable("id") UUID id) {
//        System.out.println(id);
        Optional<Dog> tutorialData = obj.findById(id);
//        System.out.println(tutorialData.get());
        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/dogs")
    public ResponseEntity<Dog> createDog(@RequestBody Dog tutorial) {
        try {
            Dog _tutorial = obj
                    .save(new Dog(tutorial.getName(), tutorial.getBreed()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/dogs/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable("id") long id, @RequestBody Dog tutorial) {
        Optional<Dog> tutorialData = obj.findById(id);
        if (tutorialData.isPresent()) {
            Dog _tutorial = tutorialData.get();
            _tutorial.setName(tutorial.getName());
            _tutorial.setBreed(tutorial.getBreed());

            return new ResponseEntity<>(obj.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/dogs/{id}")
    public ResponseEntity<HttpStatus> deleteDog(@PathVariable("id") long id) {
        try {
            obj.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/dogs")
    public ResponseEntity<HttpStatus> deleteAllDogs() {
        try {
            obj.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
