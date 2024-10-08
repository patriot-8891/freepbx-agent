# FreePBX Agent

## Что умеет?
* `/DuplicateRegistrations` - показывает дублирующиеся входящие регистрации.
  * Необходимо для отслеживания случаев когда на нескольких аппаратах указаны одинаковые username/password.
* `/InvalidEvents` - показывает ошибки авторизации по SIP, AMI или ошибки выбора транспорта.
  * Необходимо для отслеживания аппаратов у которых нет действующих регистраций и/или для блокировки спама-запросов.
* `/PeerStatus` - показывает актуальные регистрации и состояние.
  * Необходимо для отслеживания актуальных входящих регистраций.
* `/Registrations` - состояние исходящих регистраций.
  * Необходимо для отслеживания актуальных исходящих регистраций.

## Что-то еще?
* MD5 Auth - пароль от AMI не передается в открытом виде.
* Может работать в режиме "несколько агентов к одной базе данных".

### Поддержать.
* USDT (TRC20) `TTHFub4Ji7HcQP5hZgm9KQhsB1dkQnTBgF`
* TON `UQDrdyBYGIEm8EaZ_EDfgK7oLUA7lz8ee7Ni8TXuKlGWDVEp`
* BTC `12dJEqCz46UhRMXrx95TN5WGDM4gvAVMmk`