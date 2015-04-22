# App-analyzer

A Clojure app designed to find same apps in stores.

## Usage

links.txt - содержит список ссылок

start.bat - запуск приложения

или из папки target: java -jar app-0.1.0-SNAPSHOT-standalone.jar links.txt

Приложения считаются одинаковыми если два из трех условий равны: 
	строгое равенство по имени,
	равенство авторов с использованием расстояния Левенштейна,
	равенство описания с использованием расстояния Левенштейна
(с некоторыми модификациями)

Для внесения приложения в группу одинаковых, используется сравнение разных полей с разными приложениями, входящими в эту группу.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
