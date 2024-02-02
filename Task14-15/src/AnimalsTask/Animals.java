package AnimalsTask;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Animals{

    private ArrayList<Animal> animalsAll;

    public Animals(){
        this.animalsAll = new ArrayList<Animal>();
    }
    
    public boolean addAnimal(String[] animalInfo){
        boolean result = false;
            switch (animalInfo[0]) {
                // 1.Собака 2.Кошка 3.Хомяк 4.Лошадь 5.Верблюд 6.Осел
                case "1":
                    animalsAll.add(new Dog(animalInfo[1]));
                    result = true;
                    break;
                case "2":
                    animalsAll.add(new Cat(animalInfo[1]));
                    result = true;
                    break;
                case "3":
                    animalsAll.add(new Hamster(animalInfo[1]));
                    result = true;
                    break;
                case "4":
                    animalsAll.add(new Horse(animalInfo[1]));
                    result = true;
                    break;
                case "5":
                    animalsAll.add(new Camel(animalInfo[1]));
                    result = true;
                    break;
                case "6":
                    animalsAll.add(new Donkey(animalInfo[1]));
                    result = true;
                    break;
                default:
                    break;
            }

        return result;
    }

    // Проверка на существование животного с кличкой в базе перед запросом на команду
    public boolean animalNameExists(String nameToLookFor){ 
        if (animalsAll.size() == 0) return false;
        for (Animal animal : animalsAll) {
            if (animal.getName().equalsIgnoreCase(nameToLookFor))
                return true;
        }
        return false;
    }

    public void learnCommand(String petName, String newCommand){
        for (Animal animal : animalsAll) {
            if (animal.getName().equalsIgnoreCase(petName)){
                animal.addCommand(newCommand);
                return;
            }
        }
    }

    public String getAnimalList(){
        if (animalsAll.size() > 0){
            StringJoiner stringJoiner = new StringJoiner("\n");
            for (Animal animal : animalsAll) {
                stringJoiner.add(animal.getFullInfo());
            }
            return stringJoiner.toString();
        } else {
            return "<Нет данных о животных>";
        }
    }

    public interface Animal {
        String getName();
        String getFullInfo();
        String getAllCommands();
        Void addCommand(String newCommand);
    }

    // Подклассы животных не используются в коде, но их наличие требудется по заданию

    public class DomesticAnimal implements Animal{
        @Override
        public String getAllCommands() {
            return null;
        }
        
        @Override
        public String getFullInfo() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public Void addCommand(String newCommand) {
            return null;
        }
    }

    public class PackAnimal implements Animal {
        @Override
        public String getAllCommands() {
            return null;
        }
        
        @Override
        public String getFullInfo() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public Void addCommand(String newCommand) {
            return null;
        }
    }

    public class Dog extends DomesticAnimal{

        final String animalType = "Собака";
        private String animalName;
        private ArrayList<String> commandList = new ArrayList<String>();

        public Dog(String name){
            animalName = name;
        }

        @Override
        public String getName() {
            return animalName;
        }
        @Override
        public String getAllCommands() {
            String commandsString;
            if (commandList.size() > 0){
                commandsString = String.join(", ", commandList);
            } else {
                commandsString = "<нет>";
            }
            return commandsString;
        }

        @Override
        public Void addCommand(String newCommand) {
            commandList.add(newCommand);
            return null;
        }

        @Override 
        public String getFullInfo() {
            return animalType + ". Кличка: " + getName() + "\nИзученные команды: " + getAllCommands();
        }
    }

    public class Cat extends DomesticAnimal{

        final String animalType = "Кошка";
        private String animalName;
        private ArrayList<String> commandList = new ArrayList<String>();

        public Cat(String name){
            animalName = name;
        }

        @Override
        public String getName() {
            return animalName;
        }
        @Override
        public String getAllCommands() {
            String commandsString;
            if (commandList.size() > 0){
                commandsString = String.join(", ", commandList);
            } else {
                commandsString = "<нет>";
            }
            return commandsString;
        }

        @Override
        public Void addCommand(String newCommand) {
            commandList.add(newCommand);
            return null;
        }

        @Override 
        public String getFullInfo() {
            return animalType + ". Кличка: " + getName() + "\nИзученные команды: " + getAllCommands();
        }
    }

    public class Hamster extends DomesticAnimal{

        final String animalType = "Хомяк";
        private String animalName;
        private ArrayList<String> commandList = new ArrayList<String>();

        public Hamster(String name){
            animalName = name;
        }

        @Override
        public String getName() {
            return animalName;
        }
        @Override
        public String getAllCommands() {
            String commandsString;
            if (commandList.size() > 0){
                commandsString = String.join(", ", commandList);
            } else {
                commandsString = "<нет>";
            }
            return commandsString;
        }

        @Override
        public Void addCommand(String newCommand) {
            commandList.add(newCommand);
            return null;
        }

        @Override 
        public String getFullInfo() {
            return animalType + ". Кличка: " + getName() + "\nИзученные команды: " + getAllCommands();
        }
    }

    public class Horse extends PackAnimal{

        final String animalType = "Лошадь";
        private String animalName;
        private ArrayList<String> commandList = new ArrayList<String>();

        public Horse(String name){
            animalName = name;
        }

        @Override
        public String getName() {
            return animalName;
        }
        @Override
        public String getAllCommands() {
            String commandsString;
            if (commandList.size() > 0){
                commandsString = String.join(", ", commandList);
            } else {
                commandsString = "<нет>";
            }
            return commandsString;
        }

        @Override
        public Void addCommand(String newCommand) {
            commandList.add(newCommand);
            return null;
        }

        @Override 
        public String getFullInfo() {
            return animalType + ". Кличка: " + getName() + "\nИзученные команды: " + getAllCommands();
        }
    }

    public class Camel extends PackAnimal{

        final String animalType = "Верблюд";
        private String animalName;
        private ArrayList<String> commandList = new ArrayList<String>();

        public Camel(String name){
            animalName = name;
        }

        @Override
        public String getName() {
            return animalName;
        }
        @Override
        public String getAllCommands() {
            String commandsString;
            if (commandList.size() > 0){
                commandsString = String.join(", ", commandList);
            } else {
                commandsString = "<нет>";
            }
            return commandsString;
        }

        @Override
        public Void addCommand(String newCommand) {
            commandList.add(newCommand);
            return null;
        }

        @Override 
        public String getFullInfo() {
            return animalType + ". Кличка: " + getName() + "\nИзученные команды: " + getAllCommands();
        }
    }

    public class Donkey extends PackAnimal{

        final String animalType = "Осёл";
        private String animalName;
        private ArrayList<String> commandList = new ArrayList<String>();

        public Donkey(String name){
            animalName = name;
        }

        @Override
        public String getName() {
            return animalName;
        }
        @Override
        public String getAllCommands() {
            String commandsString;
            if (commandList.size() > 0){
                commandsString = String.join(", ", commandList);
            } else {
                commandsString = "<нет>";
            }
            return commandsString;
        }

        @Override
        public Void addCommand(String newCommand) {
            commandList.add(newCommand);
            return null;
        }

        @Override 
        public String getFullInfo() {
            return animalType + ". Кличка: " + getName() + "\nИзученные команды: " + getAllCommands();
        }
    }

}