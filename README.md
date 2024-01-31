- [x] Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их.
Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).
- Создать файл с домашними животными
```
cat > Pets.data
Собаки
Кошки
Хомяки
```
- Создать файл с вьючными животными
```
[ cat > Pack.data
Лошади
Верблюды
Ослы ]
``` 
- Объединить их
  ` cat Pets.data Pack.data > Animals.data `
- Посмотреть содержимое созданного файла
` cat Animals.data `
- Переименовать файл, дав ему новое имя (Друзья человека)
` ls Animals.data Друзья_человека `
- Создать директорию, переместить файл туда.
` mkdir Animals ; mv Друзья_человека Animals `
- [ ] Подключить дополнительный репозиторий MySQL. Установить любой пакет
из этого репозитория.
- [ ] Установить и удалить deb-пакет с помощью dpkg.
- [ ] Выложить историю команд в терминале ubuntu ( `history` )

- [x] Нарисовать диаграмму, в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).
  - Диаграмма_животных.drawio.png

- [ ] В подключенном MySQL репозитории создать базу данных “Друзья
человека”
- [ ] Создать таблицы с иерархией из диаграммы в БД
- [ ] Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
- [ ] Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу. 
- [ ] Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице 
- [ ] Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам. 
- [ ] Создать класс с Инкапсуляцией методов и наследованием по диаграмме.

### Написать программу, имитирующую работу реестра домашних животных.
В программе должен быть реализован следующий функционал:
- [ ] Завести новое животное
- [ ] определять животное в правильный класс
- [ ] увидеть список команд, которое выполняет животное
- [ ] обучить животное новым командам
- [ ] Реализовать навигацию по меню
- [ ] Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆  значение внутренней̆ int переменной̆ на 1 при нажатии “Завести новое животное”.
  Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources. Нужно бросить исключение, если работа с объектом типа счетчик была не в ресурсном try и/или ресурс остался открыт.
  Значение считать в ресурсе try, если при заведения животного заполнены все поля.
