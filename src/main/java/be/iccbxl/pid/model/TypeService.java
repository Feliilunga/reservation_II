package be.iccbxl.pid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getAllType(){
        List<Type> types = new ArrayList<>();

        typeRepository.findAll().forEach(types::add);

        return types;
    }

    public Type get(String id) {
        int indice =  Integer.parseInt(id);
        Optional<Type> type = typeRepository.findById(indice);

        return type.isPresent() ? type.get() : null;
    }

    public void add(Type type){
        typeRepository.save(type);
    }
    public void update(Long id, Type type)  {
        typeRepository.save(type);
    }
    public Type findOneById(Long typeId) {
        Optional<Type> type = typeRepository.findById(typeId);
        return type.isPresent() ? type.get() : null;
    }

    public void delete(Long id) {

        typeRepository.deleteById(id);
    }

    //
    public Type save(Type type) {
        return typeRepository.save(type);
    }

}