CREATE TABLE public.reservations (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    flight_date date NOT NULL,
    seat_number int4 NOT NULL,
    status varchar(255) NOT NULL,
    airplane_id uuid NOT NULL,
    arrival_airport_id uuid NOT NULL,
    departure_airport_id uuid NOT NULL,
    user_id uuid NOT NULL,
    CONSTRAINT reservations_pkey PRIMARY KEY (id),
    CONSTRAINT reservations_seat_number_check CHECK ((seat_number >= 1)),
    CONSTRAINT reservations_status_check CHECK (((status)::text = ANY ((ARRAY['CONFIRMED'::character varying, 'CANCELLED'::character varying])::text[]))),
	CONSTRAINT fk_reservations_users FOREIGN KEY (user_id) REFERENCES public.users(id),
	CONSTRAINT fk_reservations_airports_arrival FOREIGN KEY (arrival_airport_id) REFERENCES public.airports(id),
	CONSTRAINT fk_reservations_airports_departure FOREIGN KEY (departure_airport_id) REFERENCES public.airports(id),
	CONSTRAINT fk_reservations_airplane FOREIGN KEY (airplane_id) REFERENCES public.airplanes(id)
);