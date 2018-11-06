package com.webforms.controllers;

import com.webforms.ItemTable;
import com.webforms.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;

@Controller
@RequestMapping(path = "/api")
public class ApiController {
    @Autowired
    ItemTable itemsTable;

    @Secured("ROLE_EDITOR")
    @PostMapping(path = "/upsert", consumes = "application/json")
    public ResponseEntity add(@RequestBody Item item){
        System.out.println(item.toString());
        if(!item.isValid()){
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("");
        }
        try {
            long id = itemsTable.findByItemIdAndUserId(item.getItemId(), item.getUserId()).getId();
            item.setId(id);
        } catch(NullPointerException e) {
        }
        itemsTable.save(item);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @Secured("ROLE_EDITOR")
    @Transactional
    @PostMapping(path = "/remove")
    public ResponseEntity delete(@RequestBody Item item){
        System.out.println(item.toString());
        itemsTable.deleteByItemIdAndUserId(item.getItemId(), item.getUserId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @Secured("ROLE_EDITOR")
    @PostMapping(path = "/all")
    public @ResponseBody Iterable<Item> getAll(@RequestBody String userId){
        userId = userId.replaceAll("\"", "");
        return itemsTable.findAllByUserId(Long.parseLong(userId));
    }
}
