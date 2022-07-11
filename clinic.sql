BEGIN;

CREATE TABLE Patient (
	patient_id BIGINT NOT NULL,
	pesel VARCHAR(11),
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	date_of_birth DATE NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(15) NOT NULL,
	zip_code VARCHAR(6) NOT NULL,
	phone_number NUMERIC(9) NOT NULL,
	email_address VARCHAR(30),
	PRIMARY KEY (patient_id)
);

INSERT INTO Patient VALUES (1, '97062064754', 'Mateusz', 'Kowalski', '1997-06-20', 'Warynskiego 13', 'Torun', '87-100', 728534121, 'matikowalski@interia.pl');
INSERT INTO Patient VALUES (2, '58012659938', 'Eugeniusz', 'Walczak', '1958-01-26', 'Mickiewicza 132/12', 'Torun', '87-100', 542324367, NULL);
INSERT INTO Patient VALUES (3, NULL, 'Denys', 'Kovalenko', '2000-03-23', 'Mickiewicza 132/12', 'Torun', '87-100', 501844140, 'denyskov@wp.pl');
INSERT INTO Patient VALUES (4, '02262054621', 'Nikola', 'Jankowska', '2002-06-20', 'Mostowa 13/1', 'Torun', '87-100', 608244154, 'nikijankowska@onet.pl');
INSERT INTO Patient VALUES (5, '67051039999', 'Eustachy', 'Wozniak', '1967-05-10', 'Waniliowa 15', 'Torun', '87-100', 575354102, NULL);

CREATE TABLE Doctor (
	doctor_id INT NOT NULL,
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	job_execution_number INT NOT NULL UNIQUE,
	specialization VARCHAR(30) NOT NULL,
	is_manager BOOLEAN NOT NULL,
	PRIMARY KEY (doctor_id)
);

INSERT INTO Doctor VALUES (1, 'Jan', 'Kowalski', 654321, 'internista', false);
INSERT INTO Doctor VALUES (2, 'Maria', 'Borowska', 742148, 'ginekolog', true);
INSERT INTO Doctor VALUES (3, 'Elzbieta', 'Kalinowska', 854317, 'pediatra', false);

CREATE TABLE Visit (
	visit_id BIGINT NOT NULL,
	date_of_visit DATE NOT NULL,
	time_of_visit TIME NOT NULL,
	duration_in_minutes INT NOT NULL,
	patient_id BIGINT NOT NULL,
	doctor_id INT NOT NULL,
	office_number INT NOT NULL UNIQUE,
	PRIMARY KEY (visit_id)
);

INSERT INTO Visit VALUES (1, '2022-06-30', '12:00:00', 30, 2, 1, 3);
INSERT INTO Visit VALUES (2, '2022-06-30', '10:00:00', 45, 4, 2, 2);

CREATE TABLE Office (
	office_number INT NOT NULL,
	type_of_office VARCHAR(20) NOT NULL,
	PRIMARY KEY (office_number)
);

INSERT INTO Office VALUES (1, 'chorob wewnetrznych');
INSERT INTO Office VALUES (2, 'ginekologiczny');
INSERT INTO Office VALUES (3, 'chorob wewnetrznych');
INSERT INTO Office VALUES (4, 'pediatryczny');

CREATE TABLE Prescription (
	prescription_id INT NOT NULL,
	description VARCHAR(150) NOT NULL,
	code_of_prescription INT NOT NULL,
	date_of_issue DATE NOT NULL,
	expiration_date DATE NOT NULL,
	visit_id BIGINT NOT NULL,
	patient_id BIGINT NOT NULL,
	PRIMARY KEY (prescription_id)
);

INSERT INTO Prescription VALUES (1, 'Lekarstwo na bol gardla', 3342, '2022-06-30', '2022-07-30', 1, 2);

ALTER TABLE Visit ADD CONSTRAINT Visit_fk0 FOREIGN KEY (patient_id) REFERENCES Patient(patient_id);
ALTER TABLE Visit ADD CONSTRAINT Visit_fk1 FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id);
ALTER TABLE Visit ADD CONSTRAINT Visit_fk2 FOREIGN KEY (office_number) REFERENCES Office(office_number);

ALTER TABLE Prescription ADD CONSTRAINT Prescription_fk0 FOREIGN KEY (visit_id) REFERENCES Visit(visit_id);
ALTER TABLE Prescription ADD CONSTRAINT Prescription_fk1 FOREIGN KEY (patient_id) REFERENCES Patient(patient_id);

END;