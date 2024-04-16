package br.com.mariojp.parsers.template;

import br.com.mariojp.model.Post;
import br.com.mariojp.parsers.Template;
import br.com.mariojp.parsers.TemplateInfo;

import java.io.FileWriter;
import java.io.IOException;

@TemplateInfo( name = "imagem")
public class PostImage implements Template {
    @Override
    public void apply(Post post, String postFileName) throws IOException {
        FileWriter writer = new FileWriter("./site/"+postFileName);
        writer.write("<html><head><title>" + post.getTitle() + "</title></head><body>");
        writer.write("<h1>" + post.getTitle() + "</h1>");
        writer.write("<img src='https://picsum.photos/800/200' alt='Descrição da imagem'/>"); // Adicione a lógica para incorporar imagens
        writer.write("<p>" + post.getContent() + "</p>");
        writer.write("</body></html>");
        writer.close();
    }
}
