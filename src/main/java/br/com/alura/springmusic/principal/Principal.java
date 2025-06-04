package br.com.alura.springmusic.principal;

import br.com.alura.springmusic.model.Artistas;
import br.com.alura.springmusic.model.Musica;
import br.com.alura.springmusic.model.TipoArtista;
import br.com.alura.springmusic.repository.ArtistaRepository;
import br.com.alura.springmusic.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {


    private final ArtistaRepository reposotorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.reposotorio = repositorio;
    }

    public void exibeMenu() {
            var opcao = -1;

            while (opcao!= 9) {
                var menu = """
                    *** Musica com Spring ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarArtistas();
                        break;
                    case 2:
                        cadastrarMusicas();
                        break;
                    case 3:
                        listarMusicas();
                        break;
                    case 4:
                        buscarMusicasPorArtista();
                        break;
                    case 5:
                        pesquisarDadosDoArtista();
                        break;
                    case 9:
                        System.out.println("Encerrando a aplicação!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }




    private void cadastrarMusicas() {
        System.out.println("Cadastrar musica de qual artista?");
        var nomeArtista = leitura.nextLine();

        Optional<Artistas> artista = reposotorio.findByNomeContainingIgnoreCase(nomeArtista);

        if(artista.isPresent()){
            System.out.println("Informe o nome da musica:");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusica().add(musica);
            reposotorio.save(artista.get());
        } else {
            System.out.println("Artista nao encontrado");
        }

    }

    private void cadastrarArtistas() {
        var cadastroNovo = "S";

        while (cadastroNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do artista:");
            var nomeArtista = leitura.nextLine();
            System.out.println("Digite o tipo (Solo, Dupla ou Banda):");
            var tipoArtista = leitura.nextLine();

            TipoArtista enumArtista = TipoArtista.valueOf(tipoArtista.toUpperCase());
            Artistas artista = new Artistas(nomeArtista, enumArtista);
            reposotorio.save(artista);
            System.out.println("Deseja cadastrar mais um? S/N");
            cadastroNovo = leitura.nextLine();

        }
    }

    private void listarMusicas() {
        List<Artistas> artistas = reposotorio.findAll();

        artistas.forEach(a -> a.getMusica().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar musicas de qual artista?");
        var nomeArtista = leitura.nextLine();
        List<Musica> musicas = reposotorio.buscaMusicaPorArtista(nomeArtista);
        musicas.forEach(musica -> System.out.println("Musicas=: "+ musica.getTitulo()));

    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Pesquisar sobre qual artista?");
        var nomeArtista = leitura.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nomeArtista);
        System.out.println(resposta.trim());

    }


}
