package be.iccbxl.pid.model;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="localities")




public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NumberFormat //rivedere se e giusto
    @NotEmpty(message = "Postal code value is required and can t be empty")
    @Size(min = 2, max = 6, message = "Postal Code should have at least 2 characters and max 6 characters")
    private String postalCode;

    @NotEmpty(message = "Locality value is required and can t be empty")
    @Size(min = 2, max = 60, message = "Locality should have at least 2 characters and max 60 characters")
    @Column(name="locality")
    private String localityName;

    @OneToMany( targetEntity=Location.class, mappedBy="locality" )
    private List<Location> locations = new ArrayList<>();
    //contien la liste de lieux de spectacle situee dans cette localite, pas de setter
    //pour ca, mais on va mettre de methode plus approprier comme addLocation

    public Locality(){ }

    public Locality(String postalCode,String localityName) {
        this.postalCode = postalCode;
        this.localityName = localityName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public List<Location> getLocations() {
        return locations;
    }
    public Locality addLocation(Location location) {
        if (!this.locations.contains(location)) {
            this.locations.add(location);
            location.setLocality(this);
        }
        return this;
    }



    public Locality removeLocation(Location location) {
        if (this.locations.contains(location)) {
            this.locations.remove(location);
            if (location.getLocality().equals(this)) {
                location.setLocality(null);
            }
        }
        return this;
        //ce 2 methode mis a jour la relation bilaterale du cote de  Location ajoutee ou retire

    }


    @Override
    public String toString() {
        return "Locality [ postalCode=" + postalCode + ", locality=" + localityName +
                "]";
    }
}


