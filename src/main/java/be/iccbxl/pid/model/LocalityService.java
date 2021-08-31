package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalityService {
    @Autowired
    private LocalityRepository localityRepository;

    public List<Locality> getAllLocality() {
        List<Locality> localities = new ArrayList<>();

        localityRepository.findAll().forEach(localities::add);

        return localities;
    }

    public Locality get(String id){
       /* Long indice = (long) Integer.parseInt(id);
        Optional <Locality> locality = LocalityRepository.findById(indice);

        return locality.isPresent() ? locality.get() : null;

        */



        int indice = Integer.parseInt(id);

        return localityRepository.findById(indice);





    }

    public void add(Locality locality){
        localityRepository.save(locality);
    }

    public void update(Long id, Locality locality) //no string perche id e un long
    {
        localityRepository.save(locality);
    }

    public void delete(Long id) {
        localityRepository.deleteById(id);
    }

    //prima non so il perche nell argomento del metodo delete, aveva messo String id, e id é un Long


}
