insert into department(id, name, note) values (DEPARTMENT_SEQ.NEXTVAL, 'Администрация', 'Администрация');
insert into department(id, name, note, parent_id) values (DEPARTMENT_SEQ.NEXTVAL, 'Бухгалтерия', 'Бухгалтерия', 1);
insert into department(id, name, note, parent_id) values (DEPARTMENT_SEQ.NEXTVAL, 'IT-отдел', 'IT-отдел', 1);
insert into department(id, name, note, parent_id) values (DEPARTMENT_SEQ.NEXTVAL, 'Хоз-отдел', 'Хоз-отдел', 1);

insert into profession(id, name, note) values (PROFESSION_SEQ.NEXTVAL, 'Главный бухгалтер', 'Главный бухгалтер');
insert into profession(id, name, note) values (PROFESSION_SEQ.NEXTVAL, 'Главный инженер', 'Главный инженер');
insert into profession(id, name, note) values (PROFESSION_SEQ.NEXTVAL, 'Старший инженер', 'Старший инженер');

insert into employee(id, fio, note, department_id, profession_id) values (EMPLOYEE_SEQ.NEXTVAL, 'Демин Александр Васильевич', '', 2, 2);
insert into employee(id, fio, note, department_id, profession_id) values (EMPLOYEE_SEQ.NEXTVAL, 'Иванов Иван Иванович', '', 1, 2);
insert into employee(id, fio, note, department_id, profession_id) values (EMPLOYEE_SEQ.NEXTVAL, 'Петров Петр Петрович', '', 2, 2);
insert into employee(id, fio, note, department_id, profession_id) values (EMPLOYEE_SEQ.NEXTVAL, 'Сидоров Сидор Сидорович', '', 1, 1);