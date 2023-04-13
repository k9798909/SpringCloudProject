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
	models.InitData()
	server.GET(serverPrefix, func(ctx *gin.Context) { ctx.JSON(http.StatusOK, gin.H{"msg": "cart-service working"}) })
	server.GET(serverPrefix+"/cart/:userid", controllers.FindByUserId)
	server.POST(serverPrefix+"/cart", controllers.Update)
	server.Run(":8088")
}