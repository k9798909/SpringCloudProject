package main

import (
	"cart-app/controllers"
	"cart-app/models"

	"github.com/gin-gonic/gin"
)

func main() {
	server := gin.Default()
	models.ConnectDatabase()
	models.InitData()
	server.GET("/cart/:userid", controllers.FindByUserId)
	server.POST("/cart", controllers.Update)
	server.Run(":8088")
}
