# Full-Stack ECL Testing App

## Start Backend + Frontend (The frontend also bundled in backend jar)
- Prerequisite:
  - Java 17 should be installed

- Start (Linux): 
  - `cd ./eclbackend/bin`
  - `java -jar  eclbackend-0.0.1-SNAPSHOT.jar`

- Start (Windows):
  - `cd \eclbackend\bin` 
  - `java -jar  eclbackend-0.0.1-SNAPSHOT.jar`

- APIs:
    - `GET /products`
    - `POST /orders` — { productId, quantity }
    - `GET /orders/{id}` (optional)
	
- Test via browser
    - UI: http://localhost:18080
	- API: http://localhost:18080/products
	- API: http://localhost:18080/orders/1
	
- Test API via CURL (make sure curl is installed)
    - Get product list:
	  curl -X GET http://localhost:18080/products

	- Get order details:
	  curl -X GET http://localhost:18080/orders/1

    - [Linux] Place an order:
	  curl -X POST http://localhost:18080/orders \
			-H "Content-Type: application/json" \
			-d '{"productId": 1, "quantity": 2}'

    - [Windows] Place an order:
      curl -X POST http://localhost:18080/orders -H "Content-Type: application/json" -d "{\"productId\": 1, \"quantity\": 2}"


## [Optional] Frontend (React + Taro)
- Install deps: `npm install`
- Run (WeChat MiniProgram or H5): `npm run dev:weapp` or `npm run dev:h5`

## Notes
- No database, uses in-memory storage
- The Frontend already bundled along with Backend jar

