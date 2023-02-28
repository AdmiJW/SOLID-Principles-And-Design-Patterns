package composite;


public class Main {
    public static void main(String[] args) {
        MenuGroup games = new MenuGroup("Games");
        MenuGroup sports = new MenuGroup("Sports");

        MenuChild cricket = new MenuChild("Cricket");
        MenuChild football = new MenuChild("Football");

        MenuChild gta = new MenuChild("GTA");
        MenuChild fifa = new MenuChild("FIFA");

        games.add(gta);
        games.add(fifa);
        sports.add(cricket);
        sports.add(football);

        MenuGroup mainMenu = new MenuGroup("Main Menu");
        mainMenu.add(games);
        mainMenu.add(sports);

        mainMenu.printMenu();

    }
}