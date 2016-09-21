--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: game; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE game (
    id integer NOT NULL,
    name character varying,
    game_type character varying,
    description text,
    year character varying,
    review_id integer,
    platform_id integer
);


ALTER TABLE game OWNER TO "Guest";

--
-- Name: game_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE game_id_seq OWNER TO "Guest";

--
-- Name: game_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE game_id_seq OWNED BY game.id;


--
-- Name: platform; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE platform (
    id integer NOT NULL,
    name character varying,
    game_id integer
);


ALTER TABLE platform OWNER TO "Guest";

--
-- Name: platform_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE platform_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE platform_id_seq OWNER TO "Guest";

--
-- Name: platform_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE platform_id_seq OWNED BY platform.id;


--
-- Name: review; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE review (
    id integer NOT NULL,
    name character varying,
    content text,
    created_at timestamp without time zone,
    game_id integer
);


ALTER TABLE review OWNER TO "Guest";

--
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE review_id_seq OWNER TO "Guest";

--
-- Name: review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE review_id_seq OWNED BY review.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY game ALTER COLUMN id SET DEFAULT nextval('game_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY platform ALTER COLUMN id SET DEFAULT nextval('platform_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY review ALTER COLUMN id SET DEFAULT nextval('review_id_seq'::regclass);


--
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY game (id, name, game_type, description, year, review_id, platform_id) FROM stdin;
\.


--
-- Name: game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('game_id_seq', 1, false);


--
-- Data for Name: platform; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY platform (id, name, game_id) FROM stdin;
\.


--
-- Name: platform_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('platform_id_seq', 1, false);


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY review (id, name, content, created_at, game_id) FROM stdin;
\.


--
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('review_id_seq', 1, false);


--
-- Name: game_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY game
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);


--
-- Name: platform_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY platform
    ADD CONSTRAINT platform_pkey PRIMARY KEY (id);


--
-- Name: review_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

