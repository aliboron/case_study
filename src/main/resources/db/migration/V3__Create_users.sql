CREATE TABLE public.users (
                              id uuid NOT NULL,
                              address varchar(255) NOT NULL,
                              email varchar(255) NOT NULL,
                              "name" varchar(255) NOT NULL,
                              password_hash varchar(255) NOT NULL,
                              "role" varchar(255) NOT NULL,
                              surname varchar(255) NOT NULL,
                              CONSTRAINT users_email_unique UNIQUE (email),
                              CONSTRAINT users_pkey PRIMARY KEY (id),
                              CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'PASSENGER'::character varying])::text[])))
);