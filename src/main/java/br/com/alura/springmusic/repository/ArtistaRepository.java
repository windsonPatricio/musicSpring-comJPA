package br.com.alura.springmusic.repository;

import br.com.alura.springmusic.model.Artistas;
import br.com.alura.springmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository  extends JpaRepository<Artistas, Long> {

    Optional<Artistas> findByNomeContainingIgnoreCase(String nomeArtista);

    @Query("SELECT m FROM Artistas a JOIN a.musica m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscaMusicaPorArtista(String nome);

}
