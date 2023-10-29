
-- Шаг 1: Создание таблицы "Pets"
CREATE TABLE `Pets` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Type` VARCHAR(50) NOT NULL,
  `BirthDate` DATE NOT NULL,
  `Commands` VARCHAR(100),
  PRIMARY KEY (`ID`)
);

-- Шаг 2: Заполнение таблицы "Pets" данными
INSERT INTO `Pets` (`Name`, `Type`, `BirthDate`, `Commands`)
VALUES
  ('Fido', 'Dog', '2020-01-01', 'Sit, Stay, Fetch'),
  ('Whiskers', 'Cat', '2019-05-15', 'Sit, Pounce'),
  ('Hammy', 'Hamster', '2021-03-10', 'Roll, Hide'),
  ('Buddy', 'Dog', '2018-12-10', 'Sit, Paw, Bark'),
  ('Smudge', 'Cat', '2020-02-20', 'Sit, Pounce, Scratch'),
  ('Peanut', 'Hamster', '2021-08-01', 'Roll, Spin'),
  ('Bella', 'Dog', '2019-11-11', 'Sit, Stay, Roll'),
  ('Oliver', 'Cat', '2020-06-30', 'Meow, Scratch, Jump');

-- Шаг 3: Создание таблицы "PackAnimals"
CREATE TABLE `PackAnimals` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Type` VARCHAR(50) NOT NULL,
  `BirthDate` DATE NOT NULL,
  `Commands` VARCHAR(100),
  PRIMARY KEY (`ID`)
);

-- Шаг 4: Заполнение таблицы "PackAnimals" данными
INSERT INTO `PackAnimals` (`Name`, `Type`, `BirthDate`, `Commands`)
VALUES
  ('Thunder', 'Horse', '2015-07-21', 'Trot, Canter, Gallop'),
  ('Sandy', 'Camel', '2016-11-03', 'Walk, Carry Load'),
  ('Eeyore', 'Donkey', '2017-09-18', 'Walk, Carry Load, Bray'),
  ('Storm', 'Horse', '2014-05-05', 'Trot, Canter'),
  ('Dune', 'Camel', '2018-12-12', 'Walk, Sit'),
  ('Burro', 'Donkey', '2019-01-23', 'Walk, Bray, Kick'),
  ('Blaze', 'Horse', '2016-02-29', 'Trot, Jump, Gallop'),
  ('Sahara', 'Camel', '2015-08-14', 'Walk, Run');

-- Шаг 5: Удаление записей о верблюдах
DELETE FROM `PackAnimals` WHERE `Type` = 'Camel';

-- Шаг 6: Создание новой таблицы "YoungAnimals" для животных в возрасте от 1 до 3 лет
CREATE TABLE `YoungAnimals` AS
SELECT *, TIMESTAMPDIFF(MONTH, `BirthDate`, CURDATE()) AS `AgeMonths`
FROM (
  SELECT * FROM `Pets`
  UNION ALL
  SELECT * FROM `PackAnimals`
) AS `AllAnimals`
WHERE TIMESTAMPDIFF(YEAR, `BirthDate`, CURDATE()) BETWEEN 1 AND 3;

-- Шаг 7: Соединение всех таблиц в одну "AllAnimals" и сохранение информации о принадлежности к исходным таблицам
CREATE TABLE `AllAnimals` AS
SELECT `ID`, `Name`, `Type`, `BirthDate`, `Commands`, 'Pets' AS `Source`
FROM `Pets`
UNION ALL
SELECT `ID`, `Name`, `Type`, `BirthDate`, `Commands`, 'PackAnimals' AS `Source`
FROM `PackAnimals`
UNION ALL
SELECT `ID`, `Name`, `Type`, `BirthDate`, `Commands`, 'YoungAnimals' AS `Source`
FROM `YoungAnimals`;

-- Шаг 8: Вывод содержимого таблицы "AllAnimals"
SELECT * FROM `AllAnimals`;