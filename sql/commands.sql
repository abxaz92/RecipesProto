alter table users add constraint lpu_fk foreign key (lpu_id) references lpu(id);

grant ALL privileges on database recept to recept;
grant ALL privileges on all tables in schema public to recept;
grant ALL privileges on all sequences in schema public to recept;