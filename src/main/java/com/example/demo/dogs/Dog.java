package com.example.demo.dogs;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Dog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
@Id
//this is used because we were not able to do findById on uuid type so added this now findbyId works fine with UUID types
@Column(columnDefinition = "uuid", updatable = false)
@GeneratedValue(generator = "uuid2")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private String breed;

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }



    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
        System.out.println("Constructor with parameter");
    }
    public Dog(){
        System.out.println("Constructor with out parameter");
    }


}
