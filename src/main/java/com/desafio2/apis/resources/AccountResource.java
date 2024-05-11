package com.desafio2.apis.resources;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.dto.AccountDTO;
import com.desafio2.apis.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/account")
public class AccountResource {

    @Autowired
    private AccountService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAccount(){
        List<String> list = new ArrayList<>();
        list.add("teste");
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid AccountDTO objDto) {
        Account obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
