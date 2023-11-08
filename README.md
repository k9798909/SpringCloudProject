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
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/07fb505a-1b2e-43f3-a61a-ea3f07e4ef01)

2.註冊
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/2118a790-aa2d-4d36-b17a-771ac8a22a5a)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/7bc871ed-8816-4774-83f6-c0123e11af4c)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/822f2b67-e379-4a89-b680-62048bbfed87)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/40b57be0-0150-4d51-b485-d8f6e179219e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/497a6b88-a80a-4d88-8273-4e168bc1805b)
3.登入
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/a922f139-724c-4bc2-86d8-0ed0dad1fd4e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/b2d04338-3402-4dac-a655-12d5d8a1a25f)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/bc0288fe-a3fd-44ff-85a6-cdd3d5bf2b5e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/7b2f9ec3-3d7f-4380-b86e-dea7b3e3b23e)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/e8d1f221-750c-48eb-82b6-927ef8920243)
4.個人資訊查詢修改
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/2c4ac0a6-7b44-4f30-a773-84d30805964d)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/0b71eec5-c138-49f4-bb25-346603ca73ea)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/95dea571-2750-4d48-a307-8ccb8bb6939c)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/27e38c82-6e49-4247-a84e-7ad622101c6c)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/4bee54bb-87de-42c0-bb69-6407b6133acf)
5.商品展示加入購物車
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/203b38be-9d7b-407b-8a78-36ba9c75e7db)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/fcdf952c-fb44-48f4-9db5-b0c4f102d71f)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/04d55700-d75a-487e-aa65-47f3f392dcbc)
![image](https://github.com/k9798909/my_microservice_project/assets/62507948/88b71109-cb9e-4476-96b3-ffe11df1afd8)











