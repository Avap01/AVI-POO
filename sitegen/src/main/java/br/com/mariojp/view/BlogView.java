package br.com.mariojp.view;

import br.com.mariojp.controller.BlogController;
import br.com.mariojp.model.Blog;
import br.com.mariojp.model.Post;
import br.com.mariojp.parsers.HTMLGenerator;

import java.util.ArrayList;

import java.util.Scanner;

public class BlogView {
    private BlogController controller; // Controlador responsável pela lógica do blog
    private Scanner scanner; // Scanner para entrada do usuário

    // Construtor que recebe um BlogController como parâmetro
    public BlogView(BlogController controller) {
        this.controller = controller; // Inicializa o controlador
        this.scanner = new Scanner(System.in); // Inicializa o Scanner para entrada do usuário
    }

    // Método para iniciar a visualização do blog
    public void iniciar() {
        System.out.print("Enter the name of the blog site: ");
        String siteName = scanner.nextLine();
        System.out.print("Enter the base URL of the blog: ");
        String baseUrl = scanner.nextLine();
        System.out.print("Enter the email address for the blog: ");
        String email = scanner.nextLine();
        Blog blog = new Blog(siteName, baseUrl, email); // Cria um novo objeto Blog com as informações fornecidas
        controller.setBlog(blog); // Define o blog no controlador

        while (true) { // Loop para exibir o menu e receber as escolhas do usuário
            System.out.println("\n1. Add Post");
            System.out.println("2. Edit Post");
            System.out.println("3. Delete Post");
            System.out.println("4. List Post");
            System.out.println("5. Generate Blog");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Lê a escolha do usuário
            scanner.nextLine();

            switch (choice) { // Executa a ação correspondente à escolha do usuário
                case 1:
                    addPost(); // Adiciona um novo post
                    break;
                case 2:
                    editPost(); // Edita um post existente
                    break;
                case 3:
                    deletePost(); // Deleta um post existente
                    break;
                case 4:
                    listPosts(controller.getPosts()); // Lista todos os posts do blog
                    break;
                case 5:
                    controller.genetateSite(); // Gera o site do blog
                    break;
                case 6:
                    System.out.println("Exiting..."); // Encerra o programa
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-5."); // Mensagem de erro para escolha inválida
            }
        }
    }

    // Método para adicionar um novo post
    private void addPost() {
        System.out.print("Enter post title: ");
        String title = scanner.nextLine();
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();
        System.out.print("Enter post template: ");
        String template = scanner.nextLine();
        controller.addPost(title, content, template); // Chama o método no controlador para adicionar um novo post
    }

    // Método para editar um post existente
    private void editPost() {
        System.out.print("Enter the index of the post to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new content: ");
        String content = scanner.nextLine();
        controller.editPost(index, title, content); // Chama o método no controlador para editar um post
    }

    // Método para deletar um post existente
    private void deletePost() {
        System.out.print("Enter the index of the post to delete: ");
        int index = scanner.nextInt();
        controller.deletePost(index); // Chama o método no controlador para deletar um post
    }

    // Método para listar todos os posts do blog
    public void listPosts(ArrayList<Post> posts) {
        System.out.println("\nBlog Posts:");
        for (int i = posts.size() - 1; i >= 0; i--) { // Itera sobre todos os posts do blog
            Post post = posts.get(i);
            System.out.printf("%d: %s - %s\n", i, post.getTitle(), post.getContent() ); // Exibe o título e o conteúdo de cada post
        }
    }
}
