package br.com.mariojp.parsers;

import br.com.mariojp.model.Post;

import java.io.IOException;

public interface Template {
    public void apply(Post post, String postFileName) throws IOException;
}
