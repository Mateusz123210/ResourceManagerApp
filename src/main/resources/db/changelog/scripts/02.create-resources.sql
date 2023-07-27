--liquibase formatted sql
--changeset mateusz:1
CREATE TABLE resources (
                                  id integer NOT NULL,
                                  type smallint,
                                  user_id_id integer NOT NULL,
                                  creation_time timestamp(6) without time zone NOT NULL,
                                  modification_time timestamp(6) without time zone NOT NULL,
                                  metadata character varying(255) NOT NULL,
                                  name character varying(255) NOT NULL,
                                  CONSTRAINT resources_type_check CHECK (((type >= 0) AND (type <= 2)))
);

ALTER TABLE ONLY resources ADD CONSTRAINT resources_pkey PRIMARY KEY (id);

ALTER TABLE resources OWNER TO postgres;

CREATE SEQUENCE resources_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE resources_id_seq OWNER TO postgres;
ALTER SEQUENCE resources_id_seq OWNED BY resources.id;
ALTER TABLE ONLY resources ALTER COLUMN id SET DEFAULT nextval('resources_id_seq'::regclass);