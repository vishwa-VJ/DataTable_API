package com.example.DataTabledb.controller;

import com.example.DataTabledb.model.DataInfo;
import com.example.DataTabledb.service.DataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datas")
@CrossOrigin(origins = "http://localhost:3000")
public class DataInfoController {

    @Autowired
   private DataInfoService service;

    @PostMapping
    public DataInfo addData(@RequestBody DataInfo data){
        return service.save(data);
    }

    @GetMapping
    public List<DataInfo> getAllData() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataInfo>getById(@PathVariable Long id){
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataInfo>updateData(@PathVariable Long id,@RequestBody DataInfo newData){
        return service.update(id, newData).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
