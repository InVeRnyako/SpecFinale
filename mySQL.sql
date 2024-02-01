DROP DATABASE Друзья_человека;
CREATE DATABASE Друзья_человека;

use Друзья_человека;

CREATE TABLE Родительский_класс (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY , Подкласс VARCHAR(30) NOT NULL);
CREATE TABLE Домашние_животные (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Вид VARCHAR(30) NOT NULL);
CREATE TABLE Вьючные_животные (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Вид VARCHAR(30) NOT NULL);
CREATE TABLE Собаки (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Кличка VARCHAR(30) NOT NULL, Дата_рождения DATE, Список_команд VARCHAR(100));
CREATE TABLE Кошки (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Кличка VARCHAR(30) NOT NULL, Дата_рождения DATE, Список_команд VARCHAR(100));
CREATE TABLE Хомяки (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Кличка VARCHAR(30) NOT NULL, Дата_рождения DATE, Список_команд VARCHAR(100));
CREATE TABLE Лошади (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Кличка VARCHAR(30) NOT NULL, Дата_рождения DATE, Список_команд VARCHAR(100));
CREATE TABLE Верблюды (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Кличка VARCHAR(30) NOT NULL, Дата_рождения DATE, Список_команд VARCHAR(100));
CREATE TABLE Ослы (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Кличка VARCHAR(30) NOT NULL, Дата_рождения DATE, Список_команд VARCHAR(100));

INSERT INTO Собаки (Кличка, Дата_рождения, Список_команд) VALUES
('Рекс', '2020-01-15', 'Сидеть, Лежать, Фас'),
('Белла', '2019-05-20', 'Фас, Фу, Лапу'),
('Шарик', '2018-08-10', 'Прыгай, Ищи');

INSERT INTO Кошки (Кличка, Дата_рождения, Список_команд) VALUES
('Мурка', '2019-02-28', 'Кушать'),
('Васька', '2020-04-12', 'Апорт, Лежать'),
('Барсик', '2018-11-05', '-');

INSERT INTO Хомяки (Кличка, Дата_рождения, Список_команд) VALUES
('Боби', '2021-06-08', '-'),
('Шуша', '2020-09-17', '-'),
('Тоша', '2019-03-25', '-');

INSERT INTO Лошади (Кличка, Дата_рождения, Список_команд) VALUES
('Буран', '2015-07-12', 'Бегом, Стоять'),
('Зорька', '2016-04-28', 'Ко мне, Домой'),
('Гроза', '2014-11-15', '-');

INSERT INTO Верблюды (Кличка, Дата_рождения, Список_команд) VALUES
('Саид', '2017-08-05', 'Стоять, Лежать, Бежать'),
('Амина', '2018-02-22', 'Стоять, Лежать, Гулять'),
('Халид', '2019-12-10', 'Стоять, Лежать');

INSERT INTO Ослы (Кличка, Дата_рождения, Список_команд) VALUES
('Игорь', '2016-10-18', 'Тяни, Домой'),
('Марфа', '2017-03-02', '-'),
('Федор', '2018-07-14', '-');

TRUNCATE TABLE Верблюды; -- Вариант с DELETE FROM требует править конфигурацию, в данном случае TRUNCATE подходит
-- Создаем новую таблицу с указанием уникальности id, иначе простое UNION выдаст таблицу с id "1,2,3,1,2,3"
CREATE TABLE Лошади_и_ослы (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Кличка VARCHAR(30) NOT NULL,
  Дата_рождения DATE,
  Список_команд VARCHAR(100)
);

INSERT INTO Лошади_и_ослы (Кличка, Дата_рождения, Список_команд)
SELECT Кличка, Дата_рождения, Список_команд FROM Лошади
UNION ALL
SELECT Кличка, Дата_рождения, Список_команд FROM Ослы;

CREATE TABLE молодые_животные AS
SELECT
    Кличка,
    Дата_рождения,
    CONCAT(
        CASE WHEN TIMESTAMPDIFF(YEAR, Дата_рождения, CURDATE()) > 0 THEN
            CONCAT(TIMESTAMPDIFF(YEAR, Дата_рождения, CURDATE()), ' год(а/ов)')
        ELSE '' END,
        CASE WHEN TIMESTAMPDIFF(YEAR, Дата_рождения, CURDATE()) > 0 AND TIMESTAMPDIFF(MONTH, Дата_рождения, CURDATE()) % 12 > 0 THEN ', ' ELSE '' END,
        CASE WHEN TIMESTAMPDIFF(MONTH, Дата_рождения, CURDATE()) % 12 > 0 THEN
            CONCAT(TIMESTAMPDIFF(MONTH, Дата_рождения, CURDATE()) % 12, ' месяц(а/ев)')
        ELSE '' END
    ) AS Возраст
FROM
    (
    SELECT Кличка, Дата_рождения FROM Собаки
    UNION ALL
    SELECT Кличка, Дата_рождения FROM Кошки
    UNION ALL
    SELECT Кличка, Дата_рождения FROM Хомяки
    UNION ALL
    SELECT Кличка, Дата_рождения FROM Лошади
    UNION ALL
    SELECT Кличка, Дата_рождения FROM Верблюды
    UNION ALL
    SELECT Кличка, Дата_рождения FROM Ослы
    ) AS Объединенные_данные
WHERE
	Дата_рождения > CURDATE() - INTERVAL 3 YEAR AND Дата_рождения <= CURDATE() - INTERVAL 1 YEAR;

-- Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

CREATE TABLE Все_данные (
	Источник VARCHAR(30),
    id INT,
    Подкласс VARCHAR(30),
    Вид VARCHAR(30),
    Кличка VARCHAR(30),
    Дата_рождения DATE,
    Список_команд VARCHAR(100),
    Возраст VARCHAR(30)
);

INSERT INTO Все_данные (Источник, id, Подкласс, Вид, Кличка, Дата_рождения, Список_команд, Возраст)
SELECT 
	'верблюды' as Источник,
    T1.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T1.Кличка as Кличка,
    T1.Дата_рождения as Дата_рождения,
    T1.Список_команд as Список_команд,
    NULL as Возраст
FROM верблюды AS T1
 UNION ALL
SELECT 
	'кошки' as Источник,
    T2.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T2.Кличка as Кличка,
    T2.Дата_рождения as Дата_рождения,
    T2.Список_команд as Список_команд,
    NULL as Возраст
FROM кошки AS T2
 UNION ALL
SELECT 
	'лошади' as Источник,
    T3.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T3.Кличка as Кличка,
    T3.Дата_рождения as Дата_рождения,
    T3.Список_команд as Список_команд,
    NULL as Возраст
FROM лошади AS T3
 UNION ALL
SELECT 
	'лошади_и_ослы' as Источник,
    T4.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T4.Кличка as Кличка,
    T4.Дата_рождения as Дата_рождения,
    T4.Список_команд as Список_команд,
    NULL as Возраст
FROM лошади_и_ослы AS T4
 UNION ALL
SELECT 
	'ослы' as Источник,
    T5.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T5.Кличка as Кличка,
    T5.Дата_рождения as Дата_рождения,
    T5.Список_команд as Список_команд,
    NULL as Возраст
FROM ослы AS T5
 UNION ALL
SELECT 
	'собаки' as Источник,
    T6.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T6.Кличка as Кличка,
    T6.Дата_рождения as Дата_рождения,
    T6.Список_команд as Список_команд,
    NULL as Возраст
FROM собаки AS T6
 UNION ALL
SELECT 
	'хомяки' as Источник,
    T7.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T7.Кличка as Кличка,
    T7.Дата_рождения as Дата_рождения,
    T7.Список_команд as Список_команд,
    NULL as Возраст
FROM хомяки AS T7
 UNION ALL
SELECT 
	'верблюды' as Источник,
    T8.id as id,
    NULL as Подкласс,
    NULL as Вид,
    T8.Кличка as Кличка,
    T8.Дата_рождения as Дата_рождения,
    T8.Список_команд as Список_команд,
    NULL as Возраст
FROM верблюды AS T8
 UNION ALL
SELECT
	'вьючные_животные' as Источник,
    T9.id as id,
    NULL as Подкласс,
    T9.Вид as Вид,
    NULL as Кличка,
    NULL as Дата_рождения,
    NULL as Список_команд,
    NULL as Возраст
FROM вьючные_животные as T9
  UNION ALL
SELECT
	'домашние_животные' as Источник,
    T10.id as id,
    NULL as Подкласс,
    T10.Вид as Вид,
    NULL as Кличка,
    NULL as Дата_рождения,
    NULL as Список_команд,
    NULL as Возраст
FROM домашние_животные as T10
  UNION ALL
SELECT
	'молодые_животные' as Источник,
    NULL as id,
    NULL as Подкласс,
    NULL as Вид,
    T11.Кличка as Кличка,
    T11.Дата_рождения as Дата_рождения,
    NULL as Список_команд,
    T11.Возраст as Возраст
FROM молодые_животные as T11
  UNION ALL
SELECT
	'родительский_класс' as Источник,
    T12.id as id,
    T12.Подкласс as Подкласс,
    NULL as Вид,
    NULL as Кличка,
    NULL as Дата_рождения,
    NULL as Список_команд,
    NULL as Возраст
FROM родительский_класс as T12