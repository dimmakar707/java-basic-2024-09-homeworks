package ru.otus.java.basic.homeworks.fileManagerProject.commands;

import ru.otus.java.basic.homeworks.fileManagerProject.service.ServiceFunctions;

public class CommandHelp implements Command {
    ServiceFunctions serviceFunctions;

    public CommandHelp(ServiceFunctions serviceFunctions) {
        this.serviceFunctions = serviceFunctions;
    }

    public String run(String currentLocation, String[] commandArgs) {
        String message = """
                ls – распечатывает список файлов каталога.
                    • Пример: ls [-i] [dirPath], где:
                    • -i - необязательный параметр - флаг, позволяющий посмотреть подробную информацию о файлах.
                    • dirPath - необязательный параметр - путь к каталогу. Если не указан, то выводится содержимое текущего каталога
                cd – переход в указанную поддиректорию. cd .. – переход в родительский каталог.
                    • Пример: cd path, где:
                    • path - путь к каталогу, в который нужно перейти.
                mkdir – создание новой директории с указанным именем
                    • Пример: mkdir name, где:
                    • name - директория, которую нужно создать
                rm – удаление указанного файла или директории.
                    • Пример: rm filename, где:
                    • filename - путь до удаляемого файла или директории.
                mv – переименовать/перенести файл или директорию
                    • Пример: mv source destination [-f], где:
                    • source - путь, откуда надо перенести
                    • destination - путь, куда надо перенести
                    • -f - необязательный параметр. Позволяет принудительно перезаписать файл, если он уже существует.
                cp – переименовать/перенести файл или директорию
                    • Пример: mv source destination, где:
                    • source - путь, откуда надо перенести
                    • destination - путь, куда надо перенести
                finfo [filename] – получить подробную информацию о файле
                    • Пример: finfo filename, где:
                    • filename путь до файла
                help – вывод в консоль всех поддерживаемых команд
                    • Пример: help
                find [filename] [-all] – найти файл с указанным именем в текущем каталоге или любом его подкаталоге
                    • Пример: find filename -all, где:
                    • filename - название файла, который нужно найти
                    • -all - необязательный параметр. Если передан, будут найдены все файлы с заданным именем. Если не передан, будет найден один первый поправшийся файл
                exit – завершить работу
                    • Пример: exit
                """;
        System.out.println(message);
        return currentLocation;
    }
}
