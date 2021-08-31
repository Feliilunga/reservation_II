package be.iccbxl.pid.model;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocalityRepository extends CrudRepository<Locality, Long> {

   // Optional<Locality> findById(long id);
    Locality findById(long id);
    Locality findByLocalityName(String localityName);
    Locality findByPostalCode(String postalCode);





}