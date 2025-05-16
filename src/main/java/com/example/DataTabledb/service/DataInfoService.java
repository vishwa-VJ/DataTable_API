package com.example.DataTabledb.service;

import com.example.DataTabledb.model.DataInfo;
import com.example.DataTabledb.repository.DataInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataInfoService {

    @Autowired
    private DataInfoRepository repo;

    public DataInfo save (DataInfo data){
        return repo.save(data);
    }

    public List<DataInfo>getAll(){
        return repo.findAll();
    }

    public Optional<DataInfo>getById(Long id){
        return repo.findById(id);
    }

    public Optional<DataInfo> update(Long id, DataInfo newData) {
        return repo.findById(id).map(data -> {
            data.setName(newData.getName());
            data.setEmail(newData.getEmail());
            data.setContactNo(newData.getContactNo());
            data.setDob(newData.getDob());
            data.setWorkType(newData.getWorkType());
            return repo.save(data);
        });
    }

    public boolean delete(Long Id){
        if (repo.existsById(Id)){
            repo.deleteById(Id);
            return true;
        }
        return false;
    }
}
