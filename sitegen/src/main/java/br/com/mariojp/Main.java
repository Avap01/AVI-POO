package br.com.mariojp;

import br.com.mariojp.controller.BlogController;
import br.com.mariojp.model.Blog;
import br.com.mariojp.view.BlogView;


public class Main {

    public static void main(String[] args) {
        BlogController controller = new BlogController();
        BlogView view = new BlogView(controller);
        view.iniciar();
    }

}