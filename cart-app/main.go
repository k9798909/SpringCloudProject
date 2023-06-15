package main

import (
	"cart-app/controllers"
	"cart-app/eureka"
	"cart-app/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

var serverPrefix = "/cart-service"

func main() {
	eurekaRegister := eureka.BuildFargoInstance()
	eurekaRegister.Register()
	server := gin.Default()
	models.ConnectDatabase()
	server.GET(serverPrefix, func(ctx *gin.Context) { ctx.JSON(http.StatusOK, gin.H{"msg": "cart-service working"}) })
	server.GET(serverPrefix+"/cart/:userName", controllers.FindByUserName)
	server.POST(serverPrefix+"/cart", controllers.Insert)
	server.DELETE(serverPrefix+"/cart/:username/:productId", controllers.Delete)
	server.Run(":8088")
}
