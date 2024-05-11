package com.desafio2.apis.resources;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.dto.AccountDTO;
import com.desafio2.apis.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/accounts")
public class AccountResource {

    @Autowired
    private AccountService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<Account> list = service.findAll();
        List<AccountDTO> listDto = list.stream().map(x -> new AccountDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/{accountId}", method=RequestMethod.GET)
    public ResponseEntity<AccountDTO> findById(@PathVariable String accountId) {
        Account obj = service.findById(accountId);
        return ResponseEntity.ok().body(new AccountDTO(obj));
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid AccountDTO objDto) {
        Account obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
