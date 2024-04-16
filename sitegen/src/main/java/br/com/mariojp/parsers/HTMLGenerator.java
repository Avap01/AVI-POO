package br.com.mariojp.parsers;

import br.com.mariojp.model.Blog;
import br.com.mariojp.model.Post;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTMLGenerator {

    private Blog blog; // Armazena informações sobre o blog
    private static Reflections reflections; // Para fazer varreduras em classes anotadas
    private static Set<Class<?>> annotated; // Conjunto de classes anotadas
    static {
        reflections = new Reflections("br.com.mariojp.parsers.template"); // Define o pacote onde serão procuradas as classes anotadas
        annotated = reflections.getTypesAnnotatedWith(TemplateInfo.class); // Obtém as classes anotadas
    }

    // Construtor que recebe um objeto Blog
    public HTMLGenerator(Blog blog) {
        this.blog = blog;
    }

    // Método para gerar a página HTML principal do blog
    public void generateBlog() {
        createDirectory(); // Cria o diretório para o site
        ExecutorService executor = Executors.newFixedThreadPool(blog.getPosts().size()); // Cria um pool de threads para processamento paralelo dos posts
        try {
            FileWriter writer = new FileWriter("./site/index.html"); // Cria um FileWriter para escrever na página principal
            writer.write("<html><head><title>" + blog.getSiteName() + "</title></head><body>"); // Escreve o cabeçalho da página
            writer.write("<h1>Welcome to " + blog.getSiteName() + "</h1>"); // Título do blog
            writer.write("<ul>"); // Inicia a lista de posts

            // Itera sobre os posts do blog
            for (Post post : blog.getPosts()) {
                String postFileName = post.getTitle().replaceAll(" ", "_") + ".html"; // Nome do arquivo HTML do post
                writer.write("<li><a href='" + postFileName + "'>" + post.getTitle() + "</a></li>"); // Adiciona um link para o post na página principal

                // Inicia uma thread para gerar a página HTML do post
                executor.execute(() -> {
                    generatePost(post);
                });
            }

            writer.write("</ul></body></html>"); // Fecha a lista e o corpo da página
            writer.close(); // Fecha o FileWriter
        } catch (IOException e) {
            e.printStackTrace(); // Trata exceções de E/S
        } finally {
            executor.shutdown(); // Encerra o pool de threads após o término do processamento
        }
    }

    // Método para gerar a página HTML de um post específico
    private void generatePost(Post post) {
        try {
            String templateName = post.getTemplate(); // Obtém o nome do template do post
            Template template = findTemplate(templateName); // Encontra o template correspondente
            String postFileName = post.getTitle().toLowerCase().replaceAll(" ", "_") + ".html"; // Nome do arquivo HTML do post
            template.apply(post, postFileName); // Aplica o post ao template para gerar a página HTML
        } catch (IOException e) {
            e.printStackTrace(); // Trata exceções de E/S
        } catch (Exception e) {
            throw new RuntimeException(e); // Lança uma exceção caso ocorra um erro durante o processo de geração do post
        }
    }

    // Método para criar o diretório do site, se ainda não existir
    private void createDirectory() {
        File directory = new File("./site");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // Método para encontrar o template correspondente ao nome fornecido
    private Template findTemplate(String templateName) {
        try {
            for (Class<?> cls : annotated) { // Itera sobre as classes anotadas
                TemplateInfo info = cls.getAnnotation(TemplateInfo.class); // Obtém a anotação TemplateInfo
                if (info.name().equals(templateName)) { // Verifica se o nome do template coincide com o fornecido
                    Template template = (Template) cls.getDeclaredConstructor().newInstance(); // Instancia o template correspondente
                    return template; // Retorna o template encontrado
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e); // Lança uma exceção caso ocorra um erro durante o processo de instanciação do template
        }
        return null; // Retorna null caso nenhum template seja encontrado
    }
}
