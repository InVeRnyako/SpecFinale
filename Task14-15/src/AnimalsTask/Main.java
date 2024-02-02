package AnimalsTask;


public class Main {
        public static void main(String[] args) {
            Animals animals = new Animals();
            Menu menu = new Menu(animals);
            menu.start(animals);
        }
    }