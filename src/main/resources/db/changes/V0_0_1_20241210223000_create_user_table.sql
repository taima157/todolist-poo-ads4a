--liquibase formatted sql

--changeset gustavotaima:1
CREATE TABLE users (
   id uuid not null default gen_random_uuid (),
   name character varying not null,
   email character varying not null,
   password character varying not null,

   constraint users_pkey primary key (id),
   constraint users_email_key unique (email)
);

--rollback drop table users;