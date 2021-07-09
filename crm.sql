CREATE DATABASE IF NOT EXISTS crm;

USE crm;

CREATE TABLE IF NOT EXISTS role (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
    primary key(id)
);

CREATE TABLE IF NOT EXISTS user (
	id 				int auto_increment,
    email			varchar(100) not null unique,
    password		varchar(255) not null,
    name 			varchar(100) not null,
    address	 		varchar(255),
    phone			varchar(50),
    role_id			int,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS project (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
    start_date		date,
    end_date		date,
    owner			int,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS project_user (
	project_id		int,
    user_id			int,
    join_date		date,
    role_description varchar(255),
    primary key(project_id, user_id)
);

CREATE TABLE IF NOT EXISTS status (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
    primary key(id)
);

CREATE TABLE IF NOT EXISTS task (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
	start_date		date,
    end_date		date,
    project_id		int,
    user_id			int,
    status_id		int,
    primary key(id)
);

ALTER TABLE user
	ADD CONSTRAINT FK_USER_ROLE
		FOREIGN KEY (role_id) REFERENCES role(id); 
        
ALTER TABLE project
	ADD CONSTRAINT FK_PROJECT_USER_OWNER
		FOREIGN KEY (owner) REFERENCES user(id); 
	
ALTER TABLE project_user
	ADD CONSTRAINT FK_PROJECT_USER_PROJECT_USER_LIST
		FOREIGN KEY (project_id) REFERENCES project(id),
	ADD CONSTRAINT FK_PROJECT_USER_USER_JOIN_LIST
		FOREIGN KEY (user_id) REFERENCES user(id);

ALTER TABLE task
	ADD CONSTRAINT FK_TASK_USER
		FOREIGN KEY (user_id) REFERENCES user(id),
	ADD CONSTRAINT FK_TASK_PROJECT
		FOREIGN KEY (project_id) REFERENCES project(id),
	ADD CONSTRAINT FK_TASK_STATUS
		FOREIGN KEY (status_id) REFERENCES status(id);
INSERT INTO `crm`.`role` (`id`, `name`, `description`) VALUES ('1', 'student', 'student of cybersoft');
INSERT INTO `crm`.`role` (`id`, `name`, `description`) VALUES ('2', 'mentor', 'mentor');
INSERT INTO `crm`.`user` (`email`, `password`, `name`, `address`, `phone`, `role_id`) VALUES ('nhilong@gmail.com', '123456', 'Long', '494 LVV', '0123456789', '1');

INSERT INTO `crm`.`user` (`email`, `password`, `name`, `address`, `phone`, `role_id`) VALUES ('longvn@gmail.com', '123456', 'Nhi Long', '494 Le van Viet', '0987456321', '1');
