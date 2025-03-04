package fr.dawan.spring_rest_api.entities;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long id;
    private String description;
    private double price;

    //private byte[] image; // stocker le binaire de l'image en BD
    private String imagePath; // emplacement de l'image

}
