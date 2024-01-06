# National Blood Donor Registry

This is the assignment for the Distributed Systems course at the University of Harokopio, Dept. of Informatics and Telematics.

## Group #51
Chrysanthos Pepes, 219876
Konstantinos Gerokostas, 219137

## Database

The database is PostgreSQL and it is hosted on Docker. To run the database, run the following command:

```bash
docker run --name ds-blooddonor-51 --rm \
-e POSTGRES_PASSWORD=pass123 \
-e POSTGRES_USER=dbuser \
-e POSTGRES_DB=blooddonor \
-d --net=host \
-v ds-blooddonor-51-vol:/var/lib/postgresql/data \
postgres:14
```