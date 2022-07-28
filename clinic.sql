BEGIN;

CREATE TABLE Patients (
	patient_id INT NOT NULL,
	pesel VARCHAR(11),
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	date_of_birth DATE NOT NULL,
	address VARCHAR(50) ,
	city VARCHAR(26),
	zip_code VARCHAR(6) ,
	phone_number VARCHAR(9) NOT NULL,
	email_address VARCHAR(30),
	PRIMARY KEY (patient_id)
);

INSERT INTO Patients VALUES (1, '97062064754', 'Mateusz', 'Kowalski', '1997-06-20', 'Warynskiego 13', 'Torun', '87-100', '728534121', 'matikowalski@interia.pl');
INSERT INTO Patients VALUES (2, '58012659938', 'Eugeniusz', 'Walczak', '1958-01-26', 'Mickiewicza 132/12', 'Torun', '87-100', '542324367', NULL);
INSERT INTO Patients VALUES (3, NULL, 'Denys', 'Kovalenko', '2000-03-23', 'Mickiewicza 132/12', 'Torun', '87-100', '501844140', 'denyskov@wp.pl');
INSERT INTO Patients VALUES (4, '02262054621', 'Nikola', 'Jankowska', '2002-06-20', 'Mostowa 13/1', 'Torun', '87-100', '608244154', 'nikijankowska@onet.pl');
INSERT INTO Patients VALUES (5, '67051039999', 'Eustachy', 'Wozniak', '1967-05-10', 'Waniliowa 15', 'Torun', '87-100', '575354102', NULL);

CREATE TABLE Doctors (
	doctor_id INT NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	job_execution_number INT NOT NULL UNIQUE,
	specialization VARCHAR(40),
	PRIMARY KEY (doctor_id)
);

INSERT INTO Doctors VALUES (1, 'Jan', 'Kowalski', 654321, 'internista');
INSERT INTO Doctors VALUES (2, 'Maria', 'Borowska', 742148, 'ginekolog');
INSERT INTO Doctors VALUES (3, 'Elzbieta', 'Kalinowska', 854317, 'pediatra');

CREATE TABLE Visits (
	visit_id INT NOT NULL,
	date_of_visit DATE NOT NULL,
	time_of_visit TIME NOT NULL,
	duration_in_minutes INT NOT NULL,
	patient_id BIGINT NOT NULL,
	doctor_id INT NOT NULL,
	office_number INT NOT NULL UNIQUE,
	PRIMARY KEY (visit_id)
);

INSERT INTO Visits VALUES (1, '2022-06-30', '12:00:00', 30, 2, 1, 3);
INSERT INTO Visits VALUES (2, '2022-06-30', '10:00:00', 45, 4, 2, 2);

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
	visit_id BIGINT NOT NULL,
	PRIMARY KEY (prescription_id)
);

INSERT INTO Prescriptions VALUES (1, 'Lekarstwo na bol gardla', 3342, '2022-06-30', '2022-07-30', 1);

ALTER TABLE Visits ADD CONSTRAINT Visits_fk0 FOREIGN KEY (patient_id) REFERENCES Patients(patient_id);
ALTER TABLE Visits ADD CONSTRAINT Visits_fk1 FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id);
ALTER TABLE Visits ADD CONSTRAINT Visits_fk2 FOREIGN KEY (office_number) REFERENCES Offices(office_number);

ALTER TABLE Prescriptions ADD CONSTRAINT Prescriptions_fk0 FOREIGN KEY (visit_id) REFERENCES Visits(visit_id);

END;
