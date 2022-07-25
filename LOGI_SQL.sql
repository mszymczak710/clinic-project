

CREATE TABLE logs(
  id int GENERATED ALWAYS AS IDENTITY,
  tab varchar(50),
  date timestamp(6),
  current_opperation varchar(6),
  count_of_entities bigint
);
create or replace function entities_count()
returns bigint
language plpgsql
as
$$
declare
   count bigint;
begin
   count = (SELECT((SELECT COUNT(*) FROM doctors)+(SELECT COUNT(*) FROM offices)+(SELECT COUNT(*) FROM prescriptions)+(SELECT COUNT(*) FROM visits)));
   return count;
end;
$$;

CREATE OR REPLACE FUNCTION public.logs()
	RETURNS trigger
	VOLATILE
	 LANGUAGE 'plpgsql'
	COST 100
AS $BODY$
BEGIN

IF (TG_OP = 'INSERT') THEN
INSERT INTO logs(tab,date,current_opperation,count_of_entities)
VALUES (TG_TABLE_NAME ,now(),'INSERT', entities_count());

RETURN NEW;

ELSIF (TG_OP = 'UPDATE') THEN
INSERT INTO logs(tab,date,current_opperation,count_of_entities)
VALUES (TG_TABLE_NAME ,user,now(),'UPDATE',entities_count());
RETURN NEW;

ELSIF (TG_OP = 'DELETE') THEN
INSERT INTO logs(tab,date,current_opperation,count_of_entities)
VALUES (TG_TABLE_NAME ,user,now(),'DELETE',entities_count());
RETURN OLD; -- . The usual idiom in DELETE triggers is to return OLD.
END IF; 

END; 
$BODY$;