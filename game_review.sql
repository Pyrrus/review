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
-- Name: games; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE games (
    id integer NOT NULL,
    name character varying,
    game_type character varying,
    description text,
    year character varying,
    platform_id integer
);


ALTER TABLE games OWNER TO "Guest";

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

ALTER SEQUENCE game_id_seq OWNED BY games.id;


--
-- Name: platforms; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE platforms (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE platforms OWNER TO "Guest";

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

ALTER SEQUENCE platform_id_seq OWNED BY platforms.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE reviews (
    id integer NOT NULL,
    name character varying,
    content text,
    created_at character varying,
    game_id integer
);


ALTER TABLE reviews OWNER TO "Guest";

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

ALTER SEQUENCE review_id_seq OWNED BY reviews.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY games ALTER COLUMN id SET DEFAULT nextval('game_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY platforms ALTER COLUMN id SET DEFAULT nextval('platform_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY reviews ALTER COLUMN id SET DEFAULT nextval('review_id_seq'::regclass);


--
-- Name: game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('game_id_seq', 1, false);


--
-- Data for Name: games; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY games (id, name, game_type, description, year, platform_id) FROM stdin;
\.


--
-- Name: platform_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('platform_id_seq', 1, false);


--
-- Data for Name: platforms; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY platforms (id, name) FROM stdin;
\.


--
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('review_id_seq', 1, false);


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY reviews (id, name, content, created_at, game_id) FROM stdin;
\.


--
-- Name: game_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY games
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);


--
-- Name: platform_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY platforms
    ADD CONSTRAINT platform_pkey PRIMARY KEY (id);


--
-- Name: review_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY reviews
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

