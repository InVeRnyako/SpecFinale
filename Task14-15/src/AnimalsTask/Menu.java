package AnimalsTask;
import java.util.Scanner;

public class Menu {
    Animals animals;
    Scanner scanner;
    int addedAnimalsCount;

    public Menu(Animals animals){
        this.animals = animals;
        this.scanner = new Scanner(System.in, "UTF-8");
        addedAnimalsCount = 0;
    }
    

    public void start(Animals animals){
        boolean work = true;
        Integer menuChoice = 0;
        String inputString;
        String petName; // Переменная для хранения клички животного при вводе новой команды для животного
        
        while (work) {
            System.out.println("1. Завести новое животное");
            System.out.println("2. Показать список животных");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Выход");
            menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    try (Counter counter = new Counter()){
                        counter.add();
                        String[] animalInfo = getAnimalInfo();
                        if (animalInfo == null){
                            counter.remove();
                        } else {
                            if(!animals.addAnimal(animalInfo)){
                                counter.remove();
                            } else {
                                System.out.println("Животное успешно добавлено. Количество добавленных животных: " + addedAnimalsCount);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Список животных:");
                    System.out.println(animals.getAnimalList());
                    break;
                case 3:
                    System.out.println("Введите кличку животного, которому хотите добавить команду:");
                    scanner.nextLine();
                    inputString = scanner.nextLine();
                    if (inputString.length() == 0){
                        System.out.println("Ошибка ввода");
                        break;
                    }
                    if (animals.animalNameExists(inputString)){
                        System.out.println("Животное найдено. Введите новую команду для изучения: ");
                        petName = inputString;
                        inputString = scanner.nextLine();
                        if (inputString.length() > 0) {
                            animals.learnCommand(petName, inputString);
                            System.out.println("Команда успешно добавлена.");
                        } else {
                            System.out.println("Ошибка ввода команды. Возвращение в меню.");
                        }
                        break;
                    } else {
                        System.out.println("Животное с такой кличкой не найдено. Возвращение в меню.");
                        break;
                    }
                case 4:
                    System.out.println("Закрытие программы.");
                    work = false;
                    break;
                default:
                    System.out.println("Ошибка ввода. Выберите корректный пункт меню.");
                    break;
            }
        }
        scanner.close();
        return;
    }


    public class Counter implements AutoCloseable {
        private int count;
    
        public Counter() {
            this.count = 0;
        }
    
        public void add() {
            this.count++;
            addedAnimalsCount++;
        }
    
        public void remove(){ 
            addedAnimalsCount = addedAnimalsCount - 1;
        }

        public int getCount() {
            return this.count;
        }
    
        @Override
        public void close() throws Exception {
            if (this.count == 0) {
                throw new IllegalStateException("Counter is not incremented. Cannot close.");
            }
        }
    }

    public String[] getAnimalInfo(){
        System.out.println("Добавление нового животного.\n Введите кличку животного:");
        scanner.nextLine();
        String name = scanner.nextLine();
        if (name.length() < 1){
            System.out.println("Некорректный ввод. Возвращение в меню.");
            return null;
        }
        System.out.println("Выберите тип животного:\n1.Собака\n2.Кошка\n3.Хомяк\n4.Лошадь\n5.Верблюд\n6.Осел");
        Integer choice = scanner.nextInt();
        if (choice > 0 & choice < 7){
            String[] animalInfo = {String.valueOf(choice) , name};
            return animalInfo;
        } else {
            System.out.println("Некорректный ввод. Возвращение в меню.");
            return null;
        }
    }

}
