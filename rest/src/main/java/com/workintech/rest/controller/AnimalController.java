package com.workintech.rest.controller;

import com.workintech.rest.entity.Animal;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    @Value("${instructor.name}")
    private String name;
    @Value("${instructor.surname}")
    private String surname;

    private Map<Integer, Animal> animalMap;

    //   public AnimalController() {
    //       animalMap = new HashMap<>();
    //   }

    @PostConstruct
    public void init() {
        animalMap = new HashMap<>();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return name + " - " + surname + "say hi";
    }

    @GetMapping("/")
    public List<Animal> get() {
        return animalMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Animal get(@PathVariable int id) {
        if (id < 0) {
            // TODO id is not valid
        }
        if (!animalMap.containsKey(id)) {
            // TODO map !contains id
        }
            return animalMap.get(id);
    }


    @PostMapping ("/")
    public Animal save(@RequestBody Animal animal) {
        if(animalMap.containsKey(animal.getId())) {
            // TODO item already exist
        }
        if(animal.getId() < 0 || animal.getName() == null || animal.getName().isEmpty()){
            // TODO animal credentials are not valid
        }
        animalMap.put(animal.getId(), animal);
        return animalMap.get(animal.getId());
    }
    @PutMapping ("/{id}")
    public Animal update (@PathVariable int id, @RequestBody Animal animal) {
        if(!animalMap.containsKey(id)) {
            // TODO item is not exist
        }
        if(animal.getId() < 0 || animal.getName() == null || animal.getName().isEmpty()){
            // TODO animal credentials are not valid
        }
        animalMap.put(id, new Animal(id, animal.getName()));
        return animalMap.get(id);
    }
    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable int id){
        if(!animalMap.containsKey(id)){
            // TODO: Animal not exist
        }
        Animal foundAnimal = animalMap.get(id);
        animalMap.remove(id);
        return foundAnimal;

    }
    @PreDestroy
    public void destroy(){
        System.out.println("Animal controller has been destroyed");
    }

}
