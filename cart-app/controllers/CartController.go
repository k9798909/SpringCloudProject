package controllers

import (
	"cart-app/models"
	"encoding/json"
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
)

type CartDto struct {
	UserId    string `json:"userid"`
	ProductId string `json:"productid"`
	Quantity  int    `json:"quantity"`
}

func FindByUserId(c *gin.Context) {
	userid := c.Param("userid")

	result, err := models.DB.HGetAll(userid).Result()

	if err != nil {
		log.Panic(err)
		c.JSON(http.StatusInternalServerError, gin.H{"data": nil})
	}

	dtoArray := []CartDto{}
	for _, data := range result {
		dto := CartDto{}
		json.Unmarshal([]byte(data), &dto)
		dtoArray = append(dtoArray, dto)
	}

	c.JSON(http.StatusOK, gin.H{"data": dtoArray})
}

func Update(c *gin.Context) {
	dto := CartDto{}
	c.ShouldBindJSON(&dto)
	cartProduct := models.CartProduct(dto)
	models.SAddCartProduct(cartProduct)
}
