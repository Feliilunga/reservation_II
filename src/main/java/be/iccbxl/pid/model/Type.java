package be.iccbxl.pid.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="types")

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Type name value is required and can t be empty")
    @Size(min=3, max=60, message ="Type name should have at least 2 characters and max 60 characters")
    @Column(name="type")
    private String typeName;


    //table de jointure
    @ManyToMany
    @JoinTable(
            name="artist_type", //name table de jointure
            joinColumns= @JoinColumn(name="type_id"), //name column de jointure
            inverseJoinColumns= @JoinColumn(name = "artist_id")) //name deuxieme column de jointure
    private List<Artist> artists= new ArrayList<>();

    public Type() {
    }


    public Type(Long id, String typeName) {
        super();
        this.id = id;
        this.typeName = typeName;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Type addArtist(Artist artist) {
        if(!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.addType(this);
        }
        return this;
    }
    public Type removeType(Artist artist) {
        if(this.artists.contains(artist)) {
            this.artists.remove(artist);
            artist.getTypes().remove(this);
        }
        return this;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    } //era = type
    @Override
    public String toString() {
        return "Type [id=" + id + ", type=" + typeName + "]";
    }
}