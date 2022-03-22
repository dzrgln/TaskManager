# java-sprint2-hw
Second sprint homework

### v.1 
Не хватает видения полной картины конечного проекта. Поэтому, не уверен, что сделал то, что хотелось.
   Методы в менеджере достаточно универсальны, на этом этапе, мне кажется, так удобнее. 

###v.1.1 
Замечания приняты за исключением следующих:
* *"Должна быть возможность сохранять задачи в неизменном виде, без обновления их идентификаторов.
Изменение идентификатора задачи является нежелательным побочным эффектом, о котором стоит сообщить пользователям 
метода - в наименовании метода или документации."*
 __Возможно, я неправильно понимаю условие, но когда задача создается, ее идентификатор только появляется.
Ни при создании задачи, ни при ее обновлении идентификатор не меняется__
* *"Замечания к методу обновление задач. Пункт 2. Идентификатор задачи уже хранится в объекте задачи, не стоит передавать его отдельным параметром, 
чтобы не допустить возможности сохранения задачи по чужому id"* 
     __Мне кажется, замечание не корректно. Единственным уникальным параметром объекта задачи является как раз идентификатор.
Теоретически, мы можем изменить некую подзадачу до вида, полностью идентичного другой задаче. Допустим, у нас
есть эпик "Купить другу подарок" с некими подпунктами и есть эпик "Список дел на воскресенье". И там мы решаем заменить подзадачу, например,
"Встретиться с друзьями" на подзадачу "Купить другу подарок". И тогда у нас есть две задачи (одна эпик, другая подзадача) с 
 одинаковыми описанием и именем. Но смысл у них может быть разный, например, если имеются ввиду разные друзья. Надеюсь, я понятно
 выразился.__

###v.1.2 
Т.к. я в enum не было в программе пока, надеюсь, я корректно решил указанную проблему. А по замечанию
_"Полученный таким образом список подзадач можно изменить так, что геттер статуса начнет возвращать неверное значение.
Необходимо изменить логику расчета статуса эпика так, чтобы не допускать подобной возможности"_ у меня только вариант
добавить обновление статуса эпика в геттер, чтобы исключить неверную выдачу статуса.

###v.2
Добавлен необходимый функционал

###v.3
Добавлен необходимый функционал.
Решил убрать ограничение на 10 задач в истории. Теперь дубликаты удаляются. С точки зрения пользователя это кажется более логичным

###v.4
Добавлен необходимый функционал.
Действовал не по тех.заданию с точки зрения некоторых методов. Надеюсь, не критично.