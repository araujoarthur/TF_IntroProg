//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationManager.getInstance().setAppName("Kindle Simulator");

        if (args.length > 0) {
            ApplicationManager.getInstance().setUserName(args[0]);
        } else {
            ApplicationManager.getInstance().setUserName("Usuário");
        }

        ApplicationManager.getInstance().run();
    }
}