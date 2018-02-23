DROP TABLE slick_pg_test.jsontable;

CREATE TABLE slick_pg_test.jsontable (
  id      SERIAL PRIMARY KEY,
  json    JSONB,
  created TIMESTAMP
);

CREATE INDEX json_idx_1
  ON slick_pg_test.jsontable USING GIN (json);
CREATE INDEX json_idx_2
  ON slick_pg_test.jsontable USING GIN ((json -> 'location'));

-- https://www.postgresql.org/docs/10/static/functions-json.html

SELECT
  json -> 'location' -> 'lat'  AS lat,
  json -> 'location' -> 'long' AS long
FROM slick_pg_test.jsontable;

SELECT json -> 'name' AS name
FROM slick_pg_test.jsontable; -- "Watership Down"

SELECT json ->> 'name' AS name
FROM slick_pg_test.jsontable; -- Watership Down

SELECT
  json -> 'residents'                AS residents,
  json -> 'residents' -> 0 -> 'name' AS example
FROM slick_pg_test.jsontable
WHERE json ->> 'name' = 'Watership Down';
-- [{"age": 4, "name": "Fiver", "role": null}, {"age": 6, "name": "Bigwig", "role": "Owsla"}]  /  "Fiver"
