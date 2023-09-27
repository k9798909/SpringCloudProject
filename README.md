# my_microservice_project

這個專案是一個基於微服務架構的專案，旨在實現多個獨立的服務共同合作以構建一個功能完整的應用程序。下面是專案的概要和目標。

專案目前不會再更新，結帳及訂單未實作，目前實作功能皆有測試過，在正常情形步驟下皆可運作，但一些細節並沒有達到完美，如果有異常皆會在畫面及log顯示，可以用來debug。

## 專案內容

目前的專案內容包括：

1. **Docker 容器化**: 使用 Docker 容器化和管理不同的服務，這有助於簡化部署和環境管理。

2. **前端（shop-app）**: 使用 Vue 3，並整合 TypeScript、SCSS、Vuetify、Pinia、Axios 等技術，以實現現代化的前端界面。

3. **購物車資料服務（cart-app）**: 使用 Golang 撰寫，使用 Gin 框架，並使用 Redis 作為資料庫，實現購物車相關功能。

4. **商品資料服務（product-service）**: 使用 Spring Boot、Java 17、JPA，資料庫使用 PostgreSQL，提供商品相關的服務。

5. **微服務管理(eureka-server)**: 使用 Spring Cloud Eureka 管理微服務狀態，以確保它們可以相互發現和通信。

6. **權限路由控管(gatewar-server)**: 使用 Spring Cloud Gateway 統一管理各個微服務的 URL，同時實現登入驗證，使用 Spring Security 進行 token 驗證，統一管理需要驗證的 URL。Spring Security 使用 WebFlux 寫法。



## 開始使用

以下是如何設定和運行專案的簡要步驟：

1. 安裝 Docker：確保你已經安裝了 Docker。

2. 開啟package.bat。

3. 執行完後即可開啟，http://localhost:5173/。

## 圖片功能展示
1.首頁
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/73f6911f-d51a-490d-bb6a-af2d85c42897)
2.註冊
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/47df51eb-6918-4e5c-a12e-9ac71109642e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/19e9be75-1417-499e-a9bf-043ae3500bbc)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/a4f99d58-dc4a-40b4-98d5-96ebabe42313)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/55c26dc6-cd8a-4365-b973-0b6087c9012e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/45ae7946-c1ca-4d30-a997-2e0a91eb08d1)
3.登入
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/64a38028-9130-48d0-ab1b-9a7bde1e41f8)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/8c1808e8-4344-4b07-8708-52907ee2ab26)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/d0f87ca3-d0a1-4099-a63c-65af32a60b59)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/26e2b688-953e-457f-af63-313b8047236e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/caf2158f-ca95-4459-a253-d387632701cf)
4.個人資訊查詢修改
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/0ed5f01d-f277-4ee7-b6f2-15520d2af52d)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/66295a6f-798c-4ec6-9c72-0b971259fd94)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/6afbe2ed-cec3-494e-99aa-ebb66afdff0e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/f243a677-8599-4c1a-af0f-ca5c3183bfe4)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/bcf27994-5495-4463-ab8a-346ba734faa0)
5.商品展示加入購物車
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/4407dd7f-8ac7-4c52-bf17-64c409a6849f)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/63a11556-eef0-4ab6-bd08-656700ac37f0)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/fe7611cc-7a85-4da4-a63b-1403942e41f1)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/a5f89f50-e963-4c74-9939-10da531c8ca1)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/7acc16f7-8428-4235-aa0d-6c7e7640fc8b)













