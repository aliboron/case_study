CREATE TABLE public.airports (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    city varchar(255) NOT NULL,
    country varchar(255) NOT NULL,
    iata_code varchar(3) NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT airports_pkey PRIMARY KEY (id),
    CONSTRAINT airports_iata_code_uq UNIQUE (iata_code)
);