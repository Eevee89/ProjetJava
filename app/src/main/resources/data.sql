-- Fill work_time table with all possibles slots

INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Lundi', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Mardi', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Mercredi', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Jeudi', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Vendredi', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Samedi', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Dimanche', 8, 13);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Lundi', 13, 18);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Mardi', 13, 18);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Mercredi', 13, 18);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Jeudi', 13, 18);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Vendredi', 13, 18);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Samedi', 13, 18);
INSERT INTO work_time (day, start_hour, end_hour) VALUES ('Dimanche', 13, 18);

-- Add some addresses

INSERT INTO address (street, city, zip_code) VALUES ('8 rue Jean Lamour', 'Vandoeuvre-les-Nancy', '54127');
INSERT INTO address (street, city, zip_code) VALUES ('44 rue Aristide Briand', 'Dijon', '21000');
INSERT INTO address (street, city, zip_code) VALUES ('21 Avenue Jeanne DArc', 'Vandoeuvre-les-Nancy', '54127');
INSERT INTO address (street, city, zip_code) VALUES ('123 Rue de Nulpar', 'Perpette', '66099');
INSERT INTO address (street, city, zip_code) VALUES ('5 Chemin de la Tour Carillon', 'Rosalia', '22222');
INSERT INTO address (street, city, zip_code) VALUES ('8 Rue du marché', 'Dijon', '21000');

-- Add some patients
INSERT INTO patient (first_name, last_name, gender, birth_date, email, phone, address_id, password)
VALUES ('Yves', 'Remort', false, '01/01/2000', 'yves.remort@nomail.fake', '0123456789', 3, '0123456789');

INSERT INTO patient (first_name, last_name, gender, birth_date, email, phone, address_id, password)
VALUES ('Gaspard', 'Touze', false, '14/07/2008', 'gaspard.touze69@hotmail.com', '0987654321', 4, 'p4ssw0rd');

-- Add some centers

INSERT INTO center (phone, address_id) VALUES ('0912121212', 1);
INSERT INTO center (phone, address_id) VALUES ('0915151515', 2);

-- Add some staff

INSERT INTO staff (first_name, last_name, phone, privilege) VALUES ('Rémy', 'Neur', '0945454545', 2);
INSERT INTO staff (first_name, last_name, phone, privilege) VALUES ('Guy', 'Dmichelin', '0950504545', 1);
INSERT INTO staff (first_name, last_name, phone, privilege) VALUES ('Mandy', 'Paplusse', '0950504545', 1);
INSERT INTO staff (first_name, last_name, phone, privilege) VALUES ('Mélanie', 'Sanedanlgarage', '0950704895', 0);
INSERT INTO staff (first_name, last_name, phone, privilege) VALUES ('Sandra', 'Nicouette', '0950563245', 0);

-- Add work times (5j travaillés + 2j consécutifs de repos)

INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 1);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 2);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 3);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 4);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 5);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 6);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 7);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 8);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 9);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (1, 10);

INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 3);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 4);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 5);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 6);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 7);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 8);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 9);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 10);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 11);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (2, 12);

INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 5);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 6);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 7);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 8);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 9);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 10);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 11);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 12);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 13);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (3, 14);

INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 1);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 2);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 7);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 8);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 9);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 10);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 11);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 12);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 13);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (4, 14);

INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 1);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 2);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 3);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 4);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 9);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 10);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 11);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 12);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 13);
INSERT INTO staff_work_times (staff_id, work_time_id) VALUES (5, 14);

-- Add centers-staff corres

INSERT INTO staff_centers (center_id, staff_id) VALUES (1, 1);
INSERT INTO staff_centers (center_id, staff_id) VALUES (2, 1);
INSERT INTO staff_centers (center_id, staff_id) VALUES (1, 2); 
INSERT INTO staff_centers (center_id, staff_id) VALUES (2, 3);
INSERT INTO staff_centers (center_id, staff_id) VALUES (1, 4);
INSERT INTO staff_centers (center_id, staff_id) VALUES (2, 5);    