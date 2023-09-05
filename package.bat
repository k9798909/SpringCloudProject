echo eureka-server start
cd .\eureka-server
call mvn -T 4 clean package -Pprod  -Dmaven.test.skip
echo eureka-server success

cd ..

echo gatewar-server start
cd .\gatewar-server
call mvn -T 4 clean package -Pprod -Dmaven.test.skip
echo gatewar-server success

cd ..

echo product-service start
cd .\product-service
call mvn -T 4 clean package -Pprod -Dmaven.test.skip
echo product-service success

cd ..

echo shop-app start
cd .\shop-app
call npm run build-only
echo shop-app

cd ..

docker-compose up --build 



pause