package controllers

import (
	"cart-app/models"
	"encoding/json"
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
)

type CartDto struct {
	Username  string `json:"username"`
	ProductId string `json:"productId"`
	Quantity  int    `json:"quantity"`
}

func FindByUserName(c *gin.Context) {
	userName := c.Param("userName")
	result, err := models.DB.HGetAll(userName).Result()

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

	c.JSON(http.StatusOK, dtoArray)
}

func Insert(c *gin.Context) {
	dto := CartDto{}
	c.ShouldBindJSON(&dto)
	cartProduct := models.CartProduct(dto)
	error := models.SAddCartProduct(cartProduct)

	if error == nil {
		c.JSON(http.StatusOK, nil)
	} else {
		log.Panic("新增時發生錯誤", error)
		c.JSON(http.StatusInternalServerError, error)
	}
}
