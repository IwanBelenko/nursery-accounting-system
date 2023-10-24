# Информация о проекте
Необходимо организовать систему учета для питомника в котором живут
домашние и вьючные животные
## Как сдавать проект
*Для сдачи проекта необходимо создать отдельный общедоступный*
*репозиторий(Github, gitlub, или Bitbucket). Разработку вести в этом*
*репозитории, использовать пул реквесты на изменения. Программа должна*
*запускаться и работать, ошибок при выполнении программы быть не должно.*
*Программа, может использоваться в различных системах, поэтому необходимо*
*разработать класс в виде конструктора*

## Операционные системы и виртуализация (Linux)

### Использование команды cat в Linux
   - Создать два текстовых файла: "Pets"(Домашние животные) и "Pack animals"(вьючные животные), используя команду `cat` в терминале Linux. В первом файле перечислить собак, кошек и хомяков. Во втором — лошадей, верблюдов и ослов.
   - Объединить содержимое этих двух файлов в один и просмотреть его содержимое.
   - Переименовать получившийся файл в "Human friends"

     ![Image alt](https://github.com/IwanBelenko/nursery-accounting-system/blob/main/images/image1.jpg)
     ![Image alt](https://github.com/IwanBelenko/nursery-accounting-system/blob/main/images/image2.jpg)
     ![Image alt](https://github.com/IwanBelenko/nursery-accounting-system/blob/main/images/image4.jpg)
     
### Работа с директориями в Linux
   - Создать новую директорию и переместить туда файл "Human Friends".

     ![Image alt](https://github.com/IwanBelenko/nursery-accounting-system/blob/main/images/image5.jpg)

### Работа с MySQL в Linux. “Установить MySQL на вашу вычислительную машину ”

Скачиваем конфигуратор mysql:
wget https://dev.mysql.com/get/mysql-apt-config_0.8.24-1_all.deb

Переходим в папку Загрузки и устанавливаем компоненты mysql с помощью конфигуратора:

cd Загрузки sudo dpkg -i mysql-apt-config_0.8.24-1_all.deb

В процессе установки жмем Ок, чтобы выполнить полную установку

Обновляем информацию о пакетах и видим подключенный репозиторий mysql:

sudo apt-get update

Устанавливаем mysql-server:

sudo apt-get install mysql-server

Проверяем результат установки:

systemctl status mysql

### Управление deb-пакетами

Скачиваем пакет для установки:
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j_8.0.32-1ubuntu22.04_all.deb

Устанавливаем пакет mysql-connector-j_8.0.32-1ubuntu22.04_all.deb:

sudo dpkg - i mysql-connector-j_8.0.32-1ubuntu22.04_all.deb

Удаляем пакет и его сопутствующие пакеты:

sudo dpkg -r mysql-connector-j

sudo apt-get autoremove


### История команд в терминале Ubuntu
  Для получения истории введенных команд в терминале ubuntu используем:

history

   ![Image alt](https://github.com/IwanBelenko/nursery-accounting-system/blob/main/images/image6.jpg)


