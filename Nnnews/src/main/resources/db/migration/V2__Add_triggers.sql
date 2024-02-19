CREATE OR REPLACE FUNCTION newsschema.insert_and_update_date_function() RETURNS trigger AS
$BODY$
BEGIN
	NEW.insert_at = EXTRACT(epoch FROM now())*1000;
	NEW.update_at = NEW.insert_at;
	return new;
END
$BODY$
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION newsschema.update_date_function() RETURNS trigger AS
$BODY$
BEGIN
	NEW.update_at = EXTRACT(epoch FROM now())*1000;
	return new;
END
$BODY$
LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER insert_update_date_news_trigger BEFORE INSERT
ON newsschema.news
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema."insert_and_update_date_function"();

CREATE OR REPLACE TRIGGER only_update_date_news_trigger BEFORE UPDATE
ON newsschema.news
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema."update_date_function"();
