docker-compose down
docker-compose up -d
docker-compose ps
sleep 5
rm -rf target
mvn clean verify -Dtest=CukesRunner -Dcucumber.options="-t @Site=DemoWebsite" > log.txt