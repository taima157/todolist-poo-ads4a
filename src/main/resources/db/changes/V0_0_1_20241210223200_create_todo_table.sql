--liquibase formatted sql

--changeset gustavotaima:1
CREATE TABLE todo (
   id uuid not null default gen_random_uuid (),
   id_user uuid not null,
   title character varying not null default ''::character varying,
   description character varying not null,
   completed boolean not null default false,

   constraint todo_pkey primary key (id),
   constraint todo_id_user_fkey foreign key (id_user) references users (id)
);

--rollback drop table todo;