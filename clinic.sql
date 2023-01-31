BEGIN;

CREATE TABLE Patients (
	patient_id INT NOT NULL,
	pesel VARCHAR(11),
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	date_of_birth DATE NOT NULL,
	address VARCHAR(50),
	city VARCHAR(26),
	zip_code VARCHAR(6),
	phone_number VARCHAR(9) NOT NULL,
	email_address VARCHAR(30),
	pass VARCHAR(15) NOT NULL,
	PRIMARY KEY (patient_id)
);

INSERT INTO Patients VALUES (1, '97062064754', 'Mateusz', 'Kowalski', '1997-06-20', 'Ludwika Warynskiego 13', 'Torun', '87-100', '728534121', 'matikowalski@interia.pl', '123');
INSERT INTO Patients VALUES (2, '58012659938', 'Eugeniusz', 'Walczak', '1958-01-26', 'Adama Mickiewicza 121/23', 'Torun', '87-100', '542324367', NULL, '123');
INSERT INTO Patients VALUES (3, NULL, 'Denys', 'Kovalenko', '2000-03-23', 'Adama Mickiewicza 132/12', 'Torun', '87-100', '501844140', 'denyskov@wp.pl', '123');
INSERT INTO Patients VALUES (4, '02262054621', 'Nikola', 'Jankowska', '2002-06-20', 'Mostowa 13/1', 'Torun', '87-100', '608244154', 'nikijankowska@onet.pl', '123');
INSERT INTO Patients VALUES (5, '67051039999', 'Eustachy', 'Wozniak', '1967-05-10', 'Waniliowa 15', 'Torun', '87-100', '575354102', NULL, '123');
INSERT INTO Patients VALUES (6, '95092595949', 'Alicja', 'Makowska', '1995-09-25', 'Jana Dekerta 15', 'Torun', '87-100', '724543112', 'makowskaalicja@o2.pl', '123');
INSERT INTO Patients VALUES (7, '82060389419', 'Norbert', 'Malinowski', '1982-06-03', 'Mostowa 15/1', 'Torun', '87-100', '554332143', NULL, '123');
INSERT INTO Patients VALUES (8, '97081434596', 'Gabriel', 'Wisniewski', '1997-08-14', 'Szeroka 5', 'Torun', '87-100', '501242165', 'gabryswis@poczta.onet.pl', '123');
INSERT INTO Patients VALUES (9, NULL, 'Yehor', 'Bodnar', '2008-12-24', 'Targowa 35', 'Torun', '87-100', '632765231', NULL, '123');
INSERT INTO Patients VALUES (10, NULL, 'Daria', 'Novak', '2002-01-03', 'Golebia 32', 'Torun', '87-100', '703435694', 'novakd@gmail.com', '123');
INSERT INTO Patients VALUES (11, '86070539876', 'Mariusz', 'Kowalski', '1986-07-05', 'Filtrowa 15', 'Torun', '87-100', '756423832', 'mariokowal@wp.pl', '123');
INSERT INTO Patients VALUES (12, '63112729872', 'Jerzy', 'Sawicki', '1963-11-27', 'Turkusowa 12', 'Torun', '87-100', '654123543', NULL, '123');
INSERT INTO Patients VALUES (13, NULL, 'Karina', 'Kovalenko', '1995-04-20', 'Adama Mickiewicza 132/12', 'Torun', '87-100', '608231435', NULL, '123');
INSERT INTO Patients VALUES (14, '62082951119', 'Roman', 'Jankowski', '1962-08-29', 'Juliana Tuwima 15', 'Torun', '87-100', '701234761', NULL, '123');
INSERT INTO Patients VALUES (15, NULL, 'Artem', 'Morhun', '1999-07-31', 'Mikolaja Reja 13', 'Torun', '87-100', '578224163', 'artem123@gmail.com', '123');
INSERT INTO Patients VALUES (16, '84083016257', 'Robert', 'Laskowski', '1984-08-30', 'Marii Sklodowskiej-Curie 85', 'Torun', '87-100', '580432127', NULL, '123');
INSERT INTO Patients VALUES (17, NULL, 'Julia', 'Pavlenko', '1999-12-31', 'Podchorazych 15/12', 'Torun', '87-100', '578265160', 'jpavlenko@gmail.com', '123');

CREATE TABLE Doctors (
	doctor_id INT NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	job_execution_number INT NOT NULL UNIQUE,
	specialization VARCHAR(40),
	pass VARCHAR(15) NOT NULL,
	PRIMARY KEY (doctor_id)
);

INSERT INTO Doctors VALUES (1, 'Jan', 'Kowalski', 654321, 'internista', '123');
INSERT INTO Doctors VALUES (2, 'Maria', 'Borowska', 742148, 'ginekolog', '123');
INSERT INTO Doctors VALUES (3, 'Elzbieta', 'Kalinowska', 854317, 'pediatra', '123');


CREATE TABLE Visits (
	visit_id INT NOT NULL,
	date_of_visit TIMESTAMP NOT NULL,
	duration_in_minutes INT  NOT NULL,
	patient_id INT NOT NULL,
	doctor_id INT NOT NULL,
	office_number INT NOT NULL,
	PRIMARY KEY (visit_id)
);

INSERT INTO Visits VALUES (1, '2022-06-30 12:00:00', 30, 2, 1, 3);
INSERT INTO Visits VALUES (2, '2022-06-30 10:00:00', 45 , 4, 2, 2);
INSERT INTO Visits VALUES (3, '2022-09-04 11:00:00', 25, 6, 1, 1);
INSERT INTO Visits VALUES (4, '2022-10-21 09:00:00', 20 , 8, 3, 4);
INSERT INTO Visits VALUES (5, '2022-05-15 13:00:00', 15, 14, 1, 3);
INSERT INTO Visits VALUES (6, '2022-11-03 08:30:00', 25, 2, 1, 1);

CREATE TABLE Offices (
	office_number INT NOT NULL,
	type_of_office VARCHAR(50),
	PRIMARY KEY (office_number)
);

INSERT INTO Offices VALUES (1, 'chorob wewnetrznych');
INSERT INTO Offices VALUES (2, 'ginekologiczny');
INSERT INTO Offices VALUES (3, 'chorob wewnetrznych');
INSERT INTO Offices VALUES (4, 'pediatryczny');


CREATE TABLE Prescriptions (
	prescription_id INT NOT NULL,
	description VARCHAR(200) NOT NULL,
	code_of_prescription INT NOT NULL,
	date_of_issue DATE NOT NULL,
	expiration_date DATE NOT NULL,
	visit_id INT NOT NULL,
	patient_id INT NOT NULL,
	PRIMARY KEY (prescription_id)
);

INSERT INTO Prescriptions VALUES (1, 'Paracetamol - dawkowanie: 1-0-1', 3342, '2022-06-30', '2022-07-30', 1, 2);
INSERT INTO Prescriptions VALUES (2, 'Aspiryna - dawkowanie: 1-0-1', 7543, '2022-10-21', '2022-11-21', 4, 8);
INSERT INTO Prescriptions VALUES (3, 'Azycyna - dawkowanie: 0-0-1', 1243, '2022-11-03', '2022-12-03', 6, 2);

ALTER TABLE Visits ADD CONSTRAINT Visits_fk0 FOREIGN KEY (patient_id) REFERENCES Patients(patient_id);
ALTER TABLE Visits ADD CONSTRAINT Visits_fk1 FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id);
ALTER TABLE Visits ADD CONSTRAINT Visits_fk2 FOREIGN KEY (office_number) REFERENCES Offices(office_number);

ALTER TABLE Prescriptions ADD CONSTRAINT Prescriptions_fk0 FOREIGN KEY (visit_id) REFERENCES Visits(visit_id);

END;