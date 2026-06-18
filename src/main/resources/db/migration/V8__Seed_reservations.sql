-- Flight 1: JFK → LAX, 2026-06-20, N123DL
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-20'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='N123DL'),
       (SELECT id FROM airports WHERE iata_code='LAX'),
       (SELECT id FROM airports WHERE iata_code='JFK'),
       user_id
FROM seat_assignments;

-- Flight 2: LAX → ORD, 2026-06-20, N456UA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a19'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a25'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a26'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a27'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a28'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a29'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a30'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-20'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='N456UA'),
       (SELECT id FROM airports WHERE iata_code='ORD'),
       (SELECT id FROM airports WHERE iata_code='LAX'),
       user_id
FROM seat_assignments;

-- Flight 3: ORD → LHR, 2026-06-21, N789AA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a19'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-21'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='N789AA'),
       (SELECT id FROM airports WHERE iata_code='LHR'),
       (SELECT id FROM airports WHERE iata_code='ORD'),
       user_id
FROM seat_assignments;

-- Flight 4: LHR → CDG, 2026-06-21, G-BAAA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a25'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a26'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a27'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a28'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a29'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a30'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-21'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='G-BAAA'),
       (SELECT id FROM airports WHERE iata_code='CDG'),
       (SELECT id FROM airports WHERE iata_code='LHR'),
       user_id
FROM seat_assignments;

-- Flight 5: CDG → FRA, 2026-06-22, D-AIXA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a19'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-22'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='D-AIXA'),
       (SELECT id FROM airports WHERE iata_code='FRA'),
       (SELECT id FROM airports WHERE iata_code='CDG'),
       user_id
FROM seat_assignments;

-- Flight 6: FRA → AMS, 2026-06-22, A6-EDA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a25'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a26'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a27'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a28'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a29'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a30'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-22'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='A6-EDA'),
       (SELECT id FROM airports WHERE iata_code='AMS'),
       (SELECT id FROM airports WHERE iata_code='FRA'),
       user_id
FROM seat_assignments;

-- Flight 7: AMS → DXB, 2026-06-23, VH-EBA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a25'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a26'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a27'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a28'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a29'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a30'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-23'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='VH-EBA'),
       (SELECT id FROM airports WHERE iata_code='HND'),
       (SELECT id FROM airports WHERE iata_code='DXB'),
       user_id
FROM seat_assignments;

-- Flight 8: DXB → HND, 2026-06-23, F-HRBA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a19'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-23'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='F-HRBA'),
       (SELECT id FROM airports WHERE iata_code='DXB'),
       (SELECT id FROM airports WHERE iata_code='AMS'),
       user_id
FROM seat_assignments;

-- Flight 9: HND → SYD, 2026-06-24, 9V-SWA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a18'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a19'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a20'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a24'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a25'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-24'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='9V-SWA'),
       (SELECT id FROM airports WHERE iata_code='SYD'),
       (SELECT id FROM airports WHERE iata_code='HND'),
       user_id
FROM seat_assignments;

-- Flight 10: SYD → SIN, 2026-06-24, TC-LSA
WITH seat_assignments (seat, user_id) AS (
    VALUES
        (1,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a26'::uuid),
        (2,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a27'::uuid),
        (3,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a28'::uuid),
        (4,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a29'::uuid),
        (5,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a30'::uuid),
        (6,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03'::uuid),
        (7,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'::uuid),
        (8,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05'::uuid),
        (9,  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06'::uuid),
        (10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07'::uuid),
        (11, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08'::uuid),
        (12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09'::uuid),
        (13, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10'::uuid),
        (14, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid),
        (15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12'::uuid)
)
INSERT INTO reservations (flight_date, seat_number, status, airplane_id, arrival_airport_id, departure_airport_id, user_id)
SELECT '2026-06-24'::date, seat, 'CONFIRMED',
       (SELECT id FROM airplanes WHERE tail_number='TC-LSA'),
       (SELECT id FROM airports WHERE iata_code='SIN'),
       (SELECT id FROM airports WHERE iata_code='SYD'),
       user_id
FROM seat_assignments;