package com.yeta.sbl.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/save")
    public Person save(){
        Person person = new Person("yeta", 20);
        Collection<Location> locations = new LinkedHashSet<Location>();
        Location loc1 = new Location("上海", "2008");
        Location loc2 = new Location("合肥", "2010");
        Location loc3 = new Location("广州", "2012");
        Location loc4 = new Location("成都", "2014");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        person.setLocations(locations);
        return personRepository.save(person);
    }

    @GetMapping(value = "/q1")
    public Person q1(@RequestParam(value = "name") String name){
        return personRepository.findByName(name);
    }

    @GetMapping(value = "/q2")
    public List<Person> q2(@RequestParam(value = "age") Integer age){
        return personRepository.withQueryFindByAge(age);
    }
}
