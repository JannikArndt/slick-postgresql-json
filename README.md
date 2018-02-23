# Slick with slick-pg on a PostgreSQL with JSON

1. run a PostgreSQL locally 
2. configure access via `src/main/resources/application.conf` 
3. run `CREATE SCHEMA slick_pg_test`
4. run `src/main/resources/create_db.sql`
5. run `sbt run`

## Example 

This query

```sql
SELECT json -> 'location' -> 'lat' FROM slick_pg_test.jsontable;
```

equals this code

```scala
table.map(_.json +> "location" +> "lat")
```

## Resources

- https://github.com/slick/slick
- https://github.com/tminglei/slick-pg
- https://www.postgresql.org/docs/10/static/functions-json.html