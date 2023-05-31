package com.example.vehiclesrent.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO É recomendável usar uma interface se tu tiver mais de uma implementação pra ela.
// Se não houver essa necessidade, não precisa ter essa interface, o que enxuga teu código.
public interface ICrudService<T> {

    // TODO A IDE avisou que tu não precisa colocar o modificador public aqui,
    //  pois por padrão os métodos de uma interface são públicos.
    public List<T> getAll();
    public Optional<T> getById(UUID uuid);
    public T save(T object);
    public void delete(UUID uuid);
}
