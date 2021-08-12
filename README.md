# Управление общежитием

Проект разработан для курса Веб-программирования в ИТМО

Пояснительная записка - [Ссылка](https://drive.google.com/drive/folders/19vN_E7TtvkZ-RJKuRCO1equ_N3rLdOr3)

## Дукин Никита P3221

Сервис разработан на Spring Boot (Java) + JSTL + MySQL
В сервисе присутсвуют четыре главных панели:
1. Стена ![image](forReadMe/2021-01-19_03-05-56.png)
2. Маркет ![image](forReadMe/2021-01-19_03-12-08.png)
3. Управленчество ![image](forReadMe/2021-01-19_03-13-05.png)
4. Настройки ![image](forReadMe/2021-01-19_03-13-51.png)
## Вход
Для входа в сервис необходимо ввести логин и пароль.
![image](forReadMe/2021-01-19_03-14-39.png)
В случае отсутствия учётной записи, необходимо произвести регистрацию.

Для регистрации необходимо иметь пару *Логин - код*, выданную доверенным лицом.

Все пароли кодируются и хранятся в базе данных в закодированном виде.

### Стена
**Стена** представляет собой набор новостей, которые добавляет администратор.
Новости сортируются в порядке новизны, будующие новости не отображаются. У новостей есть теги:
- Важно - новость является важной, рекомендуется обратить на неё внимание
- Новое - новсоть появилась в течение 2 последних дней
- Стандартно - тег не отображается, так как новость является стандартной
- Запланировано - тег только для администратора, так как подразумевается, что он может ставить появление новостей заранее


Новости просматриваются одна за другой, последовательно скрывая друг друга.


Администратор может добавлять новости за любую дату, а также их удалять.
Для этого необходимо нажать на кнопку "*Добавить новость*" и заполнить форму.

![image](forReadMe/2021-01-19_03-15-27.png)

Чтобы удалить или редактировать новость администратор может выбрать соответствующую новость и нажать кнопку "*Выбрать*", после чего
ему предложат заполнить заного все формы у новости, отобразив через всплывающие надписи исходное заполнение новости. Чтобы применить изменения к новости, следует нажать кнопку "*Изменить*".
Для удаления данной новости, необходимо просто нажать кнопку "*Удалить*". 

![image](forReadMe/2021-01-19_03-16-15.png)

### Маркет
Раздел маркета создан для демонстрации всех объявлений, выложенных в сервис

Из-за огромного количества возможных объявлений предусмотрены три вида сортировок:
- По рейтингу (по умолчанию)
- По дате
- По названию новости

Новости можно также отбирать при помощи поиска, введя необходимую строчку (не чувствительна к регистру) и нажав "*Поиск*"

Новости можно добавлять в избранное при помощи нажатия на сердечко. Тогда оно изменит цвет и при наведении на рейтинг такой новости она будет подсвечиваться красным.

К тому же, можно применить фильтр "*Понравившиеся*" и оставить только избранные новости.

Все три вида фильтров (сортировка, поиск, избранное) можно комбинировать, однако по-умолчанию при применении фильтра "*Избранное*" новости отсортируются по рейтингу.

![image](forReadMe/2021-01-19_03-17-50.png)

Можно и добавлять свои новости. При добавлении новости "*студентом*" - появляется задержка в 7 секунд, от которой можно избавить получив МЕГА-подписку.

Чтобы добавить новость, необходимо:
- нажать на кнопку добавления новости;
- загрузить картинку в нижнюю форму и получить её ID;
- заполнить форму и указать в качетсве ID картинки число, полученное в пункте выше;
- нажать на кнопку "Добавить".

![image](forReadMe/2021-01-19_03-20-22.png)

Так же можно удалить новость (если вы её автор) или получить контактную информацию об авторе новости (в противном случае). Если вы - "студент" - появляется задержка в 7 секунд.

При загрузке новости в целях контроля она отображается лишь при перезагрузке сервера. Тем не менее, просмотреть изображение можно по ссылке, помня о том, что фотография может содержать любой контент. 

![image](forReadMe/2021-01-19_03-21-28.png)

## Управленчество
В разделе управленчества отображаетя общая информация. Это статическая страница, редактированию она не подлежит.

## Настройки

При нажатии на вкладку настроек в навигационной панели, первым выпадающим списком отображается логин в системе. Далее идёт возможность покупки МЕГА-подписки, смена пароля и выход из системы
- Получение МЕГА-подписки осуществляется с помощью нажатия на кнопку "Получить МЕГА-подписку на 2 дня";
  ![image](forReadMe/2021-01-19_03-22-25.png)
- Смена пароля осуществляется в специальной форме. При несовпадении действующего пароля и введённого или двух новых паролей, отображается ошибка.
- Выход из системы: пользователь становится Гостем, который может только авторизироваться или зарегистрироваться.
