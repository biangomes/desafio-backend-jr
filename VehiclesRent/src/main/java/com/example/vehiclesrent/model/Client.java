package com.example.vehiclesrent.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Client {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID uuid;

    @Column(length = 50)
    private String name;

    @Column(unique=true)
    private String cpf;

    @OneToOne
    private Car cars;

    private LocalDate birth;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public Client() {
    }

    /* TODO Aqui estão sendo passados 2 parâmetros para o construtor, mas eles não estão sendo utilizados.
       Na minha interpretação, esses valores deveriam ser gerados no momento em que o objeto estiver sendo salvo para
       corresponder ao horário exato da operação.
       Sugiro remoção dos parâmetros created_at e updated_at da assinatura e implementação do construtor.

       Também tem getters/setters que não estão sendo utilizados. Recomendo criá-los sob demanda e apenas se for necessário.
       É importante para não quebrar o encapsulamento expondo atributos que não deveriam expostos sem que haja uma necessidade.
    */
    public Client(UUID uuid, String name, String cpf, Car cars, LocalDate birth) {
        this.uuid = uuid;
        this.name = name;
        this.cpf = cpf;
        this.cars = cars;
        this.birth = birth;
    }

    public UUID getUuid() {
        return uuid;
    }

    // TODO Daqui pra baixo tem muitos getters/setters que não estão sendo utilizados.
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Car getCars() {
        return cars;
    }

    public void setCars(Car cars) {
        this.cars = cars;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }


    
}
