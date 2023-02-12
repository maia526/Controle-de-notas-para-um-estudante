public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        Menu menu = new Menu(admin);

        menu.recebeOpcao();
    }
}