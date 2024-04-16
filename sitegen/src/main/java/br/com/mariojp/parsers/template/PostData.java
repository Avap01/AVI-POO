package br.com.mariojp.parsers.template;

import br.com.mariojp.model.Post;
import br.com.mariojp.parsers.Template;
import br.com.mariojp.parsers.TemplateInfo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TemplateInfo(name = "data") // Anotação indicando o nome do template
public class PostData implements Template {

    // Método para obter a data formatada
    private String getDataFormatada() {
        LocalDateTime now = LocalDateTime.now(); // Obtém a data e hora atuais
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Define o formato da data e hora
        return now.format(formatter); // Retorna a data formatada conforme o formato especificado
    }

    // Implementação do método apply da interface Template
    @Override
    public void apply(Post post, String postFileName) throws IOException {
        FileWriter writer = new FileWriter("./site/" + postFileName); // Cria um FileWriter para escrever na página HTML do post
        writer.write("<html><head><title>" + post.getTitle() + "</title></head><body>"); // Escreve o cabeçalho da página
        writer.write("<h1>" + post.getTitle() + "</h1>"); // Título do post
        writer.write("<p>" + post.getContent() + "</p>"); // Conteúdo do post
        writer.write("<footer>Site gerado em: " + getDataFormatada() + "</footer>"); // Adiciona a data formatada no rodapé da página
        writer.write("</body></html>"); // Fecha o corpo da página
        writer.close(); // Fecha o FileWriter
    }
}
