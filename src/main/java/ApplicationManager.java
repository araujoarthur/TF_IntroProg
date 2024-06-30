import java.util.Scanner;

public class ApplicationManager {
    private static ApplicationManager instance;
    private final Scanner inputHandler;
    private Library userLibrary;
    private String appName;
    private String userName;

    // PRIVATE SINGLETON CONSTRUCTOR
    private ApplicationManager() {
        this.inputHandler = new Scanner(System.in);
    }

    // GETTERS
    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getUserName() {
        return this.userName;
    }

    // SETTERS

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void run() {
        this.userLibrary = new Library();
        boolean showOptions = true;
        String command = "V";
        while (!command.equals("T")) {
            System.out.println("Olá, " + this.userName + "! ツ");
            System.out.println("✜ Boas vindas ao " + this.appName + " ✜");
            if(showOptions || (command.equals("O"))) System.out.println(Constants.SR_COMMAND_PALETTE_MAIN);
            switch (command) {
                case "V":
                    userLibrary.printLibrary();
                    break;
                case "A":
                    // Perguntar o nome do Livro.
                    System.out.print("✍ Digite o nome do livro que deseja adicionar: ");
                    String bookName = inputHandler.nextLine();

                    // Verificar se livro com mesmo nome já existe
                    if (userLibrary.getBook(bookName) == null) {
                        userLibrary.addBook(bookName);
                        System.out.printf("✔ Livro %s adicionado com sucesso.%n", bookName);
                    } else {
                        System.out.println("✗ Já existe um livro com este nome.");
                    }
                    // Se não existir, adicionar livro, se existir, mostrar erro.
                    break;
                case "R":
                    // Perguntar o nome do Livro.
                    System.out.print("✍ Digite o nome do livro que deseja remover: ");
                    String bookNameToRemove = inputHandler.nextLine();

                    // Verificar se livro com nome existe
                    if (userLibrary.getBook(bookNameToRemove) != null) {
                        userLibrary.removeBook(bookNameToRemove);
                        System.out.printf("✔ Livro %s removido com sucesso.%n", bookNameToRemove);
                    } else {
                        System.out.println("✗ Livro não encontrado.");
                    }
                    break;
                case "L":
                    // Procura se livro existe
                    System.out.print("✍ Digite o nome do livro que deseja ler: ");
                    String bookNameToRead = inputHandler.nextLine();

                    if (userLibrary.getBook(bookNameToRead) != null) {
                        // Se existir, mostrar livro
                        BookReader bookReader = new BookReader(userLibrary.getBook(bookNameToRead));
                        String bookCommand;

                        do {
                            bookReader.printCurrentPosition();
                            System.out.println(Constants.SR_COMMAND_PALETTE_BOOK);
                            bookCommand = inputHandler.nextLine();
                            switch (bookCommand) {
                                case "P":
                                    bookReader.nextPosition();
                                    break;
                                case "A":
                                    bookReader.previousPosition();
                                    break;
                                case "S":
                                    break;
                                default:
                                    System.out.println("Comando inválido.");
                                    break;
                            }
                        } while (!bookCommand.equals("S"));
                    } else {
                        // Se não existir, mostrar erro
                        System.out.println("✗ Livro não encontrado.");
                    }
                    // Se existir, mostrar livro. Se não existir, mostrar erro.
                    // Iniciar um loop para mostrar o livro até que o usuário queira sair
                    break;
                case "O":
                    showOptions = !showOptions;
                    break;
                default:
                    System.out.println("Comando inválido.");
                    break;
            }

            System.out.print("Digite a sua opção: ");
            command = inputHandler.nextLine();
        }

        System.out.println("Obrigado!");
    }
}
