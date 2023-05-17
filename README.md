# my_microservice_project
<p>微服務架構專案</p>
<p>目前規劃</p>
<p>1.使用docker</p>
<p>2.前端頁面:vue3+typescript+scss</p>
<p>3.購物車資料:redis+golang</p>
<p>4.商品資料:springboot+postgres</p>
<p>5.登入系統:springsecurity</p>
<p>6.管理微服務:springcloud</p>
<p>目前已實作</p>1
<p>1.使用docker，並撰寫docker-compose。</p>
<p>2.前端頁面使用vue3使用技術typescript、scss、BootstrapVue、pinia、axios。</p>
<p>3.購物車服務cart-app，使用golang撰寫，框架使用gin，資料庫選擇redis。</p>
<p>4.商品服務product-service，使用springboot、java17、jpa，資料庫使用postgres。</p>
<p>5.微服物管理使用spring cloud eureka管理微服物狀態，使用Spring Cloud Gateway統一管理各個微服務url。</p>
<p>6.在Spring Cloud Gateway加上spring security，來做token的驗證，並且實作登入系統，統一管理需驗證的url，spring security為WebFlux的寫法。</p>

