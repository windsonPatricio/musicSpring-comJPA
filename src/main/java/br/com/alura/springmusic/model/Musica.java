package br.com.alura.springmusic.model;

import jakarta.persistence.*;

@Entity
@Table (name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
   @ManyToOne
    private Artistas artista;

   public Musica(){

   }

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artistas getArtista() {
        return artista;
    }

    public void setArtista(Artistas artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica = " +
                "titulo='" + titulo + '\'' +
                ", artista=" + artista.getNome();
    }
}
