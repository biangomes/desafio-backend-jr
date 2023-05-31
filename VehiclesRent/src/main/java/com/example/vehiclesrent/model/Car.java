package com.example.vehiclesrent.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private LocalDateTime createdAt;

    public Car() {
    }

    /* TODO Aqui está sendo passado para o construtor 1 parâmetro que não está sendo utilizado e nem deveria.
       Na minha interpretação, o valor do createdAt deveria ser gerado no momento em que o objeto estiver sendo salvo para
       corresponder ao horário exato da operação.
       Sugiro remoção do parâmetro created_at da assinatura e implementação do construtor.

       Também tem getters/setters que não estão sendo utilizados. Recomendo criá-los sob demanda e apenas se for necessário.
       É importante para não quebrar o encapsulamento expondo atributos que não deveriam expostos sem que haja uma necessidade.
    */
    public Car(UUID uuid, String brand, String model /* LocalDateTime createdAt */) {
        this.uuid = uuid;
        this.brand = brand;
        this.model = model;
        //this.createdAt = createdAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return uuid.equals(car.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
