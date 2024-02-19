CREATE OR REPLACE TRIGGER insert_update_date_comment_trigger BEFORE INSERT
ON newsschema.comment
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema.insert_and_update_date_function();

CREATE OR REPLACE TRIGGER only_update_date_comment_trigger BEFORE UPDATE
ON newsschema.comment
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema."update_date_function"();



CREATE OR REPLACE TRIGGER insert_update_date_like_trigger BEFORE INSERT
ON newsschema.like
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema.insert_and_update_date_function();

CREATE OR REPLACE TRIGGER only_update_data_like_trigger BEFORE UPDATE
ON newsschema.like
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema.update_date_function();



CREATE OR REPLACE TRIGGER only_update_date_user_trigger BEFORE INSERT
ON newsschema.user
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema.insert_and_update_date_function();

CREATE OR REPLACE TRIGGER only_insert_date_user_trigger BEFORE UPDATE
ON newsschema.user
	FOR EACH ROW
	EXECUTE PROCEDURE newsschema.update_date_function();