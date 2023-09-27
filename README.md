# my_microservice_project

這個專案是一個基於微服務架構的專案，旨在實現多個獨立的服務共同合作以構建一個功能完整的應用程序。下面是專案的概要和目標。

專案目前不會再更新，目前實作功能皆有測試過，在正常情形步驟下皆可運作，但一些細節並沒有達到完美，如果有異常皆會在畫面及log顯示，可以用來debug。

## 專案內容

目前的專案內容包括：

1. **Docker 容器化**: 使用 Docker 容器化和管理不同的服務，這有助於簡化部署和環境管理。

2. **前端（shop-app）**: 使用 Vue 3，並整合 TypeScript、SCSS、Vuetify、Pinia、Axios 等技術，以實現現代化的前端界面。

3. **購物車資料服務（cart-app）**: 使用 Golang 撰寫，借助 Gin 框架，並使用 Redis 作為資料庫，實現購物車相關功能。

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













