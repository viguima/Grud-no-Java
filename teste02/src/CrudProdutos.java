import java.util.ArrayList;
import java.util.Scanner;


public class CrudProdutos {


    private static void deletarProduto() {
        if (!usuarioLogado.getPerfil().equals("admin")) {
            System.out.println("Apenas administradores podem deletar produtos.");
            return;
        }
        System.out.print("Informe o ID do produto para deletar: ");
        int id = scanner.nextInt();

        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtos.remove(p);
                System.out.println("Produto deletado com sucesso!");
                return;
            }
        }
    }


    private static boolean login() {
        System.out.println(" --- Login --- ");
        System.out.print("Usuário: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        String senhaHash = HashUtil.gerarHash(senha);

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenhaHash().equals(senhaHash))
            {
                usuarioLogado = u;
            System.out.println("Login bem-sucedido! Bem-vindo, " +
                    u.getLogin());
            return true;
        }
    }

            System.out.println("Credenciais inválidas.");
            return false;
    }



    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioLogado = null;

    private static void inicializarUsuarios() {

        usuarios.add(new Usuario("admin", HashUtil.gerarHash("admin123"),
                "admin"));
        usuarios.add(new Usuario("victor", HashUtil.gerarHash("victor123"),
                "usuario"));
    }




    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int proximoId = 1;


    private static void listarProdutos() {
        if (produtos. isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
            }

            System.out.println("\n --- Lista de Produtos --- ");
            for (Produto p : produtos) {
                System.out.println(p);
            }
        }

    private static void atualizarProduto() {
        System.out.print("Informe o ID do produto para atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        for (Produto p : produtos) {
            if (p.getId() == id) {
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("Novo preço: ");
                double novoPreco = scanner.nextDouble();

                p.setNome(novoNome);
                p.setPreco(novoPreco);

                System.out.println("Produto atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Produto com ID " + id + " não encontrado.");
    }



    private static void deletarProdutos() {
        System.out.print("Informe o ID do produto para deletar: ");
        int id = scanner.nextInt();

        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtos.remove(p);
                System.out.println("Produto deletado com sucesso!");
                return;
            }
        }
                System.out.println("Produto com ID " + id + " não encontrado.");
            }

    private static void criarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço do produto: ");
        double preco = scanner.nextDouble();

        Produto novo = new Produto(proximoId++, nome, preco);
        produtos.add(novo);
        System.out.println("Produto cadastrado com sucesso!");
    }




    public static void main(String[] args) {
        int opcao;
        inicializarUsuarios();
        if (!login()) return;

        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    criarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    deletarProduto();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU PRODUTOS ---");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Atualizar produto");
        System.out.println("4 - Deletar produto");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}

