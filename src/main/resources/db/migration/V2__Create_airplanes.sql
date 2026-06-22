CREATE TABLE public.airplanes (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	airline varchar(255) NOT NULL,
	capacity int4 NOT NULL,
	model varchar(255) NOT NULL,
	tail_number varchar(255) NOT NULL,
	CONSTRAINT airplanes_capacity_check CHECK ((capacity >= 1)),
	CONSTRAINT airplanes_pkey PRIMARY KEY (id),
	CONSTRAINT airplanes_tail_number_uq UNIQUE (tail_number)
);