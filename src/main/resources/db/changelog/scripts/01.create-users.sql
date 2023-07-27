--liquibase formatted sql
--changeset mateusz:1

CREATE TABLE users (
                              id integer NOT NULL,
                              type smallint,
                              creation_time timestamp(6) without time zone NOT NULL,
                              modification_time timestamp(6) without time zone NOT NULL,
                              name character varying(255) NOT NULL,
                              nick character varying(255) NOT NULL,
                              surname character varying(255) NOT NULL,
                              CONSTRAINT users_type_check CHECK (((type >= 0) AND (type <= 1)))
);

ALTER TABLE ONLY users ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE users OWNER TO postgres;

CREATE SEQUENCE users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

ALTER SEQUENCE users_id_seq OWNED BY users.id;

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);