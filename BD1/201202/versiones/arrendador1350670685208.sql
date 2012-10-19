--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.6
-- Dumped by pg_dump version 9.1.6
-- Started on 2012-10-19 16:18:05 UYST

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 163 (class 1259 OID 16402)
-- Dependencies: 5
-- Name: arrendador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE arrendador (
    ci character varying(9) NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE public.arrendador OWNER TO postgres;

--
-- TOC entry 1893 (class 0 OID 16402)
-- Dependencies: 163 1894
-- Data for Name: arrendador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY arrendador (ci, nombre) FROM stdin;
1111111	Carlos
2222222	Mirtha
3333333	Paula
\.


--
-- TOC entry 1892 (class 2606 OID 16439)
-- Dependencies: 163 163 1895
-- Name: arrendador_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY arrendador
    ADD CONSTRAINT arrendador_pk PRIMARY KEY (ci);


-- Completed on 2012-10-19 16:18:05 UYST

--
-- PostgreSQL database dump complete
--

