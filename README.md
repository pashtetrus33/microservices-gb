# Курс "Фреймворк Spring"

## Урок 11. Spring Actuator. Настройка мониторинга с Prometheus и Grafana.

Задание:

1. Создайте новый микросервис на базе Spring Boot и включите в него зависимости Spring Actuator, Prometheus и Grafana.

2. Настройте Spring Actuator в вашем микросервисе для сбора следующих метрик: информации о состоянии приложения (`/actuator/health`),
использования памяти (`/actuator/metrics/jvm.memory.used`), количества HTTP запросов (`/actuator/metrics/http.server.requests`),
и любых других метрик по вашему выбору.

3. Создайте файл конфигурации Prometheus (`prometheus.yml`), в котором определите конфигурацию для сбора метрик из вашего микросервиса.
Настройте интервал сбора и выберите метрики, которые вы хотите собирать.

![1](https://github.com/pashtetrus33/microservices-gb/assets/86385554/ac2e2c3f-97db-4c38-b0d2-52039c998e07)
![2](https://github.com/pashtetrus33/microservices-gb/assets/86385554/ad46352a-3f7c-457a-8d00-3d46e2b6fd73)
![3](https://github.com/pashtetrus33/microservices-gb/assets/86385554/7ef629aa-2750-423e-85fe-92ee7b6c336b)
![4](https://github.com/pashtetrus33/microservices-gb/assets/86385554/7311649b-508a-48cd-b9d2-9f6e9f4113f5)
![5](https://github.com/pashtetrus33/microservices-gb/assets/86385554/66b18be5-a1e2-46cb-ad88-a0b60c7d3fba)



## Урок 9. Spring Cloud. Микросервисная архитектура.

* **
### Условие задачи:
Задание: 
Разработайте микросервисную архитектуру для онлайн-магазина электроники с использованием Spring Cloud. Структура должна включать:

1. Сервис "Товары": Управление каталогом товаров (добавление, удаление, просмотр).
2. Сервис "Корзина": Добавление товаров в корзину, удаление товаров из корзины и оформление заказа.
3. Сервис "Отзывы": Добавление и просмотр отзывов на товары.
4. API Gateway: Централизованный вход для обработки всех запросов.
5. Используйте Eureka для обнаружения сервисов.

![Eureka](https://github.com/pashtetrus33/microservices-gb/assets/86385554/f0d0713f-db9e-4240-8c71-5f9ad92e5572)

### Автор:
Баканов Павел
* **
