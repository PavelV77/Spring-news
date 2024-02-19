CREATE SCHEMA IF NOT EXISTS newsschema;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA newsschema;

DROP TYPE IF EXISTS newsschema.activity_type_for_likes CASCADE;
CREATE TYPE newsschema.activity_type_for_likes AS enum('LIKE','DISLIKE','VIEW');
DROP TYPE IF EXISTS newsschema.status_type_news CASCADE;
CREATE TYPE newsschema.status_type_news AS enum('PUBLISHED','UNPUBLISHED');

DROP TABLE IF EXISTS newsschema.likes;
DROP TABLE IF EXISTS newsschema.comment;
DROP TABLE IF EXISTS newsschema.news;
DROP TABLE IF EXISTS newsschema.user;

CREATE TABLE newsschema.user(
    id uuid NOT NULL DEFAULT newsschema.uuid_generate_v4()  PRIMARY KEY,
	login text NULL,
	insert_at int8 NOT NULL,
    update_at int8 NOT NULL,
	UNIQUE(login)
);

CREATE TABLE newsschema.news (
    id uuid NOT NULL DEFAULT newsschema.uuid_generate_v4()  PRIMARY KEY,
	head text NULL,
	body text NULL,
	status newsschema.status_type_news NOT NULL,
	update_at int8 NOT NULL,
	insert_at int8 NOT NULL,
	user_id uuid NOT NULL,
	FOREIGN KEY (user_id) references newsschema."user"(id)
);

CREATE TABLE newsschema.comment (
	id uuid NOT NULL DEFAULT newsschema.uuid_generate_v4() PRIMARY KEY,
	body text NULL,
    news_id uuid NOT NULL,
    user_id uuid NOT NULL,
    insert_at int8 NOT NULL,
    update_at int8 NOT NULL,
    FOREIGN key (news_id) references newsschema.news(id),
    FOREIGN key (user_id) references newsschema."user"(id)
);

CREATE TABLE NewsSchema.like (
    id uuid NOT NULL DEFAULT newsschema.uuid_generate_v4() PRIMARY KEY,
	user_id uuid NOT NULL,
	news_id uuid NOT NULL,
	insert_at int8 NOT NULL,
	update_at int8 NOT NULL,
    type_of_activity newsschema.activity_type_for_likes NOT NULL,
	FOREIGN KEY (user_id) REFERENCES newsschema."user"(id),
	FOREIGN KEY (news_id) REFERENCES newsschema.news(id)
);